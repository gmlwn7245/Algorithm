package MCD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class pq_01 {
	static class Node{
		int y,x;
		public Node(int y, int x) {
			this.y=y; this.x=x;
		}
	}
	
	public static int[] dx = {0,0,1,-1};
	public static int[] dy = {1,-1,0,0};
	
	public static int N, M;
	public static boolean[][] dead = new boolean[1000][1000];
	public static HashMap<Integer, Node> hm = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pq = new PriorityQueue<>();	
		
		int zeroCnt = 0;
		for(int i=0; i<N+M; i++) {
			st = new StringTokenizer(br.readLine());
			if(zeroCnt == M)
				continue;
			
			int b,y,x;
			b = Integer.parseInt(st.nextToken());
			
			if(b == 0) {
				boolean check = false;
				zeroCnt++;
				
				while(!pq.isEmpty()) {
					int idx = pq.poll();
					Node now = hm.get(idx);
					
					if(dead[now.y][now.x])
						continue;
					
					for(int j=0; j<4; j++) {
						int nx = now.x+dx[j];
						int ny = now.y+dy[j];
						
						if(!isInArea(nx, ny))
							continue;
						
						dead[ny][nx]=true;
					}
					
					dead[now.y][now.x]=true;
					
					check = true;
					bw.write(idx+"\n");
					break;
				}
				
				if(!check)
					bw.write("-1\n");
				
			}else {
				y = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				
				if(!dead[y][x]) {
					pq.add(b);
					hm.put(b, new Node(y,x));
				}
								
			}
		}
		bw.flush();
		bw.close();
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<1000 && y>=0 && y<1000;
	}
}
