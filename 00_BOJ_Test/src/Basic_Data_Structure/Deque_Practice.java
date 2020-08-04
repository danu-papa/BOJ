/**
 * 
 */
package Basic_Data_Structure;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 백준 10866
 * Deque를 API를 활용해서 구현해 보자
 */
public class Deque_Practice {
	private static int N;
	private static Scanner sc;
	public static void main(String[] args) {
		Deque_Practice dp = new Deque_Practice();
		dp.deque();
	}

	/** 덱 구현 부*/
	private void deque() {
		init();
		
		Deque<Integer> deque = new LinkedList<>();
		
		for( int i = 0; i < N; i++) {
			String command = sc.next();

			switch ( command ) {
			case "push_front":
				deque.offerFirst(sc.nextInt());
				break;
			case "push_back":
				deque.offerLast(sc.nextInt());
				break;
			case "pop_front":
				if(deque.isEmpty()) {
					System.out.println("-1");
					continue;
				}
				System.out.println(deque.pollFirst());
				break;
			case "pop_back":
				if(deque.isEmpty()) {
					System.out.println("-1");
					continue;
				}
				System.out.println(deque.pollLast());
				break;
			case "size":
				if(deque.isEmpty()) {
					System.out.println("0");
					continue;
				}
				System.out.println(deque.size());
				break;
			case "empty":
				if(deque.isEmpty()) {
					System.out.println("1");
					continue;
				}
				System.out.println("0");
				break;
			case "front":
				if(deque.isEmpty()) {
					System.out.println("-1");
					continue;
				}
				System.out.println(deque.peekFirst());
				break;
			case "back":
				if(deque.isEmpty()) {
					System.out.println("-1");
					continue;
				}
				System.out.println(deque.peekLast());
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
