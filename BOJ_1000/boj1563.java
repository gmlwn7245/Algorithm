package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;


public class boj1563 {
	private static int[][][] attend;
	private static int mod = 1000000, N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		// [출결 수] [지각 수] [연속 결석 수]
		attend = new int[N+1][2][3];
		attend[1][0][0] = attend[1][1][0] = attend[1][0][1] = 1;
		
		for(int i=2; i<=N; i++) {
			attend[i][0][0] = (attend[i-1][0][0] + attend[i-1][0][1] + attend[i-1][0][2]) % mod;
			attend[i][1][0] = (attend[i-1][0][0] + attend[i-1][1][0] + attend[i-1][0][1] + attend[i-1][0][2]  + attend[i-1][1][1] + attend[i-1][1][2]) % mod;
			attend[i][0][1] = attend[i-1][0][0] % mod; 
			attend[i][1][1] = attend[i-1][1][0] % mod;
			attend[i][0][2] = attend[i-1][0][1] % mod;
			attend[i][1][2] = attend[i-1][1][1] % mod;
		}
		
		int sum = (attend[N][0][0]+attend[N][1][0]+attend[N][0][1]+attend[N][0][2]+attend[N][1][1]+attend[N][1][2])%mod;
		
		
		bw.write(sum+"");
		bw.flush();
		bw.close();
	}
	
}
