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
 * 백준 2933 - 미네랄
 * 두 사람은 한 동굴의 소유권을 가지고 싸운다.
 * 두 사람은 막대기를 던지며 싸우는데 미네랄을 파과할 수도 있다.
 * 동굴은 RxC크기. 각 칸은 비었거나 미네랄이 존재
 * 네 방향중 하나로 인접한 미네랄은 한 클러스터.
 * 한 사람은 왼쪽에 또 다른 사람은 오른쪽에 서있음.
 * 번갈아가며 막대기를 던짐. 막대는 날아가는 높이가 있고, 땅과 수평을 그리며 날아감.
 * 날아가다 미네랄을 만다면 미네랄은 파괴되고 막대기는 멈춤.
 * 미네랄이 파괴된 경우에는 클러스터가 분리될 수도 있음. 분리된 클러스터는 중력의 영향을 받아
 * 바닥으로 떨어지는데 다른 클러스터를 만나거나, 땅에 떨어질 때 까지 이동.
 */

class MineralInfo{
	int y, x, cluster, floor;// floor = 1 바닥, 0 바닥x

	public MineralInfo(int y, int x, int cluster, int floor) {
		this.y = y;
		this.x = x;
		this.cluster = cluster;
		this.floor = floor;
	}
}

public class BOJ_2933_Mineral {
	private static int R, C, throwCnt, height[], clusterCnt;
	private static char map[][];
	private static int cluster_map[][];
	private static boolean visited[];
	private static int dx[] = {0, 0, -1, 1}; // 상하좌우
	private static int dy[] = {-1, 1, 0, 0};
	private static List<MineralInfo> mlist = new ArrayList<>(); // 모든 미네랄 정보를 담을 리스트.
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stt.nextToken());
		C = Integer.parseInt(stt.nextToken());

		map = new char[R][];
		
		for( int i = 0; i < R; i++) {
			stt = new StringTokenizer(br.readLine());
			map[i] = stt.nextToken().toCharArray();
		}

		stt = new StringTokenizer(br.readLine());
		throwCnt = Integer.parseInt(stt.nextToken());
		height = new int[throwCnt];

		stt = new StringTokenizer(br.readLine());
		for( int i = 0; i < throwCnt; i++) {
			height[i] = Integer.parseInt(stt.nextToken());
		}

		start_game();
		
		for(int i = 0; i < R; i++) {
			for( int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	/** 던지기 시작 */
	private static void start_game() {
		for( int i = 0; i < throwCnt; i++) {
			if(i%2 == 0) // 짝수면 왼쪽 사람
				throw_bar(height[i], 0); // 높이, 던지는 사람. 0-> 왼쪽
			else
				throw_bar(height[i], 1); // 1 -> 오른쪽

			// 던지고 나서 미네랄의 클러스터가 변경이 되었는지 확인해야한다.
			checkCluster();
			
			// 클러스터 넘버링이 끝나고 나면 공중에 있는 클러스터는
	        // 다른 클러스터 혹은 바닥을 만날 때까지 밑으로 이동해야한다.
			moveCluster();
		}
	}
	
	/** 공중에 있는 클러스터들을 다른 클러스터를 만나거나 바닥에 닿을 때까지 이동. 
	 * 클러스터가 분리가 되었다는 것은 무조건 떨어져있다는 뜻이다.
	 * 바닥에 붙은채로 좌 우로 떨어진 것인지, 공중에 떠있어서 다른 클러스터와
	 * 분리가 되어있는지 확인해야한다. 
	 * 바닥부터 거꾸로 올라가는 게 더 효과적이지 않을까..?
	 * 바닥에 붙어있는 클러스터들은 굳이 내릴 필요가 없기 때문에.
	 * 그럼 공중에 있는 클러스터들은 얼마나 내려야 할까?
	 * 클러스터 번호를 벗어나서 위로 몇 개 있는지 찾아야한다*/
	private static void moveCluster() {
		if(clusterCnt == 2) return; // 클러스터가 1개뿐이면 내릴 필요가 없다.
		
		visited = new boolean[clusterCnt];
		
		// 2개 이상일 때만 확인하고 내린다.
		// 막혔어 ㅠㅠㅠㅠㅠㅠㅠ
		if(mlist.isEmpty()) return;
		int size = mlist.size();
		
		for(int i = 0; i < size; i++) {
			int cur_cluster = mlist.get(i).cluster;
			// 이미 내린 클러스터 패스
			if(visited[cur_cluster]) continue;
			// 바닥 클러스터 패스
			if(mlist.get(i).floor == 1) {
				visited[cur_cluster] = true;
				continue;
			}
			
			int cnt = 0;
			int min = Integer.MAX_VALUE;
			int idx = i;
			
			int down_x = mlist.get(i).x;
			int down_y = mlist.get(i).y;
			
			while(true) {
				if(visited[mlist.get(idx).cluster]) continue;
				down_y++;
				
				if(down_y >= R) {
					idx++;
					if(idx == size) {
						min = Math.min(min, cnt);
						break;
					}
					down_y = mlist.get(idx).y;
					min = Math.min(min, cnt);
					cnt = 0;
					continue;
				} else if(cluster_map[down_y][down_x] == cur_cluster) {
					idx++;
					if(idx == size) {
						break;
					}
					down_y = mlist.get(idx).y;
					cnt = 0;
					continue;
				} else if( cluster_map[down_y][down_x] != 0 ) {
					idx++;
					if(idx == size) {
						min = Math.min(min, cnt);
						break;
					}
					down_y = mlist.get(idx).y;
					min = Math.min(min, cnt);
					cnt = 0;
					continue;
				}
				cnt++;
			}
			
			next_mineral:
			for(int j = 0; j < size; j++) {
				if(mlist.get(j).cluster == cur_cluster) {
					int jump = 1;
					int tmp_x = mlist.get(j).x;
					int tmp_y = mlist.get(j).y;
					
					while(map[tmp_y+min*jump][tmp_x] != '.') {
						jump++;
						if(tmp_y+min*jump >= R) continue next_mineral;
					}
					map[tmp_y][tmp_x] = '.';
					cluster_map[tmp_y][tmp_x] = 0;
					map[tmp_y+min*jump][tmp_x] = 'x';
					cluster_map[tmp_y+min*jump][tmp_x] = cur_cluster;
				}
			}
			visited[cur_cluster] = true;
		}
	}

	/** 막대기 던짐. 맞은 위치의 미네랄 '.'으로 변경 */
	private static void throw_bar(int start_y, int type) {
		if(type == 0) { // 왼쪽 사람
			for(int i = 0; i < C; i++) {
				if(map[R-start_y][i] == 'x') {
					map[R-start_y][i] = '.';
					break;
				}
			}
		} else { // 오른쪽 사람
			for(int i = C-1; i >= 0; i--) {
				if(map[R-start_y][i] == 'x') {
					map[R-start_y][i] = '.';
					break;
				}
			}
		}
	}

	/** 부서진 미네랄을 뺀 나머지 미네랄들의 클러스터 넘버링. 단순 번호 주기. */
	private static void checkCluster() {
		cluster_map = new int[R][C];
		mlist.clear();
		int cluster = 1;
		for( int i = R-1; i >= 0; i--) { // 애초에 바닥부터 시작
			int floor = 0;
			for( int j = 0; j < C; j++) {
				// 미네랄이 있는 장소이고, 클러스터 넘버링이 되지 않았다면
				if(map[i][j] != 'x' || cluster_map[i][j] != 0) continue;
				else {
					if(i == R-1) floor = 1; // 바닥이면
					Queue<MineralInfo> queue = new LinkedList<>();
					queue.offer(new MineralInfo(i, j, cluster, floor));
					mlist.add(new MineralInfo(i, j, cluster, floor));
					cluster_map[i][j] = cluster;

					while(!queue.isEmpty()) {
						MineralInfo tmp = queue.poll();

						for( int k = 0; k < 4; k++) {
							int next_y = tmp.y + dy[k];
							int next_x = tmp.x + dx[k];

							if(next_y < 0 || next_y >= R 
							|| next_x < 0 || next_x >= C
							|| map[next_y][next_x] != 'x'
							|| cluster_map[next_y][next_x] != 0 // 클러스터 넘버링 됨.
							) continue;
							
							// BFS용 탐색 큐
							queue.offer(new MineralInfo(next_y, next_x, cluster, tmp.floor));
							// 얼마나 내리고, 바닥에 있는지 확인 용 리스트
							mlist.add(new MineralInfo(next_y, next_x, cluster, tmp.floor));
							// 방문 체크 및 현재 맵에 속한 클러스터 확인
							cluster_map[next_y][next_x] = cluster;
						}
					} // while end
					cluster++; // 클러스터 번호 +1
				}
			}
		}
		// 현재 맵에 있는 총 클러스터의 개수 저장.
		clusterCnt = cluster;
	}
}
