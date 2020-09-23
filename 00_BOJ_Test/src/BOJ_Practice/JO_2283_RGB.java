/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 정보 올림피아드 2283 RGB마을
 *
 */
public class JO_2283_RGB {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] color = new int[3];
		int r, g, b, beforeR, beforeG, beforeB;
		
		StringTokenizer stt;
		for( int i = 0; i < N; i++) {
			stt = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(stt.nextToken());
			g = Integer.parseInt(stt.nextToken());
			b = Integer.parseInt(stt.nextToken());
			
			beforeR = color[0];
			beforeG = color[1];
			beforeB = color[2];
			
			// Red
			color[0] = r + Math.min(beforeG, beforeB);
			// Green
			color[1] = g + Math.min(beforeR, beforeB);
			// Blue
			color[2] = b + Math.min(beforeR, beforeG);
		}
		int min = Math.min(color[0], Math.min(color[1], color[2]));
		System.out.println(min);
	}
}
