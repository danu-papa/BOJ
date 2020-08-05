/**
 * 
 */
package Set02_Search_Sort02;

import java.util.Scanner;

/**
 * 백준 1026 보물
 * A B 두개의 숫자 배열이 있다.
 * S = A[0]*N[0] +...+A[N]*B[N] 이다
 * S의 값을 최소로 하기 위해 A배열을 재배치 한다고 한다
 */
public class BOJ_1026_Treasure {
	public static void main(String[] args) {
		BOJ_1026_Treasure tre = new BOJ_1026_Treasure();
		tre.reArray();
	}

	/** 구현 코드*/
	private void reArray() {
		Scanner sc = new Scanner(System.in);
		
		// 배열 크기
		int n = sc.nextInt();
		
		int[] numA = new int[n];
		int[] numB = new int[n];
		
		for ( int i = 0; i < n; i++) {
			numA[i] = sc.nextInt();
		}
		
		for ( int i = 0; i < n; i++) {
			numB[i] = sc.nextInt();
		}
		
		
		
		sc.close();
	}
}
