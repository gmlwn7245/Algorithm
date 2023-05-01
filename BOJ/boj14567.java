package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj14567 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] degree = new int[N+1];
		
		HashSet<Integer>[] next = new HashSet[N+1];
		HashSet<Integer> hs = new HashSet<>();
		int[] fin = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			next[i] = new HashSet<>();
			hs.add(i);
		}
			
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			next[a].add(b); degree[b]++;
		}	
		
		int cnt = 1;
		while(hs.size()>0) {
			HashSet<Integer> rm = new HashSet<>();
			for(int i : hs) {
				//System.out.println(i);
				if(degree[i]==0) {
					fin[i]=cnt;
					rm.add(i);
				}
			}
			//System.out.println(hs.size());
			
			for(int now : rm) {
				hs.remove(now);
				for(int nexts : next[now]) {
					degree[nexts]--;
				}
			}
			
			cnt++;
		}
			
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(fin[i]+" ");
		
		System.out.println(sb.toString().trim());
		
	}
}
