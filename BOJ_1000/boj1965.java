package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj1965 {
	private static int n,max=0;
	private static int[] box, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		box = new int[n];
		dp = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		for(int i=0; i<n; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}
		
		getMaxBox();
		
		bw.write(""+max);
		bw.flush();
		bw.close();
	}
	
	public static void getMaxBox() {
		for(int i=0; i<n; i++) {
			int maxNum = 0;
			for(int j=0; j<i; j++) {
				if(box[i]<=box[j])
					continue;
				maxNum = Math.max(maxNum, dp[j]);
			}
			
			dp[i]= maxNum+1;
			max = Math.max(dp[i], max);
		}
	}
}
