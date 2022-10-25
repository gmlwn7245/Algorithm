package BOJ_SS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj17142 {
	static class Virus {
		int x, y;
		public Virus(int x, int y) {
			this.x =x;
			this.y =y;
		}
	}
	private static int n,m,zeroCnt,min = Integer.MAX_VALUE;
	private static int[][] map; 
	private static int[] dx = {1,-1,0,0};
	private static int[] dy = {0,0,1,-1};
	
	private static ArrayList<Virus> vArr = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		
		zeroCnt=0;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 2)
					vArr.add(new Virus(i,j));
				else if(num == 0)
					zeroCnt++;
				map[i][j]=num;
			}
		}
		
		dfs(0,-1,new ArrayList<>());
		
		if(min==Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}
	
	public static void dfs(int dep, int idx, ArrayList<Virus> arr) {
		if(dep==m) {
			bfs(arr);
			return ;
		}
		
		for(int i=idx+1; i<vArr.size(); i++) {
			arr.add(vArr.get(i));
			dfs(dep+1, i, arr);
			arr.remove(arr.size()-1);
		}
	}
	
	public static void bfs(ArrayList<Virus> arr) {
		int[][] newMap = copyMap();
		boolean[][] visited = new boolean[n][n];
		Queue<Virus> q = new LinkedList<>();
		
		int cnt = 0;
		int findCnt = 0;
		
		for(Virus v : arr) {
			q.add(v);
			newMap[v.x][v.y] = 0;
			visited[v.x][v.y] = true;
		}
		
		while(!q.isEmpty()) {
			Virus v = q.poll();
			
			if(map[v.x][v.y]!=2) {
				findCnt++;
				cnt = Math.max(cnt, newMap[v.x][v.y]);
			}
				
			
			for(int i=0; i<4; i++) {
				int nextX = v.x + dx[i];
				int nextY = v.y + dy[i];
				
				if(!isInArea(nextX, nextY))
					continue;
				
				if(visited[nextX][nextY])
					continue;
				
				if(newMap[nextX][nextY]==1)
					continue;
				
				q.add(new Virus(nextX, nextY));
				visited[nextX][nextY]=true;
				newMap[nextX][nextY]=newMap[v.x][v.y]+1;
			}
		}
//		if(cnt < min) {
//			System.out.println("---POINT---");
//			for(Virus v : arr) {
//				System.out.print("("+v.x+","+v.y+")");
//			}
//			System.out.println();
//			printMap(newMap);
//		}
		
		if(findCnt!=zeroCnt)
			return ;
		min = Math.min(cnt, min);
	}
	
	public static int[][] copyMap(){
		int[][] newMap = new int[n][n];
		
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++) 
				newMap[i][j]=map[i][j];
			
		return newMap;
	}
	
	public static void printMap(int[][] nMap) {
		
		System.out.println("====PRINT====");
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(" " + nMap[i][j]);
			}
			System.out.println();
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<n && y>=0 && y<n;
	}
}
