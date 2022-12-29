package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj2638 {
	private static int N, M, cnt=0, ans=0;
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		map = new int[N][M];
		
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				
				if(num==1)
					cnt++;
			}
		}
		
		while(true) {
			bfs();
			ans++;
			if(cnt<=0)
				break;
		}
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
	
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0});
		
		Queue<int[]> cheese = new LinkedList<>();
		
		boolean[][] visited = new boolean[N][M];
		visited[0][0]=true;
		
		int[][] newMap = new int[N][M];
		for(int i=0; i<N; i++) {
			System.arraycopy(map[i], 0, newMap[i], 0, newMap[i].length);
		}
		
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		while(!q.isEmpty()) {
			int[] node = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = node[0]+dx[i];
				int ny = node[1]+dy[i];
				
				if(!isInArea(nx, ny))
					continue;
				
				if(visited[nx][ny])
					continue;
				
				if(map[nx][ny]==1) {
					cheese.add(new int[] {nx, ny});
					visited[nx][ny]=true;
					continue;
				}
				
				q.add(new int[] {nx, ny});
				visited[nx][ny]=true;
			}
		}
		
		while(!cheese.isEmpty()) {
			int[] node = cheese.poll();
			int x = node[0];
			int y = node[1];
			
			
			
			int chCnt = 0;
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				// 바깥에 있으면서 0인 곳
				if(visited[nx][ny] && map[nx][ny]==0)
					chCnt++;
			}
			
			if(chCnt >= 2) {
				newMap[x][y]=0;
				cnt--;
			}
		}
		map = newMap;
	}
	
	public static boolean isInArea(int x, int y) {
		return x >=0 && y>=0 && x<N && y<M;
	}
	
	public static void printMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
