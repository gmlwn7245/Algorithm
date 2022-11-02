package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj1328 {
	public static int N,L,R;
	public static int mod = 1000000007;
	public static long[][][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		dp=new long[N+1][N+1][N+1];
		dp[1][1][1]=1;
		
		for(int i=2; i<=N; i++) {
			dp[i][i][1] = dp[i][1][i]=1;
			for(int j=1; j<=L; j++)
				for(int k=1; k<=R; k++) {
					dp[i][j][k]=(dp[i-1][j-1][k]+dp[i-1][j][k-1]+dp[i-1][j][k]*(i-2) )% mod;
				}
		}
		
		System.out.println(dp[N][L][R]);
		
		
	}
}
