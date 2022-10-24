package BOJ_SS;

import java.util.ArrayList;
import java.util.Scanner;

public class boj15686 {
	
	static class H {
		int x, y;
		int[] dist;
		public H(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class C {
		int x, y, idx;
		public C(int x, int y, int idx) {
			this.x = x;
			this.y = y;
			this.idx = idx;
		}
	}
	
	private static int n,m,minDist=Integer.MAX_VALUE;
	private static ArrayList<C> chickens = new ArrayList<>();
	private static ArrayList<H> homes = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
				
		// 0빈칸 1집 2치킨집
		// 집 개수 <= 2N
		// M <= 치킨집 <= 13
		
		int chickenCnt = 0;
		for(int i=0; i<n; i++) 
			for(int j=0; j<n; j++) {
				int num = sc.nextInt();
				if(num==1)
					homes.add(new H(i,j));
				else if(num == 2) {
					chickens.add(new C(i,j,chickenCnt++));
				}
			}
		
		for(H h : homes) 
			h.dist = new int[chickenCnt];
		
		dfs(0,-1,new ArrayList<>());
		System.out.println(minDist);
		
	}
	
	public static void dfs(int dep, int idx, ArrayList<Integer> arr) {
		if(dep == m) {
			getTotalDist(arr);
			return ;
		}
		
		for(int i=idx+1; i<chickens.size(); i++) {
			arr.add(i);
			dfs(dep+1, i, arr);
			arr.remove(arr.size()-1);
		}
	}
	
	public static void getTotalDist(ArrayList<Integer> arr) {
		int totDist = 0;
		for(H h : homes) {
			int min = Integer.MAX_VALUE;
			for(int idx : arr) {
				C c = chickens.get(idx);
				if(h.dist[idx]==0)
					h.dist[idx]=getDist(h, c);
				min = Math.min(min, h.dist[idx]);
			}
			totDist += min;
		}
		
		minDist = Math.min(minDist, totDist);
	}
	
	public static int getDist(H h, C c) {
		return Math.abs(h.x-c.x)+Math.abs(h.y-c.y);
	}
}
