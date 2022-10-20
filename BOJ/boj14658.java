package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj14658 {
	
	static class Star {
		int x, y;
		public Star(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int n,m,l,k,min;
	private static ArrayList<Star> stars = new ArrayList<>();
	private static HashSet<Integer> starX = new HashSet<>();
	private static HashSet<Integer> starY = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st =new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		min = k;		
		
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());		
			stars.add(new Star(x,y));
			starX.add(x);
			starY.add(y);
		}
		
		// 트램펄린 모서리에 걸친 경우에만 탐색
		for(int x : starX) {
			for(int y : starY) {
				min = Math.min(min, fallCnt(x,y));
			}
		}
				
		bw.write(min+"");
		bw.flush();
		bw.close();
	}
	
	public static int fallCnt(int x, int y) {
		int cnt = 0;
		
		for(Star s : stars) {
			if(isInArea(s, x, y))
				continue;
			cnt++;
		}
		
		return cnt;
	}
	
	public static boolean isInArea(Star s, int x, int y) {
		return s.x>=x && s.x<=(x+l) && s.y>=y && s.y<=(y+l);
	}
}
