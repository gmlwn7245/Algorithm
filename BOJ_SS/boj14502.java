package BOJ_SS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj14502 {
	static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int n,m,max=0;
	private static int[][] map;
	
	private static int[] dx = {1,-1,0,0};
	private static int[] dy = {0,0,1,-1};
	
	private static ArrayList<Point> blanks = new ArrayList<>();
	private static ArrayList<Point> virus = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n][m];
		
		// 0빈칸 1벽 2바이러스
		for(int i=0; i<n; i++) 
			for(int j=0; j<m; j++) {
				map[i][j] = sc.nextInt();
				
				if(map[i][j]==0)
					blanks.add(new Point(i,j));
				else if(map[i][j]==2)
					virus.add(new Point(i,j));
			}
		
		makeWall(0,-1, new ArrayList<Point>());
				
		System.out.println(max);
	}
	
	public static void sepVirus(int[][] newMap) {
		Queue<Point> q = new LinkedList<>(virus);
		
		int[][] setVirusMap = newMap;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			setVirusMap[p.x][p.y]=2;
			
			for(int i=0; i<4; i++) {
				int nextX = p.x + dx[i];
				int nextY = p.y + dy[i];
				
				if(!isInArea(nextX, nextY))
					continue;
				
				if(setVirusMap[nextX][nextY]!=0)
					continue;
				
				q.add(new Point(nextX, nextY));
			}
		}
		
		setMax(setVirusMap);
	}
	

	// 벽 만들기
	public static void makeWall(int dep, int idx, ArrayList<Point> wall){
		if(dep==3) {
			int[][] newMap = copyMap();
			for(Point p : wall)
				newMap[p.x][p.y]=1;
			
			//printMap(newMap);
			sepVirus(newMap);
			return ;
		}
		
		for(int i=idx+1; i<blanks.size(); i++) {
			wall.add(blanks.get(i));
			makeWall(dep+1, i, wall);
			wall.remove(dep);
		}
	}
	
	public static void setMax(int[][] newMap) {
		int cnt=0;
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				if(newMap[i][j]==0)
					cnt++;
		max = Math.max(cnt, max);
	}	
	
	public static int[][] copyMap(){
		int[][] newMap = new int[n][m];
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				newMap[i][j] = map[i][j];
		
		return newMap;
	}
	
	public static void printMap(int[][] newMap) {
		System.out.println("======Print Map======");
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++)
				System.out.print(newMap[i][j]+" ");
			System.out.println();
		}
			
	}
	
	public static boolean isInArea(int x, int y) {
		return x<n && x>=0 && y<m && y>=0;
	}
}
