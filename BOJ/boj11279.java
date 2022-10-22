package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class boj11279 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		// 높은 숫자가 우선순위로 큐 생성
		PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(br.readLine());
			// 최댓값 출력
			if(num == 0)
				bw.write(q.size()==0?"0\n":q.poll()+"\n");
			
			// 추가
			else
				q.add(num);
		}
		bw.flush();
		bw.close();
	}
}
