/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1647 - 도시 분할 계획 한 마을을 2개의 구역으로 나눈다. N개의 집과 M개의 길이 있다. 길은 양방향. 각 분리된 마을은 마을
 * 안에서 집들이 서로 연결이 되어있어야 한다. 필요없는 길을 모두 없애고 남은 최소의 길의 유지비를 구하자. 정점10만개. 길 100만개..
 */
public class BOJ_1647_Divde_City_Plan_List_Solution {
	static class Node implements Comparable<Node> {
		int no, weight;

		public Node(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	// N: 집의 개수 , M: 길의 개수
	private static int N, M, resMin;
	private static List<Node>[] maplist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());
		maplist = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			maplist[i] = new ArrayList<Node>();
		}
		
		
		for (int i = 0; i < M; i++) {
			stt = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(stt.nextToken());
			int sec = Integer.parseInt(stt.nextToken());
			int weight = Integer.parseInt(stt.nextToken());

			maplist[first].add(new Node(sec, weight));
			maplist[sec].add(new Node(first, weight));
		} // input end

		resMin = 0;
		prim();
		System.out.println(resMin);
	}

	/** 프림알고리즘을 이용해서 연결된 마을간 MST를 찾자 */
	private static void prim() {
		PriorityQueue<Node> pQueue = new PriorityQueue<Node>();
		int[] minEdge = new int[N + 1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		boolean visited[] = new boolean[N + 1];

		int start_vertex = 1;
		int nodeCnt = 0;
		int max = 0;

		minEdge[start_vertex] = 0;
		pQueue.offer(new Node(start_vertex, 0));

		while (!pQueue.isEmpty()) {
			Node minVertex = pQueue.poll();
			int cur_Vertex = minVertex.no;

			if (visited[cur_Vertex])
				continue;

			visited[cur_Vertex] = true;

			if (max < minVertex.weight)
				max = minVertex.weight;

			resMin += minVertex.weight;

			for (int j = 0; j < maplist[cur_Vertex].size(); j++) {
				Node node = maplist[cur_Vertex].get(j);
				int next = node.no;
				if (!visited[next] && node.weight < minEdge[next]) {
					minEdge[next] = node.weight;
					pQueue.offer(new Node(next, node.weight));
				}
			}

			if (++nodeCnt == N)
				break;
		}
		resMin -= max;
	}
}
