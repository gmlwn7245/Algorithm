package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;


public class boj23255 {
	static class Building {
		int color = 0;
	}
	private static int N, M;
	private static Building[] color;
	private static HashSet<Integer>[] neighbor;
	private static PriorityQueue<int[]> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		color = new Building[N+1];
		neighbor = new HashSet[N+1];
		pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0])
					return o1[1]-o2[1];
				return o1[0]-o2[0];
			}
			
		});
		
		for(int i=1; i<=N; i++) {
			neighbor[i]= new HashSet<>();
			color[i] = new Building();
		}
			
		
		for(int i=0; i<M; i++) {
			str = br.readLine().split(" ");
			
			int ma = Integer.parseInt(str[0]);
			int mb = Integer.parseInt(str[1]);
			
			pq.add(new int[] {Math.min(ma, mb),Math.max(ma, mb)});
		}
		
		while(!pq.isEmpty()) {
			int[] p = pq.poll();
			
			int a = p[0];
			int b = p[1];
			
			if(color[a].color==0)
				color[a].color=1;
			
			neighbor[b].add(color[a].color);
			
			for(int j=1; j<=N; j++) {
				if(!neighbor[b].contains(j)) {
					color[b].color = j;
					break;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			if(color[i].color==0)
				color[i].color=1;
			
			sb.append(color[i].color+" ");
		}
		System.out.println(sb.toString().trim());
	}
}
