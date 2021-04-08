/**
 * 
 */
package Two_Pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class 두용액 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] solutions = new int[N];

		StringTokenizer stt = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int next = Integer.parseInt(stt.nextToken());
			solutions[i] = next;
		}

		Arrays.sort(solutions); // 오름차순 정렬

		int left = 0; // 가장 왼쪽
		int right = N - 1; // 가장 오른쪽
		int diff = Integer.MAX_VALUE;
		int left_el = 0;
		int right_el = 0;

		while (left < right) {
			// 두 용액의 합
			int sum = solutions[left] + solutions[right];

			// 차이
			int cur_diff = Math.abs(sum);

			if (cur_diff < diff) { // 차이가 더 작은 경우
				// 값 저장
				diff = cur_diff;
				left_el = solutions[left];
				right_el = solutions[right];
				if (diff == 0)
					break;
			}

			if (sum > 0) { // 0 보다 크면 오른쪽 요소 한칸 앞으로 이동
				right--;
			} else { // 0보다 작으면 왼쪽 요소 한칸 앞으로 이동
				left++;
			}
		}
		System.out.println(left_el + " " + right_el);
	}
}
