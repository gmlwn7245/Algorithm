package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj1194 {
	static class Node {
		int x, y, dist;
		HashSet<Character> hs = new HashSet<>();
		public Node(int x, int y, int dist) {
			this.x = x; this.y = y; this.dist = dist;
		}
	}
	
	public static int N, M;
	public static int[][][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				if(n1.dist == n2.dist)
					return n2.hs.size() - n1.hs.size();
				return n1.dist - n2.dist;
			}
		});
		
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				char ch = str.charAt(j);
				
				if(ch == '0') {
					map[i][j]='.';
					pq.add(new Node(i,j,1));
					continue;
				}
					
				map[i][j]=ch;
			}
		}
		
		int[] dx = {0,0,-1,1};
		int[] dy = {1,-1,0,0};
		
		int max = N*M*2;
		
		while(!pq.isEmpty() && max-- > 0) {
			Node now = pq.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				int ndist = now.dist+1;
				
				if(!isInArea(nx, ny))
					continue;
				
				char ch = map[nx][ny];
				
				//GOAL일때
				if(ch == '1') {
					System.out.println(ndist);
					return ;
				}
				
				if(ch == '#')
					continue;
				
				// 열쇠 집음
				if(ch >= 'a' && ch <= 'f') {
					// 이미 가지고 있음
					if(now.hs.contains((char)(ch+32)))
						continue;
					
					Node newNode = new Node(nx, ny, ndist);
					newNode.hs.addAll(now.hs);
					newNode.hs.add((char)(ch-32));
					pq.add(newNode);
					continue;
				}
				
				// 문
				if(ch >= 'A' && ch <= 'F') {
					if(!now.hs.contains(ch))
						continue;
					
					Node newNode = new Node(nx, ny, ndist);
					newNode.hs.addAll(now.hs);
					pq.add(newNode);
					continue;
				}
				
				// 일반 길
				if(ch == '.') {
					Node newNode = new Node(nx, ny, ndist);
					newNode.hs.addAll(now.hs);
					pq.add(newNode);
					continue;
				}
			}
		}
		
		System.out.println(-1);
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
}
