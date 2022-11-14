package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj7579 {
	static class App{
		int memory, cost;
		public App(int memory, int cost) {
			this.memory=memory;
			this.cost=cost;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String[] m = br.readLine().split(" ");
		String[] c = br.readLine().split(" ");
		
		ArrayList<App> arr = new ArrayList<>();
		
		// 0인거 모두 비활성화
		for(int i=0; i<N; i++) {
			int mem = Integer.parseInt(m[i]);
			int cos = Integer.parseInt(c[i]);			
			
			if(cos==0) {
				M -= mem;
				continue;
			}
			
			arr.add(new App(mem, cos));
		}
		
		if(M<=0) {
			bw.write("0");
			bw.flush();
			bw.close();
			return ;
		}
		
		int size = arr.size();
		if(size==1) {
			if(arr.get(0).memory>=M)
				bw.write(arr.get(0).cost+"");
			else
				bw.write("0");
			bw.flush();
			bw.close();
			return ;
		}
		
		
		// N 최대 = 100 * C 최대 100 = 10000
		int MAX = 10000;
		
		// dp [앱의 개수] [특정 비용] = 최대 메모리
		int[][] dp = new int[size][MAX+1];
		int[] cost = new int[size];
		int[] memory = new int[size];
		
		int answer = Integer.MAX_VALUE;
		
		// 앱 개수
		for(int i=0; i<size; i++) {
			App app = arr.get(i);
			
			// 총 비용
			for(int j=0; j<=MAX; j++) {
				// 앱이 1개
				if(i==0) {
					if(j >= app.cost) dp[i][j] = app.memory;
					continue;
				}
				
				if(j >= app.cost) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-app.cost]+app.memory);
				}else {
					//System.out.println(i+":"+j);
					dp[i][j] = dp[i-1][j];
				}
				
				if(dp[i][j] >= M)
					answer = Math.min(answer, j);
			}
		}
		
		bw.write(answer+"");
		bw.flush();
		bw.close();
	}
}
