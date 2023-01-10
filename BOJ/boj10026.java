package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class boj10026 {
	private static int N;
	private static char[][] map;
	private static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		for(int i=0; i<N; i++) {
			char[] str = br.readLine().replace(" ", "").toCharArray();
			
			for(int j=0;j<N; j++) {
				map[i][j] = str[j];
			}
		}
		findArea();
	}
	
	public static void findArea() {
		visited =  new boolean[N][N];
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0;j<N; j++) {
				if(visited[i][j])
					continue;
				bfs(i,j,map[i][j],false);
				cnt++;
			}
		}
		System.out.print(cnt+" ");
		
		visited =  new boolean[N][N];
		cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0;j<N; j++) {
				if(visited[i][j])
					continue;
				bfs(i,j,map[i][j],true);
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	public static void bfs(int x, int y, char color, boolean isColorBlind) {
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		visited[x][y]=true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int nowX = now[0];
			int nowY = now[1];
			
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				if(!isInArea(nextX, nextY))
					continue;
				
				if(visited[nextX][nextY])
					continue;
				
				if(isColorBlind) {
					if(color == 'B') {
						if(map[nextX][nextY]!='B')
							continue;
						
						q.add(new int[] {nextX, nextY});
						visited[nextX][nextY]=true;
					}else {
						if(map[nextX][nextY]=='B')
							continue;
						
						q.add(new int[] {nextX, nextY});
						visited[nextX][nextY]=true;
					}
					continue;
				}
				
				if(color!=map[nextX][nextY])
					continue;
				
				q.add(new int[] {nextX, nextY});
				visited[nextX][nextY]=true;
			}
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
}
