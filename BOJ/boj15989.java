package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class boj15989 {
	private static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		int maxNum = 0;
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			arr.add(num);
			maxNum = Math.max(maxNum, num);
		}
		
		dp = new int[maxNum+1][4];
		
		dp[1][1]=dp[2][1]=dp[3][1]=1;
		dp[2][2]=dp[3][3]=dp[3][2]=1;
		
		
		for(int i=4; i<=maxNum; i++) {
			dp[i][1]=1;
			dp[i][2]=dp[i-2][1]+dp[i-2][2];
			dp[i][3]=dp[i-3][1]+dp[i-3][2]+dp[i-3][3];
		}
		
		for(int i : arr) {
			int sum = dp[i][1]+dp[i][2]+dp[i][3];
			bw.write(sum+"\n");
		}
		
		bw.flush();
		bw.close();
	}
}
