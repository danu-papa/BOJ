/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2961 - 도영이가 만든 맛있는 음식
 * 재료 N개 
 * S신맛 ( 곱하기 ), B쓴맛 ( 더하기 ) 
 * 부분집합을 이용해서 모든 경우의 수를 찾아보자
 */
public class BOJ_2961_TastyFood {
	static class Ingradient{
		int s;
		int b;
		
		public Ingradient() {}
		
		public Ingradient(int s, int b) {
			this.s = s;
			this.b = b;
		}
	}
	
	private static Ingradient igd[];
	private static boolean isSelected[];
	private static int Min_diff, N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stt.nextToken());
		
		igd = new Ingradient[N];
		isSelected = new boolean[N];
		Min_diff = 1000000000;
		
		for( int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine());
			int s,b;
			s = Integer.parseInt(stt.nextToken());
			b = Integer.parseInt(stt.nextToken());
			igd[i] = new Ingradient(s,b);
		}
		subset(0);
		System.out.println(Min_diff);
	}

	/** 부분집합을 이용해 보자*/
	private static void subset(int cnt) {
		if(cnt == N) {
			int result = 0, s = 1, b = 0;
			int u_need_to_choice = 0; // 0을 그대로 유지한다면 하나도 선택 안한 경우
			
			for( int i = 0; i < N; i++) {
				if(isSelected[i]) { // 선택했다면
					s *= igd[i].s;
					b += igd[i].b;
					u_need_to_choice++;
					continue;
				}
			}
			if(u_need_to_choice != 0) {
				result = Math.abs(s - b);
				Min_diff = Min_diff > result ? result : Min_diff;
			}
		}
		
		for ( int i = cnt; i < N; i++ ) {
			isSelected[i] = true;
			subset(i + 1);
			isSelected[i+1] = false;
			subset(i + 1);
		}
	}
}
