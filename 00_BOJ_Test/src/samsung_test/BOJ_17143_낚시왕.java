/**
 * 
 */
package samsung_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class BOJ_17143_낚시왕 {
	static class Shark {
		int y, x, speed, dir, size;

		public Shark(int y, int x, int speed, int dir, int size) {
			this.y = y;
			this.x = x;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}

		public Shark(Shark shark) {
			this.y = shark.y;
			this.x = shark.x;
			this.speed = shark.speed;
			this.dir = shark.dir;
			this.size = shark.size;
		}

		@Override
		public String toString() {
			return "size: " + this.size;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		int R, C, M;
		R = Integer.parseInt(stt.nextToken());
		C = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());

		List<Shark>[][] shark_map = new ArrayList[R + 1][C + 1];

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				shark_map[i][j] = new ArrayList<Shark>();
			}
		}

		for (int i = 0; i < M; i++) {
			stt = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(stt.nextToken());
			int x = Integer.parseInt(stt.nextToken());
			int s = Integer.parseInt(stt.nextToken());
			int d = Integer.parseInt(stt.nextToken());
			int z = Integer.parseInt(stt.nextToken());
			shark_map[y][x].add(new Shark(y, x, s, d, z));
		} // input end

		int res = process(shark_map, R, C, M);
		System.out.println(res);
	}

	// 전체적인 진행과정
	private static int process(List<Shark>[][] shark_map, int r, int c, int m) {
		int sum = 0;

		// 반복문을 수행하는 것이 낚시꾼의 위치가 옮겨지는 것
		for (int x = 1; x <= c; x++) {
			sum += catch_shark(x, shark_map, r); // 상어를 잡음. 격자판에서 out
			move_sharks(shark_map, r, c, m); // 상어 이동
			eat_shark(shark_map, r, c); // 겹친 상어 제거
		}
		return sum;
	}

	private static void move_sharks(List<Shark>[][] shark_map, int r, int c, int m) {
		int[] dy = { 0, -1, 1, 0, 0 }; // 위 아래 오른쪽 왼쪽
		int[] dx = { 0, 0, 0, 1, -1 };

		List<Shark> tmp_shark = new ArrayList<Shark>();

		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				int length = shark_map[i][j].size();
				if (length <= 0)
					continue;

				for(Shark shark: shark_map[i][j]) {
					int speed = shark.speed;
					int dir = shark.dir;
					
					// 위쪽 혹은 아래쪽
					if(dir == 1 || dir == 2) {
						speed = speed % ((r-1)*2);
					} else { // 왼쪽 혹은 오른쪽
						speed = speed % ((c-1)*2);
					}
					
					int size = shark.size;
					int next_y = i;
					int next_x = j;

					// 상어의 속도만큼 이동
					for (int s = 0; s < speed; s++) {
						next_y = next_y + dy[dir];
						next_x = next_x + dx[dir];
						// 다음 위치가 범위를 벗어남
						if (rangeCheck(next_y, next_x, r, c)) {
							// 상어 위치를 이동 전으로
							next_y = next_y - dy[dir];
							next_x = next_x - dx[dir];
							// 상어 방향 변경
							switch (dir) {
							case 1:
								dir = 2;
								break;
							case 2:
								dir = 1;
								break;
							case 3:
								dir = 4;
								break;
							case 4:
								dir = 3;
								break;
							}
							s--;
							continue;
						}
						// 다음위치로 이동 가능
					} // 이동 end
					tmp_shark.add(new Shark(next_y, next_x, speed, dir, size));
				} // 상어 end
				shark_map[i][j].clear();
			} // j end
		} // i end
		for (Shark shark : tmp_shark) {
			int y = shark.y;
			int x = shark.x;
			shark_map[y][x].add(shark);
		}
	}

	// 겹친 상어가 있다면 한마리를 제외하고 모두 잡아먹자
	private static void eat_shark(List<Shark>[][] shark_map, int r, int c) {
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if (shark_map[i][j].size() < 2)
					continue;
				int max_size = -1;
				Shark max_shark = new Shark(0, 0, 0, 0, 0);
				for (Shark shark : shark_map[i][j]) {
					if (max_size < shark.size) {
						max_size = shark.size;
						max_shark = new Shark(shark);
					}
				}
				shark_map[i][j].clear();
				shark_map[i][j].add(max_shark);
			} // j end
		} // i end
	}

	// 범위 체크
	private static boolean rangeCheck(int next_y, int next_x, int r, int c) {
		return next_y <= 0 || next_y >= r + 1 || next_x <= 0 || next_x >= c + 1;
	}

	// 낚시꾼이 상어를 잡았다!
	private static int catch_shark(int x, List<Shark>[][] shark_map, int r) {
		int sum = 0;
		for (int y = 1; y <= r; y++) {
			for (Shark shark : shark_map[y][x]) {
				sum += shark.size;
				shark_map[y][x].remove(shark);
				return sum;
			}
		}
		return 0;
	}
}
