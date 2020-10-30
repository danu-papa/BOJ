package BOJ_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6109_2048게임 {
	private static int N,T, map[][], create[][];
	private static int dx[] = {0, 0, -1, 1};
	private static int dy[] = {-1, 1, 0, 0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer stt = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(stt.nextToken());
			map = new int[N][N];
			create = new int[N][N];
			
			String action = stt.nextToken();
			
			for(int i = 0; i < N; i++) {
				stt = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stt.nextToken());
				}
			} // input end
			
			move(action);
			
			System.out.println("#"+test_case);
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}//test_case end
	}
	
	private static void move(String action) {
		switch(action) {
		case "up":
			for(int i = 0; i < N; i++) {
				for(int j = 1; j < N; j++) {
					int cur = map[j][i];
					// 현재 위치 0이면 넘김
					if(cur == 0) continue;
					// 0이 아닌경우
					// 현재 위치에서 가야할 곳을 확인해봐야한다.
					// 현재 위치와 가야할 위치의 값이 같고, 새로 만들어 진것이 아닌경우
					int next = j-1;
					while(true) {
						if(map[next][i] == 0) {
							map[next][i] = cur;
							map[next+1][i] = 0;
							if(next == 0) break;
							next--;
							continue;
						}
						if(cur == map[next][i] && create[next][i] == 0) {
							map[next][i] = cur*2;
							map[next+1][i] = 0;
							create[next][i] = 1;
							break;
						}
						// 값이 다르거나, 이미 합쳐진 곳이면 멈춤
						if(cur != map[next][i] || 
						  (cur == map[next][i] && create[next][i] == 1)) break;
					}
				}
			}
			break;
		case "down":
			for(int i = 0; i < N; i++) {
				for(int j = N-2; j >= 0; j--) {
					int cur = map[j][i];
					// 현재 위치 0이면 넘김
					if(cur == 0) continue;
					// 0이 아닌경우
					// 현재 위치에서 가야할 곳을 확인해봐야한다.
					// 현재 위치와 가야할 위치의 값이 같고, 새로 만들어 진것이 아닌경우
					int next = j+1;
					while(true) {
						if(map[next][i] == 0) {
							map[next][i] = cur;
							map[next-1][i] = 0;
							if(next == N-1) break;
							next++;
							continue;
						}
						if(cur == map[next][i] && create[next][i] == 0) {
							map[next][i] = cur*2;
							map[next-1][i] = 0;
							create[next][i] = 1;
							break;
						}
						// 값이 다르거나, 이미 합쳐진 곳이면 멈춤
						if(cur != map[next][i] || 
						  (cur == map[next][i] && create[next][i] == 1)) break;
					}
				}
			}
			break;
		case "left":
			for(int i = 0; i < N; i++) {
				for(int j = 1; j < N; j++) {
					int cur = map[i][j];
					if(cur == 0) continue;
					
					int next = j-1;
					while(true) {
						if(map[i][next] == 0) {
							map[i][next] = cur;
							map[i][next+1] = 0;
							if(next == 0) break;
							next--;
							continue;
						}
						if(cur == map[i][next] && create[i][next] == 0) {
							map[i][next] = cur*2;
							map[i][next+1] = 0;
							create[i][next] = 1;
							break;
						}
						if(cur != map[i][next] || 
						  (cur == map[i][next] && create[i][next] == 1)) break;
					}
				}
			}
			break;
		case "right":
			for(int i = 0; i < N; i++) {
				for(int j = N-2; j >= 0; j--) {
					int cur = map[i][j];
					if(cur == 0) continue;
					
					int next = j+1;
					while(true) {
						if(map[i][next] == 0) {
							map[i][next] = cur;
							map[i][next-1] = 0;
							if(next == N-1) break;
							next++;
							continue;
						}
						if(cur == map[i][next] && create[i][next] == 0) {
							map[i][next] = cur*2;
							map[i][next-1] = 0;
							create[i][next] = 1;
							break;
						}
						if(cur != map[i][next] || 
						  (cur == map[i][next] && create[i][next] == 1)) break;
					}
				}
			}
			break;
		}
		
	}
}
