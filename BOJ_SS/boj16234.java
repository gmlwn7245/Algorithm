package BOJ_SS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj16234 {
	private static int n, l, r;
	private static boolean flag = false;
	private static int[][] nation;
	private static boolean[][] visited;
	
	private static int[] dx = {1,-1,0,0};
	private static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		l = sc.nextInt();
		r = sc.nextInt();
		
		nation=new int[n][n];
		
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				nation[i][j] = sc.nextInt();
		
		
		int res = 0;
		while(true) {
			flag = true;
			visited = new boolean[n][n];
			
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++) {
					if(visited[i][j])
						continue;
					bfs(i,j);
				}
			
			if(flag)
				break;
			res++;
		}
		System.out.println(res);
	}
	
	public static void bfs(int x, int y) {
		HashSet<String> hs = new HashSet<>();
		Queue<int[]> q = new LinkedList<>();
		ArrayList<int[]> unions = new ArrayList<>();
		
		q.add(new int[] {x,y});
		hs.add(x+" "+y);
		
		int cnt = 0;
		int sum = 0;
		while(!q.isEmpty()) {
			
			int[] now = q.poll();
			int nowX = now[0];
			int nowY = now[1];
			cnt++;
			sum += nation[nowX][nowY];
			
			unions.add(new int[] {nowX, nowY});
			visited[nowX][nowY] = true;
			
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if(!isInArea(nextX, nextY))
					continue;
				if(!isUnion(nowX, nowY, nextX, nextY))
					continue;
				if(visited[nextX][nextY])
					continue;
				if(hs.contains(nextX + " "+nextY))
					continue;
				
				q.add(new int[] {nextX, nextY});
				hs.add(nextX + " "+nextY);
				flag = false;
			}
		}
		
		if(cnt==1)
			return ;
		
		int avg = sum / cnt;
	
		for(int[] p : unions) 			
			nation[p[0]][p[1]] = avg;	
		
	}
	public static boolean isUnion(int x, int y, int nx, int ny) {
		int diff = Math.abs(nation[x][y]-nation[nx][ny]);
		return diff >= l && diff<=r;
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<n && y>=0 && y<n;
	}
	
	public static void printMap(String str) {
		System.out.println("======"+str+"======");
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(nation[i][j]+" ");
			}
			System.out.println();
		}
	}
}
