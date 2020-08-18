/**
 * 
 */
package BOJ_Practice;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 1697 - 숨바꼭질
 * 걷는다면 x - 1 혹은 x + 1
 * 순간이동 한다면 2*x 위치로
 * 
 * +1 -1 *2 연산을 해서 숫자가 같아 지는 가장 최소의 경우.
 */
public class BOJ_1697_숨바꼭질 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int subin = sc.nextInt(); // 수빈이의 현재 위치
		int bro = sc.nextInt(); // 동생의 위치
		int cnt = 0;

		// 총 10만개의 위치좌표를 가지고 방문 여부를 판단.
		boolean visited[] = new boolean[100001];
		
		Queue<Integer> subin_Pos_Queue = new LinkedList<Integer>();
		subin_Pos_Queue.offer(subin); // 수빈이의 최초 위치를 넣어줌.
		visited[subin] = true;
		
		loop:
		while( !subin_Pos_Queue.isEmpty() ) {
			int size = subin_Pos_Queue.size();
			
			//큐 레벨별로 돌리기.
			for( int i = 0; i < size; i++) {
				int tmp = subin_Pos_Queue.poll();
				
				if( tmp == bro) break loop; // 같은 경우 탈출
				
				if(tmp - 1 >= 0 ) // 범위를 벗어나지 않고
				if(!visited[tmp - 1]) { // 방문하지 않은 경우에만
					subin_Pos_Queue.offer(tmp-1); // x-1 위치를 큐에 넣는다
					visited[tmp-1] = true; // 해당 위치 방문
				}
				
				if(tmp + 1 <= 100000) // 범위 벗어나지않고
				if(!visited[tmp + 1]) { // 방문하지 않았으면
					subin_Pos_Queue.offer(tmp+ 1); // x+1위치를 큐에 넣는다
					visited[tmp + 1] = true; // 해당 위치 방문
				}
				
				if(tmp * 2 <= 100000) // 범위 벗어나지 않고
				if(!visited[tmp * 2]) { // 방문하지 않았으면
					subin_Pos_Queue.offer(tmp * 2); // 2x 위치리를 큐에 넣는다
					visited[tmp * 2] = true; // 해당위치 방문
				}
			}
			cnt++; // 적절히 위치를 옮겼고, 아직 동생을 만나지 못한 경우에 카운트 +1
		}
		System.out.println(cnt);
		sc.close();
	}
}
