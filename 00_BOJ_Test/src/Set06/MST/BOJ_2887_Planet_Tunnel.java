/**
 * 
 */
package Set06.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 2887 - 행성터널
 * 행성의 위치는 x,y,z 의 좌표로 주어짐.
 * 행성간 터널을 뚫는데, A(x1,y1,z1) - B(x2,y2,z2)가 주어지고
 * min(|x1-x2|,|y1-y2|,|z1-z2|)를 구하면 된다.
 * 예를들어 A(1,2,3) B(10, 2, 32) 인경우에는 y좌표가 같으므로 비용은 0이 된다.
 * 행성 10만개.. 좌표값 -10^9 ~ + 10^9
 * 안터질까 이거..
 * 
 * X좌표를 기준으로 정렬하고 Edge 추가
 * Y좌표를 기준으로 정렬하고 Edge 추가
 * Z좌표를 기준으로 정렬하고 Edge 추가
 * 
 * 모든 Edge에 대해서 Kruskal 적용
 */
public class BOJ_2887_Planet_Tunnel {
	static int N;
	static List<Planet> plist = new ArrayList<>();
	static List<Edge> elist = new ArrayList<>();
	static int[] parents;
	
	static class Planet{
		int x, y, z;
		int vertex_number;

		public Planet(int x, int y, int z,int vertex_number) {
			this.vertex_number = vertex_number;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stt.nextToken());
		
		for( int vertex = 0; vertex < N; vertex++) {
			stt = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(stt.nextToken());
			int y = Integer.parseInt(stt.nextToken());
			int z = Integer.parseInt(stt.nextToken());
			plist.add(new Planet(x,y,z,vertex));
		}

		// 가중치 정보 입력
		input_Edge_Info();
		// 입력 된 X,Y,Z좌표의 가중치 모두 정렬
		Collections.sort(elist);
		// 서로소 집합 만들기
		makeSet();
		int result = 0, cnt = 0;
		
		// 모든 간선에 대해서 kruskal 실행
		for ( Edge e : elist) {
			if(unionSet(e.from, e.to)) {
				result += e.weight;
				if(++cnt == N - 1) break;
			}
		}
		System.out.println(result);
	}

	/** 합집합 만들기*/
	private static boolean unionSet(int from, int to) {
		int aroot = findSet(from);
		int broot = findSet(to);
		
		// 이미 같은 집합이라면 false
		if( aroot == broot) return false;
		
		parents[broot] = aroot;
		return true;
	}

	/** 자신이 속한 집합찾기*/
	private static int findSet(int num) {
		if(parents[num] == num) return num;
		return parents[num] = findSet(parents[num]);
	}
	
	/** 가중치 정보 입력*/
	// for문 2번 돌리면 상당한 부하. 메모리가 오버한다.
	// x,y,z 하나씩 for문 하나로 반복하는 것이 더 효율적
	/*private static void input_Edge_Info() {
		for( int i = 0; i < N; i++) {
			for( int j = 0; j < N; j++) {
				if( i == j ) continue;
				int compare_x = Math.abs(plist.get(i).x - plist.get(j).x);
				int compare_y = Math.abs(plist.get(i).y - plist.get(j).y);
				int compare_z = Math.abs(plist.get(i).z - plist.get(j).z);
				if(compare_x < compare_y && compare_x < compare_z) {
					elist.add(new Edge(plist.get(i).vertex_number, plist.get(j).vertex_number, compare_x));
				}
				if(compare_y < compare_x && compare_y < compare_z) {
					elist.add(new Edge(plist.get(i).vertex_number, plist.get(j).vertex_number, compare_y));
				}
				if(compare_z < compare_y && compare_z < compare_x) {
					elist.add(new Edge(plist.get(i).vertex_number, plist.get(j).vertex_number, compare_z));
				}
			}
		}
	}/*

	/** 가중치 정보 입력*/
	private static void input_Edge_Info() {
		// 각각 x, y, z에 대해서 정렬하고 가중치를 저장하는 가능.
		// 람다식으로 한 번 표현해봤다.
		Collections.sort(plist,(o1,o2) -> o1.x - o2.x);
		for( int i = 0; i < N-1; i++) {
			int weight = Math.abs(plist.get(i).x - plist.get(i+1).x);
			elist.add(new Edge(plist.get(i).vertex_number, plist.get(i+1).vertex_number, weight));
		}
		
		Collections.sort(plist,(o1,o2) -> o1.y - o2.y);
		for( int i = 0; i < N-1; i++) {
			int weight = Math.abs(plist.get(i).y - plist.get(i+1).y);
			elist.add(new Edge(plist.get(i).vertex_number, plist.get(i+1).vertex_number, weight));
		}
		
		Collections.sort(plist,(o1,o2) -> o1.z - o2.z);
		for( int i = 0; i < N-1; i++) {
			int weight = Math.abs(plist.get(i).z - plist.get(i+1).z);
			elist.add(new Edge(plist.get(i).vertex_number, plist.get(i+1).vertex_number, weight));
		}
	}

	/** 서로소 만들기*/
	private static void makeSet() {
		parents = new int[N+1];
		for( int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
}
