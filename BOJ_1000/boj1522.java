package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj1522 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split("");
		
		int aCnt = 0, strlen = str.length;
		for(String s : str) {
			if(s.equals("a"))
				aCnt++;
		}
		
		
		if(aCnt >= strlen-1) {
			bw.write("0");
			bw.flush();
			bw.close();
			return ;
		}
		
		int minCnt = Integer.MAX_VALUE;
		// 시작점
		for(int i=0; i<strlen; i++) {
			int bCnt = 0;
			// 범위
			for(int j=0; j<aCnt; j++) {
				int idx = (i+j)%strlen;
				if(str[idx].equals("b")) {
					bCnt++;
				}
			}
			
			minCnt = Math.min(minCnt, bCnt);
		}
		bw.write(minCnt+"");
		bw.flush();
		bw.close();
	}
}
