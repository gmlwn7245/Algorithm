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
import java.util.Queue;
import java.util.StringTokenizer;

public class boj17404 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int MAXNUM = 1000001;
		int[][] color = new int[N][3];
		int[][] dp = new int[N][3];
		int[][] start = new int[N][3];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			if(str.equals("")){
				i--;
				continue;
			}
			StringTokenizer st = new StringTokenizer(str);
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			color[i] = new int[]{r,g,b};
			dp[i] = new int[] {r,g,b};
		}
		
		for(int i=0; i<3; i++) {
			start[0][i] = i;
		}
		
		int firstMin = MAXNUM, firstIdx = -1;
		int lastMin = MAXNUM, lastIdx = -1;
		
		for(int i=0; i<3; i++) {
			if(firstMin > color[0][i]) {
				firstMin = color[0][i];
				firstIdx = i;
			}
			
			if(lastMin > color[N-1][i]) {
				lastMin = color[N-1][i];
				lastIdx = i;
			}
		}
		
		if(firstMin > lastMin && lastIdx == firstIdx) {
			start[0][lastIdx] = -1;
		}
		
		
		int ans = MAXNUM;
		for(int i=1; i<N-1; i++) {
			
			// 각 RGB 자리의 최소값 구하기
			for(int j=0; j<3; j++) {
				// 최소값과 최소값을 가진 이전 줄 idx
				int min = MAXNUM;
				int idx = 0;
				
				for(int k=0; k<3; k++) {
					if(j==k)
						continue;
					if(start[i-1][k]==-1)
						continue;
					
					if(dp[i-1][k] < min) {
						idx = start[i-1][k];
						min = dp[i-1][k];
					}
				}
				
				dp[i][j] += min;
				start[i][j] = idx;
			}
		}
		
		
		// 마지막 3개
		for(int i=0; i<3; i++) {
			int min = MAXNUM;
			
			// 앞에 3개
			for(int j=0; j<3; j++) {
				if(i==j)
					continue;
				if(start[N-2][j]==i)
					continue;
				
				if(dp[N-2][j] < min) {
					min = dp[N-2][j];
				}
			}
			
			if(min==MAXNUM)
				continue;
			
			min += color[N-1][i];
			ans = Math.min(ans, min);
		}
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
}
