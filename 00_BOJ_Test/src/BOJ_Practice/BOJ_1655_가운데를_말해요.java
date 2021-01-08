/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class BOJ_1655_가운데를_말해요 {
	public static void main(String[] args) throws IOException {
		// 속도를 높히기 위해서 무조건 bufferedReader / BufferedWriter를 써야한다
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		/** 
		 * step 1
		 * 중간 값을 기준으로 작은 쪽과 큰 쪽을 나누기 위해
		 * 힙 구조를 가진 두개의 우선순위 큐를 이용한다
		 */
		PriorityQueue<Integer> pQueueRight = new PriorityQueue<Integer>(); 
		PriorityQueue<Integer> pQueueLeft = new PriorityQueue<Integer>((o1,o2)->(o2-o1));
		
		// 가장 처음 값을 중간 값으로 선정
		int mid = Integer.parseInt(br.readLine());
		bw.write(String.valueOf(mid)); // 처음 값은 바로 출력해주면 된다
		bw.newLine(); // BufferedWriter는 줄 바꿈을 자동으로 해주지 않기 때문에 입력해주자
		
		/**
		 * step 2
		 * 중간 값을 계속 갱신해가면서 왼쪽 오른쪽 우선순위 큐를 갱신해간다
		 * */
		for(int i = 1; i < N; i++) {
			int next = Integer.parseInt(br.readLine());
			
			if(mid > next) { // 중간 값이 다음 숫자보다 큰경우
				pQueueLeft.offer(next); // 다음 숫자를 왼쪽으로
				pQueueRight.offer(mid); // 중간 값을 오른쪽으로
			} else { // 반대의 경우
				pQueueLeft.offer(mid);
				pQueueRight.offer(next);
			}
			
			/**
			 * step 3
			 * 계속해서 중간 값을 구하기 위해 항상 왼쪽과 오른쪽의 큐 사이즈는 1로 유지하고자 한다.
			 */
			int leftLength = pQueueLeft.size();
			int rightLength = pQueueRight.size();
			
			if(leftLength - rightLength > 1) { // 왼쪽이 오른쪽보다 2이상 큰 경우
				pQueueRight.offer(pQueueLeft.poll()); // 왼쪽 숫자를 하나 빼서 오른쪽으로 옮겨준다
			} else if(rightLength - leftLength > 1) { // 반대의 경우
				pQueueLeft.offer(pQueueRight.poll());
			}
			
			/**
			 * step 4
			 * 왼쪽과 오른쪽중에 길이가 더 긴 쪽에 있는 값이 중간 값이다.
			 * 만약 길이가 같은 경우(짝수) 더 작은 숫자가 중간 값이 된다.
			 * */
			if(leftLength > rightLength) {
				mid = pQueueLeft.poll();
			} else if(leftLength < rightLength){
				mid = pQueueRight.poll();
			} else {
				mid = pQueueRight.peek() < pQueueLeft.peek() ? pQueueRight.poll() : pQueueLeft.poll();
			}
			
			/** 
			 * step 5 
			 * 중간 값 출력
			 * */
			bw.write(String.valueOf(mid));
			bw.newLine();
		}
		bw.flush();
	}
}