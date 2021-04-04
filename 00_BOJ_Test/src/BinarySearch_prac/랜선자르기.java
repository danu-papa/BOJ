package BinarySearch_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 랜선자르기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stt = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(stt.nextToken());
		int N = Integer.parseInt(stt.nextToken());
		
		int[] lan = new int[K];
		int max = Integer.MIN_VALUE;
		
		for(int i = 0; i < K; i++) {
			int next = Integer.parseInt(br.readLine());
			lan[i] = next;
			max = max > next ? max : next;
		}
		
		Arrays.sort(lan);
		
		long res = binarySearch(0, max, lan, lan.length, N);
		
		System.out.println(res);
	}

	private static long binarySearch(int l, int r, int[] lan, int size, int n) {
		long left = l;
		long right = r;
		long res = 0;
		
		while(left <= right) {
			long mid = (left+right)/2; // 현재 나눌 길이
			if(mid == 0) break;
			long cnt = 0;
			
			for(int i = 0; i < size; i++) {
				cnt += lan[i]/mid;
			}
			
			if(cnt >= n) {
				res = res > mid ? res : mid;
				left = mid+1; // 더 크게 해서 나누어 보자
			} else {
				right = mid - 1; // 더 작게 나누어야 한다
			}
		}
		return res;
	}
}
