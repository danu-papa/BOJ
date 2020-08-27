/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1987 알파벳
 * 세로 R칸, 가로 C칸의 표 모양의 보드가 있다. 
 * 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀있다.
 * 1행1열에는 말이 놓여있다. 
 * 상하좌우중 한칸으로 이동이 가능한데 알파벳이 적힌 칸을 두번 지날수는 없다.
 * 좌측상단부터 시작해서 최대 몇칸을 갈 수 있는지 구하라.
 * DFS를 이용해서 구현
 */
public class BOJ_1987_Alphabet {
	private static int R, C, totalCnt;
	private static char[][] map;
	private static int dx[] = {0, 0, -1, 1}; // 델타 , 상하좌우
	private static int dy[] = {-1, 1, 0, 0};
	private static int selected_Alpha[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());

		R = Integer.parseInt(stt.nextToken());
		C = Integer.parseInt(stt.nextToken());
		map = new char[R][];
		selected_Alpha = new int[26]; // 선택한 알파벳들 저장. 알파벳은 26자
		totalCnt = 1; // 자기 자신도 카운트하므로 1부터

		for( int i = 0; i < R; i++) {
			stt = new StringTokenizer(br.readLine());
			map[i] = stt.nextToken().toCharArray();
		}

		Search(0,0,1);
		System.out.println(totalCnt);
	}

	/** */
	private static void Search(int y, int x, int cnt) {
		if(cnt == 26) {
			totalCnt = cnt;
			return;
		}
		selected_Alpha[map[y][x] - 'A'] = 1; // 해당 문자 저장

		Move:
			// 4방향 이동 탐색
			for( int k = 0; k < 4; k++) {
				int next_x = x + dx[k];
				int next_y = y + dy[k];

				if( next_x < 0 || next_x >= C || next_y < 0 || next_y >= R) { // 범위넘으면 다음으로
					continue;
				}
				char tmp = map[next_y][next_x]; // 가려는 곳의 문자가
				for(int i = 0; i < selected_Alpha.length; i++) {
					// 이미 선택한 적이 있는 문자라면 다음으로 
					if(selected_Alpha[tmp-'A'] == 1) continue Move;
				}
				// 선택한적없고 간적도 없으면 카운트+1해서 DFS
				Search(next_y, next_x, cnt+1);
			}
		// 더이상 갈 곳이 없다. 즉 끝에 다다른 경우 해당 문자를 저장리스트에서 제거
		selected_Alpha[map[y][x] - 'A'] = 0; // 해당 문자 저장
		
		// 막다른곳에 다다른경우 카운트 비교
		totalCnt = totalCnt > cnt ? totalCnt : cnt;
	}
}	
