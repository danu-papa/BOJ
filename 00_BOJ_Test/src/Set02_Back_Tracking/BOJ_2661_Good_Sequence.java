/**
 * 
 */
package Set02_Back_Tracking;

import java.util.Scanner;

/**
 * 백준 2661 - 좋은 수열
 * 1, 2, 3으로만 이루어지는 수열이 있음.
 * 인접 숫자가 하나라도 자신과 같으면 나쁜수열.
 * 같은 숫자가 연속죄어 나오는 경우도 나쁜 수열. 
 * ex) 123123213 -> 123123 연속.
 * 좋은 수열중에 가장 짧은 길이의 수열을 구하자.
 *
 */
public class BOJ_2661_Good_Sequence {
	private static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		StringBuilder sb = new StringBuilder("1");
		findSequence(1, sb);
	}
	
	/** 
	 * 수열을 찾아보자! 
	 * DFS, 백트래킹
	 * */
	private static void findSequence(int cnt, StringBuilder sb) {
		if(cnt == N) { // 1, 2, 3을 순차적으로 돌기 때문에. 제일 먼저 도착한 수가 가장 작은 수
			System.out.println(sb.toString());
			System.exit(0);
		}
		for( int i = 1; i <= 3; i++) {
			sb.append(i); // 일단 문자 추가.
			if(checkSequence(sb)) { // 유망하면 다음으로
				findSequence(cnt+1, sb);
			}
			// 유망하지 않다면 제거
			sb.deleteCharAt(sb.length()-1);
		}
	}

	/** 앞의 수열에 더했을 때 유망한지 체크 */
	private static boolean checkSequence(StringBuilder sb) {
		int len = sb.length(); // 현재까지 입력된 문자열 길이
		int half = len/2; // 문자열의 절반.
		int start = len - 1; // 시작점.
		int end = len; // 문자열 끝
		
		// substring(start,end)
		// start에 문자는 포함, end의 문자는 비포함.
		for( int i = 1; i <= half; i++) { // i는 자리수를 뜻한다.
			if(sb.substring(start - i, end - i).equals(sb.substring(start, end))) {
				return false; // 해당하면 false
			}
			start -= 1; // 자리수 옮김.
		}
		return true; // 해당없이 왔다면 유망.
	}
}
