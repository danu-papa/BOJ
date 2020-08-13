/**
 * 
 */
package Set01_search_sort01;

import java.util.Scanner;

/**
 * 백준 1026 보물
 * A B 두개의 숫자 배열이 있다.
 * S = A[0]*N[0] +...+A[N]*B[N] 이다
 * S의 값을 최소로 하기 위해 A배열을 재배치 한다고 한다
 * for문 내에 for문 2번 더 돌며 A B 배열의 최대값 최소값을 구해서 곱한다.
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
		
		int sum = 0;
		
		for( int i = 0; i < n; i++) {
			int max = 0;
			int min = 100;
			int idx = 0;
			for( int j = 0; j < n; j++) {
				if( numA[j] > max) {
					max = numA[j];
					idx = j;
				}
			}
			numA[idx] = -1;
			
			for( int k = 0; k < n; k++) {
				if( numB[k] < min) {
					min = numB[k];
					idx = k;
				}
			}
			numB[idx] = 101;
			sum += max*min;
		}
		System.out.println(sum);
		
		sc.close();
	}
}
