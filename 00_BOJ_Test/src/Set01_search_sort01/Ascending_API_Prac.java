/**
 * 
 */
package Set01_search_sort01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 정렬하려는 숫자가 상당히 !!!! 크므로
 * 일반적으로 정렬을 하면 시간이 엄청엄청 걸린다
 * API를 적극 활용해보자
 */
public class Ascending_API_Prac{
	private static int N, numbers[];
	/** @param args*/
	public static void main(String[] args) throws IOException {
		Ascending_API_Prac aap = new Ascending_API_Prac();
		aap.ascending_API();
	}

	/**ArrayList와 Sort API로 구현 */
	private void ascending_API() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		for(int i = 0; i < N; i++) {
			array.add(Integer.parseInt(br.readLine()));
		}
		
		Collections.sort(array);
		
		for( int i = 0; i < array.size(); i++) {
			System.out.println(array.get(i));
		}
	}	
}
