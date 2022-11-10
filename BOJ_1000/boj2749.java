package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/*
 * 피사노 주기★
 * https://www.acmicpc.net/blog/view/28
 * 
 * */


public class boj2749 {
	//private static HashMap<Long,Long> hm = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long N = Long.parseLong(br.readLine());
		long mod = 1000000;
		long P = 15 * mod / 10;
		
		N %= P;
		
		long n1 = 0, n2 = 1;
		for(long i=2; i<=N; i++) {
			long tmp = n1+ n2;
			if(tmp > mod) tmp %= mod;
			n1 = n2;
			n2 = tmp;
		}
		
		bw.write(n2+"");
		bw.flush();
		bw.close();
	}
}
