package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 15685 - 드래곤 커브
 * 세가지 속성으로 이루어져있음
 * 1. 시작점
 * 2. 시작 방향
 * 3. 세대
 * 
 * 0세대 : 시작위치에서 시작방향으로 1칸 이동
 * 1세대 : 0세대의 도형의 끝 점을 기준으로 시계방향 90도 회전
 * 2세대 : 1세대 도형의 끝 점을 기준으로 시계방향으로 90도 회전
 * 			    .....
 * 규칙이 있다!!
 * 규칙을 잘 찾아야한다!!
 * */
public class BOJ_15685_드래곤커브 {
	private static class Dragon{
		int x, y, dir, generation;

		public Dragon(int y, int x, int dir, int generation) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.generation = generation;
		}
	}
	private static int dcCnt, map[][], rectCnt;
	// 우 상 좌 하
	private static int dx[] = {1,0,-1,0};
	private static int dy[] = {0,-1,0,1};
	private static int cx[] = {1, 1, 0};
	private static int cy[] = {0, 1, 1};
	private static List<Dragon> dragons;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		dcCnt = Integer.parseInt(br.readLine());
		map = new int[101][101];
		dragons = new ArrayList<>();
		
		StringTokenizer stt = null;
		for(int i = 0; i < dcCnt; i++) {
			stt = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(stt.nextToken());
			int y = Integer.parseInt(stt.nextToken());
			int dir = Integer.parseInt(stt.nextToken());
			int gen = Integer.parseInt(stt.nextToken());
			
			dragons.add(new Dragon(y, x, dir, gen));
		} // input end
		
		rectCnt = 0;
		process();
		
		System.out.println(rectCnt);
		
	}
	
	// 각 드래곤들 회전하자
	private static void process() {
		// 모든 드래곤 회전
		for(int i = 0; i < dcCnt; i++) {
			Dragon dragon = dragons.get(i);
			int N = (int) Math.pow(2, dragon.generation);
			int[] moveDir = new int[N];
			
			// 가장 마지막 세대까지의 움직임을 저장
			for(int g = 0; g <= dragon.generation; g++) {
				int count = (int)Math.pow(2, g); // 현재 세대의 최대 움직임
				moveDir[0] = dragon.dir; // 최초 방향
				if(count==1) continue;
				int idx = 1;
				for(int c = count/2; c < count; c++) {
					moveDir[c] = (moveDir[c-idx]+1)%4;
					idx += 2;
				}
			}
			
			// 지도 반영
			int y = dragon.y;
			int x = dragon.x;
			map[y][x] = i+1;
			for(int m = 0; m < N; m++) {
				map[y+dy[moveDir[m]]][x+dx[moveDir[m]]] = i+1;
				y = y+dy[moveDir[m]];
				x = x+dx[moveDir[m]];
			}
		}
		
		// 개수 세기
		for(int i = 0; i < 101; i++) {
			for(int j = 0; j < 101; j++) {
				if(map[i][j] == 0) continue;
				int cnt = 1;
				for(int d = 0; d < 3; d++) {
					int next_y = i + cy[d];
					int next_x = j + cx[d];
					if(rangeCheck(next_y, next_x)) continue;
					if(map[next_y][next_x] == 0) continue;
					cnt++;
				}
				if(cnt == 4) rectCnt++;
			}
		}
	}
	private static boolean rangeCheck(int y, int x) {
		if(y < 0 || y >= 101 || x < 0 || x >= 101) return true;
		return false;
	}
}
