package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj1759 {
	private static int L,C;
	private static HashSet<Character> vow = new HashSet<>();
	private static ArrayList<Character> arr = new ArrayList<>();
	private static PriorityQueue<String> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		
		vow.add('a');
		vow.add('e');
		vow.add('i');
		vow.add('o');
		vow.add('u');
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			char ch = st.nextToken().charAt(0);
			arr.add(ch);
		}
		
		Collections.sort(arr);
		
		for(int i=0; i<C-L; i++) {
			System.out.println(i);
			getString(i, 0, new ArrayList<>());
		}
		
		while(!pq.isEmpty()) {
			bw.write(pq.poll()+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static void getString(int idx, int vCnt, ArrayList<Character> ans) {
		if(ans.size() == L) {
			StringBuilder sb  = new StringBuilder();
			for(char ch : ans) {
				sb.append(ch);
			}
			pq.add(sb.toString());
			return ;
		}
		
		for(int i = idx; i<arr.size(); i++) {
			char ch = arr.get(i);
			
			if(ans.size()==L-1 && vCnt == 0 && !vow.contains(ch))
				continue;
			
			if(vCnt == L-2 && vow.contains(ch))
				continue;
			
			ans.add(ch);
						
			if(vow.contains(ch))
				getString(i+1, vCnt+1, ans);
			else
				getString(i+1, vCnt, ans);
			
			ans.remove(ch);
		}
	}
}
