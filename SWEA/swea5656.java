package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea5656 {
	public static int N, W, H, res=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			res = H * W;
			
			int[][] map = new int[H][W];
			
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			//printMap(map);
			
			dfs(0, map, new int[N]);
			
			ans.append("#"+test_case+" "+res);
			if(test_case != T)
				ans.append("\n");
		}
		System.out.println(ans.toString());
	}
	
	public static void setCnt(int[][] map) {
		int cnt = 0;
		for(int i=0; i<H; i++)
			for(int j=0; j<W; j++)
				if(map[i][j]!=0)
					cnt++;
		res = Math.min(res, cnt);
	}	
	
	public static void dfs(int dep, int[][] map, int[] check) {
		if(dep==N) {
			setCnt(map);
			return ;
		}
		
		for(int i=0; i<W; i++) {
			int[][] copy = copyMap(map);
			
			// 첫 번째 위치 확인
			int[] first = firstHit(copy, i);
			
			
			if(first[0] != -1) {
				copy = destory(copy, first[0], first[1], dep);
				copy = getDown(copy);
			}
			
			check[dep]=i;
			
			dfs(dep+1, copy, check);
		}
	}
	
	// 해당 열에 아무것도 없을 경우
	public static int[] firstHit(int[][] tmp, int w) {
		for(int j=0; j<H; j++)
			if(tmp[j][w]!=0)
				return new int[] {j, w};
		return new int[] {-1,-1};
	}
	
	// 현재 map 복사
	public static int[][] copyMap(int[][] map){
		int[][] res = new int[H][W];
		for(int i=0; i<H; i++)
			for(int j=0; j<W; j++)
				res[i][j] = map[i][j];
		
		return res;
	}
	
	// 밑으로 당기기
	public static int[][] getDown(int[][] map) {
		int[][] newMap = new int[H][W];
		for(int j=0; j<W; j++) {
			Queue<Integer> q = new LinkedList<Integer>();
			for(int i=H-1; i>=0; i--) {
				if(map[i][j]!=0)
					q.add(map[i][j]);
			}
			
			int idx = H-1;
			while(!q.isEmpty()) {
				newMap[idx--][j] = q.poll();
			}
		}
		
		
		return newMap;
	}
	
	// 파괴하기
	public static int[][] destory(int[][] map, int x, int y, int dep) {
		Queue<Ball> q = new LinkedList<>();
		q.add(new Ball(x,y,map[x][y]));
		
			
		map[x][y]=0;
		
		while(!q.isEmpty()) {
			Ball now = q.poll();
			
			// 위
			int nx = now.x , ny = now.y;
			int cnt = now.area;
			while(cnt-- > 1) {
				nx--;
				if(!isInArea(nx, ny))
					break;
				if(map[nx][ny]==1)
					map[nx][ny]=0;
				if(map[nx][ny]==0)
					continue;
				
				
				q.add(new Ball(nx,ny,map[nx][ny]));
				map[nx][ny]=0;
			}
			
			
			// 아래
			nx = now.x; ny = now.y;
			cnt = now.area;
			while(cnt-- > 1) {
				nx++;
				if(!isInArea(nx, ny))
					break;
				if(map[nx][ny]==1)
					map[nx][ny]=0;
				if(map[nx][ny]==0)
					continue;
				
				q.add(new Ball(nx,ny,map[nx][ny]));
				map[nx][ny]=0;
			}
			
			// 왼
			nx = now.x; ny = now.y;
			cnt = now.area;
			
			while(cnt-- > 1) {
				ny--;
				if(!isInArea(nx, ny))
					break;
				if(map[nx][ny]==1)
					map[nx][ny]=0;
				if(map[nx][ny]==0)
					continue;
				
				
				q.add(new Ball(nx,ny,map[nx][ny]));
				map[nx][ny]=0;
			}
			
			// 오
			nx = now.x; ny = now.y;
			cnt = now.area;
			
			while(cnt-- > 1) {
				ny++;
				if(!isInArea(nx, ny))
					break;
				if(map[nx][ny]==1)
					map[nx][ny]=0;
				if(map[nx][ny]==0)
					continue;
				
				
				q.add(new Ball(nx,ny,map[nx][ny]));
				map[nx][ny]=0;
			}
		}
		
		return map;
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<H && y>=0 && y<W;
	}
	
	public static void printMap(int[][] map) {
		System.out.println("==========PRINT MAP===========");
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}

class Ball {
	int x, y, area;
	public Ball(int x, int y, int area) {
		this.x = x; this.y = y;
		this.area = area;
	}
}
