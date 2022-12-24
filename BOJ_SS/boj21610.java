package BOJ_SS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class boj21610 {
	private static int N, M;
	private static int[][] map;
	private static ArrayList<int[]> arr = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		for(int i=0; i<M; i++) {
			str = br.readLine().split(" ");
			moveCloud(Integer.parseInt(str[0]),Integer.parseInt(str[1]));
			
			for(int[] a : arr) {
				if(i==2)
					System.out.println(a[0]+" "+a[1]);

			}
				
			
			copyWater();
		}
		
		int sum = 0;
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				sum += map[i][j];
		
		bw.write(sum+"");
		bw.flush();
		bw.close();
		
	}
	
	public static void copyWater() {
		int[] dx = {-1,-1,1,1};
		int[] dy = {1,-1,-1,1};
		
		for(int[] a : arr) {
			int x = a[0];
			int y = a[1];
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(!isInArea(nx,ny))
					continue;
				if(arr.contains(new int[] {nx, ny}))
					continue;
				
				if(map[nx][ny]<=2)
					map[nx][ny]=0;
				else
					map[nx][ny]-=2;
			}
		}
	}
	
	public static void moveCloud(int dir, int cnt) {
		int[][] clouds = {{N-1,0},{N-1,1},{N-2,0},{N-2,1}};
		arr.clear();
		cnt %= N;
		
		// ¡ç, ¢Ø, ¡è, ¢Ö, ¡æ, ¢Ù, ¡é, ¢×
		for(int i=0; i<4; i++) {
			int nx = clouds[i][0];
			int ny = clouds[i][1];
			
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
			
			if(nx > N)
				nx %= N;
			if(ny > N)
				ny %= N;
			
			if(nx < 0)
				nx += N;
			if(ny < 0)
				ny += N;
			
			arr.add(new int[] {nx, ny});
		}
		
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
}
