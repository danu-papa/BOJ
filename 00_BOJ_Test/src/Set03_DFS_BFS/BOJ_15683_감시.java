/**
 * 
 */
package Set03_DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15683_감시 {
	private static class CCTV{
		int y, x, dir, type;

		public CCTV(int y, int x, int dir, int type) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.type = type;
		}
	}
	
	private static int N, M, resMin, map[][];
							// 우, 상, 좌, 하
	private static int dx[] = {1, 0, -1, 0};
	private static int dy[] = {0, -1, 0, 1};
	private static List<CCTV> cctvList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());
		cctvList = new ArrayList<>();
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				int in = Integer.parseInt(stt.nextToken());
				if(in != 0 && in < 6) { // CCTV인 경우만
					cctvList.add(new CCTV(i, j, 0, in));
				}
				map[i][j] = in;
			}
		} // input end
		
		resMin = Integer.MAX_VALUE;
		// type별로 CCTV의 방향을 정하자.
		selectCCTVdir(0, cctvList.size());
		
		System.out.println(resMin);
	}

	private static void selectCCTVdir(int idx, int listsize) {
		if(idx == listsize) {
			int tmpMap[][] = new int[N][M];
			initMap(tmpMap);
			setRoute(tmpMap);
			int cnt = findZero(tmpMap);
			
			resMin = Math.min(cnt, resMin);
			return ;
		}
		int dir = 4;
		for(int i = idx; i < listsize; i++) {
			CCTV cctv = cctvList.get(i);
			if(cctv.type == 5) {
				selectCCTVdir(i+1, listsize);
				continue;
			}
			if(cctv.type == 2) dir = 2; // 2번 CCTV는 우, 상만 있으면 된다.
			for(int d = 0; d < dir; d++) {
				// 시작 지점을 정해주자.
				//if(cctv.type==1 && rangeCheck(cctv.y + dy[d], cctv.x+dx[d])) continue;
				cctv.dir = d;
				selectCCTVdir(i+1, listsize);
			}
		}
	}

	private static int findZero(int[][] tmpMap) {
		int res = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(tmpMap[i][j] == 0) res++;
			}
		}
		return res;
	}

	/** 맵 복사 */
	private static void initMap(int[][] tmpMap) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
	}

	/** CCTV의 방향대로 -1을 채워주자 */
	private static void setRoute(int[][] tmpMap) {
		//boolean visited[][] = new boolean[N][M];
		
		// 모든 CCTV의 정보를 얻어오자
		for(int j = 0; j < cctvList.size(); j++) {
			CCTV curCCTV = cctvList.get(j);
			int next_y = curCCTV.y;
			int next_x = curCCTV.x;
			int dir = curCCTV.dir;
			int type = curCCTV.type;
			//visited[next_y][next_x] = true;
			for(int d = 0; d < 4; d++) {
				if(type == 1) { // 한 방향
					if(d != 0) continue;
				}
				else if(type == 2) {  // 선택한 방향 + 180도 뒤 방향
					if(d == 1 || d == 3) continue;
				}
				else if(type == 3) { // 선택 방향 + 90도 {
					if(d == 2 || d == 3) continue;
				}
				else if(type == 4) { // 선택방향 + 90도 + 180도
					if(d == 3) continue;
				}
				next_y += dy[(dir+d)%4];
				next_x += dx[(dir+d)%4];
				
				if(rangeCheck(next_y, next_x) || tmpMap[next_y][next_x] == 6 ) {
					next_y = curCCTV.y;
					next_x = curCCTV.x;
					continue;
				}
//					if(visited[next_y][next_x]) {
//						d--;
//						continue;
//					}
				tmpMap[next_y][next_x] = -1;
				//visited[next_y][next_x] = true;
				d--;
			}
		}
	}
	
	/** 범위 체크 */
	private static boolean rangeCheck(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}
