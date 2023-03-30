package BOJ_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj17135 {
	public static int N,M,D, anemy=0;
	public static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
		@Override
		public int compare(int[] o1, int[] o2) {
			if(o1[2]==o2[2])
				return o1[1]-o2[1];
			
			return o1[2]-o2[2];
		}
	});
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1)
					anemy++;
			}
		}
		
		int ans = 0;
		//printMap();
		while(anemy!=0) {
			//dfs(0,0,new int[3]);
			getCnt();
			
			int cnt = 0;
			while(!pq.isEmpty()) {
				int[] p = pq.poll();
				int x = p[0], y = p[1];
				//System.out.println(x+" "+y);
				if(map[x][y]==0)
					continue;
				
				map[x][y]=0;
				ans++;
				anemy--;
				if(++cnt == 3)
					break;
			}
			
			//System.out.print("BEF --- ");
			//printMap();
			
			move();
			
			//System.out.print("AFT --- ");
			//printMap();
		}
		System.out.println(ans);
	}
	
	public static void move() {
		// 마지막 줄 없앰
		for(int i=0; i<M; i++)
			if(map[N-1][i]==1)
				anemy--;
							
		// 앞으로 당기기
		for(int i=N-1; i>0; i--) {
			for(int j=0; j<M; j++) {
				map[i][j]=map[i-1][j];
			}
		}
		
		// 맨 윗줄
		for(int i=0; i<M; i++)
			map[0][i]=0;
	}
	
	
	public static void getCnt() {
		for(int i=0; i<M; i++) {
			boolean found = false;
			
			if(map[N-1][i]==1) {
				pq.add(new int[] {N-1, i, 1});
				continue;
			}
			
			for(int j=1; j<D; j++) {
				int d = j*2+1;
				int x = N-1, y=i-j;
				
				while(d-- > 0) {
					if(!isInArea(x,y))
						continue;
					
					if(map[x][y]==1) {
						pq.add(new int[] {x,y, j+1});
						found = true;
						break;
					}
					
					y++;
					int res = j-Math.abs(y-i);
					x = N-1-res;
				}
				if(found)
					break;
			}
		}	
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
	
	public static void printMap() {
		System.out.println("=====PRINT");
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
