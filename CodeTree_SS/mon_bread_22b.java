package CodeTree_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class mon_bread_22b {
	static class Person {
		int x, y, dist;
		int mx, my;
		boolean arrived=false;
		public Person (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void desc() {
			if(dist == 1) {
				dist = 0;
				arrived = true;
				arrivedCnt++;
			}else {
				dist--;
			}
				
		}
	}
	
	public static int N, M, arrivedCnt = 0;
	public static int[][] map;
	public static HashSet<int[]> basecamp = new HashSet<>();
	public static Person[] person;
	public static int[][] dp = new int[N][N];
	public static int[][] dir = new int[N][N];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map=new int[N][N];
		person = new Person[M+1];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1)
					basecamp.add(new int[] {i,j});
			}
		}
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			person[i]=new Person(x-1,y-1);
		}
		
		findBaseCamp(person[1]);
		int i = 1;
		for(i=2; i<=M; i++) {
			findBaseCamp(person[i]);
			
			for(int j=1; j<i; j++) {
				if(person[j].arrived)
					continue;
				person[j].desc();
			}
		}
		int res = 0;
		while(arrivedCnt != M) {
			for(int j=1; j<=M; j++) {
				if(person[j].arrived)
					continue;
				person[j].desc();
			}
			res++;
		}
		
		System.out.println(M+res);
	}
	
	public static int[] dx = {-1,0,0,1};
	public static int[] dy = {0,-1,1,0};


	public static void findBaseCamp(Person p) {
		dp = new int[N][N];
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {p.x, p.y, 0});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int nowX = now[0];
			int nowY = now[1];
			if(dp[nowX][nowY]!=now[2])
				continue;
			
			for(int i=0; i<4; i++) {
				int nx = nowX + dx[i];
				int ny = nowY + dy[i];
				
				// 시작 지점
				if(nx == p.x && ny == p.y)
					continue;
				
				// 겪자 밖 or 지나갈 수 없는 곳
				if(!canGo(nx, ny))
					continue;
				
				// 더 먼 거리로 왔을 경우
				if(dp[nx][ny]!=0)
					continue;
				
				dp[nx][ny] = dp[nowX][nowY]+1;
				q.add(new int[] {nx, ny, dp[nx][ny]});
			}
		}
		
		
		int[] res = null;
		int minDist = 31;
		
		for(int[] bc : basecamp) {
			int x = bc[0], y = bc[1];
			if(dp[x][y]==0)
				continue;
			if(minDist > dp[x][y]) {
				res = bc;
				minDist = dp[x][y];
			}else if(minDist == dp[x][y]) {
				if(x < res[0] || (x==res[0] && y<res[1]))
					res = bc;
			}
		}
		
		basecamp.remove(res);
		p.mx = res[0]; p.my =res[1];
		p.dist = minDist;
		map[res[0]][res[1]]=-1;
	}
	
	
	public static boolean canGo(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N && map[x][y]!=-1;
	}
	
	public static void printMap() {
		System.out.println("=====PRINTMAP");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}		
	}
}
