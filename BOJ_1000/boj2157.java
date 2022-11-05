package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2157 {
	static class Node {
		// 노드번호, 누적방문횟수, 누적기내식점수
		int nNum, vNum, fSum;
		public Node(int nNum, int vNum, int fSum) {
			this.nNum = nNum;
			this.vNum = vNum;
			this.fSum = fSum;
		}
	}
	public static int N,M,K;
	public static int[][] skyRoad;
	public static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 노드 번호, 방문번호 = 누적 기내식점수 최대값
		dp = new int[N+1][M+1];
		skyRoad = new int[N+1][N+1];
		
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a > b)
				continue;
			
			skyRoad[a][b] = Math.max(c, skyRoad[a][b]);
		}
		for(int[] r : dp) {
			Arrays.fill(r, -1);
		}
		
		
		dp[1][1] = 0;
		for(int i=1; i<N; i++) {
			for(int j=1; j<M; j++) {
				// 해당 방문번호 존재X
				if(dp[i][j]==-1)
					continue;
				
				for(int k = i+1; k<= N ; k++) {
					// 항로 없음
					if(skyRoad[i][k]==0)
						continue;
					
					dp[k][j+1] = Math.max(dp[k][j+1], dp[i][j]+skyRoad[i][k]);
				}
			}
		}
		
		int max = 0;
		for(int i=1; i<=M; i++) {
			max = Math.max(max , dp[N][i]);
		}
		
		bw.write(max+"");
		bw.flush();
		bw.close();
	}
}
