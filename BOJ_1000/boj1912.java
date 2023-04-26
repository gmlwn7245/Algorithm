package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1912 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] dp = new int[N];
		dp[0]=Integer.parseInt(st.nextToken());
		
		int ans = dp[0];
		
		for(int i=1; i<N; i++) {
			int now = Integer.parseInt(st.nextToken());
			dp[i] = Math.max(now, dp[i-1]+now);
			//System.out.print(dp[i]+" ");
			ans = Math.max(dp[i], ans);
		}
		
		System.out.println(ans);
	}
}
