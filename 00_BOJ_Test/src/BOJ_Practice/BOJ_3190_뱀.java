/**
 * 
 */
package BOJ_Practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * BOJ 3190 - 뱀
 * NxN 보드. 보드위에는 사과가 있음
 * 뱀의 처음 길이는 1. 오른쪽으로 향함
 * 매초 이동 몸 길이를 늘려 머리를 다음칸에 위치
 * 이동한 칸에 사과가 있다면 사과는 없어지고 꼬리는 그대로
 * 사과가 없다면 꼬리 위치 비워짐
 * 
 * 사과 K개 
 * L 왼쪽 D 오른쪽 90도 꺾음
 * 
 * 벽 또는 자기 자신과 부딪히지 않고 갈 수 있는 최장 시간 구하기
 */
public class BOJ_3190_뱀 {
	private static class Snake{
		int hy, hx; // 머리 위치
		int ty, tx; // 꼬리 위치
		List<int[]>body = new ArrayList<>();
		
		public Snake(int hy, int hx, int ty, int tx) {
			this.hy = hy;
			this.hx = hx;
			this.ty = ty;
			this.tx = tx;
		}
	}
	private static int N, C, map[][], command[][], Time;
	private static final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4, D = 1, L = 2, SNAKE = 1, APPLE = 2; 
	// 상 하 좌 우
	private static int dx[] = {0, 0, 0, -1, 1};
	private static int dy[] = {0, -1, 1, 0, 0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N+1][N+1];
		map[1][1] = 1;
		
		int K = sc.nextInt();
		
		for(int i = 0; i < K; i++) {
			int y = sc.nextInt();
			int x = sc.nextInt();
			map[y][x] = 2;
		}
		
		C = sc.nextInt();
		
		command = new int[C][2];
		
		for(int c = 0; c < C; c++) {
			command[c][0] = sc.nextInt();
			if(sc.next().equals("D")) {
				command[c][1] = D;
			} else {
				command[c][1] = L;
			}
		}
		
		Time = 0;
		start();
		
		System.out.println(Time);
	}

	private static void start() {
		// 커맨드 인덱스
		int idx = 0;
		// 초기 이동 방향 오른쪽
		int dir = RIGHT;
		Snake snake = new Snake(1,1,1,1); // 처음 머리,꼬리 위치 0,0
		snake.body.add(new int[] {0, 0}); // 몸통 추가
		while(true) { // 벽이나 자기 자신을 만날 때까지 반복
			// 시작과 동시에 시간 + 1
			Time++;
			int next_hx = snake.hx + dx[dir];
			int next_hy = snake.hy + dy[dir];
			
			// 범위를 벗어났다는 소리는 벽에 부딪혔다는 소리!
			if(rangeCheck(next_hy, next_hx)) break;
			// 몸이랑 만났다는 소리!!
			if(map[next_hy][next_hx] == SNAKE) break;
			// 사과를 만난 경우 꼬리는 그대로있고 머리만 이동
			if(map[next_hy][next_hx] == APPLE) {
				map[next_hy][next_hx] = SNAKE;
				snake.body.add(new int[] {next_hy, next_hx}); // 몸통 추가
			} else { // 빈 곳인 경우 꼬리 비우기
				map[next_hy][next_hx] = SNAKE;
				map[snake.ty][snake.tx] = 0;
				snake.body.add(new int[] {next_hy, next_hx}); // 몸통 추가
				snake.body.remove(0); // 가장 먼저 들어온 몸통(꼬리) 제거하고 꼬리 재 할당
				snake.ty = snake.body.get(0)[0];
				snake.tx = snake.body.get(0)[1];
			}
			
			snake.hy = next_hy;
			snake.hx = next_hx;
			
			// 시간이 맞다면 머리 방향 변경
			if(idx < C)
			if(command[idx][0] == Time) {
				// 지금 가고 있는 방향에 맞게 90도 회전
				if(dir == UP) { // 이동방향 위쪽
					if(command[idx][1] == L) { // 왼쪽으로
						dir = LEFT;
					} else {
						dir = RIGHT;
					}
				} else if(dir == DOWN) { // 이동방향 아래쪽
					if(command[idx][1] == L) { // 왼쪽으로
						dir = RIGHT;
					} else {
						dir = LEFT;
					}
				} else if(dir == LEFT) { // 이동방향 왼쪽
					if(command[idx][1] == L) { // 왼쪽으로
						dir = DOWN;
					} else {
						dir = UP;
					}
				} else { // 이동방향 오른쪽
					if(command[idx][1] == L) { // 왼쪽으로
						dir = UP;
					} else {
						dir = DOWN;
					}
				}
				// 다음 시간 커맨드로
				idx++;
			}
			
		}
	}
	
	private static boolean rangeCheck(int y, int x) {
		if(y < 1 || y >= N+1 || x < 1 || x >= N+1) return true;
		return false;
	}
}








