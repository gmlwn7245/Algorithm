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

public class bfs_05 {
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
		
		
		int N = Integer.parseInt(br.readLine());
		int T = Integer.parseInt(br.readLine());
		
		HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
		
		for(int i=0; i<T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> arr = hm.getOrDefault(a, new ArrayList<>());
			arr.add(b);
			hm.put(a, arr);
			
			arr = hm.getOrDefault(b, new ArrayList<>());
			arr.add(a);
			hm.put(b, arr);
		}
		
		int coco = Integer.parseInt(br.readLine());
		int target = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new LinkedList<>();
		q.add(coco);
		
		HashSet<Integer> visited = new HashSet<>();
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int i : hm.getOrDefault(now, new ArrayList<>()) ) {
				if(visited.contains(i))
					continue;
				
				if(i==target) {
					bw.write("YES");
					bw.flush();
					bw.close();
					return ;
				}
				
				visited.add(i);
				q.add(i);
			}
		}
		
		bw.write("NO");
		
		bw.flush();
		bw.close();				
	}
}