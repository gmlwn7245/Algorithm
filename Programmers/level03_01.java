package Programmers;
import java.util.*;

class level03_01 {    
    public int solution(int[][] triangle) {        
        int len = triangle.length;
        int[][] dp = new int[len][len];
        
        dp[0][0]= triangle[0][0];
        for(int i=0; i<len-1; i++){
            for(int j=0; j<=i; j++){
                int now = dp[i][j];
                dp[i+1][j] = Math.max(dp[i+1][j], now + triangle[i+1][j]);
                dp[i+1][j+1] = Math.max(dp[i+1][j+1], now + triangle[i+1][j+1]);
            }
        }
        
        int max = 0;
        for(int i=0; i<len-1; i++){
            max = Math.max(dp[len-1][i], max);
        }
        return max;
    }
    
   
}