package BOJ_1000;

import java.util.HashMap;
import java.util.Scanner;

public class boj1351 {
	private static long N;
	private static int P, Q;
	private static HashMap<Long, Long> hm = new HashMap<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextLong();
		P = sc.nextInt();
		Q = sc.nextInt();
		
		System.out.println(getNum(N));
	}
	
	public static long getNum(long num) {
		if(num==0) return 1;
		if(hm.containsKey(num)) return hm.get(num);
		
		long nP = (long) (num/P);
		long nQ = (long) (num/Q);
		
		hm.put(num, getNum(nP)+getNum(nQ));
		return hm.get(num);
	}
}
