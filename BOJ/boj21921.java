package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj21921 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new LinkedList<>();
		
		// 최대 합
		int maxSum = 0;
		// 총 합
		int sum = 0;
		// 최대 합 개수
		int cnt = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(q.size() < X) {
				sum += num;
				q.add(num);
				
				if(q.size()==X) {
					maxSum = sum;
					cnt = 1;
				}
				
				continue;
			}
			
			int last = q.poll();
			sum = sum - last + num;
			
			if(sum == maxSum) {
				cnt++;
			}else if(sum > maxSum) {
				maxSum = sum;
				cnt=1;
			}
			q.add(num);
		}
		
		if(maxSum==0)
			bw.write("SAD");
		else {
			bw.write(maxSum+"\n"+cnt);
		}
		
		bw.flush();
		bw.close();
		
	}
}
