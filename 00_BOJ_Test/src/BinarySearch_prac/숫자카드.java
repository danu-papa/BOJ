package BinarySearch_prac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자카드 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] have = new int[N];
		
		StringTokenizer stt = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			have[i] = Integer.parseInt(stt.nextToken());
		}
		
		Arrays.sort(have);
		
		int M = Integer.parseInt(br.readLine());
		
		stt = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < M; i++) {
			int target = Integer.parseInt(stt.nextToken());
			if(binarySearch(0,N-1, have, target)) {
				bw.write("1");
			} else {
				bw.write("0");
			}
			bw.write(" ");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean binarySearch(int l, int r, int[] have, int target) {
		int left = l;
		int right = r;
		
		while(left <= right) {
			int mid = (left+right)/2;
			
			if(target > have[mid]) {
				left = mid+1;
			} else if(target < have[mid]) {
				right = mid-1;
			} else {
				return true;
			}
		}
		return false;
	}
}
