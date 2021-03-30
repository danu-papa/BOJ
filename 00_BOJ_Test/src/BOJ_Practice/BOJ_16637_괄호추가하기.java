/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_16637_괄호추가하기 {
	private static int N, resMax;
	private static char[] problem;
	private static List<Character> operators;
	private static List<Integer> operands;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		problem = br.readLine().toCharArray();
		operators = new ArrayList<>();
		operands = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			if(i%2 == 0) operands.add((int)(problem[i]-'0'));
			else operators.add(problem[i]);
		} // input end
		
		resMax = Integer.MIN_VALUE;
		dfs(operands.get(0), 0);
		
		System.out.println(resMax);
	}
	
	private static void dfs(int res, int idx) {
		if(idx >= operators.size()) {
			resMax = Math.max(res, resMax);
			return ;
		}
		int res1 = calc(operators.get(idx), res, operands.get(idx+1));
		dfs(res1, idx+1);
		
		if(idx + 1 < operators.size()) {
			int res2 = calc(operators.get(idx + 1), operands.get(idx+1), operands.get(idx+2));
			dfs(calc(operators.get(idx), res, res2), idx+2);
		}
	}

	private static int calc(char operator, int op1, int op2) {
		switch(operator) {
			case '+':
				return op1 + op2;
			case '-':
				return op1 - op2;
			case '*':
				return op1 * op2;
		}
		return -1;
	}
}
