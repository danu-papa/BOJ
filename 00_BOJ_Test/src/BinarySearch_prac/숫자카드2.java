package BinarySearch_prac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자카드2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] have = new int[N];
		
		StringTokenizer stt = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int key = Integer.parseInt(stt.nextToken());
			have[i] = key;
		}
		
		Arrays.sort(have);
		
		int M = Integer.parseInt(br.readLine());
		
		stt = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < M; i++) {
			int target = Integer.parseInt(stt.nextToken());
			int up_res = upperBound(0,N, have, target);
			int lo_res = lowerBound(0, N, have, target);
			
			bw.write(up_res-lo_res + " ");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static int upperBound(int l, int r, int[] have, int target) {
		int left = l;
		int right = r;
		
		while (left < right) {
			int mid = (left + right) / 2;

			if (have[mid] <= target) { 
				left = mid + 1; 
			} // 상한은 같은 수를 더 작은 수로 판단
			else right = mid;
		}

		return left;
	}
	
	private static int lowerBound(int l, int r, int[] have, int target) {
		int left = l;
		int right = r;
		
		while (left < right) {
			int mid = (left + right) / 2;

			if (have[mid] < target) { 
				left = mid + 1; 
			} // 상한은 같은 수를 더 작은 수로 판단
			else right = mid;
		}

		return right;
	}
}

