/**
 * 
 */
package Set01_Basic_Data_Structure;

import java.util.Scanner;

/**
 * 백준 10828
 * 스택의 기능을 구현해보자
 * PUSH : 삽입
 * POP : 삭제
 * SIZE : 스택에 들어있는 정수 개수
 * EMPTY : 비었으면 1, 아니면 0
 * TOP : 스택 가장 위의 수를 출력. 없으면 -1 
 */
public class Stack_basic {
	private static int top, stack[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		stack = new int[N];
		top = -1;
		
		for( int i = 0; i < N; i++) {
			String command = sc.next();
			
			switch ( command ) {
			case "push":
				push(sc.nextInt());
				break;
			case "pop":
				pop();
				break;
			case "size":
				size();
				break;
			case "empty":
				isEmpty();
				break;
			case "top":
				peek();
				break;
			}
		}
		sc.close();
	}

	/** 비어있는지 확인*/
	private static void isEmpty() {
		if(top == -1) {
			System.out.println("1");
			return ;
		}
		System.out.println("0");
	}

	/** 스택 크기 출력*/
	private static void size() {
		if(top == -1) {
			System.out.println("0");
			return ;
		}
		System.out.println(top+1);
	}

	/** 스택 가장 상위 원소 출력*/
	private static void peek() {
		if( top == -1) {
			System.out.println("-1");
			return ;
		}
		System.out.println(stack[top]);
	}

	/** 스택 가장 상위 원소 출력 & 삭제*/
	private static void pop() {
		if( top == -1) {
			System.out.println("-1");
			return ;
		}
		int top_element = stack[top];
		stack[top--] = 0;
		System.out.println(top_element);
	}

	/** 스택에 저장*/
	private static void push(int input) {
		stack[++top] = input;
	}

}
