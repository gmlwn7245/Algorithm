package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj2206 {
	static class Node{
		int x, y, cnt=0;
		boolean thWall;
		
		public Node(int x, int y, int cnt, boolean thWall) {
			this.x =x;
			this.y =y;
			this.cnt =cnt;
			this.thWall = thWall;
		}
	}
	
	private static int N, M, MAX = Integer.MAX_VALUE;
	private static int[] dx = {1,-1,0,0};
	private static int[] dy = {0,0,1,-1};
	private static int[][] map;
	private static int[][][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dp = new int[N][M][2];
		
		if(N==1&&M==1) {
			br.readLine();
			bw.write("1");
			bw.flush();
			bw.close();
			return ;
		}
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j)-'0';
				dp[i][j] = new int[]{MAX,MAX};
			}
		}
		bfs();
		
		int ans = Math.min(dp[N-1][M-1][0], dp[N-1][M-1][1]);
		
		if(ans==MAX)
			bw.write("-1");
		else
			bw.write(ans+1+"");
		
		bw.flush();
		bw.close();
	}
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0,0,0,false));
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			for(int i=0;i<4;i++) {
				int nextX = n.x + dx[i];
				int nextY = n.y + dy[i];
				
				// 격자 밖으로 나감
				if(!isInArea(nextX, nextY))
					continue;
				
				// 벽을 지나왔는데 벽을 만날때
				if(n.thWall && map[nextX][nextY]==1)
					continue;
				
				// 시작점일 경우
				if(nextX == 0 && nextY == 0)
					continue;
				
				// 끝 점일 경우
				if(nextX == N-1 && nextY == M-1) {
					if(n.thWall)
						dp[nextX][nextY][1]=Math.min(dp[nextX][nextY][1], n.cnt+1);
					else
						dp[nextX][nextY][0]=Math.min(dp[nextX][nextY][0], n.cnt+1);
					continue;
				}
				
				// 전보다 cnt가 클 때
				if(n.thWall && dp[nextX][nextY][1] <= n.cnt+1)
					continue;
				
				else if(!n.thWall && dp[nextX][nextY][0] <= n.cnt+1)
					continue;
				
				// 벽일때
				if(map[nextX][nextY]==1) {
					dp[nextX][nextY][1] = n.cnt+1;
					q.add(new Node(nextX, nextY,n.cnt+1, true));
				}else if(n.thWall){	//아닐때
					dp[nextX][nextY][1] = n.cnt+1;
					q.add(new Node(nextX, nextY,n.cnt+1, n.thWall));
				}else {
					dp[nextX][nextY][0] = n.cnt+1;
					q.add(new Node(nextX, nextY,n.cnt+1, n.thWall));
				}
			}			
		}
		
	}
	
	public static boolean isInArea(int x, int y) {
		return x >=0 && x<N && y>=0 && y<M;
	}
}
