package BOJ_SS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

// 마법사 상어와 비바라기
public class boj21610 {
	static class Cloud {
		public int x, y;
		public Cloud(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object object) {
			return this.x == ((Cloud)object).x && this.y == ((Cloud)object).y;
		}
	}
	private static int N, M;
	private static int[][] map;
	private static ArrayList<Cloud> clouds = new ArrayList<>();
	private static ArrayList<Cloud> arr = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		map = new int[N][N];
		clouds.add(new Cloud(N-1,0));
		clouds.add(new Cloud(N-1,1));
		clouds.add(new Cloud(N-2,0));
		clouds.add(new Cloud(N-2,1));
		
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		for(int i=0; i<M; i++) {
			str = br.readLine().split(" ");
			if(i>0) {
				findCloud();
			}
			
			moveCloud(Integer.parseInt(str[0]),Integer.parseInt(str[1]));
			addWater();
			copyWater();
		}
		findCloud();
		
		int sum = 0;
		
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				sum += map[i][j];
		
		bw.write(sum+"");
		bw.flush();
		bw.close();
		
	}
	
	public static void findCloud() {
		clouds.clear();

		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]>=2) {
					if(arr.contains(new Cloud(i,j))) {
						continue;
					}
					
					clouds.add(new Cloud(i,j));
					map[i][j]-=2;
				}
			}
		}		
	}
	
	public static void addWater() {
		for(Cloud c : arr) {
			map[c.x][c.y]++;
		}
	}
	
	public static void copyWater() {
		int[] dx = {-1,-1,1,1};
		int[] dy = {1,-1,-1,1};
		
		for(Cloud a : arr) {
			int x = a.x;
			int y = a.y;
			
			int cnt = 0;
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!isInArea(nx,ny))
					continue;
				
				if(map[nx][ny]==0)
					continue;
				
				cnt++;
			}
			
			map[x][y]+= cnt;
		}
	}
	
	
	
	public static void moveCloud(int dir, int cnt) {
		arr.clear();
		cnt %= N;
		
		// ←, ↖, ↑, ↗, →, ↘, ↓, ↙
		
		for(Cloud c : clouds) {
			int nx = c.x;
			int ny = c.y;
			
			switch(dir) {
			case 1 : {
				ny -= cnt;
				break;
			}
			case 2 : {
				nx -= cnt;
				ny -= cnt;
				break;
			}
			case 3 : {
				nx -= cnt;
				break;
			}
			case 4 : {
				nx -= cnt;
				ny += cnt;
				break;
			}
			case 5 : {
				ny += cnt;
				break;
			}
			case 6 : {
				nx += cnt;
				ny += cnt;
				break;
			}
			case 7 : {
				nx += cnt;
				break;
			}
			case 8 :{
				nx += cnt;
				ny -= cnt;
				break;
			}
			}
			
			if(nx >= N)
				nx %= N;
			if(ny >= N)
				ny %= N;
			
			if(nx < 0)
				nx += N;
			if(ny < 0)
				ny += N;
			
			
			//System.out.println(nx+" "+ny+" / ");
			arr.add(new Cloud(nx,ny));
		}
		
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	
	public static void printMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}		
	}
	
	public static void printCloud() {
		for(Cloud c : clouds) {
			System.out.print(c.x+" "+c.y+"/");
		}		
	}
}
