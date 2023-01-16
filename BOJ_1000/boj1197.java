package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj1197 {
	static class Node implements Comparable<Node>{
		int a, b, w;
		public Node(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {	
			// weight가 작은 순서대로
			return this.w - o.w;
		}
	}
	private static int V, E;
	private static int[] root;
	private static PriorityQueue<Node> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		
		V = Integer.parseInt(str[0]);
		E = Integer.parseInt(str[1]);
		pq = new PriorityQueue<>();
		root = new int[V+1];
		
		for(int i=1; i<=V; i++) {
			root[i] = i;
		}
		
		for(int i=0; i<E; i++) {
			String s = br.readLine();
			
			if(s.equals("")) {
				i--;
				continue;
			}
			
			str = s.split(" ");
			
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			int w = Integer.parseInt(str[2]);
			
			pq.add(new Node(a,b,w));
		}
		
		Kruskal();
	}

	
	// 크루스칼
	public static void Kruskal() {
		int sum = 0;
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			
			if(find(n.a)!=find(n.b)) {
				sum += n.w;
				union(n.a, n.b);
			}
		}
		System.out.println(sum);
	}
	
	// Union 
	public static void union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		
		if(aParent < bParent) root[bParent] = aParent;
		else root[aParent] = bParent;
	}
	
	// Find
	public static int find(int x) {
		if(root[x] == x) return x;
		return find(root[x]);
	}

}
