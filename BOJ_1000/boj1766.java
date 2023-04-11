package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj1766 {
	public static int[] degree;
	public static ArrayList<Integer>[] arr;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		degree = new int[N+1];
		arr = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++)
			arr[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			degree[b]++;
			arr[a].add(b);
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=1; i<=N; i++)
			if(degree[i]==0) {
				pq.add(i);
			}
				
		
		while(!pq.isEmpty()) {
			int now = pq.poll();
			sb.append(now+" ");
			
			for(int next : arr[now]) {
				if(--degree[next]==0)
					pq.add(next);
			}
		}
		
		System.out.println(sb.toString());
		
	}
}
