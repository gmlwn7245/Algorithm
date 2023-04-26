package BOJ_1000;

import java.io.IOException;
import java.util.Scanner;

public class boj1309 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(), mod = 9901;
		int[][] dp = new int[N][3];
		
		// 00 , 01, 10
		dp[0][0] = dp[0][1] = dp[0][2] = 1;
		
		for(int i=1; i<N; i++) {
			int a = dp[i-1][0], b = dp[i-1][1], c = dp[i-1][2];
			dp[i][0] = (a+b+c)%mod;
			dp[i][1] = (a+c)%mod;
			dp[i][2] = (a+b)%mod;
		}
		
		System.out.println((dp[N-1][0]+dp[N-1][1]+dp[N-1][2])%mod);
	}
}