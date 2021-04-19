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
public class BOJ_14503_로봇청소기 {
	static class Robot {
		int y, x, dir;

		public Robot(int y, int x, int dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}

	private static int MAX;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(stt.nextToken()); // 지도 세로 크기
		int M = Integer.parseInt(stt.nextToken()); // 지도 가로 크기
		int[][] map = new int[N][M];

		stt = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(stt.nextToken());
		int x = Integer.parseInt(stt.nextToken());
		int dir = Integer.parseInt(stt.nextToken());
		Robot robot = new Robot(y, x, dir);

		for (int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stt.nextToken());
			}
		} // input end

		process(N, M, robot, map);
		System.out.println(MAX);
	}

	private static void process(int n, int m, Robot robot, int[][] map) {

		boolean[][] selected = new boolean[n][m];

		// 반시계 방향 탐색
		while (true) {
			// 현재 위치 청소
			clean(robot, selected, map);

			// 다음 위치 탐색. 더이상 탐색 할 수 없는 경우 멈춤
			if(!move(n, m, robot, map, selected)) break;
		}
	}

	private static boolean move(int n, int m, Robot robot, int[][] map, boolean[][] selected) {
		// 붇 동 남 서. 반시계 방향 이동
		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		
		int first_dir = robot.dir;
		
		while (true) {
			// 로봇 정보
			int y = robot.y;
			int x = robot.x;
			int dir = robot.dir;

			// 다음 방향 탐색
			int next_dir = dir == 0 ? 3 : dir - 1;
			int next_y = y + dy[next_dir];
			int next_x = x + dx[next_dir];

			// 이미 청소가 된 경우 또는 벽인 경우
			if (selected[next_y][next_x] || map[next_y][next_x] == 1) {
				// 방향 전환
				robot.dir = next_dir;
				
				// 4방향을 다 돌고 현재 자리로 돌아 온 경우
				if(robot.dir == first_dir) {
					// 후진
					int pre_y = y - dy[first_dir];
					int pre_x = x - dx[first_dir];
					
					// 후진했는데도 벽인 경우
					if(map[pre_y][pre_x] == 1) {
						return false;
					}
					
					// 후진 정보 저장
					robot.y = pre_y;
					robot.x = pre_x;
				}
				// 다음 방향 탐색
				continue;
			}
			
			// 청소 할 수 있는 곳이 있는 경우
			robot.y = next_y;
			robot.x = next_x;
			robot.dir = next_dir;
			break;
		}
		return true;
	}

	private static void clean(Robot robot, boolean[][] selected, int[][] map) {
		selected[robot.y][robot.x] = true;
		map[robot.y][robot.x] = -1;
		MAX++;
	}
}
