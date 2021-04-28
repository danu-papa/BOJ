/**
 * 
 */
package Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class BOJ_1946_신입사원 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			PriorityQueue<int[]> pQueue = new PriorityQueue<int[]>((o1, o2) -> (o1[0] - o2[0]));
			for(int i = 0; i < N; i++) {
				StringTokenizer stt = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(stt.nextToken());
				int sec = Integer.parseInt(stt.nextToken());
				pQueue.offer(new int[] {first, sec});
			}
			
			// 1등은 무조건 합격
			int pass = 1;
			int[] first = pQueue.poll();
			int pre_grade1 = first[0];
			int pre_grade2 = first[1];
			
			for(int i = 1; i < N; i++) { // 모든 지원자 확인
				int[] grades = pQueue.poll();
				int grade1 = grades[0];
				int grade2 = grades[1];
				
				// 이전 합격자가 지금의 점수보다 등수가 둘 다 높은 경우. 불합격
				if(pre_grade1 < grade1 && pre_grade2 < grade2) continue;
				
				// 하나라도 높은 경우. 다음 지원자에 대한 지표로 삼음
				pre_grade1 = grade1;
				pre_grade2 = grade2;
				pass++;
			}
			
			System.out.println(pass);
		}
	}
}
