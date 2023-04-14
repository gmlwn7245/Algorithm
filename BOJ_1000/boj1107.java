package BOJ_1000;

import java.io.*;
import java.util.*;

public class boj1107 {
    public static int ans=Integer.MAX_VALUE, N, min = Integer.MAX_VALUE;
    public static boolean[] notAct = new boolean[10];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        if(M != 0) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            while(M-- > 0){
            	notAct[Integer.parseInt(st.nextToken())]=true;
            }
        }
        
        
        if(N==100) {
        	System.out.println(0);
        	return ;
        }else if(canReach(N)) {
        	if(min < Math.abs(N-100)) {
        		System.out.println(min);
        	}else {
        		System.out.println(Math.abs(N-100));
        	}
        	return ;
        }
        
        int cnt = 1;
        int bN = (N-1)<0?0:N-1, aN = N+1;
        while(true){
        	if(bN == 100 || aN == 100) {
        		ans = Math.min(cnt, ans);
        		break;
        	}
        	
        	boolean aRes = canReach(aN), bRes = canReach(bN);
            if(aRes || bRes){
            	ans = Math.min(ans, min+cnt);
            }
            
            bN--; aN++;
            
            if(bN < 0)
            	bN = 0;
            cnt++;
        }
        System.out.println(ans);
	}
	
    public static boolean canReach(int n){
        String str = n+"";
        
        for(int i=0; i<str.length(); i++){
            int num = str.charAt(i)-'0';
            
            if(notAct[num])
                return false;
        }
        min = Math.min(str.length(), min);
        return true;
    }
}
