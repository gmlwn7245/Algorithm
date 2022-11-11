package trail_error;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 메모리 초과,, 시간초과 ,,, dfs로 풀기
public class boj1987 {
	static class Node {
		int x, y;
		HashSet<Integer> hs = new HashSet<>();
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void addNow() {
			hs.add(map[x][y]);
		}
		
		public void setHs(HashSet<Integer> addhs) {
			hs.addAll(addhs);
		}
	}
	private static int R, C, maxCnt = 0;
	private static int[][] map;
	private static int[] dx = {1,-1,0,0};
	private static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		bfs();
		bw.write(maxCnt+"");
		bw.flush();
		bw.close();
	}
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		Node fNode = new Node(0,0);
		fNode.addNow();
		q.add(fNode);
		
		while(!q.isEmpty()) {
			Node nowN = q.poll();
			maxCnt = Math.max(maxCnt, nowN.hs.size());
			
			for(int i=0; i<4; i++) {
				int nx = nowN.x + dx[i];
				int ny = nowN.y + dy[i];
				
				if(!isInArea(nx, ny))
					continue;
				
				if(nowN.hs.contains(map[nx][ny]))
					continue;
				
				Node nextN = new Node(nx, ny);
				nextN.setHs(nowN.hs);
				nextN.addNow();
				
				q.add(nextN);
			}
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}
}
