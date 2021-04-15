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
public class 청소년상어 {
	static class Shark { // 상어 정보
		int y, x, dir;
		int eat = 0;

		public Shark() {
			super();
		}

		public Shark(int y, int x, int eat, int dir) {
			this.y = y;
			this.x = x;
			this.eat = eat;
			this.dir = dir;
		}
	}

	private static int MAX;
	private static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	private static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][][] map = new int[4][4][2]; // 물고기의 크기와 방향 저장
		int[][] fish = new int[17][2]; // 사이즈 별로 물고기의 위치 정보를 담아야한다.
		Shark shark = new Shark();

		for (int i = 0; i < 4; i++) {
			StringTokenizer stt = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int size = Integer.parseInt(stt.nextToken());
				int move = Integer.parseInt(stt.nextToken());
				fish[size][0] = i;
				fish[size][1] = j;
				map[i][j][0] = size;
				map[i][j][1] = move;
			}
		} // input end

		MAX = Integer.MIN_VALUE;
		// 상어를 0,0에 위치시키고 맵 변경
		shark = new Shark(0, 0, map[0][0][0], map[0][0][1]);
		fish[map[0][0][0]][0] = -1;
		fish[map[0][0][0]][1] = -1;
		map[0][0][0] = -1;
		map[0][0][1] = -1;
		// 상어정보, 물고기정보, 지도정보
		process(shark, fish, map);
		System.out.println(MAX);
	}

	private static void process(Shark shark, int[][] fish, int[][][] map) {
		// step 01. 전체 물고기 위치 변경
		for (int i = 1; i < 17; i++) {
			int cur_y = fish[i][0];
			int cur_x = fish[i][1];
			if (cur_y == -1) // 물고기 없는 경우 pass
				continue;
			if (cur_y == shark.y && cur_x == shark.x) // 상어 위치인 경우 pass
				continue;
			int dir = map[cur_y][cur_x][1];
			int cnt = 0;

			while (cnt < 8) { // 한바퀴 반복하면 제자리
				int next_y = cur_y + dy[dir];
				int next_x = cur_x + dx[dir];

				// 범위 체크, 상어체크
				if (rangeCheck(next_y, next_x) || (next_y == shark.y && next_x == shark.x)) {
					dir += 1;
					if (dir > 8) // 8 이상이면 다시 1부터 시작
						dir = 1;
					cnt++;
					continue;
				}

				// 이동 가능. 해당 위치에 물고기가 있다면 위치 교체, 빈곳이면 그냥 이동
				if (map[next_y][next_x][0] == -1) {
					map[next_y][next_x][0] = map[cur_y][cur_x][0];
					map[next_y][next_x][1] = dir;

					map[cur_y][cur_x][0] = -1;
					map[cur_y][cur_x][1] = -1;
					
					// 크기 i인 물고기 위치 변경
					fish[i][0] = next_y;
					fish[i][1] = next_x;
				} else { // 물고기가 있는 경우
					// 이동할 위치의 물고기 정보
					int next_fish_size = map[next_y][next_x][0];
					int next_fish_pos = map[next_y][next_x][1];

					// 교환
					map[next_y][next_x][0] = map[cur_y][cur_x][0];
					map[next_y][next_x][1] = dir;

					map[cur_y][cur_x][0] = next_fish_size;
					map[cur_y][cur_x][1] = next_fish_pos;

					fish[next_fish_size][0] = cur_y;
					fish[next_fish_size][1] = cur_x;
					fish[i][0] = next_y;
					fish[i][1] = next_x;
				}
				break;
			}
		} // 물고기 위치 변경 end

		// step 00. 지도 복사, 물고기 정보 복사
		int[][][] tmp_map = new int[4][4][2];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 2; k++) {
					tmp_map[i][j][k] = map[i][j][k];
				}
			}
		}
		int[][] tmp_fish = new int[17][2];
		for(int i = 1; i < 17; i++) {
			for(int j = 0; j < 2; j++) {
				tmp_fish[i][j] = fish[i][j];
			}
		}

		// step 02. 현재 지도로 process dfs
		int cur_shark_y = shark.y;
		int cur_shark_x = shark.x;
		int cur_shark_dir = shark.dir;
		int cur_shar_eat = shark.eat;
		int next_y = cur_shark_y;
		int next_x = cur_shark_x;
		while (true) {
			next_y += dy[cur_shark_dir];
			next_x += dx[cur_shark_dir];

			// 더이상 물고기를 먹을 수 없다
			if (rangeCheck(next_y, next_x)) {
				MAX = MAX > cur_shar_eat ? MAX : cur_shar_eat;
				break;
			}
			// 먹을 물고기가 있다
			if (map[next_y][next_x][0] != -1) {
				int next_fish_size = map[next_y][next_x][0];
				int next_fish_dir = map[next_y][next_x][1];
				shark.y = next_y;
				shark.x = next_x;
				shark.eat += next_fish_size;
				shark.dir = next_fish_dir;
				map[next_y][next_x][0] = -1;
				map[next_y][next_x][1] = -1;
				fish[next_fish_size][0] = -1;
				fish[next_fish_size][1] = -1;

				process(shark, fish, map);

				shark.y = cur_shark_y;
				shark.x = cur_shark_x;
				shark.eat -= next_fish_size;
				shark.dir = cur_shark_dir;
				fish[next_fish_size][0] = next_y;
				fish[next_fish_size][1] = next_x;
				init_map(tmp_map, map);
				init_fish(tmp_fish, fish);
			} else
				continue;
			// step 04. dfs가 끝났으면 다시 지도 되돌리기
		}
	}

	private static void init_fish(int[][] tmp_fish, int[][] fish) {
		for(int i = 1; i < 17; i++) {
			for(int j = 0; j < 2; j++) {
				fish[i][j] = tmp_fish[i][j];
			}
		}
	}

	private static void init_map(int[][][] tmp_map, int[][][] map) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 2; k++) {
					map[i][j][k] = tmp_map[i][j][k];
				}
			}
		}
	}

	private static boolean rangeCheck(int next_y, int next_x) {
		return next_y >= 4 || next_y < 0 || next_x >= 4 || next_x < 0;
	}
}
