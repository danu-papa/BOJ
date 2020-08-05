/**
 * 
 */
package BOJ_Practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 7576 토마토가 익는 시간 구하기
 * N x M 사이즈의 상자에 토마토가 들어있다.
 * 1 : 익은 토마토, 0: 안익은 토마토, -1: 비어있음
 * 익은 토마토 상 하 좌 우의 토마토는 영향을 받아 익게 된다.
 * 모든 토마토가 익는 시간을 구하라
 * (시간이 지나 혼자 익는 경우는 없다)
 */
public class BOJ_7576_Tomato {
	private static int M,N, box[][], day;
	private static int[] dx = { 0, 0, -1, 1};
	private static int[] dy = { -1, 1, 0, 0};

	class Tinfo{
		int x;
		int y;

		public Tinfo(int y, int x) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) {
		BOJ_7576_Tomato tomato = new BOJ_7576_Tomato();
		tomato.ripe();
	}

	/** 토마토가 익어가는 코드. BFS를 이용하자.*/
	private void ripe() {
		Scanner sc = new Scanner(System.in);

		M = sc.nextInt(); // 가로
		N = sc.nextInt(); // 세로

		box = new int[N][M];
		day = -1;
		
		Queue<Tinfo> queue = new LinkedList<>();

		for(int i = 0; i < N; i++) {
			for( int j = 0; j < M; j++) {
				int tomato = sc.nextInt();
				box[i][j] = tomato;
				if(tomato == 1) {
					queue.offer(new Tinfo(i,j));
				}

			}
		}

		bfs(queue);
		
		for(int i = 0; i < N; i++) {
			for( int j = 0; j < M; j++) {
				if(box[i][j] == 0) {
					System.out.println("-1");
					return;
				}
			}
		}
		
		System.out.println(day);
		sc.close();
	} // ripe end

	/** Breadth First Search */
	private void bfs(Queue<Tinfo> queue) {
		while(!queue.isEmpty()) {
			int size = queue.size();

			day++;
			for( int i = 0; i < size; i++) {
				Tinfo tmp = queue.poll();
				
				// 사방 탐색
				for( int k = 0; k < 4; k++) {
					int cur_x = tmp.x + dx[k];
					int cur_y = tmp.y + dy[k];
					
					if(cur_x < 0 || cur_x >= M
							|| cur_y < 0 || cur_y >= N) {
						continue;
					}
					if(box[cur_y][cur_x] != 0) continue;

					box[cur_y][cur_x] = 1;
					queue.offer(new Tinfo(cur_y, cur_x));
				}
			}
		}
	}
}
