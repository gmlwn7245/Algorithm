package trail_error;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;


public class test {
	public static int[][] map = new int[5][7];
	public static void main(String[] args) {
		int i = 3, N=5;
		int x = N-1, y = i;
		map[N-1][i]=1;
		
		int D = 3;
		
		for(int j=1; j<=D; j++) {
			int d = j*2+1;
			y=i-j; x=N-1;
			
			while(d-- > 0) {
//				System.out.println(x+" "+y);
				map[x][y]=j+1;
				
				y++;
				int res = j-Math.abs(y-3);
				x = 4-res;
				
				
			}
		}
		
		//printMap();
	}
	
	public static void printMap() {
		System.out.println("+++PRINT+++");
		for(int i=0; i<5; i++) {
			for(int j=0; j<7; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
