/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1992 - 쿼드트리
 * 흑백 영상을 압축하여 표현하는 데이터 구조로 쿼드트리라는 방법이 있다.
 * 흰점을 나타내는0 검은 점을 나타내는 1로만 이루어진 영상(2차원배열) 
 * 영상 모두가 0이면 압축결과 0.
 * 모두 1이면 압축결과 1
 * 0과 1이 섞여있으면  왼쪽위, 오른쪽귀, 왼쪽아래, 오른쪽아래의
 * 4개의 영상으로 나누어 압축.
 * 압축 결과를 괄호를 묶어서 표현.
 * N은 언제나 2의 제곱수이며 1~64의 값을 가짐.
 * 분할정복을 이용하여 구현.
 */
public class BOJ_1992_QuadTree {
	private static int N; // 전체 영상(배열) 길이
	private static char map[][]; // 전체 정보 저장
	private static StringBuilder sb; // 최종 출력.
	public static void main(String[] args) throws Exception {
		// 읽는 속도 향상
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stt.nextToken());
		map = new char[N][];
		sb = new StringBuilder();
		
		for( int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine());
			map[i] = stt.nextToken().toCharArray(); // char배열로 저장
		}
		// 압축 시작, N = 2차원 배열의 길이, y좌표, x좌효
		compress(N, 0, 0);
		System.out.print(sb.toString());
	}

	/** 영상 압축*/
	private static void compress(int n, int y, int x) {
		char tmp = map[y][x]; // 압축해야할 영역의 처음 위치 문자 정보
		boolean flag = false; // 영역 내 모든 문자가 같은지 다른지 판단
		
		// 영역 내 모든 문자가 같은지 검사
		for( int i = y; i < y+ n; i++) {
			if(flag) break; // 문자가 다를 경우
			for( int j = x; j < x + n; j++) {
				if(tmp != map[i][j]) { // 처음 위치 문자와 이후의 문자가 하나라도 다르면 안된다.
					flag = true; // 다른경우 
					break; // 멈춤
				}
			}
		}
		if(!flag) { // 문자가 모두 같은 경우에
			sb.append(tmp); // 그냥 합침
			return;
		} else { // 문자가 하나라도 다른 경우
			int size = n/2; // 4등분을 한 다음 검사
			sb.append("("); // 4등분 되는 시점에 괄호가 생긴다.
			for( int i = 0; i < 2; i++) { 
				for( int j = 0; j < 2; j++) {
					compress(size, y + size*i, x + size*j); // 절반 길이와, y, x 영역 좌표로 다시 압축 시도 
				}
			}
			sb.append(")"); // 4등분의 끝
		}
	}
}
