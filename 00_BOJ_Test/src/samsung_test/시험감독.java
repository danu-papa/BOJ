/**
 * 
 */
package samsung_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class 시험감독 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] classes = new int[N];
		
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		StringTokenizer stt2 = new StringTokenizer(br.readLine());
		
		int first = Integer.parseInt(stt2.nextToken());
		int sec = Integer.parseInt(stt2.nextToken());
		
		long answer = N;
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(stt.nextToken()) - first;
			if(num <= 0) continue;
			answer += (long)Math.ceil((double)((double)num/(double)sec));
		}
		System.out.println(answer);
	}
}
