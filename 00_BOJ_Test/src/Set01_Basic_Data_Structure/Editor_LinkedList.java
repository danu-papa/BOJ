/**
 * 
 */
package Set01_Basic_Data_Structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 백준 1406
 * 에디터의 기능을 구현해보자
 * 커서를 기준으로 오른쪽 왼쪽을
 * 자유자재로 이동해야 하므로
 * List를 이용해서 만들어보자
 * size 계속해서 호출해서
 * 시간초과
 * 다른 방법을 찾아야 할 듯 
 */
public class Editor_LinkedList {

	public static void main(String[] args) throws IOException{
		Editor_LinkedList ep = new Editor_LinkedList();
		ep.editor();
	}

	/** 에디터 구현 부*/
	private void editor() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String stream = br.readLine();
		
		ArrayList<Character> list = new ArrayList<>();
		for( int i = 0; i < stream.length(); i++){
			list.add(stream.charAt(i));
		}
		
		int command_amount = Integer.parseInt(br.readLine());
		int cursur = 0;
		
		for( int i = 0; i < command_amount; i++) {
			String command = br.readLine();
			StringTokenizer stt = new StringTokenizer(command);
			
			
			
			switch( stt.nextToken() ) {
			case "L":
				if(cursur + list.size() == 0) {
					continue;
				}
				cursur--;
				break;
			case "D":
				if(cursur == 0) {
					continue;
				}
				cursur++;
				break;
			case "B":
				if(list.size() + cursur == 0 ) continue;
				
				list.remove(list.size() + cursur);
				break;
			case "P":
				char txt = stt.nextToken().charAt(0);
				list.add(list.size() + cursur, txt);
				break;
			}
		}
		for( int i = 0; i < list.size(); i++)
		System.out.print(list.get(i));
	}
}
