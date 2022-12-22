package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj17281 {
	private static int N, maxSum=0;
	private static int[][] players;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
				
		N = Integer.parseInt(br.readLine());
		players = new int[N][9];
				
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				players[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] order = new int[9];
		order[3]=0;
		
		comb(0,order, new HashSet<Integer>());
		
		bw.write(maxSum+"");
		bw.flush();
		bw.close();
	}
	
	public static void comb(int cnt, int[] player, HashSet<Integer> hs) {
		if(cnt==9) {
			getMaxSum(player);
			return ;
		}
		
		if(cnt==3)
			cnt++;
		
		for(int i=1; i<9; i++) {
			if(hs.contains(i))
				continue;
			
			hs.add(i);
			player[cnt]=i;
			
			comb(cnt+1, player, hs);
			
			player[cnt]=0;
			hs.remove(i);
		}
		
	}
	
	public static void getMaxSum(int[] player) {
		int start = 0;
		int sum = 0;
		
		for(int i=0; i<N; i++) {
			int outCnt = 0;
			int[] base = new int[3];
			while(outCnt < 3) {
				int idx = player[start++];
				int res = players[i][idx];
				
				switch(res) {
				case 0:{
					outCnt++;
					break;
				}
				case 1:{
					if(base[2]==1) {
						base[2]=0;
						sum++;
					}
					
					if(base[1]==1) {
						base[1]=0;
						base[2]=1;
					}
					
					if(base[0]==1) {
						base[0]=0;
						base[1]=1;
					}
					
					base[0]=1;
					
					break;
				}
				case 2:{	//2·çÅ¸
					if(base[2]==1) {
						base[2]=0;
						sum++;
					}
					if(base[1]==1) {
						sum++;
					}
					if(base[0]==1) {
						base[2]=1;
						base[0]=0;
					}
					
					base[1]=1;
					break;
				}
				case 3:{
					sum+= base[0]+base[1]+base[2];
					base[0]=base[1]=0;
					base[2]=1;
					break;
				}
				default:{
					sum+= base[0]+base[1]+base[2]+1;
					base[0]=base[1]=base[2]=0;
				}
				}
				start %= 9;
			}
		}
		
		maxSum = Math.max(maxSum, sum);
	}
}
