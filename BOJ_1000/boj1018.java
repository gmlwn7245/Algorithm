package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1018 {
	public static int N, M, ans;
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		map = new int[N][M];
		ans = N * M;
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				char ch = s.charAt(j);
				if(ch == 'B')
					map[i][j]=1;
			}
		}
		
		for(int i=0; i+8<=N; i++) {
			for(int j=0; j+8<=M; j++) {
				getMin(i,j);
			}
		}
		
		System.out.println(ans);
	}
	
	public static void getMin(int x, int y) {
		int cnt1 = 0, cnt2 = 0;
		
		// Â¦¼ö ¿­
		for(int i=x; i<x+8; i+=2) {
			for(int j=y; j<y+8; j+=2) {
				if(map[i][j]!=1)
					cnt1++;
			}
			for(int j=y+1; j<y+8; j+=2) {
				if(map[i][j]!=0)
					cnt1++;
			}
		}
		
		// È¦¼ö ¿­
		for(int i=x+1; i<x+8; i+=2) {
			for(int j=y; j<y+8; j+=2) {
				if(map[i][j]!=0)
					cnt1++;
			}
			for(int j=y+1; j<y+8; j+=2) {
				if(map[i][j]!=1)
					cnt1++;
			}
		}
		
		cnt2 = 64 - cnt1;
		
		ans = Math.min(ans, Math.min(cnt1, cnt2));
	}
	
}
