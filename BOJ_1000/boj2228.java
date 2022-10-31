package BOJ_1000;

import java.util.Arrays;
import java.util.Scanner;

public class boj2228 {
	private static int N,M;
	private static int[] num,sum;
	private static int[][] dp;
	// => idx까지 m개 구간 선택
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
				
		N = sc.nextInt();
		M = sc.nextInt();
		
		dp = new int[N+1][M+1];		
		num = new int[N+1];
		sum = new int[N+1];
		
		
		for(int i=1; i<=N; i++) {
			num[i] = sc.nextInt();
			sum[i] = sum[i-1]+num[i];
		}
		
		for(int i=0; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				dp[i][j]=Integer.MIN_VALUE;
			}
		}
		
		dp[1][1] = num[1];
		
		for(int i=2; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				// 포함 X
				dp[i][j] = dp[i-1][j];
						
				int min = 0;
				if(j==1)
					min = -1;
				
				for (int k = i - 2; k >= min; k--) {
					if (k < 0)
						dp[i][j] = Math.max(dp[i][j], sum[i]);
					else
						dp[i][j] = Math.max(dp[i][j], dp[k][j - 1] + sum[i] - sum[k + 1]);
				}
					
			}
		}
		
		
		System.out.println(dp[N][M]);
	}
	
}
