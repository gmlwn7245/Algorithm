package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj13549 {
	public static class Node {
		int x, time;
		
		public Node(int x, int time) {
			this.x = x;
			this.time = time;
		}
	}
	
	private static int n,k,minTime=Integer.MAX_VALUE,limit;
	private static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		k = sc.nextInt();
		limit = 1000000;
		visited = new boolean[2000001];
		
		bfs();
		System.out.println(minTime);		
	}
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(n, 0));
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			visited[n.x] = true;
			
			if(n.x == k) {minTime = Math.min(minTime, n.time); continue;}
			
			if(isInArea(n.x*2) && !visited[n.x*2]) q.add(new Node(n.x*2, n.time));
			if(isInArea(n.x+1) && !visited[n.x+1]) q.add(new Node(n.x+1, n.time+1));
			if(isInArea(n.x-1) && !visited[n.x-1]) q.add(new Node(n.x-1, n.time+1));
		}
	}
	
	public static boolean isInArea(int num) {
		return num>=0 && num<limit;
	}
}
