package BinarySearch_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class 수_찾기_silver4 {
	private static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		int[] origin = new int[N];
		
		StringTokenizer stt = new StringTokenizer(br.readLine());
		for(int i = 0; i < N ; i++) {
			origin[i] = Integer.parseInt(stt.nextToken());
		}
		
		Arrays.sort(origin);
		
		M = Integer.parseInt(br.readLine());
		
		stt = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < M; i++) {
			int target = Integer.parseInt(stt.nextToken());
			
			if(binarySearch(0, N-1, target, origin)) {
				System.out.println("1");
			} else {
				System.out.println("0");
			}
		}
	}
	
	private static boolean binarySearch(int l, int r, int target, int[] arr) {
		int left = l;
		int right = r;
		
		while(left <= right) {
			int mid = ( left + right ) / 2;
			
			if(target < arr[mid]) {
				right = mid - 1;
			} else if(target > arr[mid]) {
				left = mid + 1;
			} else {
				return true;
			}
		} 
		
		return false;
	}
}
