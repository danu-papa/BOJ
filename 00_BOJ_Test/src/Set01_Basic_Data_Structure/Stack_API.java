/**
 * 
 */
package Set01_Basic_Data_Structure;

import java.util.Scanner;
import java.util.Stack;

/**
 * 백준 10828
 * 스택의 기능을 API로 사용해보자
 * PUSH : 삽입
 * POP : 삭제
 * SIZE : 스택에 들어있는 정수 개수
 * EMPTY : 비었으면 1, 아니면 0
 * TOP : 스택 가장 위의 수를 출력. 없으면 -1 
 */
public class Stack_API {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		Stack<Integer> stack = new Stack<Integer>();
		
		for( int i = 0; i < N; i++) {
			String command = sc.next();
			
			switch ( command ) {
			case "push":
				stack.push(sc.nextInt());
				break;
			case "pop":
				if(stack.isEmpty()) {
					System.out.println("-1");
					continue;
				}
				System.out.println(stack.pop());
				break;
			case "size":
				if(stack.isEmpty()) {
					System.out.println("0");
					continue;
				}
				System.out.println(stack.size());
				break;
			case "empty":
				if(stack.isEmpty()) {
					System.out.println("1");
					continue;
				}
				System.out.println("0");
				break;
			case "top":
				if(stack.isEmpty()) {
					System.out.println("-1");
					continue;
				}
				System.out.println(stack.peek());
				break;
			}
		}
		sc.close();
	}
}
