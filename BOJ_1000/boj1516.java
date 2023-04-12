package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1516 {
	public static int N;
	public static int[] degree, time, end;
	public static HashMap<Integer, HashSet<Integer>> hm = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		degree = new int[N+1];
		time = new int[N+1];
		end = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i]= Integer.parseInt(st.nextToken());
			
			while(st.hasMoreTokens()) {
				int n = Integer.parseInt(st.nextToken());
				if(n!=-1) {
					degree[i]++;
					HashSet<Integer> hs = hm.getOrDefault(n, new HashSet<>());
					hs.add(i);
					hm.put(n, hs);
				}
			}
		}
		
		// 작업 번호, 누적 시간
		Queue<int[]> q = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(degree[i]==0) {
				q.add(new int[] {i, time[i]});
				end[i]=time[i];
			}
				
		}
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int nowIdx = now[0];
			int timeSum = now[1];
			
			for(int next : hm.getOrDefault(nowIdx, new HashSet<>())) {
				if(end[next]!=0)
					end[next] = Math.max(end[next], timeSum+time[next]);
				else
					end[next]=timeSum+time[next];
				
				if(--degree[next] == 0) {
					q.add(new int[] {next, end[next]});
				}
			}
		}
		
		for(int i=1; i<=N; i++)
			System.out.println(end[i]);
	}
}
