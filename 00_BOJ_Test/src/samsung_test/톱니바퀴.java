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
public class 톱니바퀴 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] gears = new int[5][8];
		for (int i = 1; i < 5; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				gears[i][j] = input[j]-'0';
			}
		}

		int K = Integer.parseInt(br.readLine()); // 회전 수

		// 회전 방법
		for (int i = 0; i < K; i++) {
			StringTokenizer stt = new StringTokenizer(br.readLine());
			// 중심 기어
			int center_gear = Integer.parseInt(stt.nextToken());
			// 1 시계, -1 반시계
			int dir = Integer.parseInt(stt.nextToken());

			// 오른쪽 왼쪽 기어 돌리기
			checkLeftGear(-dir, gears[center_gear][6], center_gear - 1, gears);
			checkRighGear(-dir, gears[center_gear][2], center_gear + 1, gears);
			rotate_gear(dir, gears, center_gear);
		}
		
		int answer = 0;
		int[] grade = {0,1,2,4,8};
		
		for(int i = 1; i < 5; i++) {
			if(gears[i][0] == 1)
				answer += grade[i];
		}
		System.out.println(answer);
	}

	private static void checkRighGear(int dir, int left, int center_gear, int[][] gears) {
		if (center_gear == 5)
			return;

		// 왼쪽과 같은 방향이면 못돌림
		if (gears[center_gear][6] == left)
			return;

		// 다른 방향인 경우. 회전 방향의 반대 방향으로 회전
		checkRighGear(-dir, gears[center_gear][2], center_gear + 1, gears);
		rotate_gear(dir, gears, center_gear);
	}

	private static void checkLeftGear(int dir, int right, int center_gear, int[][] gears) {
		if (center_gear == 0)
			return;

		// 오른쪽과 같은 방향이면 못돌림
		if (gears[center_gear][2] == right)
			return;

		// 다른 방향인 경우. 회전 방향의 반대 방향으로 회전
		checkLeftGear(-dir, gears[center_gear][6], center_gear - 1, gears);
		rotate_gear(dir, gears, center_gear);
	}

	private static void rotate_gear(int dir, int[][] gears, int center_gear) {
		switch (dir) {
		case 1: // 시계 방향
			int pre = gears[center_gear][7];
			for (int i = 0; i < 8; i++) {
				int cur = gears[center_gear][i];
				gears[center_gear][i] = pre;
				pre = cur;
			}
			break;
		case -1: // 반시계 방향
			pre = gears[center_gear][0];
			for (int i = 7; i >= 0; i--) {
				int cur = gears[center_gear][i];
				gears[center_gear][i] = pre;
				pre = cur;
			}
			break;
		}
	}
}
