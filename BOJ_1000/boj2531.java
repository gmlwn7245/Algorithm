package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj2531 {
	private static int n,d,k,c,max;
	private static int[] sushiNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 접시 수 N 초밥 가짓수 d 연속 접시 수 k 쿠폰 번호 c
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		sushiNum = new int[n];
		
		for(int i=0; i<n; i++)
			sushiNum[i]=Integer.parseInt(br.readLine());
		
		getMaxCnt();
		System.out.println(max);
	}
	
	public static void getMaxCnt() {
		for(int i=0; i<n; i++) {
			HashSet<Integer> hs = new HashSet<>();
			for(int j=0; j<k;j++) {
				int idx = (i+j)%n;
				hs.add(sushiNum[idx]);
			}
			hs.add(c);
			
			max = Math.max(max, hs.size());
		}
	}
}
