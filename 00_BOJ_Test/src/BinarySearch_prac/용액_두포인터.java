/**
 * 
 */
package BinarySearch_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class 용액_두포인터 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] input = new int[N];

		StringTokenizer stt = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(stt.nextToken());
		}
		
		Arrays.sort(input);
		
		int diff = Integer.MAX_VALUE;
		int left_el = 0;
		int right_el = 0;
		int right_idx = N-1;
		boolean flag = false;
		for(int i = 0; i < N; i++) {
			int left = input[i];
			for(int j = right_idx; j > i; j--) {
				int right = input[j];
				
				int sum = left + right;
				int cur_diff = Math.abs(sum);
				if(diff > cur_diff) {
					diff = cur_diff;
					left_el = left;
					right_el = right;
					if(diff == 0) {
						flag = true;
						break;
					}
				}
				if(sum < 0) break;
				right_idx = j;
			}
			if(flag) break;
		}
		System.out.println(left_el + " " + right_el);
	}
}
