package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj2529 {
	public static char[] op;
	public static String max = "0", min = Long.MAX_VALUE+"";
	public static int K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		op = new char[K];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<K; i++) {
			op[i]=st.nextToken().charAt(0);
		}
		
		for(int i=0; i<=9; i++) {
			HashSet<Integer> hs = new HashSet<>();
			hs.add(i);
			
			int[] nums = new int[K+1];
			nums[0]=i;
			dfs(1, nums, hs);
		}
		
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void dfs(int dep, int[] arr, HashSet<Integer> hs) {
		if(dep > K) {
			StringBuilder sb = new StringBuilder();
			for(int i : arr)
				sb.append(i+"");
			
			//System.out.println(sb.toString());
			long num = Long.parseLong(sb.toString());
			if(num > Long.parseLong(max))
				max = sb.toString();
			if(num < Long.parseLong(min))
				min = sb.toString();
			return ;
		}
		
		char ch = op[dep-1];
		if(ch == '>') {
			for(int i=0; i<arr[dep-1]; i++) {
				if(hs.contains(i))
					continue;
				
				arr[dep]=i;
				hs.add(i);
				dfs(dep+1, arr, hs);
				hs.remove(i);
			}
		}else {
			for(int i=arr[dep-1]+1; i<=9; i++) {
				if(hs.contains(i))
					continue;
				
				arr[dep]=i;
				hs.add(i);
				dfs(dep+1, arr, hs);
				hs.remove(i);
			}
		}
		
	}
}
