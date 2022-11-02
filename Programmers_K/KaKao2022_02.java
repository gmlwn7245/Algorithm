package Programmers_K;

import java.util.*;

import Programmers_K.KaKao2020_01.Solution;

public class KaKao2022_02 {
	public static void main(String[] args) {		
		String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
		int[] choices = {5, 3, 2, 7, 5};
		
		
		System.out.println(Solution.solution(survey, choices));
	}
	
	static class Solution {
	    
	    public static String solution(String[] survey, int[] choices) {
	        StringBuilder sb  = new StringBuilder();
	        HashMap<Integer, Integer> hm = new HashMap<>();
	        String[] type = {"RT","CF","JM","AN"};
	        
	        for(int i=0; i<survey.length; i++){
	            String str = survey[i];
	            int num = choices[i];
	            
	            if(num<4){  // 비동의
	                int t = str.charAt(0);
	                hm.put(t, hm.getOrDefault(t, 0)+4-num);
	            }else if(num>4){    // 동의
	                int t = str.charAt(1);
	                hm.put(t, hm.getOrDefault(t, 0)+num-4);
	            }
	        }
	        
	        for(int i=0; i<4; i++){
	            int t1 = type[i].charAt(0);
	            int t2 = type[i].charAt(1);
	            
	            if(hm.getOrDefault(t2,0)>hm.getOrDefault(t1,0)){
	                sb.append((char)t2);
	            }else {
	                sb.append((char)t1);
	            }
	        }
	        
	        return sb.toString();
	    }
	}
}
