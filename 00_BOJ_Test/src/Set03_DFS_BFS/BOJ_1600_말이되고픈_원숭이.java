/**
 * 
 */
package Set03_DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1600 말이 되고싶은 원숭이
 * K번 체스의 나이트와 같이 이동 가능
 * K번 이후는 인접 4방향으로 1칸씩 이동
 * 시작위치(0,0)에서 원하는 위치(H-1,W-1)까지 이동하는 최소 경로
 */
public class BOJ_1600_말이되고픈_원숭이 {
	private static class Monkey implements Comparable<Monkey>{
		int y, x, cnt, k;

		public Monkey(int y, int x, int cnt, int k) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.k = k;
		}

		@Override
		public int compareTo(Monkey o) {
			return Integer.compare(cnt, o.cnt);
		}
	}

	private static int K, H, W, map[][], totalCnt;
	private static boolean visited[][][];
	private static int dx[] = {0, 0, -1 , 1};
	private static int dy[] = {-1, 1, 0 , 0};
	// 나이트의 8방 이동
	private static int kdx[] = {-2, -1, 1 , 2, -2, -1, 1, 2};
	private static int kdy[] = {-1, -2, -2 , -1, 1, 2, 2, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());

		StringTokenizer stt = new StringTokenizer(br.readLine());
		W = Integer.parseInt(stt.nextToken());
		H = Integer.parseInt(stt.nextToken());

		map = new int[H][W];
		visited = new boolean[H][W][K+1];

		for(int i = 0; i < H; i++) {
			stt = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(stt.nextToken());
			}
		} // input end

		totalCnt = 0;
		bfs();

		System.out.println(totalCnt);
	}

	private static void bfs() {
		PriorityQueue<Monkey> pQueue = new PriorityQueue<>();

		pQueue.offer(new Monkey(0, 0, 0, K));
		visited[0][0][0] = true;

		while(!pQueue.isEmpty()) {
			Monkey monkey = pQueue.poll();

			if(monkey.y == H-1 && monkey.x == W-1) {
				totalCnt = monkey.cnt;
				return ;
			}

			if(rangeChk(monkey.y, monkey.x)) continue;
			if(visited[monkey.y][monkey.x][monkey.k]) continue;
			if(map[monkey.y][monkey.x] == 1) continue;
			visited[monkey.y][monkey.x][monkey.k] = true;

			for(int d = 0; d < 4; d++) {
				int next_y = monkey.y + dy[d];
				int next_x = monkey.x + dx[d];
				
				pQueue.offer(new Monkey(next_y, next_x, monkey.cnt+1, monkey.k));
			}
			
			// 나이트 처럼 뛸 수 있다면 이동 방향 12방향
			if(monkey.k == 0) continue;
			for(int d = 0; d < 8; d++) {
				int next_y = monkey.y + kdy[d];
				int next_x = monkey.x + kdx[d];

				pQueue.add(new Monkey(next_y, next_x, monkey.cnt+1, monkey.k-1));
			}
		}
		// return 안하고 내려오면 도달 못한다는 뜻
		totalCnt = -1;
	}

	private static boolean rangeChk(int y, int x) {
		if(y < 0 || y >= H || x < 0 || x >= W) return true;
		return false;
	}
}
