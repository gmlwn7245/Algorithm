package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj11401 {
	private static long mod = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long U = getFactorial(N);
		long D = getFactorial(K) * getFactorial(N-K) % mod;
		
		long ans = (U * getPow(D, mod-2)) % mod;
		
		bw.write(ans +"");
		bw.flush();
		bw.close();
		
	}
	
	public static long getFactorial(long num) {
		long res = 1L;
		while(num > 1) {
			res = res * num % mod;
			num--;
		}
		
		return res;
	}
	
	public static long getPow(long x, long a) {
		if(a==1)
			return x % mod;
	
		long tmp = getPow(x, a/2);
		
		if(a%2==0) {
			return (tmp * tmp) % mod;
		}
		
		return ((tmp * tmp % mod) * x )%mod;
	}
}
