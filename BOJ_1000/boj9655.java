package BOJ_1000;

import java.io.IOException;
import java.util.Scanner;

public class boj9655 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt() % 4;
		
		if(N%2==0)
			System.out.println("CY");
		else
			System.out.println("SK");
	}
}
