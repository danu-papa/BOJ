/**
 * 
 */
package search_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

import sun.util.locale.StringTokenIterator;

/**
 * 백준 10815
 * 상근이가 가지고 있는 숫자 찾기
 * 숫자가 엄청나게 크고 많으므로
 * 2진 탐색을 이용해서 찾아보자
 */
public class Find_Num_Binary_Search {
	private static int N, M;

	/** @param args*/
	public static void main(String[] args) throws IOException {
		Find_Num_Binary_Search fnbs = new Find_Num_Binary_Search();
		fnbs.input();
	}

	/** 입력 부분 구현 부*/
	private void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // N 개의 수 입력
		StringTokenizer stt = new StringTokenizer(br.readLine());
		ArrayList<Integer> numberlist = new ArrayList<Integer>();

		// 배열 입력
		for ( int i = 0; i < N; i++) {
			numberlist.add(Integer.parseInt(stt.nextToken()));
		}

		// 정렬
		Collections.sort(numberlist);
		
		M =  Integer.parseInt(br.readLine()); // 찾아야 할 숫자 갯수 입력
		stt = new StringTokenizer(br.readLine()); // 재 입력
		
		// 2진 탐색
		for ( int i = 0; i < M; i++) {
			int key = Integer.parseInt(stt.nextToken());
			int result = binarySearch(0,N,numberlist,key);
			System.out.print(result + " ");
		}
	} // input end

	/** 2진 탐색 구현 부*/
	private int binarySearch(int start, int end, ArrayList<Integer> arr, int find) {
		while(start < end) {
			int mid = (start + end) / 2;
			if(arr.get(mid) == find) {
				return 1;
			}
			else if( arr.get(mid) < find) {
				start = mid + 1;
			}
			else if( arr.get(mid) > find){
				end = mid;
			}
		}
		return 0;
	} // binarySearch end
}