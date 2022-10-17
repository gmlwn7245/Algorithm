package BOJ_SS;

import java.util.*;

public class boj20057 {
	private static int n, sandCnt=0;
	private static int[][] map;
	
	
	// 2좌 1아래 3우 0위
	private static int[] dirs = {2,1,3,0};
	
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	
	private static int[][] sandDx = {
			{1,1,0,0,0,0,-1,-1,-2,-1},		//위로 이동
			{-1,-1,0,0,0,0,1,1,2,1},		//아래로 이동
			{1,-1,1,-1,2,-2,1,-1,0,0},		//좌로 이동
			{-1,1,-1,1,-2,2,-1,1,0,0}		//우로 이동
			};
	
	private static int[][] sandDy = {
			{-1,1,-1,1,-2,2,-1,1,0,0},		//위로 이동
			{1,-1,1,-1,2,-2,1,-1,0,0},		//아래로 이동
			{1,1,0,0,0,0,-1,-1,-2,-1},		//좌로 이동
			{-1,-1,0,0,0,0,1,1,2,1}		//우로 이동
			};
	
	private static double[] ratio = {0.01,0.01,0.07,0.07,0.02,0.02,0.1,0.1,0.05};
	
	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j]+" ");
				 
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		map = new int[n][n];
		sandCnt=0;
		
		int x = n/2, y = n/2;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		boolean flag = true;
		
		//방향 0위 1아래 2좌 3우
		int level = 1;
		int dirIdx = 0;
		while(true) {
			// 현재 lv의 첫번째 이동
			for(int i=0; i<level; i++) {
				if(x==0 && y==0)
					break;			
				
				int dir = dirs[dirIdx];
				x += dx[dir];
				y+=dy[dir];
				
				sweepSand(x, y, dir);
			}
			dirIdx = (dirIdx + 1) % 4;
			
			if(x==0 && y==0)
				break;	
			
			
			// 현재 lv의 두번째 이동
			for(int i=0; i<level; i++) {
				if(x==0 && y==0)
					break;			
				
				int dir = dirs[dirIdx];
				x+=dx[dir];
				y+=dy[dir];
				
				sweepSand(x, y, dir);
			}
			
			if(x==0 && y==0)
				break;	
			dirIdx = (dirIdx + 1) % 4;
			level++;
		}
		
		System.out.println(sandCnt);
	}
	
	// 이동전 x, y | 방향 dir
	public static void sweepSand(int x, int y, int dir) {
		int tot = map[x][y];
		int a = tot;
		
		for(int i=0; i<9; i++) {
			int chX = x + sandDx[dir][i];
			int chY = y + sandDy[dir][i];
			int sand = (int)(ratio[i] * tot);
			
			
			if(!isInArea(chX, chY))
				sandCnt += sand;
			else
				map[chX][chY] += sand;
			a -= sand;
		}	
		
		int aX = x + sandDx[dir][9];
		int aY = y + sandDy[dir][9];
		
		if(!isInArea(aX,aY))
			sandCnt += a;
		else
			map[aX][aY] += a;
		map[x][y]=0;
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<n && y>=0 && y<n;
	}
}
