/**
 * 
 */
package Set02_Back_Tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1182 - 부분집합의 합구하기
 * 하나도 선택하지 않은 경우를 잘 잡아주자
 */
public class BOJ_1182_Subset_Sum {
	static int N, SUM, NUMBER[], totalCnt;
	static boolean isSelected[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stt.nextToken());
		NUMBER = new int[N];
		isSelected = new boolean[N];
		totalCnt = 0;
		
		SUM = Integer.parseInt(stt.nextToken());
		
		stt = new StringTokenizer(br.readLine());
		
		for ( int i = 0; i < N; i++) {
			NUMBER[i] = Integer.parseInt(stt.nextToken());
		}
		
		subset_sum(0,0);
		System.out.println(totalCnt);
	}
	
	/** 부분집합의 합*/
	private static void subset_sum(int idx, int cnt) {
		if( cnt == N) {
			int no_sel = -1; // 하나도 선택을 하지 않은 경우는 Pass
			int sum = 0;
			for( int i = 0; i < N; i++) {
				if(isSelected[i]) { // 선택 한 수를
					sum += NUMBER[i]; // 다 더했는데
					no_sel = 0;
				}
			}
			if(no_sel == -1) return; // 하나도 선택 안하면 pass
			if(sum == SUM) { // 그 값이 내가 원하는 값이다
				totalCnt++; // 카운트
			}
			return; // 어떤 경우도 안나오더라 하면다음으로
		}
		
		isSelected[idx] = true;
		subset_sum(idx + 1, cnt + 1);
		isSelected[idx] = false;
		subset_sum(idx + 1, cnt + 1);
	}
}
