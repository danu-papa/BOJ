package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA 5643 키순서
 * 자기 자신은 next_from 모두 보자
 * next or from으로 옮겨왔다면 한 뱡향으로만 가자
 * 모든 학생이 나온다면 자신의 키를 확실하게 알 수 있다!
 * */
public class SWEA_5643_키순서 {
	private static class Height{
		int vertex;

		public Height(int vertex) {
			this.vertex = vertex;
		}
	}
	private static int N, M, res;
	private static List<Height> To[];
	private static List<Height> From[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			To = new ArrayList[N+1];
			From = new ArrayList[N+1];
			
			for(int i = 1; i <= N; i++) {
				To[i] = new ArrayList<>();
				From[i] = new ArrayList<>();
			}
			
			StringTokenizer stt;
			for (int i = 0; i < M; i++) { // M번의 비교횟수
				stt = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(stt.nextToken());
				int to = Integer.parseInt(stt.nextToken());
				To[idx].add(new Height(to));
				From[to].add(new Height(idx));
			} // input end
			
			res = 0;
			process();
			
			System.out.println("#"+test_case+" "+res);
		} // test_case end
	}

	private static void process() {
		for(int i = 1; i <= N; i++) { // 모든 학생 체크
			boolean[] visited = new boolean[N+1];
			visited[i] = true; // 현재 학생 방문함
			List<Height> toes = To[i];
			for(int j = 0; j < toes.size(); j++) {
				Height height = toes.get(j);
					if(height.vertex != 0) { // 이전 학생이 있다면
						if(!visited[height.vertex]) {
						checkNext(height.vertex, visited);  // 모든 작은 학생을 체크
					} 
				}
			}
			
			List<Height> froms = From[i];
			for(int j = 0; j < froms.size(); j++) {
				Height height = froms.get(j);
					if(height.vertex != 0) { // 이전 학생이 있다면
						if(!visited[height.vertex]) {
						checkFrom(height.vertex, visited);  // 모든 작은 학생을 체크
					} 
				}
			}
			
			boolean flag = true;
			for(int v = 1; v <= N; v++) {
				if(!visited[v]) { // 한명이라도 포함이 안 된 경우
					flag = false;
					break;
				}
			}
			if(flag) {
				res++;
			}
		}
	}

	private static void checkFrom(int current, boolean[] visited) {
		List<Height> cur_height =  From[current];
		visited[current] = true;
		for(int i = 0; i < cur_height.size(); i++) {
			Height height = cur_height.get(i);
				if(height.vertex != 0) { // 이전 사람 있다면
					if(!visited[height.vertex]) { // 이미 방문 했으면 가지마
					visited[height.vertex] = true;
					checkFrom(height.vertex, visited);
				}
			}
		}
	}

	private static void checkNext(int current, boolean[] visited) {
		List<Height> cur_height =  To[current];
		visited[current] = true;
		for(int i = 0; i < cur_height.size(); i++) {
			Height height = cur_height.get(i);
				if(height.vertex != 0) { // 이전 사람 있다면
					if(!visited[height.vertex]) { // 이미 방문 했으면 가지마
					visited[height.vertex] = true;
					checkNext(height.vertex, visited);
				}
			}
		}
	}
}
