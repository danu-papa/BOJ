/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 16236 - 아기상어
 * NxN크기 물고기 M마리 상어 1마리.
 * 한칸에는 물고기 1마리
 * 아기상어 초기크기 2. 상하좌우로 한칸씩 이동
 * 상어보다 큰 물고기의 칸은 지나갈 수 없음.
 * 같은 크기의 물고기는 먹지는 못하지만 지나갈 수는 있음.
 * 더 이상 먹을 수 있는 물고기가 공간에 없으면 엄마 상어 호출.
 * 먹을 수 있는 물고기가 1마리. 그 물고기 먹으러 이동
 * 1마리 이상이면 가장 가까운 물고기를 먹으러 간다.
 * - 거리 : 물고기가 있는 칸 까지 지나야하는 칸의 개수
 * - 가까운 물고기가 많다면 가장 위에 물고기. 그런 물고기가 많으면
 * - 가장 왼쪽부터 먹는다.
 * 상어 크기가 2인경우 물고기를 2마리 먹으면 3으로 커진다.
 * 3인경우에는 3마리. ( 크기는 상관없다 )
 * 상어는 단 한마리만 존재할 때
 * 몇초동안 엄마를 부르지않고 물고기를 먹을 수 있을까?
 */
public class BOJ_16236_Baby_Shark {
	static class Shark { // 상어 정보를 담을 클래스
		// eat : 현재까지 먹은 수, growCnt : 성장에 필요한 먹이 수
		int y, x, eat, growCnt = 2;

		public Shark(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Shark [y=" + y + ", x=" + x + ", eat=" + eat + ", growCnt=" + growCnt + "]";
		}
	}

	static class Fish implements Comparable<Fish>{ // 물고기 정보를 담을 클래스
		int y, x, time;
		public Fish(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}

		@Override
		public int compareTo(Fish o) {
			// 두 y값이 같은 경우
			if(this.y == o.y) {
				//가장 왼쪽 물고기부터 먹어야하므로 x 내림차순 정렬
				return Integer.compare(this.x, o.x);
			}
			// 가장 위쪽 물고기 부터 먹어야 하므로 y 내림차순 정렬
			return Integer.compare(this.y, o.y);
		}

		@Override
		public String toString() {
			return "Fish [y=" + y + ", x=" + x + "]";
		}
	}
	// N: 맵크기, map[][] : 0빈칸, 1~6 물고기, 9 : 상어
	private static int N, map[][],maxTime;
	private static boolean visited[][];
	private static int dy[] = {-1, 1, 0, 0};
	private static int dx[] = {0, 0, -1, 1};
	private static Shark shark;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];

		StringTokenizer stt;
		for (int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int mapinfo = Integer.parseInt(stt.nextToken());
				if(mapinfo == 0) continue;
				if(mapinfo == 9) { // 상어!
					shark = new Shark(i,j);
				}
				map[i][j] = mapinfo;
			}
		} // input end
		maxTime = 0;
		// 먹기 시작
		startEat();
		System.out.println(maxTime);
	}
	/** 자기보다 작은 크기의 물고기를 먹자!*/
	private static void startEat() {
		PriorityQueue<Fish> pQueue = new PriorityQueue<Fish>();
		while(true) {
			// 시간에따른 먹을 수 있는 물고기를 찾자.
			findFish(pQueue);

			// 먹을 수 있는 물고기가 없는 경우
			if(pQueue.isEmpty()) break;

			eatFish(pQueue);
		}
	}

	/** 물고기를 먹자 */
	private static void eatFish(PriorityQueue<Fish> pQueue) {
		Fish fish = pQueue.poll(); // 가장 앞의 물고기
		int x = fish.x;
		int y = fish.y;
		int time = fish.time;
		maxTime += time; // 시간 누적
		map[shark.y][shark.x] = 0; // 원래 상어위치 0
		shark.x = x;
		shark.y = y;
		shark.eat++;
		map[y][x] = 9; // 물고기 위치 9
		// 상어 성장.
		if(shark.eat == shark.growCnt) {
			shark.eat = 0;
			shark.growCnt++;
		}
		pQueue.clear(); // 먹고났으니 비워준다
	}
	
	/** 물고기를 찾아서 큐에 넣어주자. */
	private static void findFish(PriorityQueue<Fish> pQueue) {
		visited = new boolean[N][N]; // 찾을 때 마다 초기화
		// 상어위치
		int cur_x = shark.x;
		int cur_y = shark.y;
		int grow = shark.growCnt;
		Queue<int[]> queue = new LinkedList<>();
		// 상어 위치 넣어줌.
		// y, x , 흐른시간
		queue.offer(new int[] {cur_y, cur_x, 0});
		visited[cur_y][cur_x] = true;

		while(!queue.isEmpty()) {
			int size = queue.size();
			// 레벨 단위로 BFS
			for(int s = 0; s < size; s++) {
				int [] pos = queue.poll();
				for( int k = 0; k < 4; k++) {
					int ny = pos[0] + dy[k];
					int nx = pos[1] + dx[k];
					int time = pos[2];

					// 범위 벗어나면 패스
					if(outRange(ny,nx)) continue;
					// 이미 방문 했으면 패스
					if(visited[ny][nx]) continue;
					// 맵의 물고기가 상어보다 큰 경우 패스
					if(map[ny][nx] > grow) continue;

					// 빈 곳 큐에 넣고 패스 , 같은 경우에는 이동은 가능해서 큐에 넣고 패스
					if (map[ny][nx] == 0 || map[ny][nx] == grow) {
						queue.offer(new int[] { ny, nx, time + 1 });
						visited[ny][nx] = true;
						continue;
					}

					// 작은 경우만 큐에 저장하고 패스
					if(map[ny][nx] < grow) {
						// 먹을 위치를 찾았다면 큐에 더 안넣어줘도 괜찮아!
						pQueue.offer(new Fish( ny,nx,time+1 ));
						visited[ny][nx] = true;
						continue;
					}
				}
			}
			if(!pQueue.isEmpty()) break; // 무언가 먹을 수 있다.
		}
	}

	/** 범위 확인 */
	private static boolean outRange(int ny, int nx) {
		if(ny < 0 || ny >= N || nx < 0 || nx >= N) return true;
		return false;
	}
}
