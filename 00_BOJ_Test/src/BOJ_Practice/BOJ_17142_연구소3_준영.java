package BOJ_Practice;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17142_연구소3_준영 {
   static class Point{
      int x,y;
      public Point(int x, int y) {
         this.x = x;
         this.y = y;
      }
   }
   static int map[][], copy[][], N, M, minTime, combination[];
   static int dx[]= {0,0,-1,1}; //위, 아래, 왼쪽 ,오른쪽
   static int dy[]= {1,-1,0,0};
   static int notViruscnt, time;
   static List<Point> totalVirus;
   static boolean visited[][];
   static Point virus[];
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      map = new int[N][N];
      
      virus = new Point[M];
      
      totalVirus=new ArrayList<>();
      int index=0;
      
      for(int i=0; i<N; i++) {
          st = new StringTokenizer(br.readLine(), " ");
         for(int j=0; j<N; j++) {
            map[i][j]=Integer.parseInt(st.nextToken());
            if(map[i][j]==2) totalVirus.add(new Point(i,j));
            else if(map[i][j]==0) notViruscnt++;
         }
      }
      minTime=Integer.MAX_VALUE;
      combination = new int[M];
      solve();
      if(minTime==Integer.MAX_VALUE) System.out.println(-1);
      else if(notViruscnt==0) System.out.println(0);
      else if(totalVirus.size()==0) System.out.println(-1);
      else System.out.println(minTime);
   }
   private static void solve() {
      combi(0,0);
      
   }
   private static void combi(int cnt, int start) {
      if(cnt==M) {
         time=0;
         visited = new boolean[N][N];
         if(timeCal()) {
            minTime=Math.min(time, minTime);
         }
         return ;
      }
      
      
      for(int i=start; i<totalVirus.size(); i++) {
         combination[cnt]=i;
         combi(cnt+1,i+1);
      }
   }
   private static boolean timeCal() {
      int cnt=0;
      Queue <Point> queue=new LinkedList<>();
      for(int i=0; i<M; i++) {
         int x=totalVirus.get(combination[i]).x;
         int y=totalVirus.get(combination[i]).y;
         queue.offer(new Point(x,y));
         visited[x][y]=true;
      }
      
      while(true) {
         time++;
         if(minTime==time) return false;
         if(time==(N*N)+1) return false;
         int size=queue.size();
         if(size==0) return false;
         for(int i=0; i<size; i++) {
            Point point=queue.poll();
            int x=point.x;
            int y=point.y;
            for(int d=0; d<4; d++) {
               int nx=x+dx[d];
               int ny=y+dy[d];
               
               if(nx<0 || ny<0 || nx>=N || ny>=N ||visited[nx][ny]) continue;
               if(map[nx][ny]==1) continue;
               
               if(map[nx][ny]==0) cnt++;
               visited[nx][ny]=true;
               queue.offer(new Point(nx,ny));
               
            }
         }
         if(cnt==notViruscnt) return true;
         
      }
      
   }

}