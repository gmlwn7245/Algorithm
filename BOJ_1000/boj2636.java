package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj2636 {
	private static int N,M,cnt;
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		map = new int[N][M];
		
		cnt=0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num==1)
					cnt++;
			}
		}
		
		int ans = 0;
		int last = 0;
		while(true) {
			last = cnt;
			bfs();
			ans++;
			if(cnt<=0)
				break;
		}
		bw.write(ans+"\n"+last);
		bw.flush();
		bw.close();
	}
	
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0});
		
		boolean[][] visited = new boolean[N][M];
		visited[0][0]=true;
		
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
					cnt--;
					map[nx][ny]=0;
					visited[nx][ny]=true;
					continue;
				}
				
				q.add(new int[] {nx,ny});
				visited[nx][ny]=true;
			}
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
}
