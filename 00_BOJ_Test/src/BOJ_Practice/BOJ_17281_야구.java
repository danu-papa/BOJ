/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 야구
 * 선수들의 득점은 정해져있다. 1번 선수가 4번타자.
 * 순서를 정해서 최대값을 구해보자.
 */
public class BOJ_17281_야구 {
	private static int N, hits[][], entry[], resMax;
	private static final int ONE = 1, TWO = 2, THREE = 3, HOMERUN = 4, OUT = 0;
	private static boolean  selected[], base[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		hits = new int[N+1][10];
		entry = new int[10];
		selected = new boolean[10];
		
		StringTokenizer stt;
		for(int n = 1; n <= N; n++) { // 전체 이닝 만큼
			stt = new StringTokenizer(br.readLine(), " ");
			for(int i = 1; i <= 9; i++) {
				hits[n][i] = Integer.parseInt(stt.nextToken());
			}
		}// input end
		entry[4] = 1; // 4번타자는 1번
		selected[1] = true;
		permutation(1);
		System.out.println(resMax);
	}
	
	/** 선수들의 순서를 정하자 . 순서만 정해서 계산만 하면 되는데?! 순서를..... 
	 * 일반 순열*/
	private static void permutation(int idx) {
		if(idx == 10) { // entry 순서를 정했어요
			calcScore(); // 해당 순서로 최대값을 구해봅시다
			return;
		}
		if(idx == 4) permutation(idx+1); // 4번은 1번으로 정해져있어.
		else {
			for(int j = 2; j <= 9; j++) {
				if(selected[j]) continue;
				selected[j] = true;
				entry[idx] = j;
				permutation(idx+1);
				selected[j] = false;
			}
		}
	}

	/** 정해진 순서를 기준으로 각 이닝의 점수를 구하자 */
	private static void calcScore() {
		int outCnt = 0;
		int no = 1; // 1번 엔트리 부터 시작
		int score = 0;
		boolean stop = false;
		for(int i = 1; i <= N; i++) { // 이닝
			stop = false;
			base = new boolean[4]; // 새 이닝이 시작되면 base를 비워주자
			while(!stop) { // 선수 번호. outCnt가 3이 될 떄까지 반복
				switch(hits[i][entry[no]]) {
				case OUT:
					if(++outCnt == 3) { // out 3개 쌓이면
						outCnt = 0; // out 초기화
						stop = true; // 멈추기
					}
					break;
				case ONE:
					score += go(1);
					break;
				case TWO:
					score += go(2);
					break;
				case THREE:
					score += go(3);
					break;
				case HOMERUN:
					score += go(4);
					break;
				default:
					break;
				}
				no = ++no == 10 ? 1 : no; // 9번타자 이후로는 다시 1번타자로 돌리자
			}
		} // 이닝
		// 여기 오면 이닝 끝. 최대값 갱신
		resMax = Math.max(score, resMax);
	}

	/** 타자가 몇루타를 쳤는지 */
	private static int go(int hit) {
		int res = 0;
		for(int run = 3; run >= 1; run--) { // 홈에 가까운 곳부터 시작하자
			if(base[run]) { // 현재 base에 사람이 있어
				if(run + hit >= 4) { // 홈 밟는 경우
					base[run] = false; // 출발지 비워주기
					res++;
				} else {
					base[run] = false; // 현재 위치 떠남
					base[run+hit] = true; // 도착할 루에 도착 표시
				}
			}
		}
		if(hit == 4) res++; // 홈런쳤으면 자기자신 점수 + 1
		else base[hit] = true; // 홈런이 아니면 진루
		return res;
	}
}
