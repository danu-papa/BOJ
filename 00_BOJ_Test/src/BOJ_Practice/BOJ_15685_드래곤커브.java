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
	private static List<Dragon> dragons;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		dcCnt = Integer.parseInt(br.readLine());
		map = new int[100][100];
		dragons = new ArrayList<>();
		
		StringTokenizer stt = null;
		for(int i = 0; i < dcCnt; i++) {
			stt = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(stt.nextToken());
			int y = Integer.parseInt(stt.nextToken());
			int dir = Integer.parseInt(stt.nextToken());
			int gen = Integer.parseInt(stt.nextToken());
			
			dragons.add(new Dragon(x, y, dir, gen));
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
			
		}
	}
}
