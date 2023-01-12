package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class boj11404 {
	static class Bus implements Comparable<Bus> {
		int idx, cost;
		
		public Bus(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
		
		
		@Override
		public int compareTo(Bus o) {
			return this.cost - o.cost;
		}
		
	}
	private static int N,M;
	private static int MAX = 9900001;
	private static int[][] dp;
	private static ArrayList<Bus>[] next;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dp = new int[N+1][N+1];
		next = new ArrayList[N+1];
	
		for(int i=1; i<=N; i++) {
			Arrays.fill(dp[i], MAX);
			dp[i][i]=0;
			next[i] = new ArrayList<Bus>();
		}
		
		for(int i=0; i<M; i++) {
			String[] str = br.readLine().split(" ");
			
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			int w = Integer.parseInt(str[2]);
			
			next[a].add(new Bus(b,w));
		}
		
		for(int i=1; i<=N; i++) {
			bfs(i);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(dp[i][j]>=MAX)
					dp[i][j]=0;
				
				sb.append(dp[i][j]+" ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void bfs(int num) {
		PriorityQueue<Bus> pq = new PriorityQueue<>();
		pq.add(new Bus(num, 0));
		
		while(!pq.isEmpty()) {
			Bus now = pq.poll();
			
			if(dp[num][now.idx] < now.cost)
				continue;
			
			for(Bus bus : next[now.idx]) {
				if(dp[num][bus.idx] > now.cost + bus.cost) {
					dp[num][bus.idx] = now.cost + bus.cost;
					
					pq.add(new Bus(bus.idx, dp[num][bus.idx]));
				}
			}
		}
	}
}
