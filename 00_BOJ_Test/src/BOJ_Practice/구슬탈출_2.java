package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출_2 {
	static class Marble{
		int y, x;
		int moved_y, moved_x;
		
		public Marble(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	private static int N, M, resMin;
	private static char[][] map;
	private static int[] dy = {-1, 1, 0, 0};
	private static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());
		
		map = new char[N][M];
		
		Deque<Marble> red_queue = new ArrayDeque<>();
		Deque<Marble> blue_queue = new ArrayDeque<>();
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				char token = map[i][j];
				if(token == 'R') {
					map[i][j] = '.';
					red_queue.offer(new Marble(i, j));
					continue;
				}
				if(token == 'B') {
					map[i][j] = '.';
					blue_queue.offer(new Marble(i, j));
				}
			}
		} // input end
		
		resMin = Integer.MAX_VALUE;
		move_marbles(red_queue, blue_queue);
		
		System.out.println(resMin);
	}
	
	private static void move_marbles(Deque<Marble> red_queue, Deque<Marble> blue_queue) {
		int move_cnt = 1;
		
		while(move_cnt < 11) { // 10번 까지만 돌린다.
			int size = red_queue.size(); // 큐의 사이즈 별로 BFS
			for(int s = 0; s < size; s++) {
				boolean red_correct = false; // 빨간 구슬 골인
				boolean blue_correct = false; // 파란 구슬 골인
				
				Marble red = red_queue.poll();
				Marble blue = blue_queue.poll();
				
				for(int dir = 0; dir < 4; dir++) { // 상하좌우 체크
					map[red.y][red.x] = 'R'; // BFS 시작 할 때 구슬 위치 체크
					map[blue.y][blue.x] = 'B';
					char first = check_first(dir, red, blue); // 먼저 움직일 구슬 체크
					if(first == 'R') {
						// 빨간 구슬이 먼저 이동
						red_correct = move(red_queue, red, dir, 'R');
						
						// 파란 구슬 이동
						blue_correct = move(blue_queue, blue, dir, 'B');
					} else {
						// 파란 구슬 이동
						blue_correct = move(blue_queue, blue, dir, 'B');
						
						// 빨간 구슬이 먼저 이동ㅇ
						red_correct = move(red_queue, red, dir, 'R');
					}
					if(red_correct && !blue_correct) { // 빨간구슬 들어감, 파란구슬 안들어감
						resMin = move_cnt; // 정답
						return;
					}
					if(!red_correct && blue_correct) { // 빨간 구슬 안들어감, 파란구슬 들어감
						red_queue.removeLast(); // 파란구슬 마지막 구슬 제거, 더이상 볼 필요 없음
					}
					map[red.moved_y][red.moved_x] = '.'; // 움직이고 난 다음의 위치의 구슬 .으로 초기화
					map[blue.moved_y][blue.moved_x] = '.';
				}
			}
			move_cnt++;
		}
		resMin = -1;
	}
	/*
	 * 실제 구슬을 움직이자
	 * */
	private static boolean move(Deque<Marble> marble_queue, Marble marble, int dir, char color) {
		map[marble.y][marble.x] = '.'; // 현재 구슬 위치 . 변경
		int next_y = marble.y;
		int next_x = marble.x;
		while(true) { // 방향따라 이동
			next_y += dy[dir];
			next_x += dx[dir];
			if(map[next_y][next_x] == '#') { // 벽 또는 장애물을 만난 경우 deque 적재
				marble_queue.offer(new Marble(next_y-dy[dir], next_x-dx[dir]));
				break;
			}
			if(map[next_y][next_x] == 'O') { // 골인. deque에 넣지않고 true 리턴
				return true;
			}
			if(map[next_y][next_x] == 'B' || map[next_y][next_x] == 'R') { // 다른 구슬을 만난 경우 적재
				marble_queue.offer(new Marble(next_y-dy[dir], next_x-dx[dir]));
				break;
			}
		}
		marble.moved_y = next_y-dy[dir]; // 구슬이 이동하고 난 다음의 위치 저장
		marble.moved_x = next_x-dx[dir];
		map[next_y-dy[dir]][next_x-dx[dir]] = color; // 다른 구슬을 위해 위치 잠시 알려주는 역할
		return false;
	}
	
	/**
	 * 빨간 구슬과 파란 구슬의 위치를 보고 어느 것을 먼저 움직일지 판단
	 * */	
	private static char check_first(int dir, Marble red, Marble blue) {
		switch(dir) { // 방향에 따라 먼저 움직일 구슬을 정하자
			case 0: // 상
				if(red.y < blue.y) { // 빨간 구슬이 파란구슬 보다 위에 있는 경우
					return 'R';
				} else {
					return 'B';
				}
			case 1: // 하
				if(red.y < blue.y) { // 빨간 구슬이 파란구슬 보다 위에 있는 경우
					return 'B';
				} else {
					return 'R';
				}
			case 2: // 좌
				if(red.x < blue.x) { // 빨간 구슬이 파란구슬 보다 왼쪽에 있는 경우
					return 'R';
				} else {
					return 'B';
				}
			case 3: // 우
				if(red.x < blue.x) { // 빨간 구슬이 파란구슬 보다 왼쪽에 있는 경우
					return 'B';
				} else {
					return 'R';
				}
		}
		return ' ';
	}
}
