/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 17406 - 배열돌리기 4
 * r,c,s가 주어진다.
 * (r,c)위치에서 s크기만큼 8방향의 배열을 한칸씩 이동시켜 회전한다.
 * 여러개의 회전연산이 주어질때 배열의 한 행의 합이 최소인 경우를 출력하라
 *
 */
class RotateInfo{
	int r, c, s;

	public RotateInfo(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}

public class BOJ_17406_Rotate_Array_4 {
	private static int N,M,K, Arr[][], minValue, tmpMin, selected[];
	private static boolean selected_rotate[];
	private static List<RotateInfo> rlist = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());
		K = Integer.parseInt(stt.nextToken());

		Arr = new int[N+1][M+1];
		selected_rotate = new boolean[K];
		selected = new int[K];

		for( int i = 1; i <= N; i++) {
			stt = new StringTokenizer(br.readLine());
			for( int j = 1; j <= M; j++) {
				Arr[i][j] = Integer.parseInt(stt.nextToken());
			}
		}

		// 회전 수 만큼 반복
		for( int i = 0; i < K; i++) {
			stt = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(stt.nextToken());
			int c = Integer.parseInt(stt.nextToken());
			int s = Integer.parseInt(stt.nextToken());

			rlist.add(new RotateInfo(r, c, s));
		}
		minValue = Integer.MAX_VALUE;
		tmpMin = Integer.MAX_VALUE;
		permutation(0);
		System.out.println(minValue);
	}

	/** 순열, 중복되지 않게 회전하는 경우를 구한다 */
	private static void permutation(int cnt) {
		if(cnt == K) {
			int[][]tmpArr = new int[N+1][M+1];
			
			for( int i = 1; i <= N; i++) {
				for(int j = 1; j <= M; j++) {
					tmpArr[i][j] = Arr[i][j];
				}
			}
			
			// 선택한 회전의 경우의 수로 회전 시작
			start_Rotate(tmpArr);
			
			// 저장된 최소값과 새로운 최소값 비교
			minValue = Math.min(tmpMin, minValue);
			return;
		}

		for( int i = 0; i < rlist.size(); i++) {
			if(!selected_rotate[i]) { // 이미 선택한 것은 선택 안함
				selected_rotate[i] = true;
				selected[cnt] = i;
				permutation(cnt+1);
				selected_rotate[i] = false;
			}
		}
	}


	/** 순열로 선택된 회전방법으로 최소값 구하기*/
	private static void start_Rotate(int[][] tmpArr) {
		tmpMin = Integer.MAX_VALUE;

		for( int i = 0; i < K; i++) { // 전체 회전방법의 수만큼 반복
			int sel = selected[i];
			int r = rlist.get(sel).r;
			int c = rlist.get(sel).c;
			int s = rlist.get(sel).s;

			// 회전 시작
			rotate(tmpArr, r, c, s);
		}
		
		for( int j = 1; j <= N; j++) {
			int sum = 0;
			for( int k = 1; k <= M; k++) {
				sum += tmpArr[j][k]; // 회전 하고 나온 배열의 열을 다 더해서 비교
			}
			tmpMin = Math.min(sum, tmpMin);
		}
	}

	/** 아......세상에... 조건 제대로 보자.. 진짜
	 * 1<= r-s < r < r+s <=N
	 * 1<= c-s < c < c+s <=M
	 * 이 조건 없이 하려니까 코드가 산으로가지!!!!!
	 * 바꾸는 방법
	 * ex) 1 2 3 4
	 * -> 처음 시작하면 pre에 가장 첫 값(1) 저장 cur 2저장 cur에 pre값 저장
	 * 	   1 1 3 4
	 * -> cur에 현재 값(3) 저장 현재 위치에 pre 저장
	 *     1 1 2 4
	 * -> cur에 현재 값(4) 저장 현재 위치에 pre 저장
	 *     1 1 2 3 ---> pre에 4를 가지고 다음 연산으로 넘어간다.
	 *     가장 앞 1은 마지막에 왼쪽 열연산에서 변경된다.
	 * */
	private static void rotate(int[][] tmpArr, int r, int c, int size) {
		for(int s = 1; s <= size; s++) { // 1부터 주어진 사이즈만큼 반복
			int x = c - s; // 왼쪽 위부터 시작
			int y = r - s;
			
			// 위쪽 행
			int pre = 0; // 이전값
			int cur = 0; // 현재 배열의 값

			pre = tmpArr[y][x]; // 최초 위치 저장
			for(int i = x+1; i <= x+s*2; i++) {
				cur = tmpArr[y][i]; // 현재 값 저장
				tmpArr[y][i] = pre; // 현재위치에 이전 값 저장
				pre = cur; // 이전 위치를 현재 값으로 변경
			}

			// 오른쪽 열
			x = c + s;
			y = r - s;
			for(int i = y+1; i <= y+s*2; i++) {
				cur = tmpArr[i][x]; // 현재 위치 값 저장
				tmpArr[i][x] = pre; // 이전값은 위의 연산에서 이어져 내려온다
				pre = cur;
			}

			// 아래 행
			x = c + s;
			y = r + s;
			for(int i = x-1; i >= x-s*2; i--) {
				cur = tmpArr[y][i];
				tmpArr[y][i] = pre;
				pre = cur;
			}

			// 왼쪽 열
			x = c - s;
			y = r + s;
			for(int i = y - 1; i >= y-s*2; i--) {
				cur = tmpArr[i][x];
				tmpArr[i][x] = pre;
				pre = cur;
			}
		}
	}
}
