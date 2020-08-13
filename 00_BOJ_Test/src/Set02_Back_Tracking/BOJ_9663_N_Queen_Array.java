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
 * 
 * n : 퀸의 y좌표
 * queens[n]의 값 : x좌표
 * 재귀 매개변수로 y+1이 들어가므로 행은 신경 안써도 된다.
 * 같은 열, 같은 대각선상에 퀸이 있는 경우 다음 열을 봐야한다.
 * 대각선상에 있는 것을 확인하려면 현재 놓으려는 퀸의 x2,y2좌표와 이미 놓아진 퀸의 x1,y1 좌표를 비교한다.
 * Math.abs(y1-y2) == Math.abs(x1-x2)인 경우 같은 대각선에 위치한다고 볼 수있다.
 */
public class BOJ_9663_N_Queen_Array {
	static int N, totalCnt, Qcnt;
	static int[] queens;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		queens = new int[N];

		totalCnt = 0;
		Qcnt = 0;

		place_queen_pos(0); // y, x 좌표

		System.out.println(totalCnt);
		sc.close();
	}

	/** 퀸 자리 잡기.*/
	private static void place_queen_pos(int y) {
		if(Qcnt == N) {
			totalCnt++;
			return;
		}

		Loop:
			for( int i = 0; i < N; i++) {
				if( Qcnt > 0) {
					for( int j = 0; j < Qcnt; j++) {
						if(queens[j] - i == 0 || Math.abs(queens[j] - i) == Math.abs(y - j) ) {
							continue Loop;
						}
					}
				}
				queens[y] = i; 

				Qcnt++;
				place_queen_pos(y + 1); // 여기를 나오는 순간 해당 열의 퀸을 재배치 해야 한다.
				Qcnt--;
			}
	}
}
