/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1647 - 도시 분할 계획 한 마을을 2개의 구역으로 나눈다. N개의 집과 M개의 길이 있다. 길은 양방향. 각 분리된 마을은 마을
 * 안에서 집들이 서로 연결이 되어있어야 한다. 필요없는 길을 모두 없애고 남은 최소의 길의 유지비를 구하자.
 * 정점10만개. 길 100만개..
 */
public class BOJ_1647_Divde_City_Plan_List {
	static class Node implements Comparable<Node>{
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
	private static int N, M, resMin, tmpres;
	private static boolean isSelected[];
	private static List<Node>[] maplist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());
		maplist = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			maplist[i] = new ArrayList<Node>();
		}
		
		isSelected = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			stt = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(stt.nextToken());
			int sec = Integer.parseInt(stt.nextToken());
			int weight = Integer.parseInt(stt.nextToken());
			
			maplist[first].add(new Node(sec, weight));
		} // input end

		resMin = Integer.MAX_VALUE;
		divide_region(1);
		System.out.println(resMin);
	}

	/**
	 * 선택할 수 있는 모든 구역의 경우를 구하자. 부분집합으로 구현
	 */
	private static void divide_region(int idx) {
		if (idx == N + 1) { // 모든 경우 확인.
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if (isSelected[i])
					cnt++;
			}
			// 모두 선택했거나, 하나도 선택 안했거나.
			if (cnt == N || cnt == 0)
				return;

			//if (checkConnect()) {
				// 남은 길을 가지고 프림.
				tmpres = 0;
				prim();
				
				resMin = Math.min(resMin, tmpres);
			//}

			return;
		}
		isSelected[idx] = true;
		divide_region(idx + 1);
		isSelected[idx] = false;
		divide_region(idx + 1);
	}

	/** 프림알고리즘을 이용해서 연결된 마을간 MST를 찾자 */
	private static void prim() {
		PriorityQueue<Node> pQueue = new PriorityQueue<Node>();
		int[] minEdge = new int[N+1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		boolean visited[] = new boolean[N+1];
		
		int start_vertex = 1;
		int result = 0;
		
		minEdge[start_vertex] = 0;
		pQueue.offer(new Node(start_vertex,0));
		
		while(!pQueue.isEmpty()) {
			Node minVertex = pQueue.poll();
			int cur_Vertex = minVertex.no;
			
			if(visited[cur_Vertex]) continue;
			
			result += minVertex.weight;
			visited[cur_Vertex] = true;
			
			for(int j = 0; j < maplist[cur_Vertex].size(); j++) {
				Node minNode = maplist[cur_Vertex].get(j);
				int next = minNode.no;
				if(!visited[next] && minNode.weight != 0 && isSelected[cur_Vertex] == isSelected[next]
						&& maplist[cur_Vertex].get(j).weight < minEdge[next]) {
					minEdge[next] = minNode.weight;
					pQueue.offer(new Node(next,minNode.weight));
				}
			}
		}
		
		tmpres += result;
	}

	/**
	 * 선택된 구역이 모두 연결되었는지 확인 BFS이용
	 */
	private static boolean checkConnect() {
		// 모든 곳이 연결이 되어있나 확인할 배열
		boolean arr[] = new boolean[N + 1];

		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			if (isSelected[i]) { // 선택한 지점부터 탐색 시작.
				queue.offer(i);
				arr[i] = true;
				break;
			}
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			if (isSelected[cur]) { // 현재 정점 선택함.
				for (int i = 0; i < maplist[cur].size(); i++) {
					int next = maplist[cur].get(i).no;
					if (arr[next]) continue;
					if (isSelected[next]) {
						queue.offer(next);
						arr[next] = true;
					}
				}
			}
		} // 선택 while end
		

		for (int i = 1; i <= N; i++) {
			if (!isSelected[i]) { // 선택안한 지점부터 탐색 시작.
				queue.offer(i);
				arr[i] = true;
				break;
			}
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			if (!isSelected[cur]) { // 현재 정점 선택안함.
				for (int i = 0; i < maplist[cur].size(); i++) {
					int next = maplist[cur].get(i).no;
					if (arr[next]) continue;
					if (!isSelected[next]) {
						queue.offer(next);
						arr[next] = true;
					}
				}
			}
		} // 선택 안한 while end
		
		for(int i = 1; i <= N; i++) {
			if(!arr[i]) return false;
		}
		
		return true;
	}
}
