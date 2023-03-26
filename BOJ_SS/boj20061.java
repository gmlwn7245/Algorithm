package BOJ_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj20061 {
	public static int ans = 0;
	public static boolean[][] blue, green;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		blue = new boolean[6][4];
		green = new boolean[6][4];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			/* t = 1 : x,y
			 * t = 2 : x,y  x,y+1
			 * t = 3 : x,y  x+1,y
			 * */
			
			setGreen(t,x,y);
			setBlue(t,y,x);
		}
		
		int sum = cnt(blue)+cnt(green);
		System.out.println(ans);
		System.out.println(sum);
	}
	
	public static int cnt(boolean[][] map) {
		int cnt = 0;
		for(int i=0; i<6; i++)
			for(int j=0; j<4;j++)
				if(map[i][j])
					cnt++;
		return cnt;
	}
	
	public static void setBlue(int t, int x, int y) {
		if(t == 1) {
			int nx = getX(y, blue);
			blue[nx][y]=true;
		}else if(t == 3) {
			int nx = Math.min(getX(y, blue), getX(y+1, blue));
			blue[nx][y]=true;
			blue[nx][y+1]=true;
		}else {
			int nx = getX(y,blue);
			blue[nx][y]=true;
			blue[nx-1][y]=true;
		}
		
		checkRow(blue);
		checkTop(blue);
	}
	
	public static void setGreen(int t, int x, int y) {
		if(t == 1) {
			int nx = getX(y, green);
			green[nx][y]=true;
		}else if(t == 2) {
			int nx = Math.min(getX(y, green), getX(y+1, green));
			green[nx][y]=true;
			green[nx][y+1]=true;
		}else {
			int nx = getX(y,green);
			green[nx][y]=true;
			green[nx-1][y]=true;
		}
		
		checkRow(green);
		checkTop(green);
	}
	
	public static int getX(int y, boolean[][] map) {
		for(int x=0; x<6; x++)
			if(map[x][y])
				return x-1;
		return 5;
	}
	
	public static void checkRow(boolean[][] map) {
		for(int i=0; i<6; i++) {
			boolean check = true;
			
			for(int j=0; j<4; j++) {
				check &= map[i][j];
			}
			
			// ´ç±â±â
			if(check) {
				ans++;
				for(int r = i-1; r>=0; r--) {
					for(int c = 0; c<4; c++) {
						map[r+1][c] = map[r][c];
						map[r][c]=false;
					}
				}
			}
		}
	}
	
	public static void checkTop(boolean[][] map) {
		int cnt = 0;
		for(int i=0; i<2; i++)
			for(int j=0; j<4; j++) {
				if(map[i][j]) {
					cnt++;
					break;
				}
			}
		if(cnt == 0)
			return ;
		
		for(int i=5-cnt; i>=0; i--) {
			for(int j=0; j<4; j++) {
				map[i+cnt][j]=map[i][j];
				map[i+cnt][j]=false;
			}
		}
	}
	
	public static void printMap(String str,boolean[][] map) {
		System.out.println("======="+str+"=======");
		for(int i=0; i<6; i++) {
			for(int j=0; j<4; j++) {
				if(!map[i][j])
					System.out.print("0 ");
				else
					System.out.print("1 ");
			}
			System.out.println();
		}
	}
	
	
	
}
