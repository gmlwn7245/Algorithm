package BOJ_SS;

import java.util.*;

public class boj20057 {
	private static int n, sandCnt=0;
	private static int[][] map;
	
	
	// 2�� 1�Ʒ� 3�� 0��
	private static int[] dirs = {2,1,3,0};
	
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	
	private static int[][] sandDx = {
			{1,1,0,0,0,0,-1,-1,-2,-1},		//���� �̵�
			{-1,-1,0,0,0,0,1,1,2,1},		//�Ʒ��� �̵�
			{1,-1,1,-1,2,-2,1,-1,0,0},		//�·� �̵�
			{-1,1,-1,1,-2,2,-1,1,0,0}		//��� �̵�
			};
	
	private static int[][] sandDy = {
			{-1,1,-1,1,-2,2,-1,1,0,0},		//���� �̵�
			{1,-1,1,-1,2,-2,1,-1,0,0},		//�Ʒ��� �̵�
			{1,1,0,0,0,0,-1,-1,-2,-1},		//�·� �̵�
			{-1,-1,0,0,0,0,1,1,2,1}		//��� �̵�
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
		
		//���� 0�� 1�Ʒ� 2�� 3��
		int level = 1;
		int dirIdx = 0;
		while(true) {
			// ���� lv�� ù��° �̵�
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
			
			
			// ���� lv�� �ι�° �̵�
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
	
	// �̵��� x, y | ���� dir
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
