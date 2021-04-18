package BOJ_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Z {
	private static int res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int R = Integer.parseInt(input[1]);
		int C = Integer.parseInt(input[2]);
		
		res = 0;
		
		//  인덱스, 탐색할 전체 길이, R, C, 시작 x, 시작 y
		dfs((int)Math.pow(2, N), R, C, 0, 0);
		
	}

	private static void dfs(int n, int r, int c, int nr, int nc) {
		if(n == 1) {
			if(r == nr && c == nc) {
				System.out.println(res);
				System.exit(0);
			}
			res++;
			
			return;
		}
		
		// 탐색 하는 영역에 타겟이 있는 경우만 
		if(nr <= r && r <= (nr+n) && nc <= c && c <= (nc+n)) {
			dfs(n/2, r, c, nr, nc); // 왼
			dfs(n/2, r, c, nr, nc+n/2); // 왼 아래
			dfs(n/2, r, c, nr+n/2, nc); // 오
			dfs(n/2, r, c, nr+n/2, nc+n/2); // 오아래
		} else { // 없으면 그 영역만큼 더해줌
			res += n * n;
		}
	}
}	
