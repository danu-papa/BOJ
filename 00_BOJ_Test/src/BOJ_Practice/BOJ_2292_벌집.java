/**
 * 
 */
package BOJ_Practice;

import java.util.Scanner;

/**
 * @author YSM
 *
 */
public class BOJ_2292_벌집 {
	public static void main(String[] args) {
		/**
		 * 1 1번
		 * 1 + 6 = 7 2번
		 * 7 + 12 = 19 3번
		 * 19 + 18 = 37 4번
		 * 37 + 24 = 61 5번
		 * ...
		 * 6의 배수만큼 테두리가 채워지고
		 * 중앙에서 해당 테두리까지의 일정 거리는
		 * 모두 일치한다.
		 * 점화식 세울 수 있을까..?
		 * a1 = 1
		 * a2 = a1+6x1
		 * a3 = a2+6x2
		 * a4 = a3+6x3
		 * an = an-1 + 6x(n-1) (n>1)
		 * */

		// N이 10억개.... 라도 실제 연산 수는 적을 것 같다.
		// a 배열의 크기를 몇으로 줘야할까?
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		
		int sum = 1;
		int idx = 1;
		while(true) {
			sum += 6*idx++;
			
			if(sum > N) break;
		}
		if(N==1) idx = 1;
		System.out.println(idx);
	}
}
