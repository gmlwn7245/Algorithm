package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj1446 {
	static class Road implements Comparable<Road>{
		int start, end, scut;
		public Road(int start, int end, int scut) {
			this.start = start;
			this.end = end;
			this.scut = scut;
		}
		@Override
		public int compareTo(Road o) {
			if(this.start == o.start) {
				if(this.end == o.end) {
					return this.scut - o.scut;
				}
				return this.end - o.end;
			}
			return this.start - o.start;
		}
	}
	
	private static int ans,N,D;
	private static ArrayList<Road> arr = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Road> pq = new PriorityQueue<>();
		
		int minEnd = Integer.MAX_VALUE;
		ans = D;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end  = Integer.parseInt(st.nextToken());
			int scut = Integer.parseInt(st.nextToken());
			
			// 지름길이 더 오래 걸릴때
			if((end-start) <= scut)
				continue;
			
			// 지름길이 도착점보다 더 멀때
			if(end > D)
				continue;
			
			minEnd = Math.min(end, minEnd);
			
			pq.add(new Road(start, end, scut));
		}
		
		if(pq.isEmpty()) {
			bw.write(ans+"");
			bw.flush();
			bw.close();
			return ;
		}
		
		Road r = pq.poll();
		arr.add(r);
		
		while(!pq.isEmpty()) {			
			Road nr = pq.poll();
			if(r.start == nr.start && r.end == nr.end)
				continue;
			
			arr.add(nr);
			r = nr;
		}
		
		for(int i=0; i<arr.size(); i++) {
			r = arr.get(i);
			if(arr.get(i).start >= minEnd)
				break;
			
			int dis = r.start + r.scut;
			dfs(i+1, r.end, dis);
		}
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
	
	public static void dfs(int idx, int end, int dis) {
		for(int i=idx; i<arr.size(); i++) {
			Road r = arr.get(i);
			if(r.start < end)
				continue;
			
			int ndis = dis + r.start-end + r.scut;
			dfs(i+1, r.end, ndis);
		}

		ans = Math.min(ans, D-end+dis);
	}
}
