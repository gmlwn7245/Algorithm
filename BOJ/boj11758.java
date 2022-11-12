package BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class boj11758 {
	static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Point> points = new ArrayList<>();
		for(int i=0; i<3; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			points.add(new Point(x,y));
		}
		
		Point p1 = points.get(0);
		Point p2 = points.get(1);
		Point p3 = points.get(2);
		
		int n1 = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
		int n2 = p2.x * p1.y + p3.x * p2.y + p1.x * p3.y;
		
		int sum = n1- n2;
		
		// ¹Ý½Ã°è
		if(sum > 0) {
			System.out.println(1);
		}else if(sum < 0) {
			System.out.println(-1);
		}else {
			System.out.println(0);
		}
		
	}
	
}
