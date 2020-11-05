/**
 * 
 */
package BOJ_Practice;

import java.util.Scanner;
//더 걸림
public class BOJ_14500_테트로미노 {

	static int[][] map;
	static int N=10,M;
	static int[] dr={-1,0,1,0};
	static int[] dc={0,-1,0,1};
	static int max;
	static boolean[][] visited;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		M=scann.nextInt();
		map=new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j]=scann.nextInt();
			}
		}
		max=-1000000;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i,j,0,0);
				dfs2(i,j);
			}
		}
		
		System.out.println(max);
	}

	static void dfs(int tr, int tc, int cnt, int sum) {
		//if(!check(tr, tc)){ return ;}
		//mm[tr][tc]=1;
		if(cnt==4){
			max = Math.max(max, sum);
/*			for (int i = 0; i <N; i++) {
				for (int j = 0; j < M; j++) {
					System.out.print(mm[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println("-------------------");*/
			return ;
		}
		for (int d = 0; d < 4; d++) {
			int nr=tr+dr[d];
			int nc=tc+dc[d];
			if(!check(nr, nc)){ continue ;}
			if(visited[nr][nc]){ continue ;}
			visited[nr][nc] = true;
			//mm[nr][nc]=1;
			dfs(nr,nc,cnt+1, sum+map[nr][nc]);
			visited[nr][nc] = false;
			//mm[nr][nc]=0;
		}
	}
	static void dfs2(int tr, int tc) {
		int min = Integer.MAX_VALUE;
	    int sum = map[tr][tc];
		//if(!check(tr, tc)){ return ;}
		//mm[tr][tc]=1;
		int sabang=4;
		//int temr=tr;
		//int temc=tc;
		for (int d = 0; d < 4; d++) {
			int nr=tr+dr[d];
			int nc=tc+dc[d];
			if (sabang <= 2) return;
			if(!check(nr, nc)){ sabang--;  continue ;}
			//mm[nr][nc]=1;
			if(min>map[nr][nc]){
				min=map[nr][nc];
				//temr=nr;
				//temc=nc;
			}
			sum+=map[nr][nc];
		}
		//mm[temr][temc]=0;
		if (sabang == 4) {
          sum = sum - min;
      }
      max = Math.max(max, sum);
/*	    for (int i = 0; i <N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(mm[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("-------------------");*/
	}
	static boolean check(int r, int c){
		return r>=0 && r<N && c>=0 && c<M;
	}
}

