package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj19590 {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long sum = 0, max = 0;
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			sum += num;
			max = Math.max(max, num);
		}
		
		if(max >= sum-max) {
			System.out.println(max-(sum-max));
		}else {
			System.out.println(sum%2);
		}
		
	}
	
}
