/**
 * 
 */
package samsung_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class BOJ_14890_경사로 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stt.nextToken());
		int L = Integer.parseInt(stt.nextToken());

		int[][] map = new int[N][N];
		int[][] rmap = new int[N][N];

		for (int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				rmap[j][i] = map[i][j] = Integer.parseInt(stt.nextToken());
			}
		} // input end

		int res = process(N, L, map, rmap);
		System.out.println(res);
	}

	private static int process(int n, int l, int[][] map, int[][] rmap) {
		int max_cnt = 0;
		for (int i = 0; i < n; i++) {
			if (makeRoad(map[i], n, l))
				max_cnt++;
			if (makeRoad(rmap[i], n, l))
				max_cnt++;
		}
		return max_cnt;
	}

	private static boolean makeRoad(int[] map, int n, int l) {
		int beforeHeight = map[0];
		int count = 1;
		for (int j = 1; j < n; j++) {
			// 같은 높이
			if (beforeHeight == map[j]) {
				count++;
			}
			
			// 이전 칸이 한 칸 낮음
			else if (beforeHeight + 1 == map[j]) {
				if(count < l) return false;
				beforeHeight++;
				count = 1;
			}

			// 이전 칸이 한 칸 높음
			else if (beforeHeight - 1 == map[j]) {
				// 앞으로 평지의 수를 세어야 한다.
				int remain = 0;
				for(int k = j; k < n; k++) {
					if(beforeHeight-1 != map[k]) break;
					remain++;
				}
				if(remain < l) return false;
				
				// 설치 가능
				j += l-1;
				count = 0;
				beforeHeight--;
			}

			// 높이 차이 2이상
			else {
				return false;
			}
		}
		return true;
	}
}
