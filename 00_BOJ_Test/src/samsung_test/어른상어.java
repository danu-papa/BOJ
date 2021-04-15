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
public class 어른상어 {
	static class Shark { // 상어 정보
		int y, x, dir;
		int[][] pri_move = new int[5][5];

		public Shark(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	private static int TIME;
	private static int[] dy = { 0, -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(stt.nextToken()); // 지도 크기
		int M = Integer.parseInt(stt.nextToken()); // 상어 수
		int K = Integer.parseInt(stt.nextToken()); // 냄새 유지 이동

		int[][][] map = new int[N][N][2];
		Shark[] shark_arr = new Shark[M + 1];

		for (int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int input = Integer.parseInt(stt.nextToken());
				if (input != 0) {
					shark_arr[input] = new Shark(i, j);
					map[i][j][0] = input;
					map[i][j][1] = K;
				}
			}
		} // 지도 정보 저장

		stt = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			shark_arr[i].dir = Integer.parseInt(stt.nextToken());
		} // 상어 초기 방향 지정

		for (int m = 1; m <= M; m++) {
			for (int i = 1; i <= 4; i++) {
				stt = new StringTokenizer(br.readLine());
				for (int j = 1; j <= 4; j++) {
					shark_arr[m].pri_move[i][j] = Integer.parseInt(stt.nextToken());
				}
			}
		} // 전체 상어들의 이동 우선순위 지정

		process(shark_arr, map, N, M, K);
		System.out.println(TIME);
	}

	// 상어들을 이동시키자!
	private static void process(Shark[] shark_arr, int[][][] map, int n, int m, int k) {
		int time = 0;
		boolean[] out = new boolean[m + 1]; // 밖으로 나간 상어 체크
		int out_count = 0;

		while (time <= 1000) { // 1000초가 지나면 탈출불가 -1 리턴
			time++;

			// step 01. 지정된 방향에 맞게 상어를 이동시킨다.
			// 모든 상어 이동
			for (int i = 1; i <= m; i++) {
				Shark cur_shark = shark_arr[i];
				int cur_x = cur_shark.x;
				int cur_y = cur_shark.y;
				int cur_dir = cur_shark.dir;
				int[] move_pri = cur_shark.pri_move[cur_dir];
				boolean flag = false;
				Shark tmp_shark = new Shark(0, 0);

				for (int d = 1; d <= 4; d++) {
					int next_dir = move_pri[d];

					int next_y = cur_y + dy[next_dir];
					int next_x = cur_x + dx[next_dir];

					// 범위 체크
					if (rangeCheck(next_y, next_x, n))
						continue;
					// 냄새가 나..
					if (map[next_y][next_x][1] != 0) {
						// 근데 내 냄새는 아니야..! 못가!
						if (map[next_y][next_x][0] != i) {
							continue;
						} else if (!flag) { // 내 냄새가 나서 갈 수 는 있어.. 우선순위가 제일 처음이야
							flag = true;
							tmp_shark.y = next_y;
							tmp_shark.x = next_x;
							tmp_shark.dir = next_dir;
							continue; // 빈 공간 계속 탐색
						}
						continue;
					}
					// 갈 수 있다!
					flag = false;
					cur_shark.x = next_x;
					cur_shark.y = next_y;
					cur_shark.dir = next_dir;
					break;
				} // 4방향 탐색 end
				if (flag) {
					cur_shark.x = tmp_shark.x;
					cur_shark.y = tmp_shark.y;
					cur_shark.dir = tmp_shark.dir;
				}
			}

			// 이동하기 때문에 지도에 모든 냄새는 -1이 된다.
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j][1] > 0) {
						map[i][j][1]--;
					}
					if (map[i][j][1] == 0) {
						map[i][j][0] = 0;
					}
				}
			}
			
			// step 02.
			// 겹치는 상어 체크
			for (int i = m; i > 1; i--) {
				if (out[i])
					continue;
				Shark mini_shark = shark_arr[i];
				for (int j = 1; j < i; j++) {
					if (out[j])
						continue;
					Shark big_shark = shark_arr[j];
					if (mini_shark.y == big_shark.y && mini_shark.x == big_shark.x) {
						out_count++;
						out[i] = true;
						break;
					}
				}
			}

			// step 03. 격자 안 모든 상어가 자신의 자리에 향기를 남긴다
			for (int i = 1; i <= m; i++) {
				if (out[i])
					continue;
				Shark cur_shark = shark_arr[i];
				map[cur_shark.y][cur_shark.x][0] = i;
				map[cur_shark.y][cur_shark.x][1] = k;
			}
			
			// 1마리 남을때까지 반복
			if (out_count == m-1) {
				TIME = time;
				return;
			}
		} // while end
		TIME = -1;
	}

	private static boolean rangeCheck(int next_y, int next_x, int n) {
		return next_y < 0 || next_y >= n || next_x < 0 || next_x >= n;
	}
}
