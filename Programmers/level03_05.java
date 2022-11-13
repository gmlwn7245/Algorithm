package Programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class level03_05 {
	// º£½ºÆ®¾Ù¹ü
	static class Solution {
		static class Music implements Comparable<Music>{
			static String genre;
			static int index, play;
	        
	        public Music(String genre, int index, int play){
	            this.genre = genre;
	            this.index = index;
	            this.play = play;
	        }
	        
	        @Override
			public int compareTo(Music o) {
	            
				if(this.play == o.play){
	                return this.index - o.index;
	            }
	            
				return o.play - this.play;
			}
	    }
	    
		public static int[] solution(String[] genres, int[] plays) {
	        ArrayList<Integer> arr = new ArrayList<>();
	        HashMap<String, Integer> sum = new HashMap<>();
	        HashMap<String, PriorityQueue<Music>> list = new HashMap<>();
	        HashSet<String> genreList = new HashSet<>();
	        
	        for(int i=0; i<genres.length; i++){
	            String g = genres[i];
	            int p = plays[i];
	            
	            // ÃÑÇÕ ³Ö±â
	            sum.put(g, sum.getOrDefault(g,0)+p);
	            
	            // 
	            PriorityQueue<Music> pq = list.getOrDefault(g, new PriorityQueue<>());
	            pq.add(new Music(g, i, p));
	            list.put(g, pq);
	            
	            genreList.add(g);
	        }
	        
	        
	        PriorityQueue<String> pq = new PriorityQueue<>((String s1, String s2)->(sum.get(s2)-sum.get(s1)));
	        for(String g : genreList){
	            pq.add(g);
	        }
	        
	        while(!pq.isEmpty()){
	            String g = pq.poll();
	            PriorityQueue<Music> m = list.get(g);
	            
	            if(m.size()==1){
	                arr.add(m.poll().index);
	                continue;
	            }
	            
	            arr.add(m.poll().index);
	            arr.add(m.poll().index);
	        }
	        
	        int[] ans = new int[arr.size()];
	        for(int i=0; i<arr.size(); i++){
	            ans[i] = arr.get(i);
	        }
	               
	        return ans;
	    }
	}
	
	public static void main(String[] args) {
		
		//Solution.solution(routes);
	}
}
