package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1949 {	
	private static int N, maxNum = 0;
	private static int[] pop;
	private static int[][] dp;
	private static ArrayList<Integer>[] nears;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
				
		N = Integer.parseInt(br.readLine());
		pop = new int[N+1];
		dp = new int[N+1][2];
		nears = new ArrayList[N+1];
				
		
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		for(int i=1; i<=N; i++) {
			pop[i] = Integer.parseInt(st.nextToken());
			nears[i] = new ArrayList<>();		
		}
		
		HashSet<Integer> starts = new HashSet<>();
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			nears[a].add(b);
			nears[b].add(a);
		}
		
		dfs(1, 0);
		
		maxNum = Math.max(dp[1][0], dp[1][1]);
		
		bw.write(maxNum +"");
		bw.flush();
		bw.close();
	}
	
	public static void dfs(int idx, int par) {
		for(int now : nears[idx]) {
			if(now==par)
				continue;
			
			dfs(now, idx);
			dp[idx][0] += Math.max(dp[now][0], dp[now][1]);
			dp[idx][1] += dp[now][0];
		}
		
		dp[idx][1] += pop[idx];
	}
}
