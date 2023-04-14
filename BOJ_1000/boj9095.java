package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class boj9095 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			bw.write(bfs(Integer.parseInt(br.readLine()))+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static int bfs(int n) {
		int cnt = 0;
		Queue<Integer> q = new LinkedList<>();
		q.add(1);q.add(2);q.add(3);
		
		while(!q.isEmpty()) {
			int now = q.poll();
			if(now == n) {
				cnt++;
				continue;
			}else if(now > n) {
				continue;
			}
			
			q.add(now+1); q.add(now+2); q.add(now+3);
		}
		
		return cnt;
	}
}
