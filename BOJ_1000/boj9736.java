package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj9736 {
	static class Node {
		int x, y, doorCnt=-1;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static int N, H, M, minD = Integer.MAX_VALUE;
	private static char[][] map;
	private static int[] dx = {1,-1,0,0};
	private static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[H][M];
		
		ArrayList<Node> prisoner = new ArrayList<>();
		ArrayList<Node> outdoor = new ArrayList<>();
		
		for(int i=0; i<H; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j]=='$')
					prisoner.add(new Node(i,j));
				if(map[i][j]=='#' && isDoor(i,j))
					outdoor.add(new Node(i,j));
			}
		}
	}
	
	public static void bfs(Node start, Node goal) {
		
		Queue<Node> q = new LinkedList<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				// 벽일때
				if(map[nx][ny]=='*')
					continue;
				
				// 수가 같거나 작을때
				
					
				
			}
		}
		
	}
	
	public static boolean isDoor(int x, int y) {
		return x==0 || x==H-1 || y==0 || y==M-1;
	}
}
