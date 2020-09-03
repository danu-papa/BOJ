/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정보 올림피아드 1681 - 해밀턴 순환회로
 * 회사에서 출발하여 물건을 모두 배달하고
 * 다시 회사로 돌아오는 최단경로를 구하자! 
 * 
 * 1 <= N <= 12
 * 0<= 비용 <= 100
 * 비용은 단방향.
 * 회사는 1번! 
 */
public class JO_1681_Hamilton_Circuit {
	// N: 맵크기, map : 비용 저장, selected[] : 선택한 정점, resMin : 최소값
	private static int N, map[][], selected[], resMin;
	private static boolean visited[]; // 방문했는지 체크
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		selected = new int[N+1];
		visited = new boolean[N+1];

		StringTokenizer stt;
		for(int i = 1; i <= N; i++) {
			stt  = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(stt.nextToken());
			}
		} // input end
		
		resMin = Integer.MAX_VALUE;
		selected[1] = 1; // 회사에서 출발하므로 선택한 상태로 출발
		start_Delivery(2,1); // 순열로 뽑아보자!
		System.out.println(resMin);
	}

	/** 
	 * 배달 시작
	 * idx : 얼마나 선택했는지
	 * pre : 이전에 어떤것을 선택했는지
	 * pre를 이용해서 다음 선택한 정점이 이어져 있는지 확인.
	 *  */
	private static void start_Delivery(int idx, int pre) {
		if(idx == N+1) { // 모든 정점 선택
			int res = calc_distance(); // 거리계산
			if(res == -1) return; // 계산중 단절발견 다음선택으로
			resMin = Math.min(res, resMin);
			return;
		}

		for( int i = 2; i <= N; i++) { // 1번은 고정. 2번부터 시작.
			if(!visited[i]) { // 아직 선택 안함.
				if(map[pre][i] == 0) { // 이전 정점에서부터 현재 선택한 정점으로
					continue;
				}
				visited[i] = true;
				selected[idx] = i;
				start_Delivery(idx+1, i);
				visited[i] = false;
			}
		}
	}

	/** 순열로 선택된 모든 경우를 따져보자 */
	private static int calc_distance() {
		int distance = 0;
		
		for( int i = 1; i < N; i++) {
			int sel = map[selected[i]][selected[i+1]];
			if(sel == 0) return -1; // 단절 지점.
			distance += sel;
		}
		if(map[selected[N]][1] == 0) return -1; // 회사로 가는 길 없음.
		distance += map[selected[N]][1];
		return distance;
	}
}
