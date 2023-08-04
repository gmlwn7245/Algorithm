package MCD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_06 {
	static class Node {
		int idx, dep;
		public Node(int idx, int dep) {
			this.idx=idx; this.dep =dep;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken());
		
		HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> arr = hm.getOrDefault(a, new ArrayList<>());
			arr.add(b);
			hm.put(a, arr);
			
			arr = hm.getOrDefault(b, new ArrayList<>());
			arr.add(a);
			hm.put(b, arr);
		}
		
		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(K==0) {
			bw.write("1");
			bw.flush();
			return;
		}
		
		
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(R, 0));
		
		HashSet<Integer> visited = new HashSet<>();
		visited.add(R);
		
		int[] busCnt = new int[N+1];
		
		int cnt = 0;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(int i : hm.getOrDefault(now.idx, new ArrayList<>()) ) {		
				if(busCnt[i]!=0&& now.dep+1 >= busCnt[i])
					continue;
				visited.add(i);
				busCnt[i] = now.dep+1;
				
				if(now.dep==K-1)
					continue;
				
				q.add(new Node(i, now.dep+1));
			}
		}
		
		bw.write(visited.size()+"");
		
		bw.flush();
		bw.close();				
	}
}
