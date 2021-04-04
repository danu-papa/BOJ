package BinarySearch_prac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class 공유기설치 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] st = br.readLine().split(" ");
		int N = Integer.parseInt(st[0]);
		int C = Integer.parseInt(st[1]);
		
		int[] wifi = new int[N];
		
		for(int i = 0; i < N; i++) {
			wifi[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(wifi);
		
		int res = binarySearch(1, wifi[N-1]-wifi[0], wifi, N, C);
		
		System.out.println(res);
	}

	private static int binarySearch(int l, int r, int[] wifi, int n, int c) {
		int left = l; // 최소거리 1
		int right = r; // 최대거리 가장 먼 곳 - 시작 지점
		int d = 0; // 거리
		int res = 0; // 최대 간격
		
		while(left <= right) {
			int mid = (left+right)/2; // 기준 간격
			int start = wifi[0]; // 시작 위치
			int cnt = 1; // 시작 위치에 설치
			
			for(int i = 1; i < n; i++) {
				d = wifi[i] - start;
				
				if(mid <= d) { // 집 사이 거리가 기준보다 큰 경우 설치
					cnt++;
					start = wifi[i]; // 다음 집 위치 변경
				}
			}
			
			if(cnt >= c) { // 설치 된 공유기의 수가 원하는 수보다 많은 경우
				res = mid; // 간격 저장. 여기로 온다는 것은 간격이 계속 늘어나기 때문에 비교 필요 x
				left = mid+1; // 공유기의 수를 줄여야 한다. 간격을 늘린다
			} else {
				right = mid-1; // 공유기의 수를 늘려야한다 간격을 더 좁혀야한다.
			}
		}
		
		return res;
	}
}
