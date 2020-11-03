/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2360 - 색종이 만들기
 * 전체 NxN크기가 같은 색으로 되어있지 않다면 
 * 4등분.
 * 1영역만 남을때까지 반복
 * 최종 남은 흰색(0), 파란색(1) 색종이의 영역 구하기
 */
public class BOJ_2360_색종이만들기 {
	private static int N, map[][], white, blue;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		StringTokenizer stt;
		int bluecnt = 0, whitecnt = 0;
		for(int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				int color = Integer.parseInt(stt.nextToken());
				if(color == 1) {
					bluecnt++;
				} else {
					whitecnt++;
				}
				map[i][j] = color;
			}
		} // input end
		
		if(bluecnt == N*N) { // 전부다 파란색
			System.out.println(0);
			System.out.println(1);
		} else if(whitecnt == N*N){ // 전부다 흰색
			System.out.println(1);
			System.out.println(0);
			
		} else {
			make(0, 0, N);
			System.out.println(white);
			System.out.println(blue);
		}
	} // main end
	
	// 개수를 세다가 다른 영역이 나오면 부분적으로 재귀호출해서 카운트를 하자.
	private static void make(int startY, int startX, int n) {
		int color = map[startY][startX]; // 처음 색 지정
		if(n == 1) { // 더 나눌 곳이 없다면 그 자체를 세자
			if(color == 0) white++;
			else blue++;
			return ;
		}
		
		boolean sameColor = true; // 이전 색이랑 같은 경우만 본다
	
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			if(!sameColor) break; // 다른색
			for(int j = 0; j < n; j++) {
				if(map[i+startY][j+startX] != color) { // 다른색
					sameColor = false;
					break ;
				}
				cnt++;
			}
		}
		
		if(cnt == n*n) { // 위에 다 체크했는데도 같은 색인 경우
			if(color == 0) white++;
			else blue++;
			return ; // 꼭 리턴 해주자 안그러면 또 4등분 한다.
		}
		
		// 위에 다 체크했더니 다른 색인 경우 4등분 해서 재호출
		int nextN = n/2;
		// 1 번
		make(startY, startX, nextN);
		// 2번
		make(startY, startX + nextN, nextN);
		// 3번
		make(startY + nextN, startX, nextN);
		// 4번
		make(startY + nextN, startX + nextN, nextN);
	}
}
