package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj19637 {
	private static int N, M;
	private static ArrayList<Integer> stacks = new ArrayList<>();
	private static HashMap<Integer, String> hm = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			int lev = Integer.parseInt(st.nextToken());
			
			if(!hm.containsKey(lev)) {
				stacks.add(lev);
				hm.put(lev, str);
			}
		}
		
		for(int i=0; i<M; i++) {
			String str = getAns(Integer.parseInt(br.readLine()));
			bw.write(str+"\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	public static String getAns(int num) {
		int start = 0, end = stacks.size()-1, mid;

		while(start <= end) {
			mid = (start+end)/2;
			int stack = stacks.get(mid);
			if(num == stack) {
				return hm.get(stack);
			}
			
			if(num > stack)
				start = mid+1;
			else
				end = mid-1;
		}
				
		return hm.get(stacks.get(start));
	}
}
