/**
 * 
 */
package samsung_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class BOJ_16234_인구이동 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stt.nextToken());
		int L = Integer.parseInt(stt.nextToken());
		int R = Integer.parseInt(stt.nextToken());
		
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stt.nextToken());
			}
		} // input end
		
		int res = process(N, L, R, map);
		System.out.println(res);
	}

	private static int process(int n, int l, int r, int[][] map) {
		int move = 0;
		while(true) {
			if(move(n, l, r, map)) { // 이동 가능
				move++;
			} else {
				break;
			}
		}
		return move;
	}

	// BFS
	private static boolean move(int n, int l, int r, int[][] map) {
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][] visited = new boolean[n][n];
		boolean isMoved = false;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				List<int[]> list = new ArrayList<int[]>();
				if(visited[i][j]) continue;
				int sum = 0;
				int count = 0;
				queue.offer(new int[] {i, j, map[i][j]});
				list.add(new int[] {i,j});
				visited[i][j] = true;
				
				while(!queue.isEmpty()) {
					int[] villageInfo = queue.poll();
					int y = villageInfo[0];
					int x = villageInfo[1];
					int population = villageInfo[2];
					
					sum += population;
					count++;
					
					for(int d = 0; d < 4; d++) {
						int next_y = y + dy[d];
						int next_x = x + dx[d];
						
						if(rangeCheck(next_y, next_x, n) || visited[next_y][next_x]) continue;
						int next_population = map[next_y][next_x];
						int diff = Math.abs(population - next_population);
						if(diff >= l && diff <= r) {
							queue.offer(new int[] {next_y, next_x, next_population});
							list.add(new int[] {next_y, next_x});
							visited[next_y][next_x] = true;
						}
					} // 4방향 탐색 end
				} // queue check end
				
				// 인구 통일화 
				int next_population = sum / count;
				if(list.size() > 1) {
					isMoved = true;
					for(int[] pos : list) {
						int y = pos[0];
						int x = pos[1];
						map[y][x] = next_population;
					}
				}
			} // j end
		} // i end
		return isMoved;
	}

	private static boolean rangeCheck(int next_y, int next_x, int n) {
		return next_y < 0 || next_y >= n || next_x < 0 || next_x >= n;
	}
}
