package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj2602 {
	private static int len, ans=0;
	private static String[] fMap, lMap,str;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		str = br.readLine().split("");

		fMap = br.readLine().split("");
		lMap = br.readLine().split("");
		
		len = fMap.length;
		dfs(true, 0,0);
		dfs(false, 0,0);
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
	
	// Â¦¼ö
	public static void dfs(boolean isFirst, int idx, int arrIdx) {
		for(int i=idx; i<len; i++) {
			if(isFirst) {
				if(!fMap[i].equals(str[arrIdx]))
					continue;
				
				if(arrIdx==str.length-1)
					ans++;
				else
					dfs(false, i+1, arrIdx+1);
			}else {
				if(!lMap[i].equals(str[arrIdx]))
					continue;
				
				if(arrIdx==str.length-1)
					ans++;
				else
					dfs(true, i+1, arrIdx+1);
			}
		}
	}
	

}
