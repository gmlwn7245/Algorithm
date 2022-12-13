package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj1922 {
   static class Conn {
      int a, b;
      int cost;
      
      public Conn(int a, int b, int cost) {
         this.a = a;
         this.b = b;
         this.cost = cost;
      }
   }
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      
      int N = Integer.parseInt(br.readLine());
      int M = Integer.parseInt(br.readLine());
      
      HashSet<Integer> hs = new HashSet<>();
      ArrayList<Conn> arr = new ArrayList<>();
      
      for(int i=0; i<M; i++) {
         StringTokenizer st = new StringTokenizer(br.readLine());
         int a = Integer.parseInt(st.nextToken())-1;
         int b = Integer.parseInt(st.nextToken())-1;
         int cost = Integer.parseInt(st.nextToken());
         
         arr.add(new Conn(a,b,cost));
      }
      
      Collections.sort(arr, new Comparator<Conn>() {
         @Override
         public int compare(Conn o1, Conn o2) {
            return o1.cost - o2.cost;
         }
      });
      
      Conn c = arr.get(0);
      int sum = c.cost;
      
      hs.add(c.a);
      hs.add(c.b);
      
      arr.remove(0);
      
      for(int i=2; i<N; i++) {
    	  for(int j=0; j<arr.size(); j++) {
    		  c = arr.get(j);
    		  if(hs.contains(c.a) && hs.contains(c.b)) {
    			  arr.remove(j);
    			  j--;
    			  continue;
    		  }
    		  
    		  if(!hs.contains(c.a) && !hs.contains(c.b))
    			  continue;
    		  
    		  hs.add(c.a);
    		  hs.add(c.b);
    		  sum += c.cost;
    		  arr.remove(j);
    		  break;
    	  }
      }
      
      bw.write(sum + "");
      bw.flush();
      bw.close();
   }
}