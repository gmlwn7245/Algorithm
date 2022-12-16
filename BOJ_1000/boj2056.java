package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj2056 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		int[] time = new int[N+1];
		int[] DP = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			time[i] = T;
			DP[i] = 0;
			
			int W = Integer.parseInt(st.nextToken());
			
			if(W==0) {
				DP[i] = T;
				ans = Math.max(ans, DP[i]);
				continue;
			}
			
			for(int j=0; j<W; j++) {
				int w = Integer.parseInt(st.nextToken());
				DP[i] = Math.max(DP[i], DP[w]+T);
			}
			ans = Math.max(ans, DP[i]);
		}
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
}
