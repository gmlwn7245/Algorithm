package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1504 {
	
	public static int N, E, m1, m2;
	public static boolean poss1=false, poss2=false;
	public static HashMap<Integer, HashSet<int[]>> hm = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			HashSet<int[]> hs = hm.getOrDefault(a, new HashSet<>());
			hs.add(new int[] {b,c});
			hm.put(a, hs);
			
			hs = hm.getOrDefault(b, new HashSet<>());
			hs.add(new int[] {a,c});
			hm.put(b, hs);
		}
		
		st = new StringTokenizer(br.readLine());
		m1 = Integer.parseInt(st.nextToken());
		m2 = Integer.parseInt(st.nextToken());
		
		// 1 - m1 - m2 - N
		int s11 = Dijkstra(1, m1);
		int mid = Dijkstra(m1,m2);
		int s12 = Dijkstra(m2, N);
		
		if(mid == 0) {
			System.out.println(-1);
			return ;
		}
		
		// 1 - m2 - m1 - N
		int s21 = Dijkstra(1, m2);
		int s22 = Dijkstra(m1, N);
		
		if((s11==0 || s12==0) && (s21==0 || s22==0))
			System.out.println(-1);
		else
			System.out.println(Math.min(s11+mid+s12, s21+mid+s22));
	}
	
	public static int Dijkstra(int start, int end) {
		int[] dp = new int[N+1];
		
		Queue<Integer> q = new LinkedList<>();
		HashSet<Integer> include = new HashSet<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			int now = q.poll();
			include.remove(now);
			
			for(int[] neighbor : hm.getOrDefault(now, new HashSet<>())) {
				int next = neighbor[0];
				int cost = neighbor[1];
				
				if(next == start)
					continue;
				
				if(next == end) {
					if(dp[next]==0 || (dp[next]!=0 && dp[next] > dp[now]+cost))
						dp[next]= dp[now]+cost;
					continue;
				}
				
				if(dp[next]==0 || dp[next]!=0 && dp[next] > dp[now]+cost) {
					dp[next]= dp[now]+cost;
					if(!include.contains(next)) {
						include.add(next);
						q.add(next);
					}
					continue;
				}
			}
		}
		
		return dp[end];
	}
}
