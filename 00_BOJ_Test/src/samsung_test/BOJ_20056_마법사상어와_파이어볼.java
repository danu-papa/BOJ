/**
 * 
 */
package samsung_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class BOJ_20056_마법사상어와_파이어볼 {
	static class Fireball { // 파이어볼 정보
		int d, s, m;

		public Fireball() {
			super();
		}

		public Fireball(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}

		@Override
		public String toString() {
			return " d: " + this.d + " s: " + this.s + " m: " + this.m;
		}
	}

	private static int WEIGHT;
	private static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	private static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(stt.nextToken());
		int M = Integer.parseInt(stt.nextToken());
		int K = Integer.parseInt(stt.nextToken());

		List<Fireball>[][] fireballs = new ArrayList[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				fireballs[i][j] = new ArrayList<Fireball>();
			}
		}

		// Step 01. 모든 파이어볼의 정보를 2차원 리스트 배열로 만들어 저장.
		// fireballs[y][x] 는 y,x좌표를 뜻한다.
		for (int i = 0; i < M; i++) {
			stt = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(stt.nextToken());
			int x = Integer.parseInt(stt.nextToken());
			int m = Integer.parseInt(stt.nextToken());
			int s = Integer.parseInt(stt.nextToken());
			int d = Integer.parseInt(stt.nextToken());
			fireballs[y][x].add(new Fireball(m, s, d));
		}

		// 이동 및 분리
		fireballs = process(fireballs, N, K);
		// Step 04. 모든 이동을 마친 후 남은 파이어볼의 질량 합산
		sumWeight(fireballs, N);

		System.out.println(WEIGHT);
	}

	private static void sumWeight(List<Fireball>[][] fireballs, int n) {
		// 모든 질량 합하기.
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (fireballs[i][j].size() > 0) {
					for (Fireball cur_ball : fireballs[i][j]) {
						WEIGHT += cur_ball.m;
					}
				}
			}
		}
	}

	private static List<Fireball>[][] process(List<Fireball>[][] fireballs, int n, int k) {
		int move_cnt = 0;
		while (move_cnt < k) {
			move_cnt++;

			// Step 02. 파이어볼을 이동시키자. 이동 하고난 다음의 2차원 리스트 배열을 넘겨 받아 Step03으로 간다.
			fireballs = moveFireballs(fireballs, n);

			// Step 03. 겹친 파이어볼을 분리시키자.
			checkOverlapBalls(fireballs, n);
		}
		// 모든 과정이 끝난 다음의 2차원 리스트 배열을 반환
		return fireballs;
	} // process 종료

	private static List<Fireball>[][] moveFireballs(List<Fireball>[][] fireballs, int n) {
		List<Fireball>[][] sum_balls = new LinkedList[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				sum_balls[i][j] = new LinkedList<Fireball>();
			}
		}
		// 모든 파이어볼 이동
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (fireballs[i][j].size() > 0) {
					for (Fireball cur_ball : fireballs[i][j]) {
						int cur_d = cur_ball.d;
						int cur_s = cur_ball.s % (n);
						int cur_m = cur_ball.m;

						// 현재 위치에서 지정된 방향으로 S칸 만큼 이동
						int next_y = i + dy[cur_d] * cur_s;
						int next_x = j + dx[cur_d] * cur_s;

						// 범위를 벗어난 경우. 반대 방향으로 나온다.
						next_x = next_x > n ? next_x - n : next_x < 1 ? next_x + n : next_x;
						next_y = next_y > n ? next_y - n : next_y < 1 ? next_y + n : next_y;

						// 해당 좌표에 파이어볼을 위치시킨다.
						sum_balls[next_y][next_x].add(new Fireball(cur_m, cur_ball.s, cur_d));
					}
				}
			}
		} // 이동 끝
		return sum_balls;
	}

	private static void checkOverlapBalls(List<Fireball>[][] fireballs, int n) {
		// 겹친 파이어볼 있는지 체크
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				// 겹친 파이어볼이 있는 경우!
				if (fireballs[i][j].size() > 1) {
					int sum_m = 0;
					int sum_s = 0;
					boolean isEven = true, isOdd = true; // 짝수 홀수 판단.

					for (Fireball cur_ball : fireballs[i][j]) {
						sum_m += cur_ball.m;
						sum_s += cur_ball.s;
						if (cur_ball.d % 2 == 0)
							isOdd = false;
						else
							isEven = false;
					}

					sum_s /= fireballs[i][j].size();
					sum_m /= 5;
					// 일단 겹친 파이어볼 모두 제거한다.
					fireballs[i][j].clear();

					// 합한 질량이 0이면 다음에 겹치는거 탐색
					if (sum_m == 0)
						continue;

					if (isEven || isOdd) { // 모두 짝/홀 방향이었다.
						int[] dir = { 0, 2, 4, 6 };
						for (int d = 0; d < 4; d++) {
							fireballs[i][j].add(new Fireball(sum_m, sum_s, dir[d]));
						}
					} else { // 다른 방향이 하나라도 있었다.
						int[] dir = { 1, 3, 5, 7 };
						for (int d = 0; d < 4; d++) {
							fireballs[i][j].add(new Fireball(sum_m, sum_s, dir[d]));
						}
					}
				}
			}
		} // 파이어볼 체크 종료
	}
}
