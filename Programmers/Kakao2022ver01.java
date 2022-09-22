package Programmers;

public class Kakao2022ver01 {
	// K진수 구하기
	public static void main(String[] args) {
		int n = 110011;
		int k = 10;
		String str = changeNum(n, k);
		int res = 0;

		String[] split = str.split("0");
		for (int i = 0; i < split.length; i++) {
			if(split[i].equals("")) continue;
			if (isPrime(Long.parseLong(split[i])))
				res++;
		}
		System.out.println(res);
	}

	public static boolean isPrime(long n) {
		if (n <= 1)
			return false;
		else if (n == 2)
			return true;
		for (long i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static String changeNum(int n, int k) {
		StringBuilder sb = new StringBuilder();
		
		while (n > 0) {
			sb.insert(0, n % k);
			n /= k;
		}
		return sb.toString();
	}
}
