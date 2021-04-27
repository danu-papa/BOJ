/**
 * 
 */
package Set03_DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class BOJ_4991_로봇청소기 {
	static class Point { // 위치 정보 클래스
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static int minMove;
	private static int[][] distance;
	private static Point[] list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer stt = new StringTokenizer(br.readLine());

			int W = Integer.parseInt(stt.nextToken());
			int H = Integer.parseInt(stt.nextToken());

			if (W == 0 && H == 0)
				break; // 종료

			char[][] map = new char[H][W];
			list = new Point[11]; // 먼지의 갯수 최대 10개. +1인 로봇청소기
			int dust_cnt = 1;
			for (int i = 0; i < H; i++) {
				char[] input = br.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					map[i][j] = input[j];
					if (input[j] == 'o') { // 0번은 무조건 로봇청소기
						list[0] = new Point(i, j);
					} else if (input[j] == '*') { // 그 뒤는 먼지
						list[dust_cnt++] = new Point(i, j);
					}
				}
			} // input end
			process(map, W, H, dust_cnt);
			System.out.println(minMove);
		} // while end
	}

	private static void process(char[][] map, int w, int h, int dust_cnt) {
		minMove = Integer.MAX_VALUE;
		// 모든 거리를 저장할 2차원 배열
		distance = new int[dust_cnt + 1][dust_cnt + 1];
		// 청소기와 먼지, 먼지와 먼지의 모든 거리를 구해서 저장
		for (int i = 0; i < dust_cnt; i++) { // 먼지의 수만큼 반복
			for (int j = i + 1; j < dust_cnt; j++) {
				int res = bfs(map, w, h, list[i], list[j]);
				if (res == -1) { // 도달할 수 없는 먼지가 있는 경우 더이상 반복 x
					minMove = -1;
					return;
				} else { // 반대에서 오는 경우도 잘 저장해주자
					distance[i][j] = distance[j][i] = res;
				}
			}
		}

		boolean[] selected = new boolean[dust_cnt];

		// 순열을 통해 모든 거리를 순회하자
		permutation(0, 0, 0, dust_cnt, selected);
	}

	private static void permutation(int idx, int cnt, int sum, int dust_cnt, boolean[] selected) {
		if (cnt == dust_cnt - 1) { // 모든 곳 방문
			minMove = minMove < sum ? minMove : sum;
			return;
		}
		for (int i = 1; i < dust_cnt; i++) {
			if (selected[i])
				continue;
			selected[i] = true;
			permutation(i, cnt + 1, sum + distance[idx][i], dust_cnt, selected);
			selected[i] = false;
		}
	}

	private static int bfs(char[][] map, int w, int h, Point src, Point dest) {
		int[] dy = { -1, 1, 0, 0 };
		int[] dx = { 0, 0, -1, 1 };
		boolean[][] visited = new boolean[h][w];
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(src);
		visited[src.y][src.x] = true;
		map[src.y][src.x] = '.';

		int move = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int s = 0; s < size; s++) { // 거리별 탐색
				Point current = queue.poll();
				int y = current.y;
				int x = current.x;

				if (y == dest.y && x == dest.x) { // 목적지 도달
					return move;
				}

				for (int d = 0; d < 4; d++) {
					int next_y = y + dy[d];
					int next_x = x + dx[d];
					if (outRange(next_y, next_x, w, h) || visited[next_y][next_x] || map[next_y][next_x] == 'x')
						continue;
					queue.offer(new Point(next_y, next_x));
					visited[next_y][next_x] = true;
				}
			}
			move++;
		}
		
		// 도달 불가
		return -1;
	}

	private static boolean outRange(int next_y, int next_x, int w, int h) {
		return next_y < 0 || next_y >= h || next_x < 0 || next_x >= w;
	}
}
