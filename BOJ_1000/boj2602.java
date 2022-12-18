package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class boj2602 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] con = br.readLine().split("");
		String[] fMap = br.readLine().split("");
		String[] lMap = br.readLine().split("");
		
		HashMap<String, Integer> hm = new HashMap<>();
		for(int i=0; i<con.length; i++) {
			// 해당 String의 Con에서의 idx
			hm.put(con[i], i);
		}
		
		int ans = 0;
		
		int[] dp1 = new int[fMap.length+1];
		int[] dp2 = new int[lMap.length+1];
		for(int i=0; i<fMap.length; i++) {
			System.out.println(fMap[i]);
			
			if(hm.getOrDefault(fMap[i],0)==0)
				dp1[i]=1;
			else {
				int idx = hm.get(fMap[i])-1;
				int sum = 0;
				for(int j=i-1; j>=0; j--) {
					if(lMap[j].equals(con[idx])) {
						sum += dp2[j];
					}
				}
				dp1[i]=sum;
			}
			
			if(hm.getOrDefault(lMap[i],0)==0)
				dp2[i]=1;
			else {
				int idx = hm.get(lMap[i])-1;
				int sum = 0;
				for(int j=i-1; j>=0; j--) {
					if(fMap[j].equals(con[idx])) {
						sum += dp1[j];
					}
				}
				dp2[i]=sum;
			}
			ans = Math.max(ans, Math.max(dp1[i], dp2[i]));
			
		}
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
}
