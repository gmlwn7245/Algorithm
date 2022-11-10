package trail_error;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class boj15685 {
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static int N;
	private static int[][] map = new int[101][101];
	private static int[] dx = {0,-1,0,1};
	private static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			getDragon(new Point(y,x), d, g);
			
		}
		
		bw.write(getCnt()+"");
		bw.flush();
		bw.close();
			
	}
	
	
	public static int getCnt() {
		int cnt = 0;
		
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j]==1 && map[i][j+1]==1 && map[i+1][j]==1 && map[i+1][j+1]==1) {
					//System.out.println(i+"="+j);
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	public static void getDragon(Point p, int d, int g) {
		ArrayList<Point> arr = new ArrayList<>();
		
		Point endP = new Point(p.x + dx[d], p.y+dy[d]);
		arr.add(p);
		arr.add(endP);
		
		if(g == 0) {
			setMap(arr);
			return ;
		}
		
		for(int i=1; i<=g; i++) {
			arr = getRot(arr, endP);
			int nx = endP.x - p.x;
			int ny = endP.y - p.y;
			if(nx < 0 && nx > 0) {
				endP = new Point(endP.x + ny, endP.y - nx);
			}else {
				endP = new Point(endP.x - ny, endP.y + nx);
			}
		}
		setMap(arr);
	}
	
	public static ArrayList<Point> getRot(ArrayList<Point> arr, Point endP) {
		ArrayList<Point> addArr = new ArrayList<>();
		for(Point p : arr) {
			int ddx = endP.x - p.x;
			int ddy = endP.y - p.y;
			int nx, ny;
			
			if(ddx < 0 && ddy > 0) {
				nx = endP.x + ddy;
				ny = endP.y - ddx;
			}
			
			nx = endP.x - ddy;
			ny = endP.y + ddx;
			
			if(!isInArea(nx, ny))
				continue;
			
			addArr.add(new Point(nx, ny));
		}
		endP = addArr.get(addArr.size()-1);
		arr.addAll(addArr);
		
		return arr;
	}
	
	public static void setMap(ArrayList<Point> arr) {
		for(Point p : arr) {
			map[p.x][p.y] = 1;
		}
	}
	
	
public static void printMap(HashSet<Point> hs) {
		
		System.out.println("======PRINT MAP=====");
		int[][] m = new int[8][8];
		
		for(Point p : hs) {
			m[p.x][p.y] = 1;
		}
		
		for(int i=0; i<8; i++) {
			for(int j=0; j<7; j++) {
				System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	
	public static boolean isInArea(int x, int y) {
		return x >= 0 && x<100 && y>=0 && y<100;
	}
}
