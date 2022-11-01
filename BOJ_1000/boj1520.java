package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1520 {
	private static int n, m;
	private static int[][] map, dp;
	private static int[] dx = {1,-1,0,0};
	private static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		dp = new int[n][m];
		
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j]=-1;
			}
		}
		
		bw.write(dfs(0,0)+"");
		bw.flush();
		bw.close();		
	}
	
	public static int dfs(int x, int y) {
		// 도착지점일 경우
		if(x==n-1 && y==m-1) {
			return 1;
		}
		
		
		// 이미 값이 나와있는 경우
		if(dp[x][y]!=-1) {
			return dp[x][y];
		}
		
		dp[x][y] = 0;
		// 다음 좌표로 되는 경우
		for(int i=0; i<4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if(!isInArea(nextX, nextY))
				continue;
			
			if(map[nextX][nextY]<map[x][y]) {
				
				// next 좌표부터 끝까지
				dp[x][y] += dfs(nextX, nextY);
			}
		}
		
		// 도출한 dp값 끝 ~ x,y까지
		return dp[x][y];		
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<n && y>=0 && y<m;
	}
}

