package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj11722 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] score = new int[N];
		for(int i=0; i<N; i++)
			score[i]=Integer.parseInt(st.nextToken());
			
		int[] dp = new int[N];
		int ans = 1; dp[0]=1;
		for(int i=1; i<N; i++) {
			int now = score[i];
			int max = 0; dp[i]=1; 
			for(int j=i-1; j>=0; j--) {
				int prev = score[j];
				if(prev > now) {
					max = Math.max(max, dp[j]);
				}
			}
			
			dp[i]+=max;
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
	}
}
