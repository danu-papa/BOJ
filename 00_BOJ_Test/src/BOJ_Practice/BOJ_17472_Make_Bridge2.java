/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 17472 - 다리만들기 2
 * 지도 NxM
 * 섬은 상하좌우로 붙어있는 덩어리.
 * 다리를 연결해서 모든 섬을 연결하고자 한다.
 * 다리는 직선만 가능. 바다에만 설치가능. 다리길이는 2이상.
 * 
 * 1. 섬에 번호붙이기 (BFS, DFS)
 * 2. 모든 지도의 좌표를 돌며 각 섬마다 설치가능한 다리 놓기( 완전탐색 )
 * 3. MST로 최소 다리 비용 찾기. (크루스칼, 프림)
 */
public class BOJ_17472_Make_Bridge2 {
	// 섬과 이어진 정보를 담는 클래스
	static class IslandInfo implements Comparable<IslandInfo>{
		int from, to, weight;

		public IslandInfo(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		// 가중치를 기준으로 오름차순 정렬 할거다.
		@Override
		public int compareTo(IslandInfo o) {
			return Integer.compare(this.weight, o.weight);
		}
		@Override
		public String toString() {
			return "IslandInfo [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
	
	// N x M : 맵 크기, map : 실제 정보가 담겨있음., islandCnt : 총 섬의 수, parents[] : 크루스칼에 쓸 부모, resMin : 최소값
	private static int N, M, map[][], islandCnt, parents[], resMin;
	private static int dx[] = {0, 0, -1, 1};
	private static int dy[] = {-1, 1, 0, 0};
	private static List<IslandInfo> ilist = new ArrayList<IslandInfo>(); 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());
		map = new int[N][M];

		for( int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stt.nextToken());
			}
		} // input end

		// 1단계 : BFS로 섬 번호 붙이기.
		numberingIsland();
		// 2단계 : 모든 지도의 좌표를 돌며 각 섬마다 설치가능한 다리 놓기( 완전탐색 )
		buildBridge();
		// 3단계 : MST 찾기. 크루스칼이용.
		kruskal();
		
		System.out.println(resMin);
	}

	/** 크루스칼을 이용해서 MST 찾기 */
	private static void kruskal() {
		// 가중치 기준으로 오름차순 정렬
		Collections.sort(ilist);
		// 서로소 집합 만들기
		make();
		resMin = 0;
		int cnt = 0;
		for(IslandInfo island : ilist) {
			if(unionSet(island.from, island.to)) {
				resMin += island.weight;
				
				if(++cnt == islandCnt) break;
			}
		}
		// 모든 섬이 연결이 되었는지 확인. 연결 되어있으면 return
		if(checkConnect()) return;
		
		// 여기 내려왔다는 소리는 연결 안된 섬이 있다는 뜻
		resMin = -1;
	}

	/** 모든 섬이 연결이 되어있는지 확인 */
	private static boolean checkConnect() {
		int cnt = 0;
		for( int i = 1; i <= islandCnt; i++) {
			if(parents[i] == i) {
				cnt++;
			}
		}
		// 모두 연결이 되어있다면 같은 경우는 1가지밖에 없음.
		if(cnt == 1) return true;
		return false;
	}

	/** 하나의 집합으로 합치기 */
	private static boolean unionSet(int from, int to) {
		int aroot = find(from);
		int broot = find(to);
		
		if(aroot == broot) return false;
		
		parents[broot] = aroot;
		return true;
	}

	/** 조상 노드 찾기 */
	private static int find(int num) {
		if(parents[num] == num) return num;
		return parents[num] = find(parents[num]);
	}

	/** 서로소 집합 만들기 */
	private static void make() {
		parents = new int[islandCnt+1];
		for( int i = 1; i <= islandCnt; i++) {
			parents[i] = i;
		}
	}

	/** 
	 * 모든 경우를 돌며 다리를 연결하자.
	 * 완전 탐색 
	 * */
	private static void buildBridge() {
		for( int i = 0; i < N; i++ ) {
			for( int j = 0; j < M; j++ ) {
				if(map[i][j] == 0) continue; // 바다는 패스
				int cur_island = map[i][j]; // 현재 섬 번호
				
				for(int dir = 0; dir < 4; dir++) {
					// 해당 섬에서 다른 섬으로 연결이 가능한 지 보자.
					int ny = i;
					int nx = j;
					int count = 0;
					while( true ) {
						ny += dy[dir];
						nx += dx[dir];
						
						// 범위 벗어나면 다른 방향으로 시도
						if(outRange(ny, nx)) break;
						// 다음 위치의 정보.
						int next = map[ny][nx];
						// 가는 도중 나랑 같은 번호를 만나면 다른 방향으로 시도
						if(cur_island == next) break;
						// 다음 위치가 나와 다른 번호의 섬이라면
						if(next != 0 && cur_island != next) {
							// 혹시 다리 1개밖에 못지으면 다른 방향 시도
							if(count == 1) {
								break;
							} else { // 아니다 여러개 지어와서 만난것이다.
								// 다리 짓기.
								// 현재 섬의 시작위치, 이어질 섬, 가중치
								ilist.add(new IslandInfo(cur_island, next, count));
								break;
							}
						}
						// 다음 위치가 바다이면 다리 짓기 가능위치.
						// count가 곧 섬과 섬 사이의 가중치가 된다.
						count++;
					}
				}
			}
		}
	}

	/** 
	 * Breadth First Search 
	 * 각 섬의 번호를 붙이자.
	 * */
	private static void numberingIsland() {
		Queue<int[]> queue = new LinkedList<int[]>();
		int island_num = 1;
		boolean visited[][] = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 0 && !visited[i][j]) {
					// int[] : y, x 좌표
					queue.offer(new int[] {i, j});
					map[i][j] = island_num;
					visited[i][j] = true;

					while(!queue.isEmpty()) {
						int[] pos = queue.poll();

						for( int k = 0; k < 4; k++) {
							int ny = pos[0] + dy[k];
							int nx = pos[1] + dx[k];

							// 범위 밖이거나 이미 방문한경우 패스
							if(outRange(ny,nx) || map[ny][nx] == 0 || visited[ny][nx]) continue;

							queue.offer(new int[] {ny, nx});
							// 현재 지도에 섬 번호 표시
							map[ny][nx] = island_num;
							visited[ny][nx] = true;
						}
					} // while end
					// 모든 섬의 개수
					islandCnt++;
					// 섬 넘버링
					island_num++;
				}
			}
		}
	}

	/** 범위 탐색*/
	private static boolean outRange(int ny, int nx) {
		if(ny < 0 || ny >= N || nx <0 || nx >= M) return true;
		return false;
	}
}
