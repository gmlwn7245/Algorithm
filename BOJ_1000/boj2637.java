package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class boj2637 {
	static class Node {
		int num, cnt;
		public Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
	
	public static int N;
	public static int[] degree, ans;
	public static HashSet<Node>[] prev;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		prev = new HashSet[N+1];
		ans = new int[N+1];
		degree = new int[N+1];
		
		for(int i=0; i<M; i++) {
			String[] st = br.readLine().split(" ");
			int X = Integer.parseInt(st[0]);
			int Y = Integer.parseInt(st[1]);
			int K = Integer.parseInt(st[2]);
			
			if(prev[X] == null)
				prev[X]=new HashSet<Node>();
			prev[X].add(new Node(Y, K));
			degree[Y]++;
		}
		
		topologicalSort();
		
		for(int i=1; i<N; i++) {
			if(prev[i]==null)
				System.out.println(i+" "+ans[i]);
		}
		
	}
	
	public static void topologicalSort() {
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		ans[N]=1;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if(prev[now]== null)
				continue;
			
			for(Node next : prev[now]) {
				ans[next.num] += ans[now] * next.cnt;
				
				if(--degree[next.num] == 0)
					q.add(next.num);
			}
		}
	}
}
