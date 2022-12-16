package trail_error;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// 시간 초과
public class boj2624 {
	static class Coin implements Comparable<Coin>{
		int price, cnt;
		public Coin(int price, int cnt){
			this.price = price;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Coin o) {
			return this.price- o.price;
		}
	}
	
	static int T, K , ans = 0;
	static int[] cIdx;
	static ArrayList<Coin> coin = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		cIdx = new int[K+1];
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int price = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			coin.add(new Coin(price, cnt));
		}
		
		Collections.sort(coin);		
		
		dfs(0,0);
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
	
	public static void dfs(int sum, int idx) {
		Coin c = coin.get(idx);
		
		for(int i=0; i <= c.cnt; i++) {
			int s = sum + c.price * i;
			
						
			if(s > T) 
				break;				
			
			if(s == T) {
				ans++;
				break;
			}
			
			if(idx == K-1)
				continue;
			
			if(s+coin.get(idx+1).price > T) {
				continue;
			}
			
			dfs(s, idx+1);
		}
	}
}
