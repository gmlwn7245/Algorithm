package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj2493 {
	static class Tower {
		int idx;
		int height;
		
		public Tower(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Stack<Tower> stack = new Stack<>();
		for(int i=1; i<=N; i++) {
			int height = Integer.parseInt(st.nextToken());
			
			if(stack.isEmpty()) {
				bw.write("0 ");
				stack.push(new Tower(i, height));
				continue;
			}
			
			while(!stack.isEmpty()) {
				Tower top = stack.peek();
				
				if(top.height > height) {
					bw.write(top.idx + " ");
					stack.push(new Tower(i, height));
					break;
				}else {
					stack.pop();
				}
			}
			
			if(stack.isEmpty()) {
				bw.write("0 ");
				stack.push(new Tower(i,height));
			}
				
		}
		
		bw.flush();
		bw.close();
	}
}
