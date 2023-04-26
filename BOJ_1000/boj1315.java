package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1315 {
	static class Node{
		int idx, STR, INT;
		public Node(int STR, int INT, int idx) {
			this.STR = STR; this.INT =INT; this.idx = idx;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<int[]> arr = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr.add(new int[] {
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken())
			});
		}
		
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(1,1,0));
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			int nowMin = Math.min(now.INT, now.STR);
			
			for(int i=now.idx; i<N; i++) {
				int[] stack = arr.get(i);
				int STR = stack[0], INT = stack[1];
				
				int min = Math.min(STR, INT);
			}
		}
	}
}
