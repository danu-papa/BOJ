/**
 * 
 */
package BOJ_Practice;

import java.util.Scanner;

/**
 * 정보 올림피아드 1863 문제
 * 학생들이 가진 종교 수 구하기
 * 서로소 집합을 이용하자
 */
public class JO_1863_Religion {
	private static int[] parents; // 집합을 나타낼 배열
	public static void main(String[] args) {
		JO_1863_Religion reli = new JO_1863_Religion();
		reli.disjoint();
	}

	/** 서로소 집합을 이용해보자*/
	private void disjoint() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		make(n); // 서로소 만들기
		int result = 0;
		
		for( int i = 0; i < m; i++) {
			int first = sc.nextInt();
			int sec = sc.nextInt();
			
			union(first,sec); // 합집합 만들기
		}
		
		result = amount_religion();
		System.out.println(result);
		
		sc.close();
	}

	/** 종교의 개수를 리턴해주자*/
	private int amount_religion() {
		int amount = 0;
		for( int i = 1; i < parents.length; i++) {
			if(parents[i] == i) amount++; // 자신의 집합이 대표자인 경우만 출력
		}
		return amount;
	}

	/** 합집합 만들기*/
	private void union(int first, int sec) {
		int aroot = find(first); // 자신이 속한 집합을 구한다.
		int broot = find(sec);
		
		if(aroot != broot) { // 서로 다른 집합이라면
			parents[broot] = aroot; // 하나로 합쳐준다
		}
	}

	/** 조상 노드 찾기*/
	private int find(int num) {
		if(parents[num] == num) return num;
		return parents[num] = find(parents[num]);
	}

	/** 초기 서로소 집합*/
	private void make(int n) {
		parents = new int[n+1];
		
		for( int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}
}
