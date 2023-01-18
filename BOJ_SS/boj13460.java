package BOJ_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj13460 {
	static class State {
		int[] red = new int[2];
		int[] blue = new int[2];
		int cnt = 0;
		
		public State(int[] red, int[] blue, int cnt) {
			this.red = red;
			this.blue = blue;
			this.cnt = cnt;
		}
	}
	private static int N, M, endLine = 10, ans = Integer.MAX_VALUE;
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[N][M];
		
		int[] red=null,blue=null;
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j);
				
				if(map[i][j]=='R') {
					red = new int[] {i,j};
					map[i][j]='.';
					System.out.println("R");
				}
				
				else if(map[i][j]=='B') {
					blue = new int[] {i,j};
					map[i][j]='.';
					System.out.println("B");
				}
			}
		}
		
		bfs(red, blue);
		if(ans==Integer.MAX_VALUE)
			ans = -1;
		
		System.out.println(ans);
	}
	
	public static void bfs(int[] red, int[] blue) {
		Queue<State> q = new LinkedList<>();
		q.add(new State(red, blue,0));
			
		while(!q.isEmpty()) {
			State now = q.poll();
			int[] nowRed = now.red;
			int[] nowBlue = now.blue;
			
			for(int i=0; i<4; i++) {
				// 0¿Þ 1¿À 2À§ 3¾Æ·¡
				
				
			}
		}
	}
	
	
}
