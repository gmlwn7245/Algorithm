package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class boj5557 {
	private static int N, lastNum;
	private static long ans=0;
	private static int[] nums;
	private static long[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		dp = new long[N][21];
		
		String[] num = br.readLine().split(" ");
		
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(num[i]);
		}
		lastNum = nums[N-1];
		
		// [¸¶Áö¸· idx] [ÃÑÇÕ]
		dp[0][nums[0]] = 1;
		
		for(int i=1; i<N-1; i++) {
			int now = nums[i];
			Arrays.fill(dp[i],-1);
			
			for(int j=0; j<=20;j++) {
				
				if(j-now >= 0 && dp[i-1][j-now] != -1) {
					if(dp[i][j]==-1)
						dp[i][j]=0;
					dp[i][j] += dp[i-1][j-now];
				}
				
				if(j+now <= 20 && dp[i-1][j+now] != -1) {
					if(dp[i][j]==-1)
						dp[i][j]=0;
					dp[i][j] += dp[i-1][j+now];
				}
			}
		}
		
		
		bw.write(dp[N-2][lastNum]+"");
		bw.flush();
		bw.close();
	}
}
