package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj11054 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] nums = new int[N];
		int[] uDP = new int[N];
		int[] dDP = new int[N];
				
		for(int i=0; i<N; i++) {
			int now = Integer.parseInt(st.nextToken());
			nums[i] = now;
			
			int maxVal = Integer.MIN_VALUE;
			// up 확인
			for(int j=i-1; j>=0; j--) {
				if(nums[j] < now) {
					maxVal = Math.max(maxVal, uDP[j]+1);
				}
			}
			if(maxVal != Integer.MIN_VALUE)
				uDP[i] = maxVal;
		}
		
		for(int i=N-1; i>=0; i--) {
			int now = nums[i];
			
			int maxVal = Integer.MIN_VALUE;
			// down 확인
			for(int j=i+1; j<N; j++) {
				if(nums[j] < now) {
					maxVal = Math.max(maxVal, dDP[j]+1);
				}
			}
			
			if(maxVal != Integer.MIN_VALUE)
				dDP[i] = maxVal;
		}
		
		int maxNum = 0;
		for(int i=0; i<N; i++) {
			int sum = uDP[i] + dDP[i];
			
			maxNum = Math.max(maxNum, sum);
			//System.out.println(nums[i]+"==>" + uDP[i]+" "+dDP[i]);
		}
		
		maxNum++;
		
		bw.write(maxNum+"");
		bw.flush();
		bw.close();
	}
}
