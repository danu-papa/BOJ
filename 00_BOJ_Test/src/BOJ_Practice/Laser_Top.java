/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 2020_07_30_알고리즘3일차 과제
 * 백준 사이트 2493번
 * N개의 탑을 왼->오 방향으로 세움
 * 6 9 5 7 4 의 탑이라고 하면
 * 6과 9가 보내는 신호를 받는 탑은 없고
 * 5,7은 9가 4는 7이 받는다
 * 즉 0 0 2 2 4 '번째' 탑이 받는다!
 * 각 탑이 보낸 신호를 받는 탑을 구하라
 */
public class Laser_Top {
	private static int N;
	public static void main(String[] args) throws Exception, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer stt = new StringTokenizer(br.readLine());
		Stack<int[]> tower = new Stack<>();
		
		for ( int i = 1; i < N; i++) {
			int cur_in = Integer.parseInt(stt.nextToken());
			while(!tower.isEmpty()) {
				if(tower.peek()[1] >= cur_in) {
					System.out.print(tower.peek()[0] + " ");
					break;
				}
				tower.pop();
			}
			if(tower.isEmpty()) {
				System.out.print("0 ");
			}
			tower.push(new int[] {i,cur_in});
		}
		
	}
}