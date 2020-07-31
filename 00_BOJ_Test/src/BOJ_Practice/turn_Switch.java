package BOJ_Practice;

import java.util.Scanner;

public class turn_Switch {
	private static int N,switchs[],stu_n;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		switchs = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			switchs[i] = sc.nextInt();
		} // 스위치 정보 입력
		
		// 학생 수
		stu_n = sc.nextInt();
		
		for(int i = 1; i <= stu_n; i++) {
			int gender = sc.nextInt();
			int switch_num =  sc.nextInt();
			int pair = 0;
			switch ( gender ) {
			case 1:
				for( int j = 1; j <= N; j++) {
					if(j % switch_num == 0) {
						switchs[j] = switchs[j] == 0 ? 1 : 0;
					}
				}
				break;
			case 2:
				for( int j = 1; j <=N; j++) {
					if(switch_num+pair >= N+1 || switch_num-pair <= 0) break;
					if(switchs[switch_num+pair] == switchs[switch_num-pair]) {
						if(switchs[switch_num+pair] == 0) {
							switchs[switch_num+pair] = 1;
							switchs[switch_num-pair] = 1;
							pair++;
							continue;
						}
						switchs[switch_num+pair] = 0;
						switchs[switch_num-pair] = 0;
						pair++;
						continue;
					}
				}
				break;
			}
		}
		int cnt = 1;
		for( int i = 1; i <= N; i++) {
			System.out.print(switchs[i] + " ");

			if(cnt == 20) {
				System.out.println();
				cnt = 0;
			}
			cnt++;
		}
		sc.close();
	}// main end

}
