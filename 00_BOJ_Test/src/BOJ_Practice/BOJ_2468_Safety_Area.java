/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2468 - 안전구역
 * 2차원 배열이 주어짐.
 * 비가와서 해당하는 높이보다 높은 구역을 묶어서
 * 총 몇개의 구역이 남는지 구하는 문제
 * BFS하자
 */
public class BOJ_2468_Safety_Area{
	static class Area_xy{
		int x, y;

		public Area_xy() {} 
		public Area_xy(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {-1, 1, 0, 0};

	public static void main(String[] args)  throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(stt.nextToken());
		int[][] area = new int[N][N];
		boolean visited[][];
		int maxValue = 0;
		int maxheight = 0;
		
		for( int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine());
			for( int j = 0; j < N; j++) {
				int height = Integer.parseInt(stt.nextToken());
				area[i][j] = height;
				
				maxheight = Math.max(height, maxheight);
			}
		}

		Queue<Area_xy> queue = new LinkedList<>();

		for( int l = 0; l < maxheight; l++) {
			int regionCnt = 0;
			visited = new boolean[N][N];
			for( int i = 0; i < N; i++) {
				for( int j = 0; j < N; j++) {
					if( area[i][j] > l ) {
						if(visited[i][j]) continue;
						queue.offer(new Area_xy(i, j));
						visited[i][j] = true;

						while(!queue.isEmpty()) {
							Area_xy tmp = queue.poll();
							for( int k = 0; k < 4; k++) {
								int cur_y = tmp.y + dy[k];
								int cur_x = tmp.x + dx[k];

								if( cur_y < 0 || cur_y >= N ||
										cur_x < 0 || cur_x >= N ) continue;
								if(visited[cur_y][cur_x]) continue;
								if(area[cur_y][cur_x] > l) {
									visited[cur_y][cur_x] = true;
									queue.offer(new Area_xy(cur_y, cur_x));
								}
							}
						} // while end
						regionCnt++;
					}
				}
			}
			maxValue = Math.max(regionCnt, maxValue);
		} // for end
		System.out.println(maxValue);
	} // main end
}
