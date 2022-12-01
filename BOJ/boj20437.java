package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class boj20437 {
	private static int minNum, maxNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			minNum=Integer.MAX_VALUE;
			maxNum=Integer.MIN_VALUE;
			String W = br.readLine();
			int K = Integer.parseInt(br.readLine());
			
			HashMap<Character, ArrayList<Integer>> hm = new HashMap<>();
			HashSet<Character> hs = new HashSet<>();
					
			for(int j=0; j<W.length(); j++) {
				char ch = W.charAt(j); 
				if(!hs.contains(ch)) {
					hs.add(ch);
					hm.put(ch, new ArrayList<>(Arrays.asList(j)));
					continue;
				}
				hm.get(ch).add(j);
			}
			
			boolean notFound = true;
			for(char ch : hs) {
				if(hm.get(ch).size() < K)
					continue;
				getNum(hm.get(ch), K);
				notFound = false;
			}
			
			
			if(notFound)
				bw.write("-1\n");
			else 
				bw.write(minNum + " "+maxNum+"\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	public static void getNum(ArrayList<Integer> arr, int k) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i+k<=arr.size(); i++) {
			min = Math.min(min, arr.get(i+k-1)-arr.get(i)+1);
			max = Math.max(max, arr.get(i+k-1)-arr.get(i)+1);
		}
		
		
		minNum = Math.min(minNum, min);
		maxNum = Math.max(maxNum, max);
	}
}
