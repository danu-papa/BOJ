/**
 * 
 */
package Set02_Back_Tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 9663 - N-Queen
 * 크기 N x N 퀸 N개를 서로 공격할 수 없게 놓는 문제
 * 퀸을 놓는 방법의 수
 * 퀸은 상 하 좌 우 대각선 모두 공격할 수 있는 체스말이다.
 * 메모리 초과 발생!!
 */
public class BOJ_9663_N_Queen_List {
	static int N, totalCnt;
	static List<Queen> queens;

	static class Queen{
		int y, x;
		public Queen(int y, int x){
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stt.nextToken());

		queens = new ArrayList<>();

		totalCnt = 0;

		place_queen_pos(0); // y, x 좌표

		System.out.println(totalCnt);
	}

	/** 퀸 자리 잡기. 다음으로 넘길 때 y+1로 넘기기 때문에 열이 겹치는 경우는 없다. 행과 대각선 처리*/
	private static void place_queen_pos(int y) {
		if(queens.size() == N) {
			totalCnt++;
			return;
		}

		loop:
			for( int i = 0; i < N; i++) {
				for( Queen q : queens) {
					if(q.x - i == 0 || 
							Math.abs(q.y - y) == Math.abs(q.x - i)) {
						continue loop;
					}
				}
				queens.add(new Queen(y, i));
				int last = queens.size()-1;
				place_queen_pos(y+1);
				queens.remove(last);
			}
	}
}
