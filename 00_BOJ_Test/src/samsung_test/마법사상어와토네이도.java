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
public class 마법사상어와토네이도 {
	private static int MAX;
	private static int[] dy = { 0, 1, 0, -1 };
	private static int[] dx = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] init = { // 초기 토네이도 모습
				{ 0, 0, 2, 0, 0 }, 
				{ 0, 10, 7, 1, 0 }, 
				{ 5, 0, 0, 0, 0 }, 
				{ 0, 10, 7, 1, 0 },
				{ 0, 0, 2, 0, 0 } };

		int[][][] tornado = new int[4][5][5];

		for (int i = 0; i < 5; i++) { // 왼쪽 방향 토네이도 저장.
			for (int j = 0; j < 5; j++) {
				tornado[0][i][j] = init[i][j];
			}
		}
		
		// Step 00.
		// 토네이도에 값 지정해주기.
		// 90도 180도 270도 방향으로 배열 돌리기.
		// 까먹지말고 잘 기억해두자!
		for (int t = 1; t < 4; t++) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					switch (t) {
					case 1: // 90도 회전
						tornado[t][i][j] = init[j][5 - 1 - i];
						break;
					case 2: // 180도 회전
						tornado[t][i][j] = init[i][5 - 1 - j];
						break;
					case 3:
						tornado[t][i][j] = init[5 - 1 - j][i];
						break;
					}
				}
			}
		} // 토네이도 저장 end

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer stt = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stt.nextToken());
			}
		} // input end

		process(tornado, map, N);

		System.out.println(MAX);
	}
	
	// 전체적인 프로세스
	private static void process(int[][][] tornado, int[][] map, int n) {
		int[] tornado_pos = new int[2]; // 현재 토네이도의 위치
		tornado_pos[0] = n / 2;
		tornado_pos[1] = n / 2;

		int idx = 0; // 몇번 진행할지 판단.
		int dir = 0; // 최초 왼쪽 이동
		boolean flag = true;
		while (flag) {
			// 왼쪽과 오른쪽일때 한칸 더 가야한다
			if (dir == 0 || dir == 2) {
				idx++;
			}

			// Step 01. 현재 토네이도의 진행방향으로 중심을 옮겨준다
			// 진행방형 왼 아 오 위, 0 1 2 3
			for (int i = 0; i < idx; i++) {
				switch (dir) {
				case 0: // 왼쪽
					tornado_pos[1]--;
					break;
				case 1: // 아래
					tornado_pos[0]++;
					break;
				case 2: // 오른쪽
					tornado_pos[1]++;
					break;
				case 3: // 위쪽
					tornado_pos[0]--;
					break;
				}
				if (tornado_pos[0] < 0 || tornado_pos[1] < 0) {
					flag = false;
					break;
				}
				
				// Step 02. 방향과 위치를 기준으로 모래를 흩뿌리자.
				// 토네이도의 현재 방향, 중심위치, 토네이도정보, 지도, 사이즈
				spread_sand(dir, tornado_pos, tornado, map, n);
			}

			// 방향 설정
			dir = (dir + 1) % 4;
		}
	}

	// 모래를 흩날리자
	private static void spread_sand(int dir, int[] tornado_pos, int[][][] tornado, int[][] map, int n) {
		// 범위를 찾아내자
		int range = 2; // 토네이도 범위 5x5. 절반인 2
		int remain = map[tornado_pos[0]][tornado_pos[1]]; // 남은 모래

		// 지정된 방향으로 모래를 흩날려 보냄
		// 중심지부터 토네이도의 영향권 안의 모래만을 대상으로 한다.
		for (int i = tornado_pos[0] - range, t1 = 0; i <= tornado_pos[0] + range; i++, t1++) {
			for (int j = tornado_pos[1] - range, t2 = 0; j <= tornado_pos[1] + range; j++, t2++) {
				if (tornado[dir][t1][t2] == 0) { // 중심지에 모래가 없으면 뿌릴 수 없다.
					continue;
				}
			
				// 해당 방향에 저장된 토네이도의 비율에 따라 계산.
				int sand = map[tornado_pos[0]][tornado_pos[1]] * tornado[dir][t1][t2] / 100;
				remain -= sand; // 뿌려준 만큼 남은 모래를 빼준다.
				if (rangeCheck(i, j, n)) { // 벗어남
					MAX += sand; // 밖의 모래 +
					continue;
				}
				map[i][j] += sand; // 현재 지도에 모래 누적 저장
			}
		}
		// 토내이도 중심지 모래 제거
		map[tornado_pos[0]][tornado_pos[1]] = 0;
		
		// 범위 체크. 범위 안이라면 지도에 업데이트
		if (!rangeCheck(tornado_pos[0] + dy[dir], tornado_pos[1] + dx[dir], n)) {
			map[tornado_pos[0] + dy[dir]][tornado_pos[1] + dx[dir]] += remain;
		} else { // 나갔으면 밖의 모래 누적
			MAX += remain;
		}
	}

	// 범위 체크
	private static boolean rangeCheck(int y, int x, int n) {
		return y < 0 || y >= n || x < 0 || x >= n;
	}
}
