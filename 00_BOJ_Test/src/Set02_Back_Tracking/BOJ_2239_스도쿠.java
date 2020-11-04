package Set02_Back_Tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * BOJ 2239 스도쿠
 * 백트래킹
 * 빈칸을 채우자.
 * 사전식으로 했을때 가장 작은 수를 출력하기
 * */
public class BOJ_2239_스도쿠 {
	private static class Zero{
		int y, x;

		public Zero(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	private static int map[][];
	private static List<Zero> zlist;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char tmp[] = new char[9];
		map = new int[9][9];
		zlist = new ArrayList<>();
		
		for (int i = 0; i < 9; i++) {
			tmp = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				int num = tmp[j] - '0';
				// 0인경우 위치 파악
				if(num == 0) zlist.add(new Zero(i,j));
				map[i][j] = num;
			}
		}
		
		solve(0);
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	// idx == 현재 0인 영역을 알려주는 지표
	private static boolean solve(int idx) {
		if(idx == zlist.size()) {
			return true;
		}
		
		int y = zlist.get(idx).y;
		int x = zlist.get(idx).x;
		for(int i = 1; i < 10; i++) { // 1부터 9까지 모든 숫자
			// 3x3범위 체크, 행체크, 열체크
			if(innerBoxCheck(i,idx)&&rowCheck(i,x)&&colCheck(i,y)) {
				map[y][x] = i;
				if(solve(idx+1)) return true;
				map[y][x] = 0;
			}
		}
		return false;
	}

	// 행 체크
	private static boolean rowCheck(int curNum, int y) {
		for(int i = 0; i < 9; i++) {
			if(map[y][i] == curNum) return false;
		}
		return true;
	}

	// 열 체크
	private static boolean colCheck(int curNum, int x) {
		for(int i = 0; i < 9; i++) {
			if(map[i][x] == curNum) return false;
		}
		return true;
	}
	
	// 3x3 내부 박스 체크
	private static boolean innerBoxCheck(int curNum, int idx) {
		// 현재 숫자가 있는 영역의 시작 좌표
		int y = (int)zlist.get(idx).y/3 * 3;
		int x = (int)zlist.get(idx).x/3 * 3;
		
		for(int i = y; i < y+3; i++) {
			for (int j = x; j < x+3; j++) {
				if(map[i][j] == curNum) return false;
			}
		}
		return true;
	}
}
