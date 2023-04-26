package BOJ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class boj16639 {
	static int N, max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		String str = sc.next();
		
		ArrayList<Integer> num = new ArrayList<>();
		ArrayList<Character> op = new ArrayList<>();
		
		int start = 0;
		for(int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			
			if(ch == '+' || ch == '-' || ch == '*') {
				num.add(Integer.parseInt(str.substring(start, i)));
				op.add(ch);
				start = i+1;
			}
		}
		num.add(Integer.parseInt(str.substring(start)));
		
		dfs(num, op);
		System.out.println(max);
	}
	
	public static void dfs(ArrayList<Integer> num, ArrayList<Character> op) {
		if(num.size() == 1) {
			max = Math.max(max, num.get(0));
			return ;
		}
		
		int len = op.size();
		for(int i=0; i<len; i++) {
			int a = num.get(i), b = num.get(i+1);
			char ops = op.get(i);
			
			num.remove(i); num.remove(i);
			op.remove(i);
			
			switch(ops) {
			case '+' : num.add(i,a+b);break;
			case '-' : num.add(i,a-b);break;
			case '*' : num.add(i,a*b);break;
			}
			
			dfs(num, op);
			
			num.remove(i);
			num.add(i,b);num.add(i,a);
			op.add(i,ops);
		}
	}
	
	
}
