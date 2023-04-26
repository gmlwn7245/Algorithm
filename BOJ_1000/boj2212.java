package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj2212 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		if(K >= N) {
			System.out.println(0);
			return ;
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(), dist = new PriorityQueue<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			pq.add(Integer.parseInt(st.nextToken()));
		}
		
		int prev = pq.poll();
		while(!pq.isEmpty()) {
			int now = pq.poll();
			dist.add(now - prev);
			prev = now;
		}
		
		int sum = 0;
		while(N-- > K) {
			sum += dist.poll();
		}
		System.out.println(sum);
	}
}
