/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 14889 - 스타트와 링크
 * N 은 짝수
 * 1부터 N까지 사람이 있음.
 * 각 사람마다 팀이되면 시너지 발생.
 * 팀의 시너지의 차이가가 최소가 되는 경우를 찾아
 * 값을 출력
 */
public class BOJ_14889_Start_Link {
	private static int N, synergy[][], resMin;
	private static boolean selected[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		synergy = new int[N][N];
		selected = new boolean[N];

		StringTokenizer stt;
		for( int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine());
			for( int j = 0; j < N; j++) {
				synergy[i][j] = Integer.parseInt(stt.nextToken());
			}
		}

		resMin = Integer.MAX_VALUE;
		findMinSynergy(0,0);
		System.out.println(resMin);
	}

	/** 시너지가 나오는 서로다른 모든 팀을 선택하자 */
	private static void findMinSynergy(int idx, int start) {
		if(idx == N) { // N개의 팀을 다 골랐으면 패스
			return;
		}
		if(idx == N/2) { // 정확히 짝수이므로 절반을 고른 상황에서 계산 시작.
			calSynergy();
			return;
		}
		for(int i = start; i < N; i++) {
			selected[i] = true;
			findMinSynergy(idx+1, i+1);
			selected[i] = false;
		}
	}

	/** 선택된 팀으로 시너지를 구해보자 */
	private static void calSynergy() {
		int first_team = 0;
		int second_team = 0;

		for( int i = 0; i < N-1; i++) {
			for( int j = i+1; j < N; j++) {
				if(selected[i] != selected[j]) continue; // 같은 팀이 아니야

				if(selected[i]) { // 같은 팀 중에서도 선택 한 팀의 합
					first_team += synergy[i][j] + synergy[j][i];
				} else { // 선택 안한 팀 계산
					second_team += synergy[i][j] + synergy[j][i];
				}
			}
		}
		// 최소값 도출
		int res = Math.abs(first_team - second_team);
		resMin = Math.min(res, resMin);
	}
}
