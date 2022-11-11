package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1987 {
	private static int R, C, maxCnt = 0;
	private static char[][] map;
	private static boolean[][] visited;
	private static HashSet<Character> hs = new HashSet<>();
	private static int[] dx = {1,-1,0,0};
	private static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		dfs(0,0,1);
		bw.write(maxCnt+"");
		bw.flush();
		bw.close();
	}
	
	public static void dfs(int x, int y, int cnt) {
		maxCnt = Math.max(maxCnt, cnt);
		visited[x][y]=true;
		hs.add(map[x][y]);
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!isInArea(nx, ny))
				continue;
			
			if(visited[nx][ny])
				continue;
			
			if(hs.contains(map[nx][ny]))
				continue;
			
			dfs(nx, ny, cnt+1);
		}
		hs.remove(map[x][y]);
		visited[x][y]=false;
	}
	
	public static boolean isInArea(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}
}
