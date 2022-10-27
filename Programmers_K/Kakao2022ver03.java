package Programmers_K;

import java.util.*;

public class Kakao2022ver03 {
	static int[] lion;
	static int[] res = {-1};
	static int max = 0;
	public static void main(String[] args) {
		int n = 5;
		int[] info = {2,1,1,1,0,0,0,0,0,0,0};
		lion = new int[11];
		
		dfs(info, n, 1);
		
		for(int i : res) {
			System.out.println(i);
		}
	}
	
	public static void dfs(int[] info, int n, int cnt) {
		// 5¹ø ³¡
		if(cnt == n+1) {
			int aScore = 0, lScore = 0;
			
			for(int i=0; i<=10; i++) {
				if(info[i]==0 && lion[i]==0)
					continue;
				if(info[i]>=lion[i])
					aScore += 10-i;
				else
					lScore += 10-i;
			}
			
			if(lScore > aScore) {
				int d = lScore - aScore;
				if(d >= max) {
					res = lion.clone();
					max = d; 
				}
				
			}
			return ;	
		}
		
		for(int i=0; i<=10 && lion[i]<=info[i]; i++) {
			lion[i]++;
			dfs(info, n, cnt+1);
			lion[i]--;
		}
			
	}
}
