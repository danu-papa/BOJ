/**
 * 
 */
package BinarySearch_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class BOJ_1072_게임 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		long X = Integer.parseInt(stt.nextToken());
		long Y = Integer.parseInt(stt.nextToken());
		long Z = Y*100 / X;
		
		if(Z >= 99) {
			System.out.println(-1);
			return;
		}
		
		long left = 1;
		long right = X;
		long mid = 0;
		long diff = 0;
		while(left <= right) {
			mid = (left+right)/2;
			long percent = (Y + mid) * 100 / (X + mid);
			if( percent > Z) {
				right = mid-1;
			} else {
				left = mid+1;
			}
		}
		System.out.println(left);
	}
}
