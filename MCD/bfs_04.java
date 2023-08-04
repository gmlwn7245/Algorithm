package MCD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_04 {
	static class Node {
		int idx, res;
		public Node(int idx, int res) {
			this.idx=idx; this.res =res;
		}
	}
	
	static class Edge{
		int idx, val;
		public Edge(int idx, int val) {
			this.idx = idx; this.val = val;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, ArrayList<Edge>> hm = new HashMap<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> arr = hm.getOrDefault(a, new ArrayList<>());
			arr.add(new Edge(b,x));
			hm.put(a, arr);
		}
		
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(1,K));
		
		int cnt = 0;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(Edge e : hm.getOrDefault(now.idx, new ArrayList<>()) ) {
				if(e.val != 0 && now.res >= e.val) {
					q.add(new Node(e.idx, now.res -e.val));
					cnt++;
				}
			}
		}
		
		bw.write(cnt+"");
		
		bw.flush();
		bw.close();				
	}
}
