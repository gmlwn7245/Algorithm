package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj2624 {
	static int T, K , ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[K+1][T+1];
		
		for(int i=1; i<=K; i++) {
			dp[i-1][0] = 1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			for(int j=1; j<=T; j++) {
				for(int k=0; k <= c && k * p <= j; k++ ) {
					dp[i][j] += dp[i-1][j - k*p];
				}
			}
		}
		
		bw.write(dp[K][T]+"");
		bw.flush();
		bw.close();
	}
}
