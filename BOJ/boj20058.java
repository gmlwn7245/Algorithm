package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class boj20058 {
	private static int N, Q, size,max=0;
	private static int[][] map;
	private static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		Q = Integer.parseInt(str[1]);
		
		size = (int) Math.pow(2.0, N);
		map = new int[size][size];
		visited = new boolean[size][size];
		
		for(int i=0; i<size; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<size; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		/*
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				map[i][j] = 0;
			}
		}*/
		
		str = br.readLine().split(" ");
		for(int i=0; i<Q; i++) {
			int L = Integer.parseInt(str[i]);
			if(L>0) {
				for(int l = 1; l<=L; l++)
					getGrid(l);
			}
			setIce();
			//printMap(L);
		}
		
		
		int ans = 0;
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				ans += map[i][j];
				
				if(map[i][j]==0 || visited[i][j])
					continue;
				
				bfs(i,j);
			}
		}
		System.out.println(ans);
		System.out.println(max);
	}
	
	public static void bfs(int x, int y) {
		Queue<int[]> q= new LinkedList<>();
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		q.add(new int[] {x,y});
		visited[x][y]=true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now[0]+dx[i];
				int ny = now[1]+dy[i];
				
				if(!isInArea(nx, ny))
					continue;
				
				if(map[nx][ny]==0)
					continue;
				
				if(visited[nx][ny])
					continue;
				
				cnt++;
				q.add(new int[] {nx,ny});
				visited[nx][ny]=true;
			}
		}
		
		max = Math.max(max, cnt);
	}
	
	// 내부 회전시킬 범위 설정
	public static void getGrid(int level) {
		int grid = (int) Math.pow(2, level);
		//System.out.println(grid);

		for(int i=0; i<size; i+=grid)
			for(int j=0; j<size; j+=grid)
				fireStorm(i,j,grid);
	}
	
	// 회전
	public static void fireStorm(int r, int c, int grid) {
		int unit = grid/2;
		HashSet<int[]> hs = new HashSet<>();
		
		// 오른쪽 이동 c +이동
		for(int i=r; i<r+unit; i++)
			for(int j=c; j<c+unit; j++)
				hs.add(new int [] {i, j+unit, map[i][j]});
		
		// 아래 이동 r +이동
		for(int i=r; i<r+unit; i++)
			for(int j=c+unit; j<c+grid; j++)
				hs.add(new int [] {i+unit, j, map[i][j]});

		// 왼쪽 이동 c -이동
		for(int i=r+unit; i<r+grid; i++)
			for(int j=c+unit; j<c+grid; j++)
				hs.add(new int [] {i, j-unit, map[i][j]});

		// 위쪽 이동 r -이동
		for(int i=r+unit; i<r+grid; i++)
			for(int j=c; j<c+unit; j++)
				hs.add(new int [] {i-unit, j, map[i][j]});
		
		for(int[] h : hs)
			map[h[0]][h[1]]=h[2];
	}
	
	// 인접칸 얼음 3미만 얼음량 감소시킴
	public static void setIce() {
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		HashSet<int[]> hs = new HashSet<>();
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map[i][j]==0)
					continue;
				
				int cnt = 0;
				for(int d=0; d<4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if(!isInArea(nx, ny))
						continue;
					if(map[nx][ny]==0)
						continue;
					cnt++;
				}
				
				if(cnt < 3)
					hs.add(new int[] {i,j});
			}
		}
		
		for(int[] p : hs)
			map[p[0]][p[1]]--;
	}
	
	public static boolean isInArea(int x, int y) {
		return x >=0 && x<size && y>=0 && y<size;
	}
	
	public static void printMap(int L) {
		System.out.println("=====PRINT => "+L+"=====");
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
