package Set01_search_sort01;

import java.util.Scanner;

/**
 * 백준 1920
 * 주어진 정수에서
 * X라는 정수가 존재하는지 찾는 문제
 * for문 2개를 돌며 해당 숫자가 있으면
 * 1을 출력 없으면 0을 출력 
 * */
public class find_num {
	private static int N,M,numbers[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		numbers = new int[N];
		for( int i = 0; i < N; i++)
			numbers[i] = sc.nextInt();

		M = sc.nextInt();

		outer:
		for( int i = 0; i < M; i++) {
			int num = sc.nextInt();
			for(int j = 0; j < N; j++) {
				if( num == numbers[j]) {
					System.out.println(1);
					continue outer;
				}
			}
			System.out.println(0);
		}
		sc.close();
	}
}
