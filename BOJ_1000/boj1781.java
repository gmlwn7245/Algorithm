package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class boj1781 {
   static class Problem implements Comparable<Problem> {
      int limit;
      int cup;
      public Problem (int limit, int cup) {
         this.limit = limit;
         this.cup = cup;
      }
      @Override
      public int compareTo(Problem o) {
         if(this.limit == o.limit)
            return o.cup - this.cup;
         return this.limit - o.limit;
      }
   }
   private static int N;
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      N = Integer.parseInt(br.readLine());
      
      PriorityQueue<Problem> pq = new PriorityQueue<>();
      PriorityQueue<Problem> res = new PriorityQueue<>(new Comparator<Problem>() {
		@Override
		public int compare(Problem o1, Problem o2) {
			return o1.cup - o2.cup;
		}
      });
      
      
      for(int i=0; i<N; i++) {
         String[] str = br.readLine().split(" ");
         int l = Integer.parseInt(str[0]);
         int c = Integer.parseInt(str[1]);
         
         pq.add(new Problem(l,c));
      }
      
      int time = 0, ans = 0;
      while(!pq.isEmpty()) {
         Problem p = pq.poll();
         if(time >= p.limit) {
        	 if(res.peek().cup < p.cup) {
        		 ans -= res.poll().cup;
        		 ans += p.cup;
        		 res.add(p);
        	 }
        	 continue;
         }
         
         res.add(p);
         ans += p.cup;
         time++;
      }
      
      System.out.println(ans);
   }
   
}