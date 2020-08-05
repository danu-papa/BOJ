/**
 * 
 */
package Set03_DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 백준 2667 - 단지 번호 붙이기
 * 1 : 집이 있는 곳, 0 : 집 없는 곳
 * 상 하 좌 우의 집만 같은 단지로 본다
 * 각 단지에 해당하는 집의 수를 세어 오름차순으로 정렬하여 출력
 * BFS를 이용
 */
public class BOJ_2667_Numbering_region_BFS {

	/** 아파트 단지의 정보를 저장하는 class*/
	class Region_info{
		int y;
		int x;

		/**
		 * Region_info(int y, int x)
		 * y : y 좌표
		 * x : x 좌표
		 * */
		public Region_info(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static int N, region[][];
	private static boolean visited[][];
	private static int dx[] = {0, 0, -1, 1};
	private static int dy[] = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException{
		BOJ_2667_Numbering_region_BFS np = new BOJ_2667_Numbering_region_BFS();
		np.numbering();
	}

	/** 넘버링 하기*/
	private void numbering() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		region = new int[N][N];
		visited = new boolean[N][N];

		for( int i = 0; i < N; i++) {
			String txt = br.readLine();
			for( int j = 0; j < N; j++) {
				region[i][j] = (int)(txt.charAt(j) - '0');
			}
		} // 단지 정보 받기
		
		bfs();
	}

	/** Breadth First Search */
	private void bfs() {
		Queue<Region_info> queue = new LinkedList<>();
		ArrayList<Integer> list = new ArrayList<>();

		int complex = 0;

		for( int i = 0; i < N; i++) {
			for( int j = 0; j < N; j++) {
				if( !visited[i][j] && region[i][j] == 1 ) {
					int cnt = 1;
					queue.offer(new Region_info(i,j));
					visited[i][j] = true;

					while(!queue.isEmpty()) {
						Region_info ri = queue.poll();

						for( int k = 0; k < 4; k++) {
							int cur_x = ri.x + dx[k];
							int cur_y = ri.y + dy[k];

							if( cur_y < 0 || cur_y >= N || 
									cur_x < 0 || cur_x >= N) {
								continue;
							}
							if(visited[cur_y][cur_x]) {
								continue;
							}
							if(region[cur_y][cur_x] == 1) {
								visited[cur_y][cur_x] = true;
								cnt++;
								queue.add(new Region_info(cur_y,cur_x));
								continue;
							}
						}
					} // while end
					list.add(cnt); // 리스트에 아파트 수 저장
					complex++; // 단지 넘버 + 1
				} // 1일때 처리 end
			}
		} // for end

		System.out.println(complex);
		Collections.sort(list); // 정렬
		for( int i = 0; i < complex; i++) {
			System.out.println(list.get(i));
		}
		
	}
}
