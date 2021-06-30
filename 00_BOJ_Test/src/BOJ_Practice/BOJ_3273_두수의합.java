/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3273_두수의합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer stt = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) { // N개 입력
			arr[i] = Integer.parseInt(stt.nextToken());
		}

		Arrays.sort(arr); // 오름차순 정렬

		int target = Integer.parseInt(br.readLine()); // 목표 숫자
		
		int idx = N; // 다음 숫자의 마지막 위치
		int count = 0; // 정답 수
		for (int i = 0; i < N; i++) {
			int cur = arr[i]; // 현재 탐색할 숫자
			for(int j = idx-1; j > i; j--) { // 마지막 위치부터 탐색 시작
				int back_num = arr[j];
				int sum = cur + back_num;
				if(sum < target) { // 합이 목표보다 작다면 멈춤
					idx = j+1; // 현재 위치에서부터 재탐색
					break;
				} else if(sum > target) { // 합이 더 크면 다음 위치로
					continue;
				} else { // 목표된 값이 있다
					count++; // 정답 증가
					idx = j; // 위치 변경
					break;
				}
			}
		}
		System.out.println(count);
	}
}
