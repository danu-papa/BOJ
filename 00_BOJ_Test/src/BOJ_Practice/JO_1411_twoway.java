/**
 * 
 */
package BOJ_Practice;

import java.util.Scanner;

/**
 * @author YSM
 *
 */
public class JO_1411_twoway {
	public static int[] memo = new int[1000001]; 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		int n = sc.nextInt(); sc.close(); 
		memo[1] = 1; 
		memo[2] = 3; 
		for(int i = 3; i <= n; i++) {
			memo[i] = ((memo[i-1]) + (2*memo[i-2]))%20100529; 
		} 
		System.out.println(memo[n]); 
	}
}
