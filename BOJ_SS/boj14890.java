package BOJ_SS;

import java.util.*;

public class boj14890 {
	static int n, l, cnt=0;
	static int[][] map;
	static boolean[][] hasSlope;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
	    n = sc.nextInt();
	    l = sc.nextInt();
	    map = new int[n][n];
	    
	    
	    for(int i=0; i<n; i++)
	    	for(int j=0; j<n; j++)
	    		map[i][j] = sc.nextInt();
	    
	    for(int i=0; i<n; i++) {
	    	hasSlope = new boolean[n][n];
	    	if(checkPath(0,i)) 
	    		cnt++;
	    
	    	hasSlope = new boolean[n][n];
	    	if(checkPath(1,i)) 
	    		cnt++;
	    }
	    
	    System.out.println(cnt);
	}
	
	
	public static boolean checkPath(int dir, int i) {
		switch(dir) {
		//좌->우
		case 0:{
			int num = map[i][0];
			for(int j=1; j<n; j++) {
	    		if(map[i][j]==num)
	    			continue;
	    		//오르막
	    		if(map[i][j]==num+1 && j>=l) {
	    			num = map[i][j];
	    			if(checkUpSlope(dir, num-1, i, j-1))
	    				continue;
	    			return false;
	    		}
	    		//내리막
	    		if(map[i][j]==num-1 && j+l<=n) {
	    			num = map[i][j];
	    			if(checkDownSlope(dir, num, i, j))
	    				continue;
	    			return false;
	    		}
	    		return false;
	    	}
			return true;
		}
		//상->하
		case 1:{
			int num = map[0][i];
			for(int j=1; j<n; j++) {
				if(map[j][i]==num)
	    			continue;
	    		//오르막
	    		if(map[j][i]==num+1 && j>=l) {
	    			num = map[j][i];
	    			if(checkUpSlope(dir, num-1, i, j-1))
	    				continue;
	    			return false;
	    		}
	    		//내리막
	    		if(map[j][i]==num-1 && j+l<=n) {
	    			num = map[j][i];
	    			if(checkDownSlope(dir, num, i, j))
	    				continue;
	    			return false;
	    		}
	    		return false;
			}
			return true;
		}
		}
		return false;
	}
	
	public static boolean checkUpSlope(int dir, int num, int i, int idx) {
		switch(dir) {
		case 0:{
			for(int j=idx; j>idx-l; j--) {
				if(hasSlope[i][j])
					return false;
				if(map[i][j]!=num)
					return false;
			}
			
			for(int j=idx; j>idx-l; j--) {
				hasSlope[i][j]=true;
			}
			
			return true;
		}
		case 1:{
			for(int j=idx; j>idx-l; j--) {
				if(hasSlope[j][i])
					return false;
				if(map[j][i]!=num)
					return false;
			}
			
			for(int j=idx; j>idx-l; j--) {
				hasSlope[j][i]=true;
			}
			
			return true;
		}
		}
		
		return false;
	}
	
	public static boolean checkDownSlope(int dir, int num, int i, int idx) {
		switch(dir) {
		case 0:{
			for(int j=idx; j<idx+l; j++) {
				if(hasSlope[i][j])
					return false;
				if(map[i][j]!=num)
					return false;
			}
			for(int j=idx; j<idx+l; j++) {
				hasSlope[i][j]=true;
			}
			return true;
		}
		case 1:{
			for(int j=idx; j<idx+l; j++) {
				if(hasSlope[j][i])
					return false;
				if(map[j][i]!=num)
					return false;
			}
			for(int j=idx; j<idx+l; j++) {
				hasSlope[j][i]=true;
			}
			return true;
		}
		}
		
		return false;
	}
}
