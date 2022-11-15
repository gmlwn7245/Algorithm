package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class boj2229 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] d = new int[N+1];
		int[] p = new int[N+1];
		for(int i=1; i<=N; i++) {
			int max = 0, min = Integer.MAX_VALUE;
			
			p[i] = Integer.parseInt(st.nextToken());
			
			for(int j=i; j>0; j--) {
				max = Math.max(max, p[j]);
				min = Math.min(min, p[j]);
				
				d[i] = Math.max(d[i], max - min + d[j-1]);
			}
		}
		bw.write(d[N]+"");
		bw.flush();
		bw.close();
	}
}
