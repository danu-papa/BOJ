/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1931번 - 미팅룸 예약하기
 * 종료 시간을 기준으로 정렬
 * Comparable 을 상속받아 오버라이딩 구현
 */
public class BOJ_1931_MeetingRoom {

	static class Resv_room implements Comparable<Resv_room>{
		int start, end;

		public Resv_room() {}
		public Resv_room(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Resv_room o) {
			if(this.end == o.end) {
				return this.start - o.start;
			}
			return this.end - o.end;
		}

	}

	private static Resv_room rr[];

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(stt.nextToken());
		rr = new Resv_room[N];

		for( int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(stt.nextToken());
			int end  = Integer.parseInt(stt.nextToken());
			rr[i] = new Resv_room(start, end);
		}
		// 끝나는 시간 기준으로 오름차순 정렬
		Arrays.sort(rr);
		
		//result = 가능한 집합의 수
		//start = 시작 지점
		int result=1, start=0;
		int maxValue = 0;
		
		/**
		 * 끝나는 시점을 기준으로 오름차순 정렬을 했기 때문에
		 * 처음부터 선택해서 마지막까지 계속 선택하는 것이
		 * 최선의 결과 Greedy
		 */
		for( int i = 0; i < rr.length; i++) {
			if(i+1 == rr.length) break;
			if(rr[start].end <= rr[i+1].start) {
				start = i+1;
				result++;
			}
		}
		maxValue = result > maxValue ? result : maxValue;

		System.out.println(maxValue);
	}
}
