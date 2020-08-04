/**
 * 
 */
package Set01_search_sort01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 백준 10989 - 수 정렬하기 
 * 정말 정말 수가 많고
 * 중복 값도 정말 많아서 
 * 정렬을 하면 시간 초과가 쉽게 난다!!
 * API도 건들지 못함.
 * 숫자가 1~ 10000까지이므로 들어오면서 카운트
 * 따로 정렬을 생각하지 않고, 배열에 들어온 숫자 그대로 사용하자
 * Scanner 금지!!!
 * 세상에 Sysyem.out.println도 금지
 */
public class Ascending_sequence_practice {

	private static int N;
	public static void main(String[] args)  throws IOException{
		Ascending_sequence_practice asp = new Ascending_sequence_practice();
		asp.ascending();
	}

	/** 오름차순 정렬 구현 부*/
	private void ascending() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] numbers = new int[10001]; // 0 초기화
		N = Integer.parseInt(br.readLine());

		for( int i = 0; i < N; i++) {
			numbers[Integer.parseInt(br.readLine())]++; // 1~10000사이의 수 자리 그대로 ++
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for( int j = 1; j <= 10000; j++) {
			if(numbers[j] > 0) {// 수가 하나도 없는 경우 점프
				for(int k = 0; k < numbers[j]; k++) {
					bw.write(Integer.toString(j) + "\n"); // 수가 있다면 그 수만큼 찍어주자
					//bw.flush();
				}
			}
		}
		br.close();
		bw.close();
	}
}
