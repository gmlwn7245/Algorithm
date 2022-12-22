package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj11058 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		if(N<4) {
			bw.write(N+"");
			bw.flush();
			bw.close();
			return ;
		}
		long[] dp = new long[N+1];
		
		for(int i=1; i<4; i++) {
			dp[i]=i;
		}
		
		for(int i=4; i<=N; i++) {
			dp[i] = dp[i-1]+1;
			for(int j=3; i-j > 0 ; j++) {
				dp[i] = Math.max(dp[i], dp[i-j] * (j-1));
			}
		}
		
		bw.write(dp[N]+"");
		bw.flush();
		bw.close();
	}
}
