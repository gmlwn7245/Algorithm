package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj14719 {
	static class Building implements Comparable<Building>{
		int h, idx;
		public Building(int h, int idx) {
			this.h = h;
			this.idx = idx;
		}
		@Override
		public int compareTo(Building o) {
			return o.h - this.h;
		}
	}
	private static int[] ans, build;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		ans = new int[W];
		build = new int[W];
		
		PriorityQueue<Building> pq = new PriorityQueue<Building>();
		
		st = new StringTokenizer(br.readLine());
		
		int minIdx = Integer.MAX_VALUE, maxIdx = Integer.MIN_VALUE;
		for(int i=0; i<W; i++) {
			int h = Integer.parseInt(st.nextToken());
			if(h==0)
				continue;
			pq.add(new Building(h, i));
			minIdx = Math.min(minIdx, i);
			maxIdx = Math.max(maxIdx, i);
			build[i] = h;
		}
		
		Building top = pq.poll();
		
		while(!pq.isEmpty()) {
			Building b = pq.poll();
			if(b.idx < top.idx)
				setWater(b.idx, top.idx, b.h);
			else
				setWater(top.idx, b.idx, b.h);
		}
		
		int ans = getAns(minIdx, maxIdx);
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
	
	public static int getAns(int start, int end) {
		int cnt = 0;
		for(int i=start; i<=end; i++) {
			if(build[i] >= ans[i])
				continue;
			cnt+= ans[i]-build[i];
		}
		return cnt;
	}
	
	public static void setWater(int start, int end, int num) {
		for(int i=start; i<=end; i++) {
			ans[i] = Math.max(ans[i], num);
		}
	}
}
