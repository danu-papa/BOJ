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
		
		int subin = sc.nextInt();
		int bro = sc.nextInt();
		int cnt = 0;

		boolean visited[] = new boolean[100001];
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(subin);
		visited[subin] = true;
		
		loop:
		while( !q.isEmpty() ) {
			int size = q.size();
			
			//큐 레벨별로 돌리기
			for( int i = 0; i < size; i++) {
				int tmp = q.poll();
				
				if( tmp == bro) break loop; 
				
				if(tmp - 1 >= 0 ) 
				if(!visited[tmp - 1]) {
					q.offer(tmp-1);
					visited[tmp-1] = true;
				}
				
				if(tmp + 1 <= 100000)
				if(!visited[tmp + 1]) {
					q.offer(tmp+ 1);
					visited[tmp + 1] = true;
				}
				
				if(tmp * 2 <= 100000)
				if(!visited[tmp * 2]) {
					q.offer(tmp * 2);
					visited[tmp * 2] = true;
				}
			}
			cnt++;
		}
		System.out.println(cnt);
		sc.close();
	}
}
