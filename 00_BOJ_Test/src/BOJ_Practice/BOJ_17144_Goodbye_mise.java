/**
 * 
 */
package BOJ_Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 17144 - 미세먼지여 안녕
 * R x C 크기.
 * 1번열에 공기청정기 설치. 두행 차지. ( 2x1 size, -1로 표시)
 * 청정기가 없는 칸에는 미세먼지가 있음. 미세먼지의 양은 Ar,c
 * 1초동안 아래와 같은 일이 발생.
 * 1. 미세먼지 확산. -> 미세먼지가 있는 모든 칸에서 동시발생.
 *   - 4방향으로 확산. 공기청정기가 있거나, 칸이 없다면 확산x
 *   - 확상양은 Ar,c/5 소수점 버림
 *   - 남은 미세먼지의 양은 Ar,c - (Ar,c/5)x(확산된개수)
 * 2. 공기청정기 작동
 *   - 공기청정기에서 바람 나옴. 
 *   - 청정기의 위쪽바람은 반시계방향 순환.
 *   - 청정기의 아래쪽 바람은 시계방향으로 순환.
 *   - 바람이 불면 바람의 방향으로 모두 한칸씩 이동.
 *   - 청정기로 들어간 미세먼지는 모두 정화
 *   
 *  T초가 지난후에 남은 미세먼지의 양을 구하자.
 *
 */
public class BOJ_17144_Goodbye_mise {
	// R: 열, C : 행, T: 시간 , room : 원본, change : 증감테이블
	private static int R, C, T, room[][], change[][];
	private static int dx[] = {0, 0, -1, 1}; // 상하좌우
	private static int dy[] = {-1, 1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stt = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stt.nextToken());
		C = Integer.parseInt(stt.nextToken());
		T = Integer.parseInt(stt.nextToken());
		room = new int[R+1][C+1];
		change = new int[R+1][C+1];

		for( int i = 1; i <= R; i++) {
			stt = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				room[i][j] = Integer.parseInt(stt.nextToken());
			}
		}
		
		// 청정기의 위치 저장.
		int y_aircon_pos = 0;
		for(int i = 1; i <= R; i++) {
			if(room[i][1] == -1) {
				y_aircon_pos = i;
				break;
			}
		}

		for (int i = 0; i < T; i++) { // 시간이 몇 초 지났는지.
			// 미세먼지 확산시키기
			spreadMise(y_aircon_pos);
			// 공기청정기 가동시키기
			removeMise(y_aircon_pos);

		}
		
		int totalMise = 0;
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				totalMise+= room[i][j];
			}
		}
		// 청정기 -2 한거 다시 +2
		System.out.println(totalMise+2);
	}

	/** 공기청정기를 가동하자. 위쪽영역은 반시계방향 아래쪽 영역은 시계방향 */
	private static void removeMise(int y_aircon_pos) {
		// 위쪽 반시계 방향
		move_upside(y_aircon_pos);

		// 아래쪽 시계 방향
		move_downside(y_aircon_pos+1);
	}

	/** 청정기의 아래쪽 공기를 순환시키자
	 * -1   위행
	 * 왼열      오른열
	 *   아래행
	 *  */
	private static void move_downside(int y_aircon_pos) {
		int pre = room[y_aircon_pos][2]; // 공기 청정기 바로 오른쪽 
		room[y_aircon_pos][2] = 0;
		int cur = 0;
		// 위 행
		for( int i = 3; i <= C; i++) {
			cur = room[y_aircon_pos][i];
			room[y_aircon_pos][i] = pre;
			pre = cur;
		}
		// 오른쪽 열
		for( int i = y_aircon_pos+1; i <= R; i++) {
			cur = room[i][C];
			room[i][C] = pre;
			pre = cur;
		}
		// 아래 행
		for( int i = C-1; i > 0; i-- ) {
			if(room[R][i] == -1) return;
			cur = room[R][i];
			room[R][i] = pre;
			pre = cur;
		}
		// 왼쪽 열
		for( int i = R-1; i > y_aircon_pos; i--) {
			cur = room[i][1];
			room[i][1] = pre;
			pre = cur;
		}
	}

	/** 공기청정기의 위쪽 부분의 공기를 순환시키자. 
	 *    위행
	 * 왼열      오른열
	 * -1  아래행  
	 * */
	private static void move_upside(int y_aircon_pos) {
		int pre = room[y_aircon_pos][2]; // 공기 청정기 바로 오른쪽 
		room[y_aircon_pos][2] = 0;
		int cur = 0;
		// 아래 행
		for( int i = 3; i <= C; i++ ) {
			cur = room[y_aircon_pos][i];
			room[y_aircon_pos][i] = pre;
			pre = cur;
		}
		// 오른쪽 열
		for( int i = y_aircon_pos-1; i > 0; i--) {
			cur = room[i][C];
			room[i][C] = pre;
			pre = cur;
		}
		// 위 행
		for( int i = C-1; i > 0; i--) {
			if(room[1][i] == -1) return;
			cur = room[1][i];
			room[1][i] = pre;
			pre = cur;
		}
		// 왼쪽 열
		for( int i = 2; i < y_aircon_pos; i++) {
			cur = room[i][1];
			room[i][1] = pre;
			pre = cur;
		}
	}

	/** 
	 * 미세먼지가 4방향으로 확산. 
	 * 퍼지는 양 = Ar,c/5
	 * 퍼지고 남은 양 = Ar,c - (Ar,c/5)*(확산수)
	 * */
	private static void spreadMise(int y_aircon_pos) {
		change = new int[R+1][C+1]; // 확산 시키기 전에 증감 테이블 초기화
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				// 빈곳 혹은 청정기가 아니라면
				if(room[i][j] != 0 && room[i][j] != -1) {
					int cnt = 0; // 몇번의 확산을 했는지 체크.
					for (int k = 0; k < 4; k++) {
						int next_y = i + dy[k];
						int next_x = j + dx[k];
						// 범위 판단
						if(outRange(next_y, next_x)) continue;
						// 청정기 판단
						if(room[next_y][next_x] == -1) continue;
						// 확산 가능한지 판단
						if(room[i][j] < 5 ) continue; 
						// 가능하다면 증감 확인
						change[next_y][next_x] += (int)(room[i][j]/5);
						cnt++; // 확산 시킨 수
					}
					// 증감 테이블 업데이트
					change[i][j] += room[i][j] - (int)(((room[i][j]/5)))*cnt;
				}
			}
		}
		// 원본 업데이트
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				room[i][j] = change[i][j];
			}
		}
		// 청정기 위치 재지정.
		room[y_aircon_pos][1] = -1;
		room[y_aircon_pos+1][1] = -1;
	} // spreadMise end

	/** y, x 좌표를 보고 범위를 벗어났는지 판단. */
	private static boolean outRange(int next_y, int next_x) {
		if(next_y < 1 || next_y >= R+1 || next_x < 1 || next_x >= C+1) return true;
		return false;
	}
}
