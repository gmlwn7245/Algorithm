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

public class boj21609 {
	static class BlockGroup {
		public ArrayList<int[]> arr;
		public int rowMin, colMin, rainCnt;
		
		public BlockGroup(ArrayList<int[]> arr, int rowMin, int colMin, int rainCnt) {
			this.arr = arr;
			this.rowMin = rowMin;
			this.colMin = colMin;
			this.rainCnt = rainCnt;
		}
	}
	public static int N, M;
	public static long sum=0;
	public static int[][] map;
	public static boolean[][] visited;
	public static BlockGroup bg;
	public static int[] dx = {-1,1,0,0};
	public static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 검정 -1 무지개 0 나머지 일반 1 ~ M
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			bg = null;
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) 
				for(int j=0; j<N; j++) {
					if(map[i][j]>0 && !visited[i][j])
						bfs(i,j);
				}
			
			if(bg == null)
				break;
					
			for(int[] n : bg.arr)
				map[n[0]][n[1]] = -2;
			
			long size = bg.arr.size();
			sum += size * size;
			setGravity();
			rot();
			setGravity();
		}
		
		System.out.println(sum);
	}
	
	public static void rot() {
		int[][] newMap = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				newMap[N-1-j][i] = map[i][j];
			}
		}
		
		map = newMap;
	}
	
	public static void setGravity() {
		for(int j=0; j<N; j++) {
			for(int i=N-1; i>=0; i--) {
				if(map[i][j]<0)
					continue;
				
				
				int height = i+1;
				
				while(height < N && map[height][j]==-2)
					height++;
				height--;
				
				
				if(height != i) {
					map[height][j] = map[i][j];
					map[i][j]=-2;
				}
			}
		}
	}
	
	public static void bfs(int startX, int startY) {
		// 비교를 위한 정보
		ArrayList<int[]> arr = new ArrayList<>();
		int rowMin=startX, colMin=startY, rainCnt=0;
		
		// 탐색을 위한 정보
		Queue<int[]> q = new LinkedList<>();
		int num = map[startX][startY];
		visited[startX][startY] = true;
		setZero();
		q.add(new int[] {startX, startY});
		arr.add(new int[] {startX, startY});
		
		//System.out.println(startX+" "+startY+" Start!");
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			//System.out.println(now[0]+" "+now[1]);
			
			for(int i=0; i<4; i++) {
				int nextX = now[0] + dx[i];
				int nextY = now[1] + dy[i];
				
				if(!isInArea(nextX, nextY))
					continue;
				
				int val = map[nextX][nextY];
				
				if(visited[nextX][nextY])
					continue;
				
				if(val < 0d || val > 0 && val != num)
					continue;
				
				
				if(val == 0)
					rainCnt++;
				else {
					rowMin = Math.min(rowMin, nextX);
					colMin = Math.min(colMin, nextY);
				}
				
				q.add(new int[] {nextX, nextY});
				arr.add(new int[] {nextX, nextY});
				visited[nextX][nextY] = true;
			}
		}
		
		if(arr.size() < 2)
			return ;
		
		BlockGroup newBg = new BlockGroup(arr, rowMin, colMin, rainCnt);
		if(bg==null || isGreater(newBg))
			bg = newBg;
	}
	
	public static boolean isGreater(BlockGroup newBg) {
		if(bg.arr.size() < newBg.arr.size())
			return true;
		else if(bg.arr.size() > newBg.arr.size())
			return false;
		
		if(bg.rainCnt < newBg.rainCnt)
			return true;
		else if(bg.rainCnt > newBg.rainCnt)
			return false;
		
		if(bg.rowMin < newBg.rowMin)
			return true;
		else if(bg.rowMin > newBg.rowMin)
			return false;
		
		if(bg.colMin < newBg.colMin)
			return true;
		return false;
	}
	
	public static void setZero() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==0)
					visited[i][j]=false;
			}
		}
	}
	
	public static void printMap() {
		System.out.println("======PRINT MAP=====");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==-2)
					System.out.print("88 ");
				else
					System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
}
