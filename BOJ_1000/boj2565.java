package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj2565 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		
		ArrayList<Integer> arr = new ArrayList<>();
		HashMap<Integer, Integer> hm = new HashMap<>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			arr.add(a); hm.put(a, b);
		}
		
		Collections.sort(arr);
		
		HashMap<Integer, Integer> dp = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			int now = arr.get(i);
			int prevN = 0;
			for(int j=i-1; j>=0; j--) {
				int prev = arr.get(j);
				if(hm.get(prev) < hm.get(now))
					prevN = Math.max(dp.get(prev), prevN);
			}
			dp.put(now, prevN+1);
			ans = Math.max(ans, prevN+1);
		}
		
		System.out.println(N-ans);
	}
}
