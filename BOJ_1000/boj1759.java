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
		
		getString(0, 0, new ArrayList<>());
		
		while(!pq.isEmpty()) {
			String str = pq.poll();
			bw.write(str+"\n");
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
			//System.out.println(sb.toString());
			pq.add(sb.toString());
			return ;
		}
		
		for(int i = idx; i<C; i++) {
			char ch = arr.get(i);
			
			// 모음 총 0개 , 1자리 남음, 현재 모음이 아닌 경우
			if(ans.size()==L-1 && vCnt == 0 && !vow.contains(ch))
				continue;
			
			// 2개 빼고 전부 모음, 현재 모음인 경우
			if(vCnt == L-2 && vow.contains(ch))
				continue;
			
			ans.add(ch);
						
			if(vow.contains(ch))
				getString(i+1, vCnt+1, ans);
			else
				getString(i+1, vCnt, ans);
		
			ans.remove(ans.size()-1);
		}
	}
}
