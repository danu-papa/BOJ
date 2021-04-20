/**
 * 
 */
package samsung_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class BOJ_12100_2048 {
	private static int MAX;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer stt = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stt.nextToken());
			}
		} // input end

		int[] selected = new int[5];
		process(0, map, N, selected);
		System.out.println(MAX);
	}

	private static void process(int idx, int[][] map, int n, int[] selected) {
		if (idx == 5) { // 5번 이동
			// 선택 한 방향을 기준으로 숫자를 이동시키자
			int res = move_map(selected, map, n);
			MAX = MAX > res ? MAX : res; // 최대치 갱신
			return;
		}
		for (int d = 0; d < 4; d++) {
			selected[idx] = d; // 방향 선택
			process(idx + 1, map, n, selected);
		}
	}

	private static int move_map(int[] selected, int[][] map, int n) {
		// 복사된 맵으로 이동 시킬 것이다!
		int[][] init_map = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				init_map[i][j] = map[i][j];
			}
		}

		// 각 방향에 따라 이동
		for (int dir : selected) {
			switch (dir) {
			case 0: // 위쪽
				move_up(init_map, n);
				break;
			case 1: // 아래쪽
				move_down(init_map, n);
				break;
			case 2: // 왼쪽
				move_left(init_map, n);
				break;
			case 3: // 오른쪽
				move_right(init_map, n);
				break;
			}
		}
		
		// 최대 치 산출
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int cur = init_map[i][j];
				max = max > cur ? max : cur;
			}
		}
		return max;
	}
	
	// 오른쪽 이동
	private static void move_right(int[][] init_map, int n) {
		for(int i = 0; i < n; i++) {
			int pre = init_map[i][n - 1];
			int sum_idx = pre == 0 ? n : n - 1;
			for (int j = n-2; j >= 0; j--) {
				int cur = init_map[i][j];
				if (cur == 0)
					continue;
				if (pre != cur) {
					sum_idx--;
					pre = cur;
					init_map[i][j] = 0;
					init_map[i][sum_idx] = cur;
				} else {
					init_map[i][sum_idx] = pre + cur;
					init_map[i][j] = 0;
					pre = 0;
				}
			}
		}
	}
	
	// 왼쪽 이동
	private static void move_left(int[][] init_map, int n) {
		for(int i = 0; i < n; i++) {
			int pre = init_map[i][0];
			int sum_idx = pre == 0 ? -1 : 0;
			for (int j = 1; j < n; j++) {
				int cur = init_map[i][j];
				if (cur == 0)
					continue;
				if (pre != cur) {
					sum_idx++;
					pre = cur;
					init_map[i][j] = 0;
					init_map[i][sum_idx] = cur;
				} else {
					init_map[i][sum_idx] = pre + cur;
					init_map[i][j] = 0;
					pre = 0;
				}
			}
		}
	}
	
	// 밑으로 이동
	private static void move_down(int[][] init_map, int n) {
		for(int j = 0; j < n; j++) {
			int pre = init_map[n-1][j];
			int sum_idx = pre == 0 ? n : n-1;
			for (int i = n-2; i >= 0; i--) {
				int cur = init_map[i][j];
				if (cur == 0)
					continue;
				if (pre != cur) {
					sum_idx--;
					pre = cur;
					init_map[i][j] = 0;
					init_map[sum_idx][j] = cur;
				} else {
					init_map[sum_idx][j] = pre + cur;
					init_map[i][j] = 0;
					pre = 0;
				}
			}
		}
	}

	// 위로 이동
	private static void move_up(int[][] init_map, int n) {
		for(int j = 0; j < n; j++) {
			int pre = init_map[0][j];
			int sum_idx = pre == 0 ? -1 : 0;
			for (int i = 1; i < n; i++) {
				int cur = init_map[i][j];
				if (cur == 0)
					continue;
				if (pre != cur) {
					sum_idx++;
					pre = cur;
					init_map[i][j] = 0;
					init_map[sum_idx][j] = cur;
				} else {
					init_map[sum_idx][j] = pre + cur;
					init_map[i][j] = 0;
					pre = 0;
				}
			}
		}
	}
}
