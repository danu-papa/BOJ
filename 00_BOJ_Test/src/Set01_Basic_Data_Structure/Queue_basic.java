/**
 * 
 */
package Set01_Basic_Data_Structure;

import java.util.Scanner;

/**
 * 백준 10845
 * 큐의 기능을 구현해보자
 * PUSH : 삽입
 * POP : 삭제, 출력
 * SIZE : 스택에 들어있는 정수 개수 출력
 * EMPTY : 비었으면 1, 아니면 0
 * FRONT : 큐 가장 앞의 수를 출력. 없으면 -1
 * BACK : 큐 가장 뒤의 수를 출력, 없으면 -1 
 */
public class Queue_basic {
	private static int N, front, end, queue[];
	private static Scanner sc;
	/** @param args*/
	public static void main(String[] args) {
		Queue_basic qb = new Queue_basic();
		qb.queue();
	}
	
	/** 전체 흐름*/
	private void queue() {
		init();

		for( int i = 0; i < N; i++) {
			String command = sc.next();

			switch ( command ) {
			case "push":
				push(sc.nextInt());
				break;
			case "pop":
				dequeue();
				break;
			case "size":
				size();
				break;
			case "empty":
				isEmpty();
				break;
			case "front":
				front();
				break;
			case "back":
				back();
				break;
			}
		}
		sc.close();
	}

	/** 초기화*/
	private void init() {
		sc = new Scanner(System.in);
		N = sc.nextInt();
		queue = new int[N];
		front = 0;
		end = 0;
	}
	
	/** 큐가 비어있는지 확인*/
	private static void isEmpty() {
		if(front == end) {
			System.out.println("1");
			return ;
		}
		System.out.println("0");
	}

	/** 큐 크기 출력*/
	private static void size() {
		if(front == end) {
			System.out.println("0");
			return ;
		}
		System.out.println(end - front);
	}

	/** 큐 가장 앞 원소 출력*/
	private static void front() {
		if( front == end) {
			System.out.println("-1");
			return ;
		}
		System.out.println(queue[front+1]);
	}
	
	/** 큐 가장 뒤 원소 출력*/
	private static void back() {
		if( front == end) {
			System.out.println("-1");
			return ;
		}
		System.out.println(queue[end]);
	}

	/** 큐  가장 앞 원소 출력 & 삭제*/
	private static void dequeue() {
		if( front == end) {
			System.out.println("-1");
			return ;
		}
		int top_element = queue[front+1];
		queue[front++] = 0;
		System.out.println(top_element);
	}

	/** 큐에 저장*/
	private static void push(int input) {
		queue[++end] = input;
	}

}
