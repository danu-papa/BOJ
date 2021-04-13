/**
 * 
 */
package samsung_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author YSM
 *
 */
public class 연산자끼워넣기 {
	private static int MIN, MAX;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer stt = new StringTokenizer(br.readLine());
		
		int[] numbers = new int[N];
		
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(stt.nextToken());
		}
		
		int[] op = new int[4];
		stt = new StringTokenizer(br.readLine());
		int op_count = 0;
		
		for(int i = 0; i < 4; i++) {
			int cnt = Integer.parseInt(stt.nextToken());
			op_count += cnt;
			op[i] = cnt;
		}
		
		int[] selected = new int[op_count];
		MIN = Integer.MAX_VALUE;
		MAX = Integer.MIN_VALUE;
		process(0, selected, numbers, op, op_count);
		
		System.out.println(MAX);
		System.out.println(MIN);
	}

	private static void process(int idx, int[] selected, int[] numbers, int[] op, int size) {
		if(idx == size) {
			calc(selected, numbers, op);
			return ;
		}
		for(int i = 0; i < 4; i++) {
			if(op[i] == 0) continue;
			selected[idx] = i;
			op[i]--;
			process(idx+1, selected, numbers, op, size);
			op[i]++;
		}
	}

	private static void calc(int[] selected, int[] numbers, int[] op) {
		int idx = 0;
		int res = 0;
		int[] tmp = new int[numbers.length];
		
		for(int i = 0; i < numbers.length; i++) {
			tmp[i] = numbers[i];
		}
		
		for(int sel : selected) {
			switch(sel) {
			case 0:
				res = tmp[idx] + tmp[idx+1];
				break;
			case 1:
				res = tmp[idx] - tmp[idx+1];
				break;
			case 2:
				res = tmp[idx] * tmp[idx+1];
				break;
			case 3:
				res = tmp[idx] / tmp[idx+1];
				break;
			}
			tmp[idx+1] = res;
			idx++;
		}
		MIN = MIN < res ? MIN : res;
		MAX = MAX > res ? MAX : res;
	}
}
