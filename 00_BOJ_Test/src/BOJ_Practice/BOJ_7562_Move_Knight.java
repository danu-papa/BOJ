/**
 * 
 */
package BOJ_Practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 7562 - 나이트의 이동
 * 나이트의 현재위치가 주어지고 가려고 하는 위치가 주어진다.
 * 최소 몇 번 움직이면 가려는 위치로 이동할 수 있을까?
 * 기본적인 BFS를 이용해서 구현.
 * 현재 위치에서 갈 수 있는 모든 위치를 큐에 넣고 해당 위치가 목적지인지
 * 확인하는 작업을 반복.
 */

class KnightInfo { // 나이트의 정보를 입력 받을 class
	int y, x, cnt;

	public KnightInfo(int y, int x, int cnt) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}
}

public class BOJ_7562_Move_Knight {
	private static int N;
	private static Scanner sc;
	//나이트의 이동 방향을 나타내는 델타 값.
	private static int dx[] = {-2,-1,-2,-1,+2,+1,+2,+1};
	private static int dy[] = {-1,-2,+1,+2,-1,-2,+1,+2};
	private static boolean[][] visited;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for( int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			visited = new boolean[N][N];
			Move_Knight();
		}
	}
	
	/** 나이트의 위치를 입력 받고 이동 시킴. */
	private static void Move_Knight() {
		Queue<KnightInfo> kq = new LinkedList<>();
		int y = sc.nextInt(); // 나이트의 최초 위치
		int x = sc.nextInt();
		kq.offer(new KnightInfo(y, x, 0)); // 큐 입력
		visited[y][x] = true; // 처음 위치 방문 표시
		
		int dest_y = sc.nextInt(); // 목적지
		int dest_x = sc.nextInt();
		
		while(!kq.isEmpty()) {
			KnightInfo tmp = kq.poll();
			
			if(tmp.x == dest_x && tmp.y == dest_y) {
				System.out.println(tmp.cnt);
				break;
			}
			
			for( int k = 0; k < 8; k++) {
				int next_x = tmp.x + dx[k]; // 갈 수 있는 모든 방향 이동
				int next_y = tmp.y + dy[k];
				
				if(next_x < 0 || next_x >= N || next_y < 0 || next_y >=N 
						      || visited[next_y][next_x]) continue;
				
				// 해당 위치를 간 적이 없고, 범위 안이라면 카운트 +1해서 큐에 입력
				kq.offer(new KnightInfo(next_y, next_x, tmp.cnt+1));
				visited[next_y][next_x] = true; // 해당 위치 방문표시
			}
		}
	}
}
