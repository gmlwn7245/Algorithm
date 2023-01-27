package BOJ_1000;

import java.util.HashMap;
import java.util.Scanner;

public class boj1016 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long min = sc.nextLong(), max = sc.nextLong();
		
		HashMap<Long,Boolean> hm = new HashMap<>();
		hm.put((long)1, false);
		
		long ans = 0;
		for(long i=2; i<=(long)Math.sqrt(max); i++) {
			long sq = i*i;
			long start = min % sq == 0? min/sq : (min/sq)+1;
			for(long j=start; j*sq <= max; j++)
				hm.put(j*sq-min, true);
		}
		
		for(long i=0; i<max-min+1; i++)
			if(!hm.getOrDefault(i,false))
				ans++;
		
		
		System.out.println(ans);
	}
}
