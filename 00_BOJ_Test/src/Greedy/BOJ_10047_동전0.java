/**
 * 
 */
package Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class BOJ_10047_동전0 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stt.nextToken());
		int K = Integer.parseInt(stt.nextToken());
		
		int[] moneys = new int[N];
		
		for(int i = 0; i < N; i++) {
			moneys[i] = Integer.parseInt(br.readLine());
		} // 오름차순으로 주어진다.
		
		int moneyCnt = 0;
		for(int i = N-1; i >= 0; i--) {
			int cur_money = moneys[i];
			if(cur_money > K) continue;
			int q = K / cur_money;
			moneyCnt += q;
			K -= q*cur_money;
		}
		
		System.out.println(moneyCnt);
		
	}
}
