/**
 * 
 */
package Set02_Back_Tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import sun.util.locale.StringTokenIterator;

/**
 * 백준 16987 - 계란으로 계란치기
 * 기본적인 BackTracking
 * 1. 가장 왼쪽 계란을 든다
 * 2. 깨지지않은 다른 계란을 친다.
 *    손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 넘어간다.
 *    각 계란의 내구도는 상대 계란의 무게만큼 깎인다.
 *    내구도가 0보다 작아지는 순간 깨짐.
 * 3. 가장 최근에 든 계란의 오른쪽 계란을 들고 2번 수행
 * 4. 반복
 * 최대 몇개의 계란을 깰 수 있는지 확인하자.
 */
public class BOJ_16987_계란으로_계란치기 {
	private static class Egg{
		int s, w;
		
		public Egg(int s, int w) {
			this.s = s;
			this.w = w;
		}
	}
	private static int N, totalCnt;
	private static Egg[] egglist;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		egglist = new Egg[N];
		
		StringTokenizer stt;
		for(int i = 0; i < N; i++) {
			 stt = new StringTokenizer(br.readLine());
			 int s = Integer.parseInt(stt.nextToken());
			 int w = Integer.parseInt(stt.nextToken());
			 egglist[i] = new Egg(s,w);
		} // input end
		
		totalCnt = 0;
		start(0, 0);
		
		System.out.println(totalCnt);
	}
	
	// idx = 현재 계란, cnt = 깨진수
	private static void start(int idx, int cnt) {
		// 모두 깨져도 N개.
		if(idx == N) {
			totalCnt = Math.max(totalCnt, cnt);
			return ;
		}
		// 현재 계란이 깨졌다. 다음계란으로 넘어가자
		// 혹은 끝까지 다 쳤는데 모두 안깨진 경우 다음계란으로 넘어가자
		if(egglist[idx].s <= 0 || cnt == N-1){
			start(idx+1, cnt);
			return;
		}
		int nCnt = cnt;
		
		for(int i = 0; i < N; i++) {
			// 자기 자신은 칠 수 없음
			if(i == idx) continue;
			
			// 자신이 깨졌다
			if(egglist[i].s <= 0) continue;
			
			// 서로 치기 시작
			egglist[i].s -= egglist[idx].w;
			egglist[idx].s -= egglist[i].w;
			
			// 깨진 계란을 세자
			if(egglist[idx].s <= 0) {
				nCnt++;
			}
			// i번째 계란이 깨졌다
			if(egglist[i].s <= 0) {
				nCnt++;
			}
			start(idx+1,nCnt);
			
			// 백트래킹
			nCnt = cnt;
			egglist[i].s += egglist[idx].w;
			egglist[idx].s += egglist[i].w;
		}
	}
}
