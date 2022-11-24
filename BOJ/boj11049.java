package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj11049 {
	static class Matrix{
		int first, last;
		public Matrix(int first, int last) {
			this.first = first;
			this.last = last;
		}
	}
	
	private static int N, ans;
	private static int[][] mat, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		mat = new int[N][2];
		dp = new int[N][N];
				
		
		for(int i=0; i<N; i++) {
			StringTokenizer st =new StringTokenizer(br.readLine());
			
			mat[i][0] = Integer.parseInt(st.nextToken());
			mat[i][1] = Integer.parseInt(st.nextToken());
		}
		getAns();
		
		bw.write(""+ans);
		bw.flush();
		bw.close();
	}
	
	public static void getAns() {
		for(int k=1; k<N; k++) {
			for(int i=0; i+k <N; i++) {
				dp[i][i+k] = Integer.MAX_VALUE;
				
				for(int j=i; j<i+k; j++) {
					dp[i][i+k] = Math.min(dp[i][i+k], dp[i][j]+dp[j+1][i+k]+mat[i][0]*mat[j][1]*mat[i+k][1]);
				}
			}
		}
		
		ans = dp[0][N-1];
	}
}
