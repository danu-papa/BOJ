/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2206 - 벽 부수며 이동하기
 * NxM
 * 0은 이동가능, 1은 벽
 * 1,1 -> NxM으로 갈 때 최단경로 구하기
 * 시작 + 끝 포함
 * 벽을 단 하나만 깨고 이동 할 수 있다.
 * 1<= N <= 1000, 1<= M <= 1000
 * 가는 길이 없으면 -1 출력
 * **벽을 부수면서 오는 것과
 * 벽을 부수지 않고 오는 것을 따로 생각해야 풀린다. **
 */

class Position{
	int y, x, cnt, canBreak;

	public Position(int y, int x, int cnt, int canBreak) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
		this.canBreak = canBreak;
	}
}

public class BOJ_2206_Move_While_Breaking_Wall {
	private static int N, M;
	private static char[][] map;
	private static boolean visited[][][]; // 벽을 부수면서 왔는지 아닌지 판단
	private static int dx[] = {0, 0, -1, 1}; // 상하좌우
	private static int dy[] = {-1, 1, 0, 0}; // 상하좌우
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][2];

		for( int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine());
			map[i] = stt.nextToken().toCharArray();
		}
		search(0,0);
	}

	/** bfs*/
	private static void search(int y, int x) {
		Queue<Position> queue = new LinkedList<>();
		visited[y][x][0] = true; // 시작지점 부수던
		visited[y][x][1] = true; // 부수지않던 방문
		
		// 부수지않는 것을 기본 1로 주었다.
		queue.offer(new Position(y,x,1,1));


		while(!queue.isEmpty()) {
			Position tmp = queue.poll();

			if(tmp.x == M-1 && tmp.y == N-1) {
				System.out.print(tmp.cnt);
				return;
			}

			for( int k = 0; k < 4; k++) {
				int next_x = tmp.x + dx[k];
				int next_y = tmp.y + dy[k];

				if( next_x < 0 || next_x >= M || next_y < 0 || next_y >= N ) continue;
				// 벽을 만난 경우
				if( map[next_y][next_x] == '1' ) {
					if( tmp.canBreak == 1 && !visited[next_y][next_x][0] ) { // 부술 횟수가 남아있고, 현재 방문하지 않음.
						visited[next_y][next_x][0] = true; // 부수면서 오는 경로 방문 확인
						queue.offer(new Position(next_y,next_x,tmp.cnt+1,0)); // 부술수있는 횟수 0, cnt+1
						continue;
					} 
				} 
				// 벽이 아닌 경우 
				else {
					if(!visited[next_y][next_x][tmp.canBreak]) { // tmp.canBreak가 기본 1. 부순적이 없다면 부수지않고 오는 경우로
						visited[next_y][next_x][tmp.canBreak] = true; // 방문확인
						queue.offer(new Position(next_y,next_x,tmp.cnt+1,tmp.canBreak));
					}
				}
			}
		} // while end
		// while문 내에 return이 있기 때문에 여기 왔단것은 탈출 불가능하다는 소리.
		System.out.print("-1");
	}
}
