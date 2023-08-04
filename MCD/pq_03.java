package MCD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class pq_03 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int Q = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<Q; i++) {
			int num = Integer.parseInt(st.nextToken());
			bw.write(getUglyNum(num)+" ");
		}
		
		bw.flush();
		bw.close();
		
	}
	
	public static long getUglyNum(int idx) {
		PriorityQueue<Long> pq = new PriorityQueue<>();
		pq.add(1L);
		
		long uglyNum = 0; int cnt = 0;
		
		while(cnt < idx) {
			uglyNum = pq.poll();
			
			while(!pq.isEmpty() && pq.peek() == uglyNum)
				pq.poll();
			
			pq.add(uglyNum * 2);
			pq.add(uglyNum * 3);
			pq.add(uglyNum * 5);
			
			cnt++;
		}
		
		return uglyNum;
	}
}
