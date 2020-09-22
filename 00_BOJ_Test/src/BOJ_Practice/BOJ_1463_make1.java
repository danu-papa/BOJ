/**
 * 
 */
package BOJ_Practice;

import java.util.Scanner;

/**
 * 백준 1463 1로 만들기
 * 1. X가 3으로 나누어지면 3으로
 * 2. X가 2로 나누어지면 2로
 * 3. 1을 뺀다.
 */
public class BOJ_1463_make1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		
		int[] D = new int[N+1];
		int min = Integer.MAX_VALUE;
		// 3으로 나누는 경우: N%3
		// 2로 나누는 경우: N%2
		// 1을 빼는 경우: N-1
		for (int i = 2; i <= N; i++) {
			min = Integer.MAX_VALUE;
			if(i%3 == 0 && D[i/3]+1 < min) min = D[i/3]+1;
			if(i%2 == 0 && D[i/2]+1 < min) min = D[i/2]+1;	
			if(D[i-1]+1 < min) min = D[i-1]+1;
			
			D[i] = min;
		}
		System.out.println(D[N]);
	}
}
