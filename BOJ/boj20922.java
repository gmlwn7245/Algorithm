package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj20922 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		int ans = 0;
		
		HashMap<Integer, Integer> hm = new HashMap<>();
		Queue<Integer> q = new LinkedList<>();
			
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(!hm.containsKey(num)) {
				hm.put(num, 1);
				q.add(num);
				ans = Math.max(ans, q.size());
				continue;
			}
			
			if(hm.get(num)<K) {
				hm.put(num, hm.get(num)+1);
				q.add(num);
				ans = Math.max(ans, q.size());
				continue;
			}
			
			if(hm.get(num)==K) {
				while(q.peek()!=num) {
					int qNum = q.poll();
					hm.put(qNum, hm.get(qNum)-1);
				}
				q.poll();
				q.add(num);
				ans = Math.max(ans, q.size());
			}
		}
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
}
