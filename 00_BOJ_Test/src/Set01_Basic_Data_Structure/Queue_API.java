/**
 * 
 */
package Set01_Basic_Data_Structure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 백준 10845
 * 큐의 기능을 API를 이용해서 구현해보자
 * PUSH : 삽입
 * POP : 삭제, 출력
 * SIZE : 스택에 들어있는 정수 개수 출력
 * EMPTY : 비었으면 1, 아니면 0
 * FRONT : 큐 가장 앞의 수를 출력. 없으면 -1
 * BACK : 큐 가장 뒤의 수를 출력, 없으면 -1
 */
public class Queue_API {
	private static int N;
	private static Scanner sc;
	/** @param args*/
	public static void main(String[] args) {
		Queue_API qb = new Queue_API();
		qb.queue();
	}
	
	/** 전체 흐름*/
	private void queue() {
		init();
		Queue<Integer> queue = new LinkedList<Integer>();
		
		for( int i = 0; i < N; i++) {
			String command = sc.next();

			switch ( command ) {
			case "push":
				queue.offer(sc.nextInt());
				break;
			case "pop":
				if(queue.isEmpty()) {
					System.out.println("-1");
					continue;
				}
				System.out.println(queue.poll());
				break;
			case "size":
				if(queue.isEmpty()) {
					System.out.println("0");
					continue;
				}
				System.out.println(queue.size());
				break;
			case "empty":
				if(queue.isEmpty()) {
					System.out.println("1");
					continue;
				}
				System.out.println("0");
				break;
			case "front":
				if(queue.isEmpty()) {
					System.out.println("-1");
					continue;
				}
				System.out.println(queue.element());
				break;
			case "back":
				if(queue.isEmpty()) {
					System.out.println("-1");
					continue;
				}
				Object[] arr = queue.toArray();
				System.out.println(arr[queue.size() -1]);
				break;
			}
		}
		sc.close();
	}

	/** 초기화*/
	private void init() {
		sc = new Scanner(System.in);
		N = sc.nextInt();
	}
}
