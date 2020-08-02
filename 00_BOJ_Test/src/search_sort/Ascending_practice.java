/**
 * 
 */
package search_sort;

import java.util.Scanner;

/**
 * 백준 2750
 * 수 정렬하기
 * N개의 숫자를 오름차순 정렬
 */
public class Ascending_practice {
	private static int N;
	/** @param args*/
	public static void main(String[] args) {
		Ascending_practice ap = new Ascending_practice();
		ap.ascending();
	}

	/** 오름차순 구현 부 */
	private void ascending() {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		int[] sorted_numbers = new int[N]; // 정렬 후의 배열, 0 초기화
		
		for( int i = 0; i < N; i++) {
			int input_number = sc.nextInt(); // 입력
			sorted_numbers[i] = input_number;
			for ( int j = i; j >= 0 ; j--) {
				if(sorted_numbers[j] >= input_number) { // 현재 배열의 값이 입력 값보다 크다면
					int temp = sorted_numbers[j];
					sorted_numbers[j] = input_number;
					if(j +1 != N)
					sorted_numbers[j+1] = temp;
					continue;
				}
			}
		}
		for(int i = 0; i < N; i++)
			System.out.println(sorted_numbers[i]);
		sc.close();
	}

}
