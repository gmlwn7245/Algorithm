package BOJ_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj17822 {
	public static int N, M, T;
	public static int[][] map;
	public static int[] dx = {0,0,1,-1};
	public static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			if(d==1)
				k *= -1;
			
			int tmp = x;
			while(tmp <= N) {
				rot(tmp-1, k);
				tmp += x;
			}
			//printMap();
			
			boolean flag = false;
			for(int i=0; i<N; i++)
				for(int j=0; j<M; j++) {
					if(map[i][j]==0)
						continue;
					if(remove(i,j))
						flag = true;
				}
			
			if(!flag)
				setAvg();
			//printMap();
		}
		
		System.out.println(tot());
	}
	
	public static int tot() {
		int sum = 0;
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				sum += map[i][j];
		return sum;
	}	
	
	public static void setAvg() {
		double avg = 0;
		int cnt = 0;
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++) {
				if(map[i][j]==0)
					continue;
				avg += map[i][j];
				cnt++;
			}
		
		avg /= (double)cnt;
				
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++) {
				int num = map[i][j];
				if(num==0)
					continue;
				if(num > avg)
					map[i][j]--;
				if(num < avg)
					map[i][j]++;
			}
		
	}
	
	public static boolean remove(int x, int y) {
		int num = map[x][y];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		
		boolean flag = false;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now[0]+dx[i];
				int ny = now[1]+dy[i];
				
				if(ny == -1)
					ny = M-1;
				if(ny == M)
					ny = 0;
				
				if(!isInArea(nx, ny))
					continue;
				
				if(map[nx][ny]!=num)
					continue;
				
				flag = true;
				map[nx][ny]=0;
				q.add(new int[] {nx, ny});
			}
		}
		
		if(flag) {
			map[x][y]=0;
			return true;
		}
		return false;
	}
	
	public static void rot(int n, int k) {
		/* 
		시계 : 뒤로 밀림 0
		반시계 : 앞으로 당김 1
		*/
		
		int[] tmp = new int[M];
		for(int i=0; i<M; i++) {
			int idx = (i+k+M)%M;
			tmp[idx]=map[n][i];
		}
		
		map[n]=tmp;
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
