/**
 * 
 */
package BOJ_Practice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author YSM
 *
 */
public class BOJ_16253_나무재태크3 {
	public static class Cell{
		int r, c, age;
		boolean life = true;
		public Cell(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
	}
	public static void main(String[] args) {
		int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
        int N, M, K;
        int [][] A;
        int [][] nutrients;
        LinkedList<Cell> cells = new LinkedList<Cell>();
        
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        
        nutrients = new int[N+1][N+1];
        
        cells.clear();
        for(int i = 0; i < M; i++) {
        	int r= sc.nextInt();
        	int c= sc.nextInt();
        	int age= sc.nextInt();
        	cells.add(new Cell(r, c, age));
        }
        for(int r = 0; r < K; r++) {
        	// 봄
        	for(Cell cell : cells) {
        		if(nutrients[cell.r][cell.c] >= cell.age) {
        			nutrients[cell.r][cell.c] -= cell.age;
        			cell.age++;
        		} else if(nutrients[cell.r][cell.c] < cell.age) {
        			cell.life = false;
        		}
        	}
        	// 여름
        	Iterator<Cell> iters = cells.iterator();
        	Cell curr = null;
        	
        	while(iters.hasNext()) {
        		curr = iters.next();
        		if(!curr.life) { // 족었어..
        			nutrients[curr.r][curr.c] += (curr.age/2);
        			iters.remove();
        		}
        	}
        	// 가을
        	LinkedList<Cell> newCell = new LinkedList<Cell>();
        	for(Cell cell : cells) {
        		if(cell.age%5 == 0) {
        			for(int d = 0; d < 8; d++) {
        				int nr = cell.r + dr[d];
        				int nc = cell.c + dc[d];
        				if(nr < 1 || nr > N || nc < 1 || nc > N) continue;
        				newCell.add(new Cell(nr, nc, 1));
        			}
        		}
        	}
        	cells.addAll(0, newCell);
        	// 겨울
        	for(int i = 1; i < N+1; i++) {
        		for(int j = 1; j < N+1; j++) {
        			nutrients[i][j] += A[i][j];
        		}
        	}
        }
        
        // 나무 세기
        System.out.println(cells.size());
	}
}
