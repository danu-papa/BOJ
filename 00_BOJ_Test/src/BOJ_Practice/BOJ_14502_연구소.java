/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class BOJ_14502_연구소 {
	private static class Virus{
		int y, x;

		public Virus(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	private static int N, M, map[][], resMax;
	private static final int  BLANK = 0, WALL = 1, VIRUS = 2;
	private static boolean visited[][];
	private static Queue<Virus>queue;
	private static int dx[] = {0, 0, -1, 1};
	private static int dy[] = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());
		
		map = new int[N][M];
		queue = new LinkedList<Virus>();
		visited = new boolean[N][M];
		//int tmpMap[][] = new int[N][M];
		resMax = 0;
		
		for(int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				int next = Integer.parseInt(stt.nextToken());
				if(next == VIRUS) queue.offer(new Virus(i,j));
				map[i][j] = next;
				//tmpMap[i][j] = next;
			}
		} // input end
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == WALL || map[i][j] == VIRUS) continue;
				map[i][j] = WALL;
				selectWall(1);
				map[i][j] = BLANK;
			}
		}
		System.out.println(resMax);
	}
	
	/** dfs로 벽을 세우자. 3개 */
	private static void selectWall(int idx) {
		if(idx == 3) {
			bfs(); // 현재 벽을 이용해서 bfs 수행
			return;
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == WALL || map[i][j] == VIRUS) continue;
				map[i][j] = WALL; // 벽 세워주고
				selectWall(idx+1);
				map[i][j] = BLANK; // dfs 끝나면 다시 빈칸으로
			}
		}
	}

	/** 바이러스 퍼트리기 */
	private static void bfs() {
		visited = new boolean[N][M];
		while(!queue.isEmpty()) {
			Virus curVirus = queue.poll();
			for(int d = 0; d < 4; d++) {
				int next_y = curVirus.y + dy[d];
				int next_x = curVirus.x + dx[d];
				if(rangeCheck(next_y,next_x)) continue; // 범위 벗어났어
				if(map[next_y][next_x] != BLANK) continue; // 빈칸이 아니야
				if(visited[next_y][next_x]) continue; // 이미 갔어
				queue.offer(new Virus(next_y, next_x));
				visited[next_y][next_x] = true;
			}
		}
		
		// bfs의 수행이 끝이난 경우
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == BLANK && !visited[i][j]) cnt++; // 안전 구역
				if(map[i][j] == VIRUS) queue.offer(new Virus(i,j)); // 바이러스 재배치
			}
		}
		resMax = Math.max(cnt, resMax);
	}

	/** 범위 체크 */
	private static boolean rangeCheck(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}
}
