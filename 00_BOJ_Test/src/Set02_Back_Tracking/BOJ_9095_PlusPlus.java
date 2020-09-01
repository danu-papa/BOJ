/**
 * 
 */
package Set02_Back_Tracking;

import java.util.Scanner;

/**
 * 백준 9095 - 1, 2, 3 더하기
 * 1,2,3을 더해서 목표하는 수가 나오는 경우의 수세기
 * 백트래킹의 기본.
 */
public class BOJ_9095_PlusPlus {
	private static int N, totalCnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for ( int t = 1; t <= T; t++) {
			N = sc.nextInt(); // 목적으로 하는 숫자.
			
			totalCnt = 0; // 초기화
			start_plust(0);
			
			System.out.println(totalCnt);
		}
	}
	
	/** 재귀적으로 호출되면서 목적합에 도달했는지 확인.*/
	private static void start_plust(int num) {
		if(num == N) { // 목적합에 도달.
			totalCnt++;
			return;
		}
		if(num > N) { // N보다 크면 더이상 더할 필요 없음.
			return;
		}
		for( int i = 1; i <= 3; i++) { // 1, 2 ,3의 조합으로 만든다.
			start_plust(num+i); // 계속해서 다한 값으로 재귀 호출
		}
	}
}
