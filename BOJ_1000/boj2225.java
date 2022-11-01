package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2225 {
	private static int mod = 1000000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 0~N ���� M�� �� => N �����
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[M+1][N+1];
		
		// ������ 1���� ���� ��� 1��
		Arrays.fill(dp[1], 1);
		
		for(int i=0; i<=M; i++) {
			// 0�� �Ǵ� ���� 1�� ��
			dp[i][0] = 1;
		}
		
		// ����
		for(int i=2; i<=M; i++) {
			// ��
			for(int j=1; j<=N; j++) {
				dp[i][j] = (dp[i-1][j]+dp[i][j-1]) % mod ;
			}
		}
		
		bw.write(dp[M][N]+"");
		bw.flush();
		bw.close();
	}
}
