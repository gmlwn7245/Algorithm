package BOJ_1000;

import java.io.IOException;
import java.util.Scanner;

public class boj1371 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int[] alpha = new int[26];
		
		while(sc.hasNext()) {
			String s = sc.nextLine();
			s = s.replace(" ", "");
			
			for(int j=0; j<s.length(); j++) {
				int idx = s.charAt(j)-'a';
				alpha[idx] += 1;
			}
		}
		
		
		int max = -1;
		for(int i=0; i<26; i++) {
			if(max < alpha[i]) {
				max = alpha[i];
			}
		}
		
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<26; i++) {
			if(max == alpha[i]) {
				sb.append((char)(i+'a'));
			}
		}
		System.out.println(sb.toString());
	}
}
