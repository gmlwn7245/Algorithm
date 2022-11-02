package Programmers_K;
import java.util.*;
public class KaKao2020_01 {
	public static void main(String[] args) {
		Solution s = new Solution();
		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		int[] answer = s.solution(gems);
		System.out.println(answer[0]+" "+ answer[1]);
	}
	
	static class Solution {
	    public int[] solution(String[] gems) {
	        HashSet<String> hs = new HashSet<String>(Arrays.asList(gems));
	        HashMap<String, Integer> hm = new HashMap<>();
	        int[] answer = new int[2];
	        
	        for(String str : hs){
	            hm.put(str,0);
	        }
	        
	        Queue<String> q = new LinkedList<>();
	        HashSet<String> now = new HashSet<>();
	        int startX = 0, startPoint=0;
	        int len = Integer.MAX_VALUE;
	        
	        for(String str : gems){
	            // ���� ������ �� ä�������� Ȯ��
	            if(now.size() < hs.size()){
	                now.add(str);
	            }            
	            
	            // ���� �ֱ�
	            q.add(str);
                hm.put(str, hm.get(str)+1);	         
                
	            while(true){
	                String s = q.peek();
	                
	                // 1���ۿ� ���� ��� ����
	                if(hm.get(s)==1){
	                    break;
	                }
	                
	                // 2�� �̻��� ��� 
	                if(hm.get(s)>1){
	                    hm.put(s, hm.get(s)-1);
	                    q.poll();
	                    startX++;
	                }
	            }      
	            
	            // ���� ����
	            if(now.size() == hs.size() && len > q.size()){
	            	len = q.size();
	                answer[0] = startX+1;
	                answer[1] = startX+q.size();
	            }
	        }
	        
	        return answer;
	    }
	}
}
