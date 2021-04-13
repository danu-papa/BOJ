/**
 * 
 */
package samsung_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class 컨베이어벨트위의로봇 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		
		int[] belt = new int[2*N];
		
		StringTokenizer stt = new StringTokenizer(br.readLine());
		for(int i = 0; i < 2*N; i++) {
			belt[i] = Integer.parseInt(stt.nextToken());
		} // input end
		
		// belt 정보 , crack 수, 벨트의 길이
		int res = process(belt, K, 2*N);
		
		System.out.println(res);
	}

	private static int process(int[] belt, int k, int size) {
		int crack = 0;
		int step = 0;
		int half = size/2; // 로봇이 내려가는 위치
		Queue<Integer> robot_queue = new LinkedList<>(); // 로봇이 순서대로 이동하므로 Queue를 이용
		boolean[] isrobot = new boolean[size]; // 현재 위치에 로봇이 있는지 판단
		
		while(crack < k) { // 망가진 벨트의 수가 k보다 작을때만 반복
			step++;
			int robot_cnt = robot_queue.size(); // size별로 수행
			// step 01. 벨트 한칸 회전
			int pre = belt[size-1]; // 가장 끝 
			for(int i = 0; i < size; i++) { // 한칸씩 앞으로 이동하며 갱신
				int cur = belt[i];
				belt[i] = pre;
				pre = cur;
			}
			
			// 로봇위치도 이동, 여기서 내려가는 위치라면 Queue에서 빼준다.
			int cnt = 0;
			while(robot_cnt > cnt) {
				cnt++;
				int pos = robot_queue.poll();
				isrobot[pos] = false;
				int next_pos = (pos+1)%size;
				if(next_pos == half) continue; // 내려가는 위치라면 큐에 넣지않는다.
				robot_queue.offer(next_pos);
				isrobot[next_pos] = true;
			}
			
			// 위에서 큐의 길이에 변동이 생겼을 수도 있다
			robot_cnt = robot_queue.size();
			// step 02. 이동 시킬 로봇이 있다면 이동시킨다. 여기서 내려가는 위치에 도착하면 Queue에서 뺀다.
			if(robot_cnt != 0) {
				cnt = 0;
				while(robot_cnt > cnt) {
					cnt++;
					int pos = robot_queue.poll();
					isrobot[pos] = false;
					int next_pos = (pos+1)%size;
					if(next_pos == half) continue;
					// 벨트의 내구도가 남아있고, 로봇이 없는 상태.
					if(belt[next_pos] != 0 && !isrobot[next_pos]) {
						if(--belt[next_pos] == 0) {
							++crack;
						}
						
						robot_queue.offer(next_pos);
						isrobot[next_pos] = true;
					} else { // 이동 불가능한 상태
						robot_queue.offer(pos);
						isrobot[pos] = true;
					}
				}
			}
			
			// step 03. 로봇을 하나 올린다. 0번 자리에 내구도가 남아있고, 로봇이 없는 경우
			if(belt[0] != 0 && !isrobot[0]) {
				robot_queue.offer(0);
				isrobot[0] = true;
				if(--belt[0] == 0) {
					++crack;
				}
			}
		}
		return step;
	}
}
