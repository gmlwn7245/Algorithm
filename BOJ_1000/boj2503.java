package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class boj2503 {
	static class Play {
		String num;
		int strike, ball;
		public Play(String s) {
			String[] str = s.split(" ");
			num = str[0];
			strike = Integer.parseInt(str[1]);
			ball = Integer.parseInt(str[2]);
		}
	}
	
	public static int ans = 0;
	public static HashSet<Play> hs = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			hs.add(new Play(br.readLine()));
		}
		
		dfs(new ArrayList<>());
		System.out.println(ans);
	}
	
	public static void dfs(ArrayList<Integer> arr) {
		if(arr.size() == 3) {
			StringBuilder sb = new StringBuilder();
			for(int i : arr)
				sb.append(i+"");
			isPossible(sb.toString());
			return ;
		}
		
		for(int i=1; i<=9; i++) {
			if(arr.contains(i))
				continue;
			arr.add(i);
			dfs(arr);
			arr.remove((Integer)i);
		}
	}
	
	public static void isPossible(String num) {
		
		for(Play p : hs) {
			
			// 스트라이크 확인
			int cntStk = 0;
			for(int i=0; i<3; i++) {
				if(p.num.charAt(i)==num.charAt(i))
					cntStk++;
			}
			
			if(p.strike != cntStk)
				return ;
			
			// 볼 확인
			int cntBall = 0;
			for(int i=0; i<3; i++) {
				char a = p.num.charAt(i);
				
				for(int j=0; j<3; j++) {
					if(i==j)
						continue;
					char b = num.charAt(j);
					if(a==b)
						cntBall++;
				}
			}
			
			if(p.ball != cntBall)
				return ;
		}
		
		ans++;
	}
}
