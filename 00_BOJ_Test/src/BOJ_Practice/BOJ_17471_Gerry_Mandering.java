package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 17471 - 게리맨더링 한 도시가 N개의 구역으로 나뉘어있음. 구역은 1~N번 구역을 두개의 선거구로 나누어야한다. 
 * 이때 선거구에 포함된 구역은 모두 연결이 되어있어야한다. 두 선거구에 포함된 인구 차이가 최소인 경우를 찾아 출력.
 */
public class BOJ_17471_Gerry_Mandering {
	// N : 구역수, area_population : 각 구역 인구수, resMin : 최소값.
	private static int N, area_population[], resMin;
	private static boolean selected_area[]; // 선택된 구역
	private static List<Integer>[] alist; // 모든 구역 정보를 담을 리스트

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		area_population = new int[N + 1];
		alist = new ArrayList[N + 1];
		selected_area = new boolean[N + 1];

		StringTokenizer stt = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			alist[i] = new ArrayList<>();
			area_population[i] = Integer.parseInt(stt.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			stt = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(stt.nextToken());
			for (int s = 0; s < size; s++) {
				alist[i].add(Integer.parseInt(stt.nextToken()));
			}
		} // 각 구역과 연결된 구역 입력받음.

		resMin = Integer.MAX_VALUE;
		make_subset(1); // 부분집합으로 구현
		System.out.println(resMin == Integer.MAX_VALUE ? -1 : resMin);
	}

	/** 모든 부분집합 만들기. */
	private static void make_subset(int idx) {
		if (idx == N + 1) { // N개의 부분 집합이 만들어 졌다면.
			int first_Area_Population = 0;
			int second_Area_Population = 0;
			int cnt = 0;
			for(int i = 1; i <= N; i++) {
				if(selected_area[i]) cnt++;
			}

			// 모두 선택하거나, 하나도 선택하지 않은 경우는 보지않는다.
			if( cnt == N || cnt == 0) return ;

			if (checkConnect()) { // 선택된 구역이 모두 연결이 되어있다는 것을 어떻게 판단할까??
				for (int i = 1; i <= N; i++) {
					if (selected_area[i]) { // 선택한 구역과 안한 구역으로 나누어 생각.
						first_Area_Population += area_population[i];
					} else { // 선택 안한 구역
						second_Area_Population += area_population[i];
					}
				}
			} else { // 체크했는데 연결이 안되면 다음 부분집합으로.
				return;
			}
			
			// 다 제대로 연결이 되어있다면 최소값 도출
			int res = Math.abs(first_Area_Population - second_Area_Population);
			resMin = Math.min(res, resMin);

			return;
		}

		// 부분집합 DFS
		selected_area[idx] = true;
		make_subset(idx + 1);
		selected_area[idx] = false;
		make_subset(idx + 1);
	}

	/** 
	 * 모두 연결이 되어있는 지 판단 
	 * BFS를 통해 각 구역에서 
	 * 연결된 (혹은 연결안된) 구역에 갈 수 있는지 없는지 판단
	 * */
	private static boolean checkConnect() {
		boolean[] arr = new boolean[N+1]; // 연결되었는지 파악할 배열
		
		// BFS 구현 큐
		Queue<Integer> queue = new LinkedList<>();

		for( int i = 1; i <= N; i++) {
			if(selected_area[i]) { // 선택한 구역을 기준으로
				queue.offer(i); // BFS
				arr[i] = true; // 선택 지점 방문표시
				break;
			}
		}
		
		int idx = 0;
		
		while(!queue.isEmpty()) {
			idx = queue.poll();

			// idx구역이 선택되었고, 방문을 안했으면.
			if(selected_area[idx]) {
				int size = alist[idx].size(); // 연결된 모든 구역 확인
				for(int s = 0; s < size; s++) {
					int next = alist[idx].get(s); 
					if(arr[next]) continue; // 방문했으면 다음 구역으로
					if(selected_area[next]) { // 연결된 구역이 선택됐다면
						queue.offer(next); // 다음 BFS
						arr[next] = true; // 방문 표시
					}
				}
			}
		}
		
		queue.clear(); // 혹시.. 남아있는 큐가 없도록 비워준다.
		
		for( int i = 1; i <= N; i++) {
			if(!selected_area[i]) { // 선택 안한 구역을 기준으로
				queue.offer(i); // BFS
				arr[i] = true; // 방문 표시
				break;
			}
		}
		
		while(!queue.isEmpty()) {
			idx = queue.poll();

			// idx구역이 선택되었고, 방문을 안했으면.
			if(!selected_area[idx]) {
				int size = alist[idx].size(); // 연결된 모든 구역 확인
				for(int s = 0; s < size; s++) {
					int next = alist[idx].get(s);
					if(arr[next]) continue; // 방문했으면 다음 구역으로
					if(!selected_area[next]) { // 연결된 구역을 선택하지 않았다면
						queue.offer(next); // 다음 BFS
						arr[next] = true; // 방문 표시
					}
				}
			}
		}
		
		// 모든 구역을 방문했다면 모두 연결이 되어있다고 말 할수 있다.
		// 하나라도 방문하지 못했다면 연결이 안됐음
		for(int i = 1; i <= N; i++) {
			if(!arr[i]) {
				return false;
			}
		}
		return true;
	}
}