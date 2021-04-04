package BOJ_Practice;

import java.util.Arrays;
import java.util.Scanner;

public class 개똥벌레 {
	private static int N, H, up[], down[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		H = sc.nextInt();
		
		up = new int[N/2];
		down = new int[N/2];
		
		int[] counted = new int[H];
		
		int idx = 0;
		for(int i = 0; i < N; i++) {
			if(i%2 == 0)
				up[idx] = sc.nextInt();
			else { 
				down[idx++] = sc.nextInt();
			}
		} // input end
		
		Arrays.sort(down);
		Arrays.sort(up);
		
		int min = Integer.MAX_VALUE;
		
		for(int i = 1; i <= H; i++) {
			int u = binarySearch(0, N/2-1, i, up);
			int d = binarySearch(0, N/2-1, H-i+1, down);
			counted[i-1] = u + d;
			if(min > counted[i-1]) min = counted[i-1];
		}
		
		int min_cnt = 0;
		for(int i = 0; i < H; i++) {
			if(min == counted[i]) min_cnt++;
		}
		
		System.out.println(min + " " + min_cnt);
		sc.close();
	}
	
	private static int binarySearch(int l, int r, int h, int[] arr) {
		int left = l;
		int right = r;
		
		int min = Integer.MAX_VALUE;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(h <= arr[mid]) {
				min = Math.min(min, mid);
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		if(min == Integer.MAX_VALUE) return 0;
		return N / 2 - min;
	}
}
