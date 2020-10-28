package Set03_DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636_치즈 {
	private static int N, M, map[][], Time;
	private static boolean visited[][];
	private static int dx[] = {0, 0, -1, 1};
	private static int dy[] = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stt.nextToken());
			}
		} // input end
		
		int res = bfs();
		System.out.println(Time-1);
		System.out.println(res);
		
	}
	
	private static int bfs() {
		Queue<int[]>queue = new LinkedList<>();
		int cheese = 0, cnt = 0;
		while(true) {
			cheese = cnt;
			cnt = 0;
			visited = new boolean[N][M];
			queue.add(new int[] {0,0});
			visited[0][0] = true;

			while(!queue.isEmpty()) {
				int[] pos = queue.poll();
				for(int k = 0; k < 4; k++) {
					int next_y = pos[0] + dy[k];
					int next_x = pos[1] + dx[k];

					if(rangeChk(next_y,next_x)) continue;
					if(visited[next_y][next_x]) continue;

					if(map[next_y][next_x] == 0) {
						visited[next_y][next_x] = true;
						queue.offer(new int[] {next_y, next_x});
					}

					if(map[next_y][next_x] == 1) {
						map[next_y][next_x] = 2;
					}
				}
			} // 시간 지남.
			
			Time++;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 2) {
						map[i][j] = 0;
						cnt++;
					}
				}
			} // 공기 노출된 치즈 제거
			if(cnt == 0) return cheese;
		}
	}
	
	private static boolean rangeChk(int y, int x) {
		if(y < 0 || y >= N || x < 0 || x >= M) return true;
		return false;
	}
}
