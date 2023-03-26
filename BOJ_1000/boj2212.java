package BOJ_1000;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class boj2212 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++)
			pq.add(sc.nextInt());
		
		int ans = 0;
		while(!pq.isEmpty()) {
			
		}
		
	}
}
