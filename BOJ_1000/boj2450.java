package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2450 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		int[][] orders = new int[][] {
			{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2},{3,2,1}
		};
		
		int[] shCnt = new int[4];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			shCnt[arr[i]]++;
		}
		
		int ans = N;
		for(int i=0; i<6; i++) {
			int[] order = orders[i];
			int idx = 0;
			
			int[][] cnt = new int[4][4];
			
			for(int j=0; j<3; j++) {
				// 현재 모양
				int sh = order[j];
				
				for(int k=idx; k<idx+shCnt[sh];k++)
					if(arr[k]!=sh)
						cnt[sh][arr[k]]++;
				
				idx += shCnt[sh];
			}
			
			
			ans = Math.min(ans, cnt[1][2]+cnt[1][3]+Math.max(cnt[3][2], cnt[2][3]));
		}
		System.out.println(ans);
	}
}
