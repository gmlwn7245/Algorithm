package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj17404 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int MAXNUM = 1000 * 1000 + 1;
		int[][] color = new int[N][3];
		int[][] dp = new int[N][3];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			
			if(str.equals("")) {
				i--;
				continue;
			}
			
			StringTokenizer st = new StringTokenizer(str);
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			color[i] = new int[]{r,g,b};
		}
		
		int ans = MAXNUM;
		
		// 시작 color
		for(int i=0; i<3; i++) {	
			
			// 첫째줄 설정
			for(int j=0; j<3; j++) {
				if(i==j)
					dp[0][j] = color[0][j];
				else	// 선택받지 않기 위함
					dp[0][j] = MAXNUM;
			}
			
			for(int j=1; j<N; j++) {
				dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2])+color[j][0];
				dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2])+color[j][1];
				dp[j][2] = Math.min(dp[j-1][1], dp[j-1][0])+color[j][2];
			}
			
			// 마지막줄에서 최솟값 확인
			for(int j=0; j<3; j++)
				if(i!=j)
					ans = Math.min(ans, dp[N-1][j]);
		}
		
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
}
