package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1476 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		int aE = Integer.parseInt(str[0]), aS = Integer.parseInt(str[1]), aM = Integer.parseInt(str[2]);
		
		int year = 0;
		int e = 1, s = 1, m = 1;
		
		while(year++ < Integer.MAX_VALUE) {
			if(e == aE && s == aS && m == aM) {
				System.out.println(year);
				return ;
			}
			
			e++;s++;m++;
			
			if(e==16) e = 1;
			if(s == 29) s = 1;
			if(m == 20) m = 1;
		}
		
	}
}
