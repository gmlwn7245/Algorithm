package MCD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_02 {
	static class Node {
		int idx, res;
		public Node(int idx, int res) {
			this.idx=idx; this.res =res;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
			
			map[a][b]=x;
		}
		
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0,K));
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			pq.add(now.idx);
			
			for(int i=0; i<N; i++) {
				int val = map[now.idx][i];
				if(val!=0 && now.res >= val) {
					q.add(new Node(i, now.res-val));
				}
			}
		}
		
		while(!pq.isEmpty()) {
			int n = pq.poll();
			if(n!=0)
				bw.write(n+" ");
		}
		
		bw.flush();
		bw.close();				
	}
}
