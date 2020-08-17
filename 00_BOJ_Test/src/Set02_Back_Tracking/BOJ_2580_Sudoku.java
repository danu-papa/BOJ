/**
 * 
 */
package Set02_Back_Tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 2580번 - 스도쿠 채우기
 * 주어진 행렬에서 모든 칸을 맞게 넣은 뒤 출력하기.
 * 빈 칸인 경우 봐야할 것. 
 * 가로줄에서 자신이 들어갈 숫자 찾기
 * 새로줄에서 자신이 들어갈 숫자 찾기
 * 자신이 있는 작은 9칸에서 들어갈 숫자 찾기
 * 3가지 검사를 마치고 나온 숫자가 존재한다면 해당 숫자를 가지고
 * DFS수행, BackTracking
 */
public class BOJ_2580_Sudoku {
	static int[][] sudoku; // 스도쿠 정보를 입력할 2차원 배열
	static List<ZeroState> zlist = new ArrayList<>(); // 스도쿠의 정보를 입력 받을 때 빈칸의 정보를 담을 리스트

	// 스도쿠 판에서 비어있는 칸의 위치 정보를 저장하는 클래스
	static class ZeroState{
		int y, x;
		public ZeroState(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws Exception {
		// 속도를 높히기 위해서 BufferedReader와 StringTokenizer 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt;

		sudoku = new int[9][9];

		for( int i = 0; i < 9; i++) {
			stt = new StringTokenizer(br.readLine());
			for( int j = 0; j < 9; j++) {
				int tmp = Integer.parseInt(stt.nextToken());
				if(tmp == 0) {
					zlist.add(new ZeroState(i, j)); // 주어진 값이 비어있는 칸이라면 리스트에 저장
				}
				sudoku[i][j] = tmp;
			}
		} // 주어진 스도쿠의 값을 입력

		solve_sudoku(0); // 스도쿠 채우기
	}

	/** 스도쿠를 해결하자!!
	 * idx는 빈칸의 위치 정보를 찾기 위한 값. ( 몇번째 빈칸인가? 생각하는 것이 편할 듯하다.)
	 * */
	private static void solve_sudoku(int idx) {
		// 모든 빈 칸에 대해서 처리를 다 한 경우
		if( idx == zlist.size()) { // DFS의 탈출 조건  --> 모든 빈칸을 수행한 경우
			for(int i = 0; i <9; i++) {
				for( int j = 0; j < 9; j++) {
					// 모든 경우가 다 차있으므로 출력
					System.out.print(sudoku[i][j] + " ");
				}
				System.out.println();
			}
			// 중복으로 채워지는 경우 방지. 단 하나의 솔루션만을 출력
			System.exit(0);
		}

		// 1 ~ 9까지 숫자에 대해서 검사
		for( int i = 1; i < 10; i++) {
			// 행, 열, 3x3
			if(checkRow(i,idx) && checkColumns(i,idx) && checkOwnSmallBox(i,idx)) {
				// 3 조건을 봤는데 중복되는 숫자가 하나도 없는 경우. 해당 빈칸에 현재 i값을 넣고 다음 빈칸으로 넘긴다.
				sudoku[zlist.get(idx).y][zlist.get(idx).x] = i;
				// 다음 빈칸으로 이동
				solve_sudoku(idx+1);
				// 해당 빈칸에 쓰여진 숫자를 지우고 다시 빈칸으로 되돌린다. 이 코드가 없으면 이전으로 돌아가도 스도쿠 판에는 숫자가 존재한다.
				// 즉, 중복 숫자가 존재 할 수 있게 된다.
				sudoku[zlist.get(idx).y][zlist.get(idx).x] = 0;
			}
		}
	}

	/** 자신이 속한 3x3의 박스안의 숫자 검사
	 * 	현재 자신의 위치 /3 *3을 수행하면 시작지점이 어디인지 쉽게 알 수 있다.
	 * */
	private static boolean checkOwnSmallBox(int cur_num, int idx) {
		int start_x = (int)(zlist.get(idx).x/3) *3;
		int start_y = (int)(zlist.get(idx).y/3) *3;
		
		for( int i = start_y; i < start_y + 3; i++) {
			for( int j = start_x; j < start_x + 3; j++) {
				// 같은 숫자 있으면 다음 요소 보기
				if(sudoku[i][j] == cur_num) return false;
			}
		}
		return true;
	}

	/** 열 검사
	 * y축 고정, x축만 이동*/
	private static boolean checkColumns(int cur_num, int idx) {
		int cur_x = zlist.get(idx).x;

		// 현재 열에서 행의 정보를 본다. 
		for( int j = 0; j < 9; j++) {
			// 같은 숫자 있으면 다음 요소 보기
			if(sudoku[j][cur_x] == cur_num) {
				return false;
			}
		}
		return true;
	}

	/** 행 검사
	 * x축 고정, y축만 이동*/
	private static boolean checkRow(int cur_num, int idx) {
		int cur_y = zlist.get(idx).y;

		// 현재 열에서 행의 정보를 본다. 
		for( int j = 0; j < 9; j++) {
			// 같은 숫자 있으면 다음 요소 보기
			if(sudoku[cur_y][j] == cur_num) {
				return false;
			}
		}
		return true;
	}
}