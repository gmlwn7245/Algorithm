package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class boj1916 {
	static class Bus implements Comparable<Bus>{
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
	private static int N, M, start, end;
	private static int[] dp;
	private static ArrayList<Bus>[] next;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		next = new ArrayList[N+1];
		dp = new int[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		for(int i=1; i<=N; i++) {
			next[i] = new ArrayList<Bus>();
		}
		
		for(int i=0; i<M; i++) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			int w = Integer.parseInt(str[2]);
			
			next[a].add(new Bus(b,w));
		}
		
		String[] str = br.readLine().split(" ");
		start = Integer.parseInt(str[0]);
		end = Integer.parseInt(str[1]);
		dp[start]=0;
		
		bfs();
		
		bw.write(dp[end]+"");
		bw.flush();
		bw.close();
	}
	
	public static void bfs() {
		PriorityQueue<Bus> pq = new PriorityQueue<>();
		pq.add(new Bus(start, 0));
		
		while(!pq.isEmpty()) {
			Bus now = pq.poll();
			
			// 더 최소인 경우가 PQ에 있는 경우
			if(dp[now.idx] < now.cost)
				continue;
			
			for(Bus next : next[now.idx]) {
				if(dp[next.idx] > now.cost + next.cost) {
					dp[next.idx] = now.cost + next.cost;
					
					if(next.idx==end)
						continue;
					
					pq.add(new Bus(next.idx, dp[next.idx]));
				}
			}
		}
	}
}
