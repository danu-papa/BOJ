/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 1753 - 최단경로
 * 방향그래프가 주어지고 주어진 시작점에서 다른 모든 정점으로의
 * 최단경로를 구하는 문제.
 * 모든 가중치는 10이하.
 * V <= 20000, E <= 300000
 * 다익스트라 이용.
 */

class Vertex_Link{
	int to, weight;

	public Vertex_Link(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
}

public class BOJ_1753_Min_Path {
	private static int V, E, distance[];
	private final static int INFINITY = Integer.MAX_VALUE;
	private static List<Vertex_Link>[] vlist;
	private static boolean visited[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());

		V = Integer.parseInt(stt.nextToken());
		E = Integer.parseInt(stt.nextToken());
		distance = new int[V+1];
		vlist = new ArrayList[V+1];
		visited = new boolean[V+1];
		Arrays.fill(distance, INFINITY);

		int start_Vertex = Integer.parseInt(br.readLine());

		for( int i = 1; i <= V; i++) {
			vlist[i] = new ArrayList<>();
		}

		for( int i = 1; i <= E; i++) {
			stt = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(stt.nextToken());
			int to = Integer.parseInt(stt.nextToken());
			int w = Integer.parseInt(stt.nextToken());
			vlist[v].add(new Vertex_Link(to, w));
		}

		dijkstra(start_Vertex);

		for(int i = 1; i <= V; i++) {
			if(distance[i] == INFINITY) {
				System.out.println("INF");
				continue;
			}
			System.out.println(distance[i]);
		}
	}

	/** @param start_Vertex*/
	private static void dijkstra(int start_Vertex) {
		int min = 0, current = 0;
		distance[start_Vertex] = 0;

		for( int i = 1; i <= V; i++) {
			min = INFINITY;
			for( int j = 1; j <= V; j++) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					current = j;
				}
			}

			visited[current] = true;
			if(vlist[current].isEmpty()) continue;
			
			for(Vertex_Link v : vlist[current]) {
				if(!visited[v.to] && distance[v.to] > min+v.weight) {
					distance[v.to] = min + v.weight;
				}
			}
		}
	}
}
