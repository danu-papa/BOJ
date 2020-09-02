/**
 * 
 */
package BOJ_Practice;

public class BOJ_17144_Goodby_Mise_wind{
	   
	   public static void main(String[] args) {
	      new BOJ_17144_Goodby_Mise_wind(7, 8, 50);
	   }
	   
	   public BOJ_17144_Goodby_Mise_wind(int r, int c, int t) {
	      R = r;
	      C = c;
	      T = t;
	      
	      setDefault(R, C);
	      getAirCleaner();
	      
	      for(int i=0; i<T; i++) {
	         moveMise();
	         moveAirCleaner(i);
	         //printROOM();
	         //getTotalMise();
	      }
	      
	      //System.out.println("#### RESULT");
	      //printROOM();
	      getTotalMise();
	   }
	   private void printROOM() {
	      System.out.println("###");
	      for(int i=0; i<R; i++) {
	         for(int j=0; j<C; j++) {
	            System.out.print(ROOM[i][j] + "\t");
	         }
	         System.out.println("");
	      }
	   }
	   private void getTotalMise() {
	      int g = 0;
	      for(int i=0; i<R; i++) {
	         for(int j=0; j<C; j++) {
	            if(ROOM[i][j] > 0)
	               g += ROOM[i][j];
	         }
	      }
	      System.out.println(g);
	   }
	   
	   private int R = 0, C = 0, T = 0;
	   private int AIR_LOC[] = new int[2];
	   private int AIR_LOC2[] = new int[2];
	   private int ROOM[][], TMP_ROOM[][];
	   private int dx[] = {0, 0, -1, 1};
	   private int dy[] = {-1, 1, 0, 0};
	   
	   private boolean TEST = true;
	   private void setDefault(int r, int c) {
	      ROOM = new int[r][c];
	      TMP_ROOM = new int[r][c];
	      
	      if(TEST)
	      {
	         int r1[] = {0,0,0,0,0,0,0,9};
	         int r2[] = {0,0,0,0,3,0,0,8};
	         int r3[] = {-1,0,5,0,0,0,22,0};
	         int r4[] = {-1,8,0,0,0,0,0,0};
	         int r5[] = {0,0,0,0,0,10,43,0};
	         int r6[] = {0,0,5,0,15,0,0,0};
	         int r7[] = {0,0,40,0,0,0,20,0};
	         ROOM[0] = r1;
	         ROOM[1] = r2;
	         ROOM[2] = r3;
	         ROOM[3] = r4;
	         ROOM[4] = r5;
	         ROOM[5] = r6;
	         ROOM[6] = r7;
	      }
	      else {
	         //입력 필요
	      }
	   }
	   private void copyROOM(int[][] tmp, int[][] room) {
	      for(int i=0; i<R; i++) {
	         for(int j=0; j<C; j++) {
	            if(tmp[i][j] != -1)
	               tmp[i][j] = room[i][j];
	         }
	      }
	   }
	   private void getAirCleaner() {
	      boolean chk = false;
	      for(int i=0; i<R; i++) {
	         for(int j=0; j<C; j++) {
	            if(ROOM[i][j] < 0) {
	               chk = true;
	               AIR_LOC[0] = i;
	               AIR_LOC[1] = j;
	               break;
	            }
	         }
	         if(chk) {
	            break;
	         }
	      }
	      AIR_LOC2[0] = AIR_LOC[0]+1;
	      AIR_LOC2[1] = AIR_LOC[1];
	   }
	   private void moveMise() {
	      copyROOM(TMP_ROOM, ROOM);
	      
	      for(int i=0; i<R; i++) {
	         for(int j=0; j<C; j++) {
	            int difMise = difMise(ROOM[i][j]);
	            
	            for(int k=0; difMise>0 && k<4; k++) {
	               int nx = i + dx[k];
	               int ny = j + dy[k];
	               
	               if(nx < 0 || nx > R-1 || ny < 0 || ny > C-1 || ROOM[nx][ny] == -1) {
	                  continue;
	               }
	               
	               TMP_ROOM[i][j] -= difMise;
	               TMP_ROOM[nx][ny] += difMise;
	            }
	         }
	      }
	      
	      copyROOM(ROOM, TMP_ROOM);
	   }
	   private int difMise(int mise) {
	      return mise/5;
	   }
	   private void moveAirCleaner(int t) {
	      copyROOM(TMP_ROOM, ROOM);
	      
	      int i=AIR_LOC[0]; int j=1;

	      int pre = 0;
	      int cur = 0;
	      
	      while(i!=AIR_LOC[0] || j!=AIR_LOC[1]) {
	         cur = TMP_ROOM[i][j];
	         TMP_ROOM[i][j] = pre;
	         pre = cur;
	         
	         if(i == AIR_LOC[0] && j < C-1) {
	            j++;
	         }
	         else if(j == C-1 && i > 0) {
	            i--;
	         }
	         else if(i == 0 && j > 0) {
	            j--;
	         }
	         else if(j == 0) {
	            i++;
	         }
	      }
	      
	      i=AIR_LOC2[0]; j=1;
	      pre = 0;
	      cur = 0;
	      while(i!=AIR_LOC2[0] || j!=AIR_LOC2[1]) {
	         cur = TMP_ROOM[i][j];
	         TMP_ROOM[i][j] = pre;
	         pre = cur;
	         
	         if(i == AIR_LOC2[0] && j < C-1) {
	            j++;
	         }
	         else if(j == C-1 && i < R-1) {
	            i++;
	         }
	         else if(i == R-1 && j > 0) {
	            j--;
	         }
	         else if(j == 0) {
	            i--;
	         }
	      }
	      
	      copyROOM(ROOM, TMP_ROOM);
	   }
	}