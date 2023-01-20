package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj6322 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int cnt = 1;
		while(true) {
			String[] str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			int c = Integer.parseInt(str[2]);
			
			if(a==0 && b==0 && c==0)
				break;
			
			bw.write("Triangle #"+cnt+"\n");
			cnt++;
			
			String s = "";
			if(a==-1) {
				if(b >= c) {
					bw.write("Impossible.\n\n");
					continue;
				}
				
				double ans = Math.sqrt(c * c - b * b);
				ans = Math.round(ans * 1000)/1000.0;
				bw.write("a = "+String.format("%.3f", ans)+"\n\n");
			}else if(b==-1) {
				if(a >= c) {
				bw.write("Impossible.\n\n");
				continue;
				}
				
				double ans = Math.sqrt(c * c - a * a);
				ans = Math.round(ans * 1000)/1000.0;
				bw.write("b = "+String.format("%.3f", ans)+"\n\n");
			}else {
				double ans = Math.sqrt(a * a + b * b);
				ans = Math.round(ans * 1000)/1000.0;
				bw.write("c = "+String.format("%.3f", ans)+"\n\n");
				
			}
			
		}
		bw.flush();
		bw.close();
	}
}
