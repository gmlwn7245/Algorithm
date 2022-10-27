package Softeer;

import java.util.*;
import java.io.*;


public class stepBridge
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] bridge = new int[N];
        int[] dp = new int[N];

        if(N==1){
            bw.write("1");
            bw.flush();
            bw.close();
            return ;
        }

        for(int i=0; i<N; i++){
            bridge[i] = Integer.parseInt(st.nextToken());
            dp[i]=1;
        }
        
        
        for(int i=0; i<N-1; i++){
        	for(int j=i+1; j<N; j++) {
        		if(bridge[i]<bridge[j]) {
        			dp[j] = Math.max(dp[j], dp[i]+1);
        		}
        	}
        }
        
        int max = 0;
        for(int i=0; i<N; i++){
        	max = Math.max(max, dp[i]);
        }
        
        bw.write(""+max);
        bw.flush();
        bw.close();
    }
}