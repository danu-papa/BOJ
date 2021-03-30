package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출_2 {
	static class Marble{
		int y, x, move;

		public Marble(int y, int x, int move) {
			this.y = y;
			this.x = x;
			this.move = move;
		}
	}
	private static int N, M, resMin;
	private static char[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());
		
		map = new char[N][M];
		
		Queue<Marble> red_queue = new LinkedList<>();
		Queue<Marble> blue_queue = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				char token = map[i][j];
				if(token == 'R') {
					red_queue.offer(new Marble(i, j, 0));
					continue;
				}
				if(token == 'B') {
					blue_queue.offer(new Marble(i, j, 0));
				}
			}
		} // input end
		
		resMin = Integer.MAX_VALUE;
		move_marbles(red_queue, blue_queue);
		
		System.out.println();
	}
	
	private static void move_marbles(Queue<Marble> red_queue, Queue<Marble> blue_queue) {
		int move_cnt = 0;
		
		while(move_cnt < 10) {
			
		}
		resMin = -1;
	}
}
