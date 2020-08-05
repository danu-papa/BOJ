/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 백준 2667 - 단지 번호 붙이기
 * 1 : 집이 있는 곳, 0 : 집 없는 곳
 * 상 하 좌 우의 집만 같은 단지로 본다
 * 각 단지에 해당하는 집의 수를 세어 오름차순으로 정렬하여 출력
 * DFS를 이용
 */
public class BOJ_2667_Numbering_region_DFS {

	private static int N, region[][];
	private static boolean visited[][];
	private static int dx[] = {0, 0, -1, 1};
	private static int dy[] = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException{
		BOJ_2667_Numbering_region_DFS np = new BOJ_2667_Numbering_region_DFS();
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
		
		ArrayList<Integer> list = new ArrayList<>();
		
		int complex = 0;
		
		for( int i = 0; i < N; i++) {
			for( int j = 0; j < N; j++) {
				if(!visited[i][j] && region[i][j] == 1) {
					int cnt = dfs(i,j,1);
					complex++;
					list.add(cnt);
				}
			}
		}
		
		Collections.sort(list);
		System.out.println(complex);
		for(int i = 0; i < complex; i++) {
			System.out.println(list.get(i));
		}
	}

	/** Depth First Search */
	private int dfs(int y, int x, int cnt) {
		int count = cnt;
		visited[y][x] = true;
		for( int k = 0; k < 4; k++) {
			int cur_y = y + dy[k];
			int cur_x = x + dx[k];
			
			if( cur_y < 0 || cur_y >= N || 
					cur_x < 0 || cur_x >= N) {
				continue;
			}
			
			if(visited[cur_y][cur_x]) continue;
			if(region[cur_y][cur_x] != 1) continue;
			
			count += dfs(cur_y, cur_x, cnt);
		}
		return count;
	}
}
