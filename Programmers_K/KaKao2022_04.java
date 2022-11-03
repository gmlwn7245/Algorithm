package Programmers_K;

import java.util.*;

public class KaKao2022_04 {
	public static void main(String[] args) {
		int n = 6;
		int[][] paths = {{1,2,3},{2,3,5},{2,4,2},{2,5,4},{3,4,4},{4,5,3},{4,6,1},{5,6,1}};
		int[] gates = {1,3};
		int[] summits = {5};
		
		int[] ans = Solution.solution(n,paths, gates, summits);
		
		System.out.println(ans[0]+" : "+ ans[1]);
	
	}

	static class Solution {
	    // dp [���츮 ��ȣ][�ٸ� ���� ��ȣ] �ּҰ�
	    public static int[][] map, dp, intensity;
	    public static int num;
	    public static HashSet<Integer> top = new HashSet<>();
	    public static HashSet<Integer> goal = new HashSet<>();
	    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
	        
	        num = n;
	        map = new int[n+1][n+1];
	        dp = new int[n+1][n+1];
	        intensity = new int[n+1][n+1];
	        
	        for(int[] d : dp){
	            Arrays.fill(d, Integer.MAX_VALUE);
	        }
	        
	        for(int g : gates){
	            goal.add(g);
	        }
	        
	        for(int s : summits){
	            top.add(s);
	        }
	        
	        for(int[] p : paths){
	            int x = p[0];
	            int y = p[1];
	            map[x][y]=p[2];
	            map[y][x]=p[2];
	        }
	        
	        // paths : [0]�� [1]������ �Ÿ� [2]
	        // gates : ���Ա� 
	        // summits : ����츮
	        
	        // �� ����츮������ �Ÿ� Ȯ��
	        for(int s : summits){
	            getDist(s);
	        }
	        
	        int min = Integer.MAX_VALUE;
	        int topNum = 0;
	        
	        for(int s : summits){
	        	
	            PriorityQueue<Integer> pq = new PriorityQueue<>();
	            for(int g : gates){
	                pq.add(intensity[s][g]);
	            }
	            
	            int first = pq.poll();
	            int second = pq.poll();
	            
	            System.out.println(s+"<==//"+first+":"+second);
	            
	            int inten = Math.max(first, second);
	            if(inten < min || (inten==min&&topNum>s)){
	                min = inten;
	                topNum = s;
	            }
	        }
	        
	        for(int i=1; i<=n; i++) {
	        	System.out.print(intensity[5][i]+" ");
	     
	        }
	        System.out.println();
	        
	        return new int[] {topNum, min};
	    }
	    
	    
	    // �� �������� �ٸ� ���츮���� �Ÿ�
	    public static void getDist(int s){        
	        Queue<Integer> q = new LinkedList<>();
	        
	        for(int i=1; i<=num; i++){
	            if(map[s][i]==0 || top.contains(i))
	                continue;
	            
	            q.add(i);
	            intensity[s][i]=map[s][i];
	        }
	        
	        while(!q.isEmpty()){
	            int now = q.poll();
	            for(int i=1; i<=num; i++){
	                // ����츮�� ���
	                if(top.contains(i)){
	                    continue;
	                }
	                
	                // ������ �ȵǾ��ִ� ���
	                if(map[now][i]==0){
	                    continue;
	                }
	                
	                // ���� intensity�� �� ���� ���
	                int inten = Math.max(intensity[s][now], map[now][i]);
	                if(intensity[s][i]!=0 && intensity[s][i] <= inten){
	                    continue;
	                }
	        
	                intensity[s][i] = inten;
	                
	                // ���������� ���
	                if(goal.contains(i))
	                	continue;
	                
	                q.add(i);
	            }
	        }
	            
	    }     
	    
	}
}
