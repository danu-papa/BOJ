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
public class BOJ_15684_사다리조작 {
	private static int MIN;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stt.nextToken()); // 가로 길이
		int M = Integer.parseInt(stt.nextToken());
		if (M == 0) {
			System.out.println(0);
			return;
		}
		int H = Integer.parseInt(stt.nextToken()); // 세로 높이

		int[][] dir_map = new int[H + 1][N + 1];
		for (int j = 1; j <= M; j++) {
			stt = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(stt.nextToken());
			int x = Integer.parseInt(stt.nextToken());
			dir_map[y][x] = 1; // 오른쪽
			dir_map[y][x + 1] = 2; // 왼쪽
		} // input end
		MIN = Integer.MAX_VALUE;
		boolean flag = false;
		for (int i = 0; i <= 3; i++) {
			int ans = i;
			process(1, 0, ans, N, M, H, dir_map);
			if (MIN != Integer.MAX_VALUE) {
				flag = true;
				break;
			}
		}
		if(flag)
			System.out.println(MIN);
		else
			System.out.println(-1);
	}

	private static void process(int idx, int cnt, int ans, int n, int m, int h, int[][] dir_map) {
		if(cnt > 3) return;
		if (ans == cnt) {
			if (checkLedder(dir_map, n, h)) {
				MIN = MIN < cnt ? MIN : cnt;
			}
			return;
		}
		// 사다리 설치
		for (int i = idx; i <= h; i++) {
			for (int j = 1; j < n; j++) {
				if (dir_map[i][j] != 0 || dir_map[i][j + 1] != 0) { // 사다리 이미 존재 설치 x
					continue;
				} else { // 설치 가능
					dir_map[i][j] = 1;
					dir_map[i][j + 1] = 2;
					process(i, cnt + 1, ans, n, m, h, dir_map); // 설치
					dir_map[i][j] = 0;
					dir_map[i][j + 1] = 0;
				}
			}
		}
	}

	private static boolean checkLedder(int[][] dir_map, int n, int h) {
		int[] dx = { 0, 1, -1 }; // 우 좌

		for (int i = 1; i <= n; i++) {
			int y = 1;
			int x = i;
			while (y <= h) {
				if (dir_map[y][x] != 0) { // 연결된 곳 확인
					int dir = dir_map[y][x];
					int next_x = x + dx[dir];
					x = next_x;
				}
				y++;
			}
			if (x != i)
				return false;
		}
		return true;

	}
}
