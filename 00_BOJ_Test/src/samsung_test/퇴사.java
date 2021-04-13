/**
 * 
 */
package samsung_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class 퇴사 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] pay = new int[N + 2];
		int[] time = new int[N + 2];

		StringTokenizer stt;
		for (int i = 1; i <= N; i++) {
			stt = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(stt.nextToken());
			pay[i] = Integer.parseInt(stt.nextToken());
		}

		int answer = 0;
		for (int i = N; i >= 1; i--) {
			if (i + time[i] > N + 1) {
				pay[i] = pay[i + 1];
			} else {
				pay[i] = Math.max(pay[i + 1], pay[i] + pay[i + time[i]]);
				answer = answer > pay[i] ? answer : pay[i];
			}
		}
		System.out.println(answer);
	}
}
