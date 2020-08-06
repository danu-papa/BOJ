/**
 * 
 */
package BOJ_Practice;

import java.util.Scanner;

/**
 * 백준 5585 - 거스름돈 구하기
 * 500 100 50 10 5 1 
 * 숫자에 딱 맞게 나누어 주기
 * 500원부터 가장 큰 숫자의 단위의 거스름돈 부터 계산하면
 * 뒤로 갈 수록 수가 작아지므로 선택할 잔돈의 수도 줄 것이라는
 * Greedy Algorithm이용
 */
public class BOJ_5585_Penny {
	private static int MONEY = 1000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int input = sc.nextInt();
		
		int penny = MONEY - input;
		
		int five_hundred = (int)penny/500;
		penny = penny%500;
		int one_hundred = (int)penny/100;
		penny = penny%100;
		int fifty = (int)penny/50;
		penny = penny%50;
		int ten = (int)penny/10;
		penny = penny%10;
		int five = (int)penny/5;
		penny = penny%5;
		if(penny < 0) penny = 0;
		int one = penny;
		
		System.out.println(five_hundred + one_hundred + fifty + ten + five + one);
		sc.close();
	}

}
