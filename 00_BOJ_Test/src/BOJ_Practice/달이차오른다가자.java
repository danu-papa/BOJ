/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class 달이차오른다가자 {
	static class Min{
		int y, x, move, key;

		public Min(int y, int x, int move, int key) {
			this.y = y;
			this.x = x;
			this.move = move;
			this.key = key;
		}
	}
	
	private static int N, M, resMin;
	private static final int KEY = (1 << 6);
	private static char[][] map;
	private static boolean[][][] visited;
	private static int[] dy = {-1, 1, 0, 0};
	private static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][KEY];
		
		int start_y = 0, start_x = 0;
		boolean find = false;
		
		for(int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine());
			map[i] = stt.nextToken().toCharArray();
			if(!find) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == '0') {
						start_y = i;
						start_x = j;
						find = true;
					}
				}
			}
		} // input end
		
		dfs(start_y, start_x);
		
		System.out.println(resMin);
	}
	
	private static void dfs(int start_y, int start_x) {
		Queue<Min> queue = new LinkedList<Min>();
		queue.offer(new Min(start_y, start_x, 0, 0));
		visited[start_y][start_x][0] = true;
		
		while(!queue.isEmpty()) {
			Min cur_min = queue.poll();
			int cur_y = cur_min.y;
			int cur_x = cur_min.x;
			int move = cur_min.move;
			
			if(map[cur_y][cur_x] == '1') {
				resMin = cur_min.move;
				return ;
			}
			
			for(int d = 0; d < 4; d++) {
				int next_y = cur_y + dy[d];
				int next_x = cur_x + dx[d];
				int cur_key = cur_min.key;
				
				if(range_check(next_y, next_x)) continue;
				if(map[next_y][next_x] == '#') continue;

				if('a' <= map[next_y][next_x] && map[next_y][next_x] <= 'f') {
					int key = map[next_y][next_x] - 'a';
					cur_key = cur_key | ( 1 << key );
				}
				
				if('A' <= map[next_y][next_x] && map[next_y][next_x] <= 'Z') {
					int key = map[next_y][next_x] - 'A';
					if((cur_key & ( 1 << key )) == 0) continue;
				}
				
				if(visited[next_y][next_x][cur_key]) continue;
				
				queue.offer(new Min(next_y, next_x, move+1, cur_key));
				visited[next_y][next_x][cur_key] = true;
			}
		}
		resMin = -1;
	}

	private static boolean range_check(int next_y, int next_x) {
		return next_y >= N || next_y < 0 || next_x >= M || next_x < 0;
	}
}
