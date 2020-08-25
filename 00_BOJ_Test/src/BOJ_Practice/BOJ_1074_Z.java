/**
 * 
 */
package BOJ_Practice;

import java.util.Scanner;

/**
 * 백준 1074 - Z
 * 2^Nx2^N 2차원 배열을 Z모양으로 탐색.
 * N , R, C가 주어졌을 때 R,C 좌표를 찾기까지
 * 탐색수 출력하기
 * Divide & Conquer 이용
 */
public class BOJ_1074_Z {
	// 한면길이, Y축, X축, 횟수 
	private static int N, R, C, cnt;
	private static boolean find; // 찾았다
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		N = (int)Math.pow(2, N);
		R = sc.nextInt();
		C = sc.nextInt();
		cnt = 0;

		search(N, 0, 0);
		System.out.println(cnt);
		sc.close();
	}

	/** 분할정복하며 탐색*/
	private static void search(int n, int x, int y) {
		if( n == 2 ){ // n이 2라는 소리는 가장 작은 2x2까지 분할 했다는 뜻.
			for( int i = y; i < y + 2; i++) { // 작은 사각형 내부는 2x2
				for( int j = x; j < x + 2; j++) {
					if( i == R && j == C) {
						find = true;
						return;
					}
					cnt++;
				}
			}
			return ;
		}

		int size = n/2; // ex) 4 x 4 사이즈 / 2 -> 2x2 즉 size는 한 면의 길이.

		for( int i = 0; i < 2; i++) {
			for( int j = 0; j < 2; j++) {
				if( find ) return; // 찾았다면 더이상 진행 하지 않고 return.
				search(size, x+size*j, y+size*i); // 각 영역에 맞게 x,y좌표의 시작점을 부여
			}
		}
	}
}
