/**
 * 
 */
package Set02_Back_Tracking;

/**
 * 백준 9663 - N-Queen
 * 크기 N x N 퀸 N개를 서로 공격할 수 없게 놓는 문제
 * 퀸을 놓는 방법의 수
 * 퀸은 상 하 좌 우 대각선 모두 공격할 수 있는 체스말이다.
 */
import java.util.Scanner;

/**
 * 백준 9663 - N-Queen
 * 크기 N x N 퀸 N개를 서로 공격할 수 없게 놓는 문제
 * 퀸을 놓는 방법의 수
 * 퀸은 상 하 좌 우 대각선 모두 공격할 수 있는 체스말이다.
 */
public class BOJ_9663_N_Queen {
	static int dx[] = {0, 0, -1, 1, -1, -1, 1, 1};
	static int dy[] = {-1, 1, 0, 0, -1, 1, -1, 1};
	static int N, map[][], totalCnt, Qcnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N]; // 0으로 초기화
		totalCnt = 0;
		Qcnt = 0;
		
		place_queen_pos(0); // y, x 좌표
		
		System.out.println(totalCnt);
		sc.close();
	}

	/** 퀸 자리 잡기.*/
	private static void place_queen_pos(int y) {
		if( y == N) {
			if(Qcnt == N)
				totalCnt++;
			return;
		}
		
		loop:
		for( int j = 0; j < N; j++) {
			int cur_y = y;
			int cur_x = j;

			// 팔방 탐색.
			for( int k = 0; k < 8; k++) {
				cur_y += dy[k];
				cur_x += dx[k];
				// 끝에 다다르면 원위치로 초기화 하고 다시 탐색
				if( cur_y < 0 || cur_y >= N || cur_x < 0 || cur_x >= N) {
					cur_y = y;
					cur_x = j;
					continue;
				}
				// 탐색도중 퀸을 만남. 더이상 탐색 할 필요 없음.
				if(map[cur_y][cur_x] == -1) {
					continue loop;
				}
				k--;
			}
			
			// 8방 탐색을 다 하고 여기로 왔다는 것은, 8방향 어디에도 퀸이 없다는 것을 뜬한다.
			map[y][j] = -1; // 퀸 배치
			Qcnt++;
			place_queen_pos(y + 1); // 여기를 나오는 순간 해당 열의 퀸을 재배치 해야 한다.
			map[y][j] = 0;
			Qcnt--;
		}
	}
}

