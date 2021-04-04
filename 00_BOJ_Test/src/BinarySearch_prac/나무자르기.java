package BinarySearch_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 나무자르기 {
	static int parseInt(String in) {return Integer.parseInt(in);}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		int N = parseInt(stt.nextToken());
		int M = parseInt(stt.nextToken());
		
		int[] trees = new int[N];
		
		int max = Integer.MIN_VALUE;
		
		stt = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int next = parseInt(stt.nextToken());
			trees[i] = next;
			max = max > next ? max : next;
		}
		
		Arrays.sort(trees);
		
		// 바닥부터 최대 높이까지 탐색
		long res = binarySearch(0, max, M, trees, N);
		
		System.out.println(res);
	}
	
	private static long binarySearch(int l, int r, int m, int[] trees, int n) {
		long left = l;
		long right = r;
		long res = 0;
		
		while(left <= right) {
			long mid = (left+right)/2; // 중간값은 자를 높이
			long total = 0;
			
			for(int i = 0; i < n; i++) {
				if(mid < trees[i]) { // 자를높이보다 큰 나무는 다 자름
					total += trees[i] - mid; // 전체 자르고 난 누적 합
				}
			}
			
			if(total >= m) { // 자른 나무가 원하는 나무보다 크거나 같은 경우
				res = res < mid ? res : mid; 
				left = mid+1;
			} else {
				right = mid-1;
			}
		}
		return res;
	}
}
