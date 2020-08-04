/**
 * 
 */
package Basic_Data_Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 백준 1406
 * 에디터의 기능을 구현해보자
 * 커서를 기준으로 오른쪽 왼쪽을
 * 자유자재로 이동해야 하므로
 * List를 이용해서 만들면 당연하다는 듯이
 * 시간초과가 나온다!!!!!
 * 인터넷 여러 사람들의 이야기를 보아하니....
 * 스택을 2개 사용해야 한다고 한다.
 * 생각 한 사람 천잰가!!?
 * 알고리즘 적 사고를 많이 키워야겠다.........
 */
public class Editor_Double_Stack {

	public static void main(String[] args) throws IOException{
		Editor_Double_Stack ep = new Editor_Double_Stack();
		ep.editor();
	}

	/** 에디터 구현 부*/
	private void editor() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String stream = br.readLine();
		
		Stack<Character> left_stack = new Stack<>();
		Stack<Character> right_stack = new Stack<>();
		
		//일단 커서가 가장 오른쪽에 있을 것이기 때문에 초기 문자열은 모두 왼쪽 스택에 쌓자
		for( int i = 0; i < stream.length(); i++){
			left_stack.add(stream.charAt(i));
		}
		
		int command_amount = Integer.parseInt(br.readLine());
		
		for( int i = 0; i < command_amount; i++) {
			String command = br.readLine();
			StringTokenizer stt = new StringTokenizer(command);
			
			switch( stt.nextToken() ) {
			// 왼쪽 이동 명령어가 들어오면 왼쪽 스택은 pop, 오른쪽 스택에 push
			case "L":
				if(!left_stack.isEmpty()) {
					right_stack.push(left_stack.pop());
				}
				
				break;
			// 오른쪽 이동 명령어가 들어오면 오른쪽 스택 pop, 왼쪽 스택 push
			case "D":
				if(!right_stack.isEmpty()) {
					left_stack.push(right_stack.pop());
				}
				break;
			// 커서 왼쪽 삭제이므로 왼쪽 스택 pop
			case "B":
				if(!left_stack.isEmpty()) {
					left_stack.pop();
				}
				break;
			// 커서 왼쪽 부분 삽입이므로 왼쪽 스택에 push
			case "P":
				char txt = stt.nextToken().charAt(0);
				left_stack.push(txt);
				break;
			}
		}
		// 출력. 스택은 FILO 구조이기도 하고, 왼쪽 , 오른쪽 스택의 내용이 순차적으로 나와야 하기 때문에
		// 왼쪽 스택의 내용을 모두 오른쪽 스택으로 옮기고 오른쪽 스택을 출력하자.
		while(!left_stack.isEmpty()) {
			right_stack.push(left_stack.pop());
		}
		while(!right_stack.isEmpty()) {
			System.out.print(right_stack.pop());
		}
	}
}
