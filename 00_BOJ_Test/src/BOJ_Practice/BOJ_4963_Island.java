/**
 * 
 */
package BOJ_Practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 4963 - 섬의 개수 세기
 * 가로 세로 대각선으로 인접한 섬이 있다면 
 * 걸어서 갈 수 있는 섬.
 * 탐색해보자 
 */
public class BOJ_4963_Island {
	private static int W,H, map[][], num_island;
	private static boolean visited[][];

	//  상   하     좌    우     좌상   좌하  우상  우하
	private static int[] dx = { 0, 0, -1, 1, -1, -1, 1, 1};
	private static int[] dy = { -1, 1, 0, 0, -1, 1, -1, 1};

	class Island{
		int y;
		int x;

		Island(int y, int x){
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) {
		BOJ_4963_Island il = new BOJ_4963_Island();
		il.start();
	}

	/** 섬의 개수를 세어보자*/
	private void start() {
		Scanner sc = new Scanner(System.in);

		Queue<Island> queue = new LinkedList<>();

		while(true) {
			num_island = 0;
			W = sc.nextInt();
			H = sc.nextInt();

			if(W == 0 && H == 0) break;

			map = new int[H][W];
			visited = new boolean[H][W];

			for( int i = 0; i < H; i++) {
				for( int j = 0; j < W; j++) {
					int input = sc.nextInt();
					map[i][j] = input;
				}
			}

			for( int i = 0; i < H; i++) {
				for( int j = 0; j < W; j++) {
					if(map[i][j] == 1) {
						if(visited[i][j]) continue;

						queue.offer(new Island(i,j));
						visited[i][j] = true;

						while(!queue.isEmpty()) {
							Island temp = queue.poll();

							// 8방향 탐색
							for ( int k = 0; k < 8; k++) {
								int cur_y = temp.y + dy[k];
								int cur_x = temp.x + dx[k];

								if( cur_y < 0 || cur_y >= H || 
										cur_x < 0 || cur_x >= W) {
									continue;
								}
								if(visited[cur_y][cur_x]) {
									continue;
								}
								if(map[cur_y][cur_x] == 1) {
									visited[cur_y][cur_x] = true;
									queue.add(new Island(cur_y,cur_x));
									continue;
								}
							}
						}
						num_island++;
					}
				}
			}
			System.out.println(num_island);
		} // while end
		sc.close();
	}
}