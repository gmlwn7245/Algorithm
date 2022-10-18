package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class boj12919 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		
		String S = br.readLine();
		String T =  br.readLine();
		boolean res = bfs(S,T);
		
		if(res)
			bw.write("1");
		else
			bw.write("0");
		
		bw.flush();
		bw.close();
		
	}
	
	public static boolean bfs(String S, String T) {
		int limit = S.length();
		
		Queue<StringBuilder> q = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		sb.append(T);
		q.add(sb);
		
		while(!q.isEmpty()) {
			sb = q.poll();
			
			if(sb.length() <= limit) {
				if(S.equals(sb.toString()))
					return true;
				else
					continue;
			}
			
			// 맨 뒤 A 지우기
			if(sb.charAt(sb.length()-1)=='A') {
				sb.setLength(sb.length()-1);
				q.add(new StringBuilder(sb.toString()));
				sb.append("A");
			}
			
			// 맨 앞 B 지우기
			if(sb.charAt(0)=='B') {
				sb.delete(0, 1);
				q.add(new StringBuilder(sb.reverse().toString()));
			}
		}
		
		return false;
	}
}
