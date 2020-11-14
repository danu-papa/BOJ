/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 17142 연구소3
 * 바이러스는 비활성 상태와 활성 상태가 있음
 * 바이러스는 상하좌우 모든 빈 칸으로 복제되고 1초가 걸린다. 
 * 활성을 M개 시켰을 때 최소시간으로 모든 칸을 감겸시키고 싶다. 최소 시간을 구하라
 * 
 * 크기는 NxN 정사각형. 
 * 0 - 빈칸
 * 1 - 벽
 * 2 - 바이러스의 위치
 * 
 * 바이러스를 모든 빈칸에 퍼트릴 수 없는 경우에는 -1 출력
 *  */
public class BOJ_17142_연구소3 {
	private static class Virus implements Comparable<Virus>{ 
		int y, x, time, no;

		public Virus(int y, int x, int time, int no) {
			this.y = y;
			this.x = x;
			this.time = time;
			this.no = no;
		}
		@Override
		public int compareTo(Virus o) {
			return Integer.compare(time, o.time);
		}
	}
	
	private static int N, M, virusCnt, map[][], minTime;
	private static List<Virus> vlist;
	private static boolean flag;
	private static int dx[] = {0, 0, -1, 1};
	private static int dy[] = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());
		
		map = new int[N][N];
		vlist = new ArrayList<Virus>();
		
		for(int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int in = Integer.parseInt(stt.nextToken());
				if(in == 2) {
					vlist.add(new Virus(i,j,0, virusCnt));
					virusCnt++;
				}
				map[i][j] = in;
			}
		} // input end
		
		minTime = Integer.MAX_VALUE;
		flag = false;
		
		// 퍼트릴 바이러스의 위치를 고른다
		int[] selected = new int[M];
		combination(0, 0, selected);
		
		if(minTime == Integer.MAX_VALUE) {
			if(flag) minTime = -1;
			else minTime = 0;
		}
		
		System.out.println(minTime);
	}
	
	/** 바이러스를 M개 고른다*/
	private static void combination(int idx, int start, int[] selected) {
		if(idx == M) {
			// 바이러스를 다 고르면 그 바이러스를 가지고 bfs수행
			// 원래 맵은 그대로 유지하고 복사된 맵을 이용해서 퍼트리자
			int[][] tmpMap = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 1) {
						tmpMap[i][j] = -1;
						continue;
					}
					if(map[i][j] == 2) {
						tmpMap[i][j] = -2;
						continue;
					}
					tmpMap[i][j] = map[i][j];
				}
			}
			boolean visited[][][] = new boolean[N][N][virusCnt];
			bfs(selected, tmpMap, visited);
			
			calcTime(tmpMap);
			
			return ;
		}
		for(int i = start; i < virusCnt; i++) {
			selected[idx] = i;
			combination(idx+1, i+1, selected);
		}
	}

	/** 시간을 계산하자 */
	private static void calcTime(int[][] tmpMap) {
		int maxtime = -1;
		exit:
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(tmpMap[i][j] == 0) {
					maxtime = -1;
					flag = true;
					break exit;
				} 
				if(map[i][j] != 2)
				if(maxtime < tmpMap[i][j])
					maxtime = tmpMap[i][j];
			}
		}
		if(maxtime != -1)
		if(minTime > maxtime) {
			minTime = maxtime;
		}
	}

	/** 바이러스를 퍼트려보자! */
	private static void bfs(int[] selected, int[][] tmpMap, boolean[][][] visited) {
		PriorityQueue<Virus> pQueue = new PriorityQueue<Virus>();
		
		for(int i = 0; i < M; i++) {
			Virus tmp = vlist.get(selected[i]);
			pQueue.add(tmp);
			visited[tmp.y][tmp.x][i] = true;
		}
		
		while(!pQueue.isEmpty()) {
			Virus currentVirus = pQueue.poll();
			int time = currentVirus.time;
			int no = currentVirus.no;
			
			for(int d = 0; d < 4; d++) {
				int next_y = currentVirus.y + dy[d];
				int next_x = currentVirus.x + dx[d];
				
				if(rangeCheck(next_y, next_x)) continue;
				if(tmpMap[next_y][next_x] == -1) continue; // 벽인 경우
				if(tmpMap[next_y][next_x] > 0 && time + 1 > tmpMap[next_y][next_x]) continue;
				if(visited[next_y][next_x][no]) continue;
				pQueue.offer(new Virus(next_y, next_x, time + 1, no));
				visited[next_y][next_x][no] = true;
				tmpMap[next_y][next_x] = time + 1;
			}
		}
	}
	
	private static boolean rangeCheck(int y, int x) {
		if(y < 0 || y >= N || x < 0 || x >= N) return true;
		return false;
	}
}
