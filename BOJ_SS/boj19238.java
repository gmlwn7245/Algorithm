package BOJ_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj19238 {
	static class Person implements Comparable<Person> {
		public int x, y, endX, endY;
		public int tDist,gDist;
		
		public Person(int x, int y, int endX, int endY) {
			this.x = x;
			this.y = y;
			this.endX = endX;
			this.endY = endY;
		}

		@Override
		public int compareTo(Person o) {
			if(this.tDist==o.tDist) {
				if(this.x == o.x)
					return this.y - o.y;
				return this.x - o.x;
			}
			return this.tDist - o.tDist;
		}
	}
	
	public static int N, M, fuel, tX, tY;
	public static int[][] map;
	public static HashSet<Person> hs = new HashSet<>();
	public static int[] dx = {1,-1,0,0};
	public static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());	// 목표
		fuel = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		tX = Integer.parseInt(st.nextToken());
		tY = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			hs.add(new Person(
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())
					));
		}
		
		for(int i=0; i<M; i++) {
			PriorityQueue<Person> pq = new PriorityQueue<>();
			for(Person p : hs) {
				minDist(p, true);
				if(p.tDist != -1)
					pq.add(p);
			}
			
			if(pq.size()==0) {
				System.out.println(-1);return;
			}
			
			Person p = pq.poll();
			hs.remove(p);
			
			minDist(p, false);
			if(p.gDist == -1) {
				System.out.println(-1); return ;
			}
			
			if(fuel < p.tDist + p.gDist) {
				System.out.println(-1);
				return ;
			}
			
			fuel += p.gDist - p.tDist;
			tX = p.endX; tY = p.endY;
		}
		
		System.out.println(fuel);
	}
	
	
	
	public static void minDist(Person p, boolean taxi) {
		int x,y,endX,endY;
		
		if(taxi) {
			x = tX; y = tY; endX = p.x; endY = p.y;
		}else {
			x = p.x; y = p.y; endX = p.endX; endY = p.endY;
		}
		boolean[][] visited = new boolean[N+1][N+1];
		
		// 거리 작은 순
		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
		
		q.add(new int[] {x,y,0});
		visited[x][y]=true;
		
		int ans = x==endX&&y==endY?0:-1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
	
			for(int i=0; i<4; i++) {
				int nextX = now[0] + dx[i];
				int nextY = now[1] + dy[i];
				int newDist = now[2] + 1;
				
				// 범위 밖
				if(!isInArea(nextX, nextY))
					continue;
				
				// 방문 or 벽
				if(visited[nextX][nextY] || map[nextX][nextY]==1)
					continue;
				
				if(fuel < newDist)
					break;
				
				if(nextX == endX && nextY == endY) {
					visited[endX][endY]=true;
					ans = newDist;
					break;
				}
				
				visited[nextX][nextY]=true;
				q.add(new int[] {nextX, nextY, newDist});
			}
			if(visited[endX][endY])
				break;
		}
		
		if(taxi)
			p.tDist = ans;
		else
			p.gDist = ans;
	}
	
	public static boolean isInArea(int x, int y) {
		return x>0 && x<=N && y>0 && y<=N;
	}
}
