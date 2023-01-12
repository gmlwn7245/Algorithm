package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class boj1753 {
	static class Node implements Comparable<Node>{
		int idx, weight;
		
		public Node (int idx, int weight) {
			this.idx =idx;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	private static int V, E, K;
	private static ArrayList<Node> next[];
	private static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		V = Integer.parseInt(str[0]);
		E = Integer.parseInt(str[1]);
		
		next = new ArrayList[V+1];
		dp = new int[V+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		K = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=V; i++)
			next[i] = new ArrayList<Node>();
		
		for(int i=0; i<E; i++) {
			str = br.readLine().split(" ");
			int u = Integer.parseInt(str[0]);
			int v = Integer.parseInt(str[1]);
			int w = Integer.parseInt(str[2]);
				
			next[u].add(new Node(v, w));
		}
		
		dp[K]=0;
		getDP();
		
		for(int i=1;i<=V;i++) {
			if(dp[i]== Integer.MAX_VALUE)
				bw.write("INF\n");
			else
				bw.write(dp[i]+"\n");
			
		}
		
		bw.flush();
		bw.close();
	}
	
	public static void getDP() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(K, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			// 해당 점과 연결되어 있는 점
			for(Node n : next[now.idx]) {
				if(dp[n.idx] > now.weight + n.weight) {
					dp[n.idx] = now.weight + n.weight;
					pq.add(new Node(n.idx, dp[n.idx]));
				}
			}
		}
	}
}
