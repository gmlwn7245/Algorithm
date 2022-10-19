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

public class boj20920 {
	private static HashMap<String, Integer> hm = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st =new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		
		
		// �ܾ� �Է� ����
		for(int i=0; i<n; i++) {
			String str = bf.readLine();
			if(str.length() < m)
				continue;
			if(hm.containsKey(str))
				hm.put(str, hm.get(str)+1);
			else
				hm.put(str, 1);
		}
		
		// �ܾ� ����
		ArrayList<String> words = new ArrayList<>(hm.keySet());
		
		
		// ���ĺ� ������ ����
		Collections.sort(words);
		// ���̰� �� �����������
		Collections.sort(words, (o1,o2)-> o2.length() - o1.length());

		// ���� ������ ������� ����
		Collections.sort(words, (v1,v2) -> (hm.get(v2).compareTo(hm.get(v1))));
		
		for(String str : words) {
			bw.write(str+"\n");
		}
		
		bw.flush();
		bw.close();
		
	}
}
