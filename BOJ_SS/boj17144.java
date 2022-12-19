package BOJ_SS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj17144 {
	
	private static int R, C, T;
	private static int loc1=-1, loc2=-1;
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		T = Integer.parseInt(str[2]);
		
		map = new int[R+1][C+1];
		
		for(int i=0; i<R; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<R; i++) {
			if(map[i][0]!=-1)
				continue;
			
			if(loc1==-1)
				loc1 = i;
			else {
				loc2 = i;
				break;
			}
		}
		
		//System.out.println(loc1+" "+loc2);
		
		for(int i=0; i<T; i++) {
			diffDust();
			cleaning1();
			cleaning2();
			//printMap();
		}
		bw.write(cntMap()+"");
		bw.flush();
		bw.close();
	}
	
	public static int cntMap() {
		int cnt = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]<1)
					continue;
				cnt+= map[i][j];
			}
		}
		return cnt;
	}
	
	public static void printMap() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void cleaning1() {
		int tmp = 0;
		
		// 우측 이동
		for(int j=1; j<C; j++) {
			int now = map[loc1][j];
			map[loc1][j] = tmp;
			tmp = now;
		}
		
		// 위로 이동
		for(int i=loc1-1; i>=0; i--) {
			int now = map[i][C-1];
			map[i][C-1] = tmp;
			tmp = now;
		}
		
		// 좌측 이동
		for(int j=C-2; j>=0; j--) {
			int now = map[0][j];
			map[0][j] = tmp;
			tmp = now;
		}
		
		// 아래로 이동
		for(int i=1; i<loc1; i++) {
			int now = map[i][0];
			map[i][0] = tmp;
			tmp = now;
		}

	}
	
	public static void cleaning2() {
		int tmp = 0;
		
		// 우측 이동
		for(int j=1; j<C; j++) {
			int now = map[loc2][j];
			map[loc2][j] = tmp;
			tmp = now;
		}
		
		// 아래로 이동
		for(int i=loc2+1; i<R; i++) {
			int now = map[i][C-1];
			map[i][C-1] = tmp;
			tmp = now;
		}
		

		
		// 좌측 이동
		for(int j=C-2; j>=0; j--) {
			int now = map[R-1][j];
			map[R-1][j] = tmp;
			tmp = now;
		}
		
		// 위로 이동
		for(int i=R-2; i>loc2; i--) {
			int now = map[i][0];
			map[i][0] = tmp;
			tmp = now;
		}
	}
	
	public static void diffDust() {
		int[][] chMap = new int[R+1][C+1];
		// 아래 위 오 왼
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]<1)
					continue;
				
				int cnt = 0;
				int sep = map[i][j]/5;
				
				for(int k=0; k<4; k++) {
					int nx = i+dx[k];
					int ny = j+dy[k];
					
					if(!isInArea(nx, ny))
						continue;
					
					if(map[nx][ny]==-1)
						continue;
					
					chMap[nx][ny] += sep;
					cnt++;
				}
				
				chMap[i][j] -= sep * cnt;
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j] += chMap[i][j];
			}
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x >=0 && y>=0 && x<R && y<C;
	}
}
