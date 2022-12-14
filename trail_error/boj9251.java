package trail_error;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

//시간초과
public class boj9251 {
	private static int limit = 0;
	private static String str1,str2;
	private static HashSet<Character> hs = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		str1 = br.readLine();
		str2 = br.readLine();
		
		for(int i=0; i<str2.length(); i++) {
			hs.add(str2.charAt(i));
		}
		
		getString(0, new StringBuilder());
		
		
		bw.write(limit+"");
		bw.flush();
		bw.close();
	}
	
	public static void getString(int idx, StringBuilder sb) {
		if(sb.length()>limit) {
			if(isContain(sb.toString(), str2)) {
				limit = sb.toString().length();
				//System.out.println(sb.toString());
			}
			
			if(idx == str1.length())
				return;
		}
		
		for(int i=idx; i<str1.length(); i++) {
			char ch = str1.charAt(i);
			
			//str2에 없는 문자
			if(!hs.contains(ch))
				continue;
			
			sb.append(ch+"");
			
			getString(i+1, sb);
			
			sb.deleteCharAt(sb.length()-1);
		}
		
	}
	
	public static boolean isContain(String s1, String s2) {
		// s1이 dfs로 만든 string
		// s2가 찾을 대상
		
		int i=0;
		for(int j=0; j<s2.length(); j++) {
			char ch = s1.charAt(i);
			if(s2.charAt(j)==ch) {
				i++;
				if(i == s1.length())
					return true;
			}
		}
		
		return false;
	}
}
