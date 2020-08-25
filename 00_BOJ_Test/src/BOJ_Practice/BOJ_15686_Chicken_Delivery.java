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
 * 백준 15686 치킨 배달
 * 크기 NxN 각 칸은 빈칸, 치킨집, 일반집 셋 중 하나
 * 0 = 빈칸, 1 = 일반집, 2 = 치킨집
 * 1,1 부터 시작
 * 치킨거리 = 집에서 가장 가까운 치킨집과의 거리
 * 도시의 치킨거리 = 치킨거리의 합
 * M개의 치킨집만 남기고 없앨 때 도시의치킨거리가 최소가 되는 경우를 구하라.
 * 
 * 처음에는 조합코드 밖에서 집집마다 치킨집과 비교를 하면 되겠지하는 생각을 먼저 했었다.
 * 그러면 전역으로 선택한 치킨집의 모든 경우의 가중치의 합을 관리해야하는 번거러움이 있어서
 * 조합 안에서 기저조건을 만족하는 시점에 2중 for문으로 한번에 처리했다. 
 */
public class BOJ_15686_Chicken_Delivery {
	private static int city_chicken_distance;
	private static int N, M, c_Length,h_Length, selected[];
	private static boolean isSelected[];
	private static List<Chicken> clist = new ArrayList<>();
	private static List<House> hlist = new ArrayList<>();

	static class Chicken { // 치킨집의 정보를 담을 클래스
		int y, x;
		public Chicken(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static class House { // 일반 집의 정보를 담을 클래스
		int y, x;
		public House(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stt.nextToken());
		M = Integer.parseInt(stt.nextToken());

		for( int i = 1; i <= N; i++) {
			stt = new StringTokenizer(br.readLine());
			for( int j = 1; j <= N; j++) {
				int tmp = Integer.parseInt(stt.nextToken());
				if( tmp == 1) { // 일반 집인 경우 hlist 저장 
					hlist.add(new House(i,j));
				} else if( tmp == 2) { // 치킨 집이면 clist 저장
					clist.add(new Chicken(i, j));
				}
			}
		}
		c_Length = clist.size(); // 치킨집의 수
		h_Length = hlist.size(); // 일반 집의 수
		city_chicken_distance = 10000;
		isSelected = new boolean[c_Length]; // 치킨 집 선택했는지
		selected = new int[M]; // 현재 선택 된 치킨집이 어딘지
		
		find_distance(0, 0); // 조합 DFS 수행

		System.out.println(city_chicken_distance);
	}

	/** 조합. DFS */
	private static void find_distance(int start, int cnt) {
		if(cnt == M) { // 남길 치킨집의 수와 같다면
			int sum = 0;
			for( int i = 0; i < h_Length; i++) { // 모든 일반 집과 비교
				int min = 10000;
				for( int j = 0; j < M; j++) { // 선택 된 치킨 집 모두 비교
					// 선택된 치킨집과 일반집과의 거리를 구해서 최소값을 구한다.
					int distance = getDistance(clist.get(selected[cnt-j-1]).x, clist.get(selected[cnt-j-1]).y, hlist.get(i).x, hlist.get(i).y);
					min = min > distance ? distance : min;
				}
				sum += min; // 최소값 누적.
			}
			// 가장 작은 최소값을 정한다.
			city_chicken_distance = sum > city_chicken_distance ? city_chicken_distance : sum;
			return ;
		}
		
		for( int i = start; i < c_Length; i++) {
			// 현재 선택 된 치킨집은 넘김
			if(isSelected[i]) continue;
			
			// 현재 치킨집을 선택한다.
			selected[cnt] = i;
			
			isSelected[i] = true;
			find_distance(i+1, cnt+1);
			isSelected[i] = false;
		}
	}

	/** 일반 집과 치킨 집과의 거리를 구하는 메서드*/
	private static int getDistance(int c_x, int c_y, int h_x, int h_y) {
		int dis = Math.abs(c_x - h_x) + Math.abs(c_y - h_y);
		return dis;
	}
}
