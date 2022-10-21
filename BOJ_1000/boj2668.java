package BOJ_1000;

import java.io.IOException;
import java.util.*;

public class boj2668 {
	static int[] nums;
	static int n, max = 0;
	static int start = 0;
	static boolean[] check;
	
	static ArrayList<Integer> res = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		nums = new int[n+1];
		check = new boolean[n+1];
		
		for(int i=1; i<n+1; i++) 
			nums[i]=sc.nextInt();
		
		for(int i=1; i<n+1; i++) {
			check[i] = true;
			start = i;
			dfs(i);
			check[i] = false;
		}
		
		Collections.sort(res);
		
		System.out.println(res.size());
		for(int i : res)
			System.out.println(i);
	}
	
	public static void dfs(int idx) {
		if(nums[idx] == start)
			res.add(nums[idx]);
		
		int nextNum = nums[idx];
		
		if(!check[nextNum]) {
			check[nextNum] = true;
			dfs(nextNum);
			check[nextNum] = false;
		}
		
	}
	
	
}
