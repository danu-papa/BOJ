/**
 * 
 */
package BOJ_Practice;

import java.util.Scanner;

/**
 * 백준 15961 - 회전초밥
 * 벨트위에 회전초밥이 있음. 같은 초밥이 둘 이상 있을 수있다.
 * 1. 연속으로 k개의 초밥을 연속으로 먹으면 가격을 할인해준다.
 * 2. 모든 고객에게 초밥의 종류가 쓰여진 쿠폰을 주고, 1번행사에 참가한 경우
 *    이 쿠폰에 적힌 종류의 초밥을 무료로 준다.
 * 행사에 참여하여 가능한한 많은 양의 초밥을 먹을 때 최대값을 출력
 * N : 접시수, d : 초밥의 가지수, k : 연속해서 먹는 접시 수, c : 쿠폰번호
 */
public class BOJ_15961_Rotate_Sushi {
	static int N, d, k, c, sushi[], table[], maxCnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		d = sc.nextInt();
		k = sc.nextInt();
		c = sc.nextInt();

		table = new int[N]; // 입력받는 초밥
		sushi = new int[d+1]; // 초밥 종류

		for( int i = 0; i < N; i++) {
			table[i] = sc.nextInt();
		}

		maxCnt = 0;

		start();

		System.out.println(maxCnt);
		sc.close();
	}

	/** 연속 k개를 순차적으로 먹었을 떄 가장 많은 종류를 먹을 수 있는 수 */
	private static void start() {
		int eat = 0; // 먹었을 때 카운트
	
		// 일단 k개 만큼 먹는다.
		for( int i = 0; i < k; i++ ) {
			if(sushi[table[i]]++ == 0) { // 초밥 [테이블에 있는 종류] +1
				eat++; // 먹음
			}
		}
		
		// 쿠폰 초밥 먹었는지 확인.
		if(sushi[c] == 0) eat++; // 쿠폰 종류 초밥 먹은 적 없으면 먹고+1
		maxCnt = Math.max(eat, maxCnt); // 최대치 갱신
		if(sushi[c] == 0) eat--; // 가장 앞의 초밥을 안먹은 거로 치고 
		                         // 추가추가 하며 갈 것이므로 먹은거 -1

		int idx = 0; // 가장 앞을 가리킬 인덱스
		for( int i = 1; i < N; i++ ) { // 가장 앞 바로 다음 초밥부터
			if(--sushi[table[idx]] == 0) { // 초밥[먹었던 가장 앞의 테이블위 종류] -1
				eat--; // 다시 안먹은걸로
			}
			
			// 원형처럼 연결이 되어있기 때문에 %N으로 나누어주어야한다. N을 벗어나면 가장 앞을 선택.
			if(sushi[table[(i+k-1)%N]]++ == 0) { 
	            eat++;
	         } 
			
			// 생각없이 아래처럼 짰다가 엄청엄청 틀렸다..
//			if(sushi[table[i]]++ == 0) {
//				eat++;
//			}

			if(sushi[c] == 0) eat++;
			maxCnt = Math.max(eat, maxCnt);
			if(sushi[c] == 0) eat--;
			
			idx++;
		}
	}
}
