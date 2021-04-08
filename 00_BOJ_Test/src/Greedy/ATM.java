/**
 * 
 */
package Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ATM {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] peoples = new int[N];
		
		StringTokenizer stt = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			peoples[i] = Integer.parseInt(stt.nextToken());
		}
		
		Arrays.sort(peoples);
		
		int[] times = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			times[i] += times[i-1] + peoples[i-1];
		}
		
		int sum = 0;
		for(int i = 0; i <= N; i++) {
			sum += times[i];
		}
		System.out.println(sum);
	}
}
