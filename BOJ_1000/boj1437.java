package BOJ_1000;

import java.util.Scanner;

public class boj1437 {
	private static int mod = 10007;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		long ans = 1;
		
		while(N >= 5) {
			ans = (ans * 3) % mod;
			N -= 3;
		}
		
		ans = (ans * N) % mod;
		System.out.println(ans);
	}
}
