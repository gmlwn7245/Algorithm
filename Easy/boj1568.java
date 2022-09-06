package Easy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class boj1568 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int num3 = sc.nextInt();
		long n = num3;
		if((num2-num1)==(num3-num2)) {
			int d = num2-num1;
			for(int i=3; i<N; i++) {
				n = sc.nextInt();
			}
			n += d;
		}else {
			int r = num2/num1;
			for(int i=3; i<N; i++) {
				n = sc.nextInt();
			}
			n *= r;
		}
			
		System.out.println(n);
	}
}
