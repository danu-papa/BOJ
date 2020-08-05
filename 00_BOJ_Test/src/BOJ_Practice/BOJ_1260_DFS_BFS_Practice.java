/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1260 - 그래프 탐색
 * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력
 * 그래프는 양방향
 * 방문 순서는 숫자가 작은 순
 */
public class BOJ_1260_DFS_BFS_Practice {
	private static int[][] vertex;
	private static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(stt.nextToken());
		
		vertex = new int[n+1][n+1];
		visited = new boolean[n+1];
		
		int edge_cnt = Integer.parseInt(stt.nextToken());
		
		int start_vertex = Integer.parseInt(stt.nextToken());
		
		for(int i = 0; i < edge_cnt; i++) {
			stt = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(stt.nextToken());
			int x = Integer.parseInt(stt.nextToken());
			
			vertex[y][x] = 1;
			vertex[x][y] = 1;
		}

		dfs(start_vertex);
		visited = new boolean[n+1]; // 방문 초기화
		System.out.println();
		bfs(start_vertex);
		
	}// main end

	/** Breadth First Search */
	private static void bfs(int start_vertex) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start_vertex);
		visited[start_vertex] = true;
		
		while(!queue.isEmpty()) {
			int next_vertex = queue.poll();
			System.out.print(next_vertex + " ");
			for( int i = 1; i < vertex.length; i++) {
				if(visited[i]) continue;
				if(vertex[next_vertex][i] == 1) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
	}

	/** Depth First Search */
	private static void dfs(int start_vertex) {
		System.out.print(start_vertex + " ");
		visited[start_vertex] = true;
		
		for( int i = 1; i < vertex.length; i++) {
			if(visited[i]) continue;
			if(vertex[start_vertex][i] == 1) {
				dfs(i);
			}
		}
	}
}
