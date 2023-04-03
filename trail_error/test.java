package trail_error;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;


public class test {
	public static int[][] map = new int[7][7];
	public static void main(String[] args) {
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0&&x<7&&y>=0&&y<7;
	}
	
	public static void printMap() {
		System.out.println("+++PRINT+++");
		for(int i=0; i<7; i++) {
			for(int j=0; j<7; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
