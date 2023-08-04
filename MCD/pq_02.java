package MCD;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class pq_02{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		PriorityQueue<Integer> leftPQ = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> rightPQ = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		int mid = 500;
		
		for(int i=1; i<=N; i++) {			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			
			if(a>mid) rightPQ.add(a);
			else leftPQ.add(a);
			
			if(b>mid) rightPQ.add(b);
			else leftPQ.add(b);
			
			
			if(rightPQ.size() > leftPQ.size()) {
				leftPQ.add(mid);
				mid = rightPQ.poll();
			}else if(rightPQ.size() < leftPQ.size()){
				rightPQ.add(mid);
				mid = leftPQ.poll();
			}
			
			bw.write(mid+"\n");
		}
		
		bw.flush();
		bw.close();
	}
}

