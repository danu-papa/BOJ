/**
 * 
 */
package Set02_Back_Tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 6603 로또
 * 독일로또 1~49 사이의 숫자중 6개를 고름.
 * 1~49 수중 6개 이상의 숫자를 골라 부분집합을 만들고
 * 나오는 모든 경우의 수 중 하나를 고르는 방법이 있다.
 * 나올 수 있는 모든 경우의 수를 출력하기.
 * 조합 쓰면 되겠다.
 */
public class BOJ_6603_Lotto {
	static int N, number[],input[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer stt = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stt.nextToken());
			
			if(N == 0) break;
			
			input = new int[N];
			number = new int[6];

			for( int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(stt.nextToken());
			}

			combination(0,0);
			System.out.println();
		}
	}

	
	/** 조합*/
	private static void combination(int start, int cnt) {
		if(cnt == 6) {
			for( int i = 0; i < 6; i++) {
				System.out.print(number[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for( int i = start; i < N; i++) {
			number[cnt] = input[i];
			combination(i+1, cnt+1);
		}
	}
}
