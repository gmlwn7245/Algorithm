package BOJ_1000;

import java.io.IOException;
import java.util.Scanner;

public class boj1904 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(), mod = 15746;
		
		int[] dp = new int[N+2];
		dp[1]=1;dp[2]=2;
		
		for(int i=3; i<=N; i++) {
			dp[i]+= dp[i-1]%mod + dp[i-2]%mod;
		}
		
		System.out.println(dp[N]%mod);
	}
}
