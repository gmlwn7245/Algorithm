package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class boj2146 {
	private static int N, minN = Integer.MAX_VALUE;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static int[][] map, visited;
	private static boolean[][] edge;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new int[N][N];
		edge = new boolean[N][N];
		
		
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			if(s.equals("")) {
				i--;
				continue;
			}
			String[] str = s.split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		findArea();
		//printMap(map);
		
		bw.write(minN+"");
		bw.flush();
		bw.close();
	}
	
	public static void findArea() {
		int num = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==0 || visited[i][j]>0)
					continue;
				bfs(i,j, num++);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(edge[i][j])
					findBridge(i,j,visited[i][j]);
			}
		}
	}
	
	public static void findBridge(int x, int y, int num) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y,0});
		
		boolean[][] tmp = new boolean[N][N];
		tmp[x][y]=true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int nowX = now[0];
			int nowY = now[1];
			int cnt = now[2];
			
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if(!isInArea(nextX, nextY))
					continue;
				
				if(tmp[nextX][nextY])
					continue;
				
				if(visited[nextX][nextY]==num)
					continue;
				
				if(visited[nextX][nextY]!=0) {
					tmp[nextX][nextY]=true;
					minN = Math.min(cnt, minN);
				}
				
				tmp[nextX][nextY]=true;
				q.add(new int[] {nextX, nextY,cnt+1});
			}
		}
	}
	
	public static void bfs(int x, int y, int num) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		visited[x][y]=num;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int nowX = now[0];
			int nowY = now[1];
			
			int cnt = 0;
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if(!isInArea(nextX, nextY))
					continue;
				
				if(map[nextX][nextY]==0) {
					cnt++;
					continue;
				}
				
				if(visited[nextX][nextY]>0)
					continue;
				
				q.add(new int[] {nextX, nextY});
				visited[nextX][nextY]=num;
			}
			
			if(cnt>0)
				edge[nowX][nowY]=true;
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	
	public static void printMap(int[][] map) {
		System.out.println("=========PRINT=========");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
