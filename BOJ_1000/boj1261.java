package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj1261 {
	static class Node{
		int x,y;
		// 이동한 수, 부순 벽 개수
		int wCnt;
		public Node(int x, int y,int wCnt) {
			this.x = x;
			this.y = y;
			this.wCnt = wCnt;
		}
	}
	
	private static int N, M, ans;
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		map = new int[N][M];
		ans = N * M;
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		
		if(N==1 && M==1) {
			System.out.println(0);
			return ;
		}
		
		bfs();
		System.out.println(ans);
	}
	
	public static void bfs() {
		//System.out.println("ff");
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0,0,0));
		// 이동한 수, 부순 벽 개수
		int[][] wall = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				
				if(!isInArea(nx, ny))
					continue;
				// 출발 지점
				if(nx==0 && ny ==0)
					continue;
				
				if(map[nx][ny]==1) {
					if(visited[nx][ny] && wall[nx][ny] <= n.wCnt + 1)
						continue;
					
					wall[nx][ny] = n.wCnt + 1;
					visited[nx][ny]=true;
					
					if(nx == N-1 && ny == M-1) {
						ans = Math.min(ans, wall[nx][ny]);
						continue;
					}
					
					q.add(new Node(nx, ny, wall[nx][ny]));
				}else {
					if(visited[nx][ny] && wall[nx][ny] <= n.wCnt)
						continue;
					
					wall[nx][ny] = n.wCnt;
					visited[nx][ny]=true;
					
					if(nx == N-1 && ny == M-1) {
						ans = Math.min(ans, wall[nx][ny]);
						continue;
					}
					
					q.add(new Node(nx, ny, wall[nx][ny]));
				}
			}
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M; 
	}
}
