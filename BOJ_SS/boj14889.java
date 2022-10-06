package BOJ_SS;

import java.util.*;

public class boj14889 {
	public static int[][] con;
	public static int[] num;
	public static int min = Integer.MAX_VALUE, n , sums;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
	    n = sc.nextInt();
	    num = new int[n/2];
	    con = new int[n][n];
	    
	    for(int i=0; i<n; i++) {
	    	for(int j=0; j<n; j++){
	    		int k = sc.nextInt();
	    		con[i][j] = k;
	    		sums += k;
	    	}
	    }
	    
	    dfs(0, num, 0);
	    
	    System.out.println(min);
	}
	
	public static void dfs(int cnt, int[] nums, int idx) {
		if(cnt == n/2) {
			int res = subs();
			if(Math.abs(res) < min)
				min = res;
			return ;
		}
		
		for(int i=idx; i<n; i++) {
			nums[cnt]=i;
			dfs(cnt+1, nums, i+1);
		}
	}
	
	public static int subs() {
		int sum1=0, sum2=0;
		ArrayList<Integer> concludes = new ArrayList<>();
		
		for(int i=0; i<n/2; i++) {
			for(int j=i+1; j<n/2; j++) {
				int n1 = num[i];
				int n2 = num[j];
				
				sum1 += con[n1][n2];
				sum1 += con[n2][n1];
				if(!concludes.contains(n1))
					concludes.add(n1);
				if(!concludes.contains(n2))
					concludes.add(n2);
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i==j)
					continue;
				if(concludes.contains(i) || concludes.contains(j))
					continue;
				
				sum2 += con[i][j];
				sum2 += con[j][i];
			}
		}
		sum2/=2;
		
		
		return Math.abs(sum2-sum1);
	}
}
