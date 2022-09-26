package BOJ_SS;

import java.util.*;

public class boj14888 {
	static int max = Integer.MIN_VALUE, min=Integer.MAX_VALUE, n;
	static int[] op = new int[4];
	static ArrayList<Integer> nums;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
	    n = sc.nextInt();
	    nums = new ArrayList<>();
	    
	    for(int i=0; i<n; i++)
	    	nums.add(sc.nextInt());
	    
	    for(int i=0; i<4; i++)
	    	op[i]=sc.nextInt();
	    
	    
	    dfs(1, nums.get(0));
	    System.out.println(max);
	    System.out.println(min);
	}
	
	public static void dfs(int cnt, int totNum) {
		if(cnt == n) {
			max = Math.max(max, totNum);
			min = Math.min(min, totNum);
			return ;
		}
		
		
		for(int i=0; i<4; i++) {
			if(op[i]>0) {
				op[i]--;
				
				switch(i) {
				case 0:
					dfs(cnt+1, totNum + nums.get(cnt));break;
				case 1:
					dfs(cnt+1, totNum - nums.get(cnt));break;
				case 2:
					dfs(cnt+1, totNum * nums.get(cnt));break;
				default:
					dfs(cnt+1, totNum / nums.get(cnt));
					
				}
				
				op[i]++;
			}
		}
		
	}
}
