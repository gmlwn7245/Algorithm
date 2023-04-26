package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj19583 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		String start = st.nextToken(), end = st.nextToken(), fin = st.nextToken();
		
		// 시작 전에 학회원 입장 확인
		HashSet<String> enter = new HashSet<>();
		HashSet<String> out = new HashSet<>();
		while(true) {
			String str = br.readLine();
			if(str==null)
				break;
			
			st= new StringTokenizer(str);
			String time = st.nextToken(), name = st.nextToken();
			
			if(start.compareTo(time) >= 0) {
				enter.add(name);
			}else if(end.compareTo(time) <= 0 && fin.compareTo(time) >= 0) {
				if(enter.contains(name))
					out.add(name);
			}
		}
		
		System.out.println(out.size());
	}
}
