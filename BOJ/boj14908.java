package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class boj14908 {
	static class Process{
		int idx;
		double rate;
		public Process(int idx, double rate) {
			this.idx = idx;
			this.rate = rate;
		}
	}
	private static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Process> pq = new PriorityQueue<Process>(new Comparator<Process>() {
			@Override
			public int compare(Process o1, Process o2) {				
				if(o1.rate==o2.rate)
					return o1.idx - o2.idx;
				
				if(o1.rate> o2.rate)
					return 1;
				
				return -1;
			}
		});
		
		for(int i=1; i<=N; i++) {
			String[] str = br.readLine().split(" ");
			double T = Double.parseDouble(str[0]);
			double S = Double.parseDouble(str[1]);
			
			double rate = T/S;
			pq.add(new Process(i,rate));
		}
		
		while(!pq.isEmpty())
			sb.append(pq.poll().idx+" ");
		System.out.println(sb.toString().trim());
	}
}
