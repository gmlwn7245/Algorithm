package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj9376 {
	static class Node {
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static int N, H, M;
	private static char[][] map;
	private static int[][] totVisited;
	private ArrayList<int[][]> maps = new ArrayList<>();
	private static int[] dx = {1,-1,0,0};
	private static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		
		for(int t=0; t<N; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			totVisited = new int[H+2][M+2];
			
			map = new char[H+2][M+2];
			for(char[] c : map) {
				Arrays.fill(c, '.');
			}
			
			ArrayList<Node> prisoner = new ArrayList<>();
			
			for(int i=1; i<=H; i++) {
				String str = br.readLine();
				for(int j=1; j<=M; j++) {
					map[i][j] = str.charAt(j-1);
					if(map[i][j]=='$')
						prisoner.add(new Node(i,j));
				}
			}
			int[][] m1 = bfs(new Node(0,0));
			int[][] m2 = bfs(prisoner.get(0));
			int[][] m3 = bfs(prisoner.get(1));
			
			
			int min = Integer.MAX_VALUE;
			for(int i=0; i<=H+1; i++) {
				for(int j=0; j<=M+1; j++) {
					if(map[i][j] =='*')
						continue;
					
					if(totVisited[i][j]!=3)
						continue;
					
					int sum = m1[i][j]+m2[i][j]+m3[i][j];
					
					if(map[i][j]=='#')
						sum -= 2;
					
					min = Math.min(min, sum);
				}
			}
			bw.write(min+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static int[][] bfs(Node start) {
		int[][] doorCnt = new int[H+2][M+2];
		boolean[][] visited = new boolean[H+2][M+2];

		visited[start.x][start.y] = true;
		
		Deque<Node> q = new ArrayDeque<>();
		q.add(start);		
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			totVisited[now.x][now.y] += 1;
			int cnt = doorCnt[now.x][now.y];
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(!isInArea(nx, ny))
					continue;
				
				char ch = map[nx][ny];
				
				// 벽일때
				if(ch=='*')
					continue;
				
				// 이미 방문한 정점에 대하여
				if(visited[nx][ny])
					continue;
								
				if(ch=='#') {
					visited[nx][ny] = true;
					doorCnt[nx][ny] = cnt+1;
					q.addLast(new Node(nx, ny));	
					
				}else {
					visited[nx][ny] = true;
					doorCnt[nx][ny] = cnt;
					q.addFirst(new Node(nx, ny));	
				}					
			}
		}
		
		doorCnt[start.x][start.y] = 0;
		return doorCnt;
	}
	
	public static boolean isOutside(int x, int y) {
		return x == 0 || y == 0 || x == H+1 || y == M+1;
	}
	
	public static boolean isInArea(int x , int y) {
		return x>=0 && x<=H+1 && y>=0 && y<=M+1;
	}
}
