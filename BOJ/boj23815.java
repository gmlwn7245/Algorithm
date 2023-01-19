package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj23815 {
	private static int N;
	private static int[][] choose;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 0 광고X  &&  1 광고O
		choose = new int[2][N+1];	
		choose[0][0]=1;
		
		for(int i=1; i<=N; i++) {
			String[] str = br.readLine().split(" ");
			char aOP = str[0].charAt(0);
			int aNum = Integer.parseInt(str[0].substring(1));
			
			char bOP = str[1].charAt(0);
			int bNum = Integer.parseInt(str[1].substring(1));
			
			// 광고 보기 전
			
			// 선택하는 경우
			int num1 = choose[0][i-1];
			
			if(num1 == -1) {
				choose[0][i]=-1;
			}else {
				int res1 = getRes(num1, aNum, aOP);
				int res2 = getRes(num1, bNum, bOP);
				
				int maxR = Math.max(res1, res2);
				if(maxR == 0)
					choose[0][i]=-1;
				else
					choose[0][i] = maxR; 
			}
			
			// 광고 본 후
			int num2 = choose[1][i-1];
			
			if(num2 == -1) {
				choose[1][i]=-1;
			}else {
				int res1 = getRes(num2, aNum, aOP);
				int res2 = getRes(num2, bNum, bOP);
				
				int maxR = Math.max(res1, res2);
				if(maxR == 0)
					choose[1][i]=-1;
				else
					choose[1][i] = maxR; 
			}
			
			// 이번 턴에서 광고를 본 경우
			
			if(choose[0][i-1]!=-1) {
				choose[1][i]=Math.max(choose[0][i-1], choose[1][i]);
			}	
			
			//System.out.println("=======TURN"+i+"=======");
			//System.out.println(choose[0][i]+" "+choose[1][i]);
		}
		
		int max = Math.max(choose[0][N], choose[1][N]);
		if(max == -1)
			System.out.println("ddong game");
		else
			System.out.println(max);
	}
	
	public static int getRes(int a, int b, char op) {
		switch(op) {
		case '+' : return a+b;
		case '-' : {
			int res = a - b;
			if(res < 0)
				res = 0;
			return res;
		}
		case '*' : return a*b;
		case '/' : return a/b;
		}
		return 0;
	}
}
