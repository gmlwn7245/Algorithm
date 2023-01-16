package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj2589 {
	private static int N,M;
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		int ans = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!isWater(i,j)) {
					if(isMiddle(i,j))
						continue;
					
					ans = Math.max(ans, bfs(i,j));
				}
			}
		}
		System.out.println(ans);
	}
	
	public static int bfs(int x, int y) {		
		int[][] dist = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y,0});
		visited[x][y]=true;
		
		int maxDist = 0;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int nowX = now[0];
			int nowY = now[1];
			int nowD = now[2];
			
			if(dist[nowX][nowY] < nowD)
				continue;
			
			maxDist = Math.max(maxDist, nowD);
			
			for(int i=0; i<4; i++) {
				int nx = nowX + dx[i];
				int ny = nowY + dy[i];
				int nd = nowD + 1;
				
				if(!isInArea(nx, ny))
					continue;
				
				if(isWater(nx, ny))
					continue;
				
				// 이미 방문한 정점
				if(visited[nx][ny])
					continue;
				
				dist[nx][ny] = nd;
				visited[nx][ny] = true;
				q.add(new int[] {nx, ny, nd});
			}
		}
		
		
		return maxDist;
	}
	
	public static boolean isMiddle(int x, int y) {
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		int aCnt=0, bCnt = 0;
		for(int i=0; i<2; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!isInArea(nx, ny))
				continue;
			
			if(isWater(nx, ny))
				continue;
			
			aCnt++;
		}
		
		if(aCnt == 2)
			return true;
		
		for(int i=2; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!isInArea(nx, ny))
				continue;
			
			if(isWater(nx, ny))
				continue;
			
			bCnt++;
		}
		
		if(bCnt==2)
			return true;
		
		return false;
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
	
	public static boolean isWater(int x, int y) {
		return map[x][y]=='W';
	}
	
}
