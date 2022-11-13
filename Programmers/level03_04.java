package Programmers;

import java.util.HashMap;
import java.util.PriorityQueue;

public class level03_04 {
	static class Solution {
	    public static int solution(int[][] routes) {
	        int answer = 0;
	        // 시작이 작은 순서대로 정렬
	        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b)->a[0]-b[0]);
	        
	        // 끝이 작은 순서대로 정렬
	        
	        int cnt = 0;
	        int end = Integer.MAX_VALUE;
	        for(int[] r : routes){
	            pq.add(r);
	        }
	        HashMap<String, Integer> hm = new HashMap<>();
	        while(!pq.isEmpty()){
	            int[] r = pq.poll();
	            // 더 빨리 끝나는 지점 찾기
	            end = Math.min(end, r[1]);
	            
	            if(r[0] > end){
	                cnt++;
	                end = r[1];
	            }
	        }
	        
	        return cnt+1;
	    }
	}
	
	public static void main(String[] args) {
		int[][] routes = {{-20,-15},{-14,-5},{-18,-13},{-5,-3}};
		Solution.solution(routes);
	}
}
