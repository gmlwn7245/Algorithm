package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj2166 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		long[] x = new long[N+1];
		long[] y = new long[N+1];
		
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split(" ");
			x[i] = Long.parseLong(str[0]);
			y[i] = Long.parseLong(str[1]);
		}
		
		x[N]=x[0];y[N]=y[0];
		
		double sum1 = 0, sum2=0;
		for(int i=0; i<N; i++) {
			sum1 += x[i] * y[i+1];
			sum2 += y[i] * x[i+1];
		}
		
		System.out.printf("%.1f",Math.abs(sum1-sum2)/2.0);
	}
}
