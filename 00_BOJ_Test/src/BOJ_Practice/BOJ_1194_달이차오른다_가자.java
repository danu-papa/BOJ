package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1194 달이 차오른다 가자
 * 빈곳 : .
 * 벽 : # (절대 이동 불가)
 * 열쇠 : a-f ( 언제나 이동 가능. 처음들어가면 열쇠 획득, 열쇠는 여러번 사용 가능)
 *   - 같은 타입의 열쇠는 여러개가 있을 수 있다. 열쇠가 없는 경우도 있음.
 * 문 : A-F (열쇠가 있을때만 이동 가능)
 *   - 같은 타입의 문도 여러개
 * 현재위치 : 0
 * 출구 : 1 (적어도 한개. 여러개 있을수도 있다)
 * 탈출하는데 최소 이동횟수
 * */
public class BOJ_1194_달이차오른다_가자 {
	private static class Minsik{
		int y, x, mCnt;
		// a b c d e f // 1 2 3 4 5 6
		int key;
		
		public Minsik(int y, int x, int mCnt, int key) {
			this.y = y;
			this.x = x;
			this.mCnt = mCnt;
			this.key = key;
		}
	}
	private static int N, M, resMin;
	private static final int KEY = (1<<6);
	private static char map[][];
	private static boolean visited[][][];
	private static int dx[] = {0, 0, -1, 1};
	private static int dy[] = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());
		map = new char[N][];
		visited = new boolean[N][M][KEY];

		int startY = 0, startX = 0;
		boolean find = false;
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			if(!find) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == '0') {
						startY = i;
						startX = j;
						find = true;
					}
				}
			}
		} // input end
		
		bfs(startY, startX);
		
		System.out.println(resMin);
	}

	private static void bfs(int startY, int startX) {
		Queue<Minsik> queue = new LinkedList<Minsik>();
		queue.offer(new Minsik(startY, startX, 0, 0)); // 아무 키도 가지고 있지 않은 상태로 저장
		visited[startY][startX][0] = true;
		
		while(!queue.isEmpty()) {
			Minsik minsik = queue.poll();
			
			if(map[minsik.y][minsik.x] == '1') {
				resMin = minsik.mCnt;
				return;
			}
			
			for(int d = 0; d < 4; d++) {
				int next_y = minsik.y + dy[d];
				int next_x = minsik.x + dx[d];
				int newkey = minsik.key;
				
				if(rangeCheck(next_y, next_x)) continue;
				if(map[next_y][next_x] == '#') continue;
				
				// 도착한 곳이 문, 열쇠비교
				if('A' <= map[next_y][next_x] && map[next_y][next_x] <= 'F') {
					int key = map[next_y][next_x]-'A';
					if((newkey & (1 << key)) == 0 ) {
						continue;
					}
				}
				
				// 도착한 곳이 열쇠
				if('a' <= map[next_y][next_x] && map[next_y][next_x] <= 'f') {
					int key = map[next_y][next_x]-'a';
					newkey = minsik.key | (1 << key);
				}
				
				if(visited[next_y][next_x][newkey]) continue; // 방문체크시 현재 열쇠와 비교 필요
				
				// 빈방
				queue.offer(new Minsik(next_y, next_x, minsik.mCnt+1,newkey));
				visited[next_y][next_x][newkey] = true;
			} // 4방향 이동 end
		} // while end
		resMin = -1; // 탈출 실패
	}

	private static boolean rangeCheck(int y, int x) {
		if(y < 0 || y >= N || x < 0 || x >= M) return true;
		return false;
	}
}
