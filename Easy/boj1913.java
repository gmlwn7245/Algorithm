package Easy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj1913 {
	private static int n, k, num=1;
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(bf.readLine());
		k = Integer.parseInt(bf.readLine());
		
		map = new int[n][n];
		int x = n/2;
		int y = n/2;
		map[x][y] = 1;
		num = 2;
		
		
		while(num<=Math.pow(n, 2)) {
			int level = (int)Math.sqrt(num);
			fillNumber(x-1, y, level);
			x--;
			y--;
		}
		
		printMap();
		
	}
	
	public static void fillNumber(int x, int y, int level) {
		//오른쪽 이동
		for(int j=0; j<=level; j++) {
			map[x][y++] = num++;
		}
			
		y--;x++;
		
		//아래 이동\
		for(int j=0; j<=level; j++) {
			map[x++][y] = num++;
		}
			
		x--;y--;
		
		
		//왼쪽 이동
		for(int j=0; j<=level; j++) {
			map[x][y--] = num++;
		}
			
		y++;x--;
		
		//위쪽 이동
		for(int j=0; j<=level; j++) {
			map[x--][y] = num++;		
		}
		
	}
	
	public static void printMap() throws IOException {
		int resX=0, resY=0;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]==k) {
					resX = i+1;
					resY = j+1;
				}
				sb.append(map[i][j]);
				if(j==n-1)
					break;
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		sb.append(resX).append(" ").append(resY);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
