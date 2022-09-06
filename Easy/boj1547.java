package Easy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class boj1547 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();

		int[] cup = {0,1,2,3};
		

		for (int i = 0; i < num; i++) {
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();

			if (num1 == num2)
				continue;
			
			int n = cup[num2];
			cup[num2] = cup[num1];
			cup[num1] = n;
		}
		
		for(int i=1; i<4; i++) {
			if(cup[i]==1) {
				System.out.println(i);
				break;
			}
		}
	}

}
