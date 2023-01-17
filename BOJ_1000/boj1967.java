package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class boj1967 {
	static class Node {
		int idx;
		int w;
		
		public Node(int idx, int w) {
			this.idx = idx;
			this.w = w;
		}
	}
	private static int N, maxNum = 0;
	private static ArrayList<Node>[] children;
	private static int[] maxSum;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		children = new ArrayList[N+1];
		maxSum = new int[N+1];
		
		for(int i=1; i<=N; i++)
			children[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			int w = Integer.parseInt(str[2]);
			
			children[a].add(new Node(b,w));
		}
		
		for(int i=1; i<=N; i++) {
			//System.out.println(i);
			if(children[i].size()==0)
				continue;
			
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			for(Node n : children[i]) {
				if(maxSum[n.idx]==0)
					getMax(n.idx);
				
				pq.add(maxSum[n.idx]+n.w);
			}
			
			int a = pq.poll();
			int b = pq.size()==0?0:pq.poll();
			
			maxNum = Math.max(a+b, maxNum);
		}
		
		System.out.println(maxNum);
	}
	
	public static void getMax(int idx) {
		for(Node n : children[idx]) {
			if(maxSum[n.idx]==0)
				getMax(n.idx);
			
			maxSum[idx] = Math.max(maxSum[idx], n.w + maxSum[n.idx]);
		}
	}
}
