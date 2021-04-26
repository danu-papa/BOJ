/**
 * 
 */
package samsung_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19238_스타트택시 {
	static class Passenger { // 승객 정보
		int src_y, src_x, dest_y, dest_x, distance;

		public Passenger(int src_y, int src_x, int dest_y, int dest_x) {
			this.src_y = src_y;
			this.src_x = src_x;
			this.dest_y = dest_y;
			this.dest_x = dest_x;
		}
	}

	static class Taxi { // 택시 정보
		int y, x, energy, passNo;

		public Taxi(int y, int x, int energy) {
			this.y = y;
			this.x = x;
			this.energy = energy;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(stt.nextToken());
		int M = Integer.parseInt(stt.nextToken());
		int E = Integer.parseInt(stt.nextToken());

		int[][] map = new int[N+1][N+1];

		for (int i = 1; i <= N; i++) {
			stt = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(stt.nextToken());
			}
		}

		stt = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(stt.nextToken());
		int x = Integer.parseInt(stt.nextToken());
		Taxi taxi = new Taxi(y, x, E);

		// 승객 번호 별 배열 관리
		Passenger[] p_arr = new Passenger[M+2];
		for (int m = 0; m < M; m++) {
			stt = new StringTokenizer(br.readLine());
			int src_y = Integer.parseInt(stt.nextToken());
			int src_x = Integer.parseInt(stt.nextToken());
			int dest_y = Integer.parseInt(stt.nextToken());
			int dest_x = Integer.parseInt(stt.nextToken());
			p_arr[m+2] = new Passenger(src_y, src_x, dest_y, dest_x);
			map[src_y][src_x] = m+2;
		} // input end

		int res = process(map, p_arr, taxi, N, M);
		System.out.println(res);
	}

	private static int process(int[][] map, Passenger[] p_arr, Taxi taxi, int n, int m) {
		for (int i = 0; i < m; i++) { // 총 승객 수만큼 반복
			// 거리가 가장 짧은 승객으로 BFS.
			// 택시 승객에게 이동
			if(bfs_to_passenger(map, p_arr, taxi, n)) {
				// 승객 목적지 이동
				if (bfs(map, p_arr, taxi, n)) {
				} else { // 승객 목적지 이동 불가
					return -1;
				}
			} else { // 택시 승객에게 이동 불가
				return -1;
			}
		}
		return taxi.energy;
	}

	// 태울 승객을 가려내자
	private static boolean bfs_to_passenger(int[][] map, Passenger[] p_arr, Taxi taxi, int n) {
		int[] dy = { -1, 1, 0, 0 }; // 상 하 좌 우
		int[] dx = { 0, 0, -1, 1 };
		boolean[][] visited = new boolean[n+1][n+1];
		Queue<int[]> queue = new LinkedList<int[]>();
		// 현재 택시 위치
		queue.offer(new int[] {taxi.y, taxi.x, taxi.energy});
		visited[taxi.y][taxi.x] = true;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			// 가장 가까운 승객의 위치 정보
			int min_y = n+1;
			int min_x = n+1;
			boolean flag = false;
			// 같은 거리의 승객이 여러명 있을 수 있으므로 사이즈 별로 반복
			for(int s = 0; s < size; s++) {
				int[] pos = queue.poll();
				int y = pos[0];
				int x = pos[1];
				int energy = pos[2];

				// 승객 발견. 규칙에 따라 태울 승객을 가리자
				if (map[y][x] > 1) {
					if(min_y > y) {
						min_y = y;
						min_x = x;
					} else if(min_y == y) {
						if(min_x > x) {
							min_y = y;
							min_x = x;
						}
					}
					flag = true;
					// 에너지 소모는 같으므로 여기서 할당
					taxi.energy = energy;
					continue;
				}

				// 에너지 다 씀
				if (energy <= 0)
					return false;

				// 4방 탐색
				for (int d = 0; d < 4; d++) {
					int next_y = y + dy[d];
					int next_x = x + dx[d];

					if (rangeCheck(next_y, next_x, n) || visited[next_y][next_x] ||
						map[next_y][next_x] == 1)
						continue;
					queue.offer(new int[] { next_y, next_x, energy - 1 });
					visited[next_y][next_x] = true;
				}
			} // 큐 사이즈 순회 end
			// 태울 승객이 있는 경우
			if(flag) {
				taxi.y = min_y;
				taxi.x = min_x;
				taxi.passNo = map[min_y][min_x];
				map[min_y][min_x] = 0;
				return true;
			}
		} // while end
		return false;
	}

	// 승객의 목적지로 이동하자
	private static boolean bfs(int[][] map, Passenger[] p_arr, Taxi taxi, int n) {
		int[] dy = { -1, 1, 0, 0 };
		int[] dx = { 0, 0, -1, 1 };
		boolean[][] visited = new boolean[n+1][n+1];
		Queue<int[]> queue = new LinkedList<int[]>();
		Passenger cur_p = p_arr[taxi.passNo];
		
		queue.offer(new int[] {taxi.y, taxi.x, taxi.energy });
		visited[taxi.y][taxi.x] = true;

		while (!queue.isEmpty()) {
			int size = queue.size();
			int[] pos = queue.poll();
			int y = pos[0];
			int x = pos[1];
			int energy = pos[2];

			// 도착
			if (y == cur_p.dest_y && x == cur_p.dest_x) {
				// 원래 에너지 - 현재 에너지 -> 소모한 에너지
				taxi.y = y;
				taxi.x = x;
				taxi.energy = energy + 2 * (taxi.energy - energy);
				return true;
			}

			// 에너지 다 씀
			if (energy <= 0)
				continue;

			for (int d = 0; d < 4; d++) {
				int next_y = y + dy[d];
				int next_x = x + dx[d];

				if (rangeCheck(next_y, next_x, n) || visited[next_y][next_x] ||
					map[next_y][next_x] == 1)
					continue;
				queue.offer(new int[] { next_y, next_x, energy - 1 });
				visited[next_y][next_x] = true;
			}
		}
		// 도착할 수 없는 경우
		return false;
	}

	private static boolean rangeCheck(int next_y, int next_x, int n) {
		return next_y <= 0 || next_y >= n+1 || next_x <= 0 || next_x >= n+1;
	}
}
