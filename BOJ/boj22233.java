package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj22233 {
	private static HashSet<String> keyword = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st =new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 메모장에 적은 키워드 N개
		for(int i=0; i<n; i++) {
			keyword.add(bf.readLine());
		}
		
		// 키워드 m개
		for(int i=0; i<m; i++) {
			String[] kw = bf.readLine().split(",");
			
			for(String s : kw) {
				keyword.remove(s);
			}
			
			bw.write(keyword.size()+"\n");
		}
		
		bw.flush();
		bw.close();
	}
}
