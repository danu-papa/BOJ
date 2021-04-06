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
public class 예산 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] money = new int[N];
		
		StringTokenizer stt = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			money[i] = Integer.parseInt(stt.nextToken());
		}
		
		int total = Integer.parseInt(br.readLine());
		
		Arrays.sort(money);
		
		int res = binarySearch(0, money[N-1], money, total, N);
		
		System.out.println(res);
	}

	private static int binarySearch(int l, int r, int[] money, int total, int size) {
		int left = l;
		int right = r;
		int res = 0;
		
		while(left <= right) {
			int mid = (left+right)/2;
			
			int sum = 0;
			for(int i = 0; i < size; i++) {
				if(money[i] <= mid) {
					sum += money[i];
				} else {
					sum += mid;
				}
			}
			
			if(sum < total) {
				left = mid+1;
				res = mid;
			} else{
				right = mid-1;
			} 
		}
		
		return res;
	}
}
