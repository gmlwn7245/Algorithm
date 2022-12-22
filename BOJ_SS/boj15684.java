package BOJ_SS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj15684 {
	private static int N, M, H, minCnt=4;
	private static int[][] map, dir;
	private static ArrayList<int[]> arr = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/*
		 * (놓아진) 세로선 수 N
		 * (놓을 수 있는) 가로선 개수 M
		 * (놓을 수 있는) 가로선 위치 H
		 * */
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		// 오른쪽 1 왼쪽 -1
		map = new int[H+1][N+1];
		dir = new int[H+1][N+1];
		
		for(int i=0; i<M; i++) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			
			map[a][b]=map[a][b+1]=1;
			dir[a][b] = 1;
			dir[a][b+1] = -1;
		}
		
		if(isAnswer())
			bw.write("0");
		else {
			findRoad();
			dfs(0,0);
			
			if(minCnt==4)
				bw.write("-1");
			else
				bw.write(minCnt+"");
		}
		
		bw.flush();
		bw.close();
	}
	
	public static void dfs(int idx, int cnt) {
		if(isAnswer()) {
			minCnt = Math.min(cnt, minCnt);
			return ;
		}
		
		if(cnt >= 3)
			return ;
		
		for(int i=idx; i<arr.size(); i++) {
			int[] point = arr.get(i);
			int x = point[0];
			int y = point[1];
			
			if(map[x][y]==1)
				continue;
			
			if(checkSide(x,y+1,false)) {
				map[x][y]=1;
				dir[x][y]=1;
				
				map[x][y+1]=1;
				dir[x][y+1]=-1;
				//System.out.println("Cnt==>" + cnt+"/// Point==>"+"("+x+","+y+")");
				dfs(i+1, cnt+1);
				
				map[x][y]=0;
				dir[x][y]=0;
				
				map[x][y+1]=0;
				dir[x][y+1]=-0;
				
			}
		}
		
	}
	public static boolean checkSide(int x, int y, boolean isLeft) {
		if(isLeft) {
			if(y==1)
				return true;
			if(dir[x][y-1] <=0 || map[x][y-1]==0)
				return true;
		}else {
			if(y==N)
				return true;
			if(dir[x][y+1] >=0 || map[x][y+1]==0)
				return true;
		}
		
		return false;
	}
	
	public static void findRoad() {
		// 시작 점
		for(int i=1; i<=H; i++) {
			for(int j=1; j<N; j++) {
				if(map[i][j]==1)
					continue;
				
				if(j<N && map[i][j+1]==0)
					arr.add(new int[] {i,j});
			}
		}
		
	}
	
	public static boolean isAnswer() {
		for(int j=1; j<N; j++) {
			int a=1, b=j;
			for(a=1; a<=H; a++) {
				if(b>1 && map[a][b-1]==1 && dir[a][b-1]==1) {
					b--;
					continue;
				}
				
				if(b<N && map[a][b+1]==1 && dir[a][b+1]==-1) {
					b++;
					continue;
				}
			}
			
			if(b!=j)
				return false;
		}
		
		return true;
	}
	
	public static void printMap() {
		for(int i=1; i<=H; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(map[i][j]+" ");
			}
			
			System.out.println();
		}
	}
}
