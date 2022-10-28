package Programmers;

import java.util.*;

class level03_03 {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> q1 = new PriorityQueue<>();
        PriorityQueue<Integer> q2 = new PriorityQueue<>(Collections.reverseOrder());
        
        int cnt = 0;
        for(String s : operations){
            String[] str = s.split(" ");
            int num = Integer.parseInt(str[1]);
            
            if(str[0].equals("I")){
                q1.add(num);
                q2.add(num);
                cnt++;
            }else if(cnt==0){
                continue;
            }else if(num == -1){    //ÃÖ¼Ú°ª
                int n = q1.poll();
                q2.remove(n);
                cnt--;
            }else {     //ÃÖ´ñ°ª
                int n = q2.poll();
                q1.remove(n);
                cnt--;
            }
        }
        
        if(cnt==0){
            return new int[] {0,0};
        }
        
        if(cnt==1){
            if(q2.size()==1){
                int n = q2.poll();
                return new int[] {n,n};
            }else {
                int n = q1.poll();
                q2.remove(n);
                return new int[] {n,n};
            }
        }
        
        return new int[] {q2.poll(), q1.poll()};
    }
}