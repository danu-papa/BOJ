package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이용권 4종류
 * 1일이용권
 * 1달이용권 - 매달 1일 시작
 * 3달이용권 - 매달 1일 시작
 * 1년이용권 - 매년 1월 1일부터 시작
 * 
 * 이용계획에 나타나는 숫자 = 해당 달에 수영장 이용
 * 각 이용권의 금액 + 이용계획 입력
 * 가장 적은 비용으로 수영장 이용하는 방법
 * */
public class SWEA_1952_수영장 {
	private static final int COSTAMOUNT = 4, MONTHCNT = 12;
	private static int cost[], plan[], resCost;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer stt = new StringTokenizer(br.readLine());
			
			cost = new int[COSTAMOUNT];
			plan = new int[MONTHCNT];
			
			for(int i = 0; i < COSTAMOUNT; i++) {
				cost[i] = Integer.parseInt(stt.nextToken());
			}

			stt = new StringTokenizer(br.readLine());
			for(int i = 0; i < MONTHCNT; i++) {
				plan[i] = Integer.parseInt(stt.nextToken());
			} // input end
			
			resCost = Integer.MAX_VALUE;
			
			dfs(0, 0);
			
			System.out.println("#"+test_case+" "+resCost);
		} // test_case end
	}
	
	private static void dfs(int idx, int sum) {
		if(resCost < sum) {
			return ;
		}
		if(idx >= MONTHCNT) {
			if(resCost > sum) {
				resCost = sum;
			}
			return ;
		}
		for(int i = 0; i < COSTAMOUNT; i++) {
			if(i == 0) { // 하루치
				dfs(idx+1, sum + plan[idx]*cost[i]);
			} else if( i == 1) { // 한달치
				dfs(idx+1, sum + cost[i]);
			} else if( i == 2) { // 세달치
				dfs(idx+3, sum + cost[i]);
			} else { // 1년치
				dfs(idx+12, sum + cost[i]);
			}
		}
	}
}
