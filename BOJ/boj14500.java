package BOJ;

import java.util.Scanner;

public class boj14500{
	static int[][] map;
	static int n,m,max = 0;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static boolean[][] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				map[i][j]=sc.nextInt();
		
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++) {
				visited[i][j]=true;
				dfs(0,map[i][j],i,j);
				visited[i][j]=false;
			}
		
		figure();
		System.out.println(max);
	}
	
	static void dfs(int dep, int sum, int x, int y) {
		if(dep==3) {
			if(sum > max)
				max = sum;
			return ;
		}
		
		for(int i=0; i<4; i++) {
			int nextX = x+dx[i];
			int nextY = y+dy[i];
			
			if(nextX<0||nextY<0||nextX==n||nextY==m)
				continue;
				
			if(visited[nextX][nextY])
				continue;
			
			sum += map[nextX][nextY];
			visited[nextX][nextY] = true;
			dfs(dep+1,sum,nextX,nextY);
			sum -= map[nextX][nextY];
			visited[nextX][nextY] = false;
		}
	}
	
	static void figure() {
		//ぬ
		for(int i=0; i<n-1;i++)
			for(int j=0; j<m-2;j++) {
				int sum = map[i][j]+map[i][j+1]+map[i][j+2]+map[i+1][j+1];
				if(sum > max)
					max = sum;
			}
		
		//た
		for(int i=0; i<n-2;i++)
			for(int j=0; j<m-1;j++) {
				int sum = map[i][j]+map[i+1][j]+map[i+2][j]+map[i+1][j+1];
				if(sum > max)
					max = sum;
			}
		
		//で
		for(int i=0; i<n-1;i++)
			for(int j=1; j<m-1;j++) {
				int sum = map[i][j]+map[i+1][j+1]+map[i+1][j]+map[i+1][j-1];
				if(sum > max)
					max = sum;
			}
		
		//っ
		for(int i=1; i<n-1;i++)
			for(int j=0; j<m-1;j++) {
				int sum = map[i][j]+map[i][j+1]+map[i-1][j+1]+map[i+1][j+1];
				if(sum > max)
					max = sum;
			}
	}
}