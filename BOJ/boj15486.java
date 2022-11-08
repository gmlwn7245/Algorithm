package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj15486 {
	static class Node {
		int T, P;
		public Node(int T, int P) {
			this.T = T;
			this.P = P;
		}
	}
	
	private static int N, maxP=0;
	private static Node[] node;
	private static boolean[] isPossible;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		node = new Node[N+1];
		isPossible = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			node[i] = new Node(t,p);
			if(canDoSchedule(i))
				isPossible[i] = true;
		}
		
		int firstend = node[1].T;
		
		for(int i=1; i<=firstend; i++) {
		}
		
		bw.write(maxP+"");
		bw.flush();
		bw.close();
	}
	
	public static boolean canDoSchedule(int n) {
		return node[n].T + n - 1 <= N;
	}
}
