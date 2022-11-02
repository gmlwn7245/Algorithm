package Programmers_K;

import java.util.*;

import Programmers_K.KaKao2020_01.Solution;

public class KaKao2022_03 {
	public static void main(String[] args) {
		int alp = 0;
		int cop = 0;
		int[][] problems = {{0,0,2,1,2},{4,5,3,1,2},{4,11,4,0,2},{10,4,0,4,2}};
		System.out.println(Solution.solution(alp, cop, problems));
	}
	
	static class Solution {
		public static int[][] dp;
	    public static int[][] problem;
	    public static int alpMax, copMax;
	    
	    public static int solution(int alp, int cop, int[][] problems) {
	        alpMax = Integer.MIN_VALUE;
	        copMax = Integer.MIN_VALUE;
	        
	        
	        for(int[] p : problems){
	            alpMax = Math.max(alpMax, p[0]);
	            copMax = Math.max(copMax, p[1]);
	        }
	        
	        // 이미 최대 능력을 가졌을 경우
	        if(alpMax <= alp && copMax <= cop)
	        	return 0;	        
	        	        
	        problem = problems;
	        dp = new int[alpMax+2][copMax+2];
	        
	        // dp 초기화
	        for(int[] d : dp){
	            Arrays.fill(d, Integer.MAX_VALUE);
	        }
	        dp[alp][cop]=0;
	        
	        for(int i=alp; i<=alpMax; i++){
	            for(int j=cop; j<=copMax; j++){
	            	if(i==alpMax && j==copMax)
	            		break;
	            	
	                dp[i+1][j] = Math.min(dp[i][j]+1, dp[i+1][j]);
	                dp[i][j+1] = Math.min(dp[i][j]+1, dp[i][j+1]);
	                
	                setDP(i,j);
	            }
	        }
	        
	        return dp[alpMax][copMax];
	    }
	    
	    public static void setDP(int i, int j){
	        for(int[] p : problem){	        	
	        	// 능력 밖일경우
	            if(p[0]>i || p[1]>j)
	                continue;
	            
	            int nx = i + p[2];
	            int ny = j + p[3];
	            
	            // 제일 중요!!
	            if(nx > alpMax)
                    nx = alpMax;
                if(ny > copMax)
                    ny = copMax;
	            
	            dp[nx][ny] = Math.min(dp[nx][ny], dp[i][j]+p[4]);
	        }
	    }
	}
}
