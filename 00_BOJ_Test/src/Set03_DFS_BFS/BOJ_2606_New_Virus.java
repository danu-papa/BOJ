/**
 * 
 */
package Set03_DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2606 - 새로운 바이러스
 * 어떤 그래프가 주어진다.
 * 해당 정점이 바이러스가 걸린다면 
 * 연결된 모든 정점이 감염된다.
 * 어떤 한 정점이 감염되었을 때
 * 그로인해 감염되는 컴퓨터의 수를 구하라
 * BFS로 구현
 */
public class BOJ_2606_New_Virus {
	static int VERTEXS, EDGES, totalCnt;
	static int graph[][];
	static boolean visited[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		VERTEXS = Integer.parseInt(stt.nextToken());
		stt = new StringTokenizer(br.readLine());
		EDGES = Integer.parseInt(stt.nextToken());
		
		graph = new int[VERTEXS + 1][VERTEXS + 1];
		visited = new boolean[VERTEXS + 1];
		totalCnt = 0;
		
		for( int i = 0; i < EDGES; i++) {
			stt = new StringTokenizer(br.readLine());
			int vertex = Integer.parseInt(stt.nextToken());
			int conntected_vertex = Integer.parseInt(stt.nextToken());
			graph[vertex][conntected_vertex] = 1;
			graph[conntected_vertex][vertex] = 1;
		} // 연결된 정점들의 정보를 입력받음
		
		// 1번 정점부터 시작
		bfs(1);
		System.out.println(totalCnt);
	}
	/** Breadth First Search */
	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start); // 1번정점 큐에 저장
		visited[start] = true; // 1번 정점 방문
		
		while(!queue.isEmpty()) { // 큐에 원소가 하나라도 있으면
			int vertex = queue.poll(); // 현재 정점을 알려준다
		
			for( int i = 1; i <= VERTEXS; i++) { // 연결된 모든 정점들의 정보를 얻어야한다.
				if(visited[i]) continue; // 해당 정점을 방문했다면 Pass
				if(graph[vertex][i] == 1) { // 연결이 되어있다면
					queue.offer(i); // 큐에 저장
					visited[i] = true; // 방문표시
					totalCnt++; // 정점 카운트 + 1 
				}
			}
		}
	}
}	
