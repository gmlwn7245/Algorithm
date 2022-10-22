package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj16637 {
	static ArrayList<Integer> nums = new ArrayList<>();
	static ArrayList<Character> ops = new ArrayList<>();
	static int N;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		
		String exp = br.readLine();
		
		for(int i=0; i<N; i++) {
			char ch = exp.charAt(i);
			if(i%2==0) {
				nums.add(ch-'0');
			}else {
				ops.add(ch);
			}
		}
		dfs(0,nums.get(0));
		bw.write(String.valueOf(max)+"\n");
		bw.flush();
		bw.close();
	}
	
	
	public static void dfs(int idx, int sum) {
		if(idx >= ops.size()) {
			max = Math.max(sum, max);
			return ;
		}
		
		// °ýÈ£ X
		int sums = calc(sum, nums.get(idx+1), ops.get(idx));
		dfs(idx+1, sums);
		
		// °ýÈ£ O
		if(idx + 2 <= ops.size()){
			sums = calc(nums.get(idx+1), nums.get(idx+2), ops.get(idx+1));
			int res = calc(sum, sums, ops.get(idx));
			dfs(idx+2, res);
		}
	}
	
	
	public static int calc(int a, int b, char op) {
		switch(op) {
		case '+':
			return a+b;
		case '-' :
			return a-b;
		default:
			return a*b;
		}
	}
	
	
}
