package BOJ;

import java.io.IOException;
import java.util.Scanner;

public class boj11726 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(), mod = 10007;
		int[] dp = new int[N+1];
		dp[0]=1;dp[1]=2;
		
		for(int i=2; i<N; i++) {
			dp[i]=(dp[i-1]%mod+dp[i-2]%mod)%mod;
		}
		
		System.out.println(dp[N-1]);
	}
}
