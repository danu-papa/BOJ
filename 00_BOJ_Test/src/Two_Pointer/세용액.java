/**
 * 
 */
package Two_Pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class 세용액 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long[] solutions = new long[N];
		
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int next = Integer.parseInt(stt.nextToken());
			solutions[i] = next;
		}
		
		Arrays.sort(solutions);
		
		long[] res = new long[3];
		
		long diff = Long.MAX_VALUE;

		for(int i = 0; i < N; i++) {
			int left = i+1;
			int right = N-1;
			
			while(left < right) {
				long sum = solutions[i] + solutions[left] + solutions[right];
				
				long cur_diff = Math.abs(sum);
				
				if(cur_diff < diff) {
					diff = cur_diff;
					res[0] = solutions[i];
					res[1] = solutions[left];
					res[2] = solutions[right];
				}
				
				if(sum > 0) {
					right--;
				} else {
					left++;
				}
			}
		}
		System.out.println(res[0] + " " + res[1] + " " + res[2]);
	}
}
