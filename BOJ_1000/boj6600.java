package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj6600 {
	private static double PI = 3.141592653589793;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str;
		while((str=br.readLine())!=null) {
			
			String[] s = str.split(" ");
			
			int idx = 0;
			double x1, y1, x2, y2, x3, y3;
			x1 = Double.parseDouble(s[idx++]);
			y1 = Double.parseDouble(s[idx++]);
			x2 = Double.parseDouble(s[idx++]);
			y2 = Double.parseDouble(s[idx++]);
			x3 = Double.parseDouble(s[idx++]);
			y3 = Double.parseDouble(s[idx++]);
			
			double d = Math.hypot(x3-x2, y3-y2);
			double ax = x2 - x1, ay = y2 - y1, bx = x3 - x1, by = y3 - y1;
			double theta = Math.acos((ax * bx + ay * by)/Math.hypot(ax, ay) / Math.hypot(bx, by));
			
			double ans = Math.round(100*(d/Math.sin(theta)*PI))/100.0;
			sb.append(ans+"\n");
		}
		System.out.println(sb.toString());
	}
}
