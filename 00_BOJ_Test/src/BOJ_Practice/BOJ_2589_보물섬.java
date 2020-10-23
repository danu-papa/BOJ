/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class BOJ_2589_보물섬 {
	/**
	 * L 욱지, W 바다 이동은 상하좌우만 가능 이동하는데 1시간 보물은 육지 두곳 서로간에 최단거리로 이동하는데 가장 긴 시간이 걸리는 곳에
	 * 나뉘어 있음. 즉, 최단시간으로 갈 수 있는 곳 중에 가장 긴 곳을 찾자!
	 */

	private static class Land {
		int y, x, cnt;

		public Land(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}

	private static int dx[] = { 0, 0, -1, 1 };
	private static int dy[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {

		//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(stt.nextToken());
		int W = Integer.parseInt(stt.nextToken());

		char[][] map = new char[H][];
		boolean[][] visited;

		for(int i = 0; i < H; i++) {
			map[i] = br.readLine().toCharArray();
		} // 입력 끝

		// 최단거리 중에 가장 최장인 곳
		int maxCnt = 0;

		// BFS로 탐색하며 최단거리를 구한다.
		// 그 중에 가장 긴 녀석이 보물이 있는 곳
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(map[i][j] == 'L') { // 땅위치만
					int cnt=0; // 현재 위치에서 카운트
					visited = new boolean[H][W];
					Queue<Land> queue = new LinkedList<>();
					queue.offer(new Land(i,j,0));
					visited[i][j] = true;

					while(!queue.isEmpty()) {
						Land tmp = queue.poll();
						cnt = tmp.cnt; // 다른 땅에 도달했을때의 거리를 저장.

						for(int k = 0; k < 4; k++) { // 상하좌우 탐색
							int ny = tmp.y + dy[k];
							int nx = tmp.x + dx[k];

							if(ny < 0 || ny >= H || nx < 0 || nx >= W) continue; // 범위 체크
							if(map[ny][nx] == 'W') continue; // 바다 체크
							if(visited[ny][nx]) continue; // 방문 체크
							queue.offer(new Land(ny,nx,tmp.cnt+1));
							visited[ny][nx] = true;
						}
					}
					if(maxCnt < cnt) { // 최대값 갱신
						maxCnt = cnt;
					}
				}
			}
		}
		System.out.println(maxCnt);
	} // end of execute
}// end of class
