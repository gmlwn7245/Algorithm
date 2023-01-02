package BOJ_SS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class boj20056 {
	static class FireBall {
		int r,c,m,s,d;
		public FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	private static int N, M, K;
	
	private static int[] dx = {-1,-1,0,1,1,1,0,-1};
	private static int[] dy = {0,1,1,1,0,-1,-1,-1};
	private static Queue<FireBall> balls = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		
		
		for(int i=0; i<M; i++) {
			String ss = br.readLine();
			if(ss.equals(" ")) {
				i--;
				continue;
			}
			StringTokenizer st = new StringTokenizer(ss);
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			balls.add(new FireBall(r,c,m,s,d));
		}
		
		for(int i=0; i<K; i++) {
			moveBall();
		}
			
		
		int ans = 0;
		while(!balls.isEmpty()) {
			FireBall fb = balls.poll();
			ans += fb.m;
		}
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
	
	public static void moveBall() {
		HashMap<String, Queue<FireBall>> mBall = new HashMap<>();
		
		while(!balls.isEmpty()) {
			FireBall fb = balls.poll();
			if(fb.m==0)
				continue;
			
			int nr = fb.r + dx[fb.d] * (fb.s%N);
			int nc = fb.c + dy[fb.d] * (fb.s%N);
			
			if(nr > 0) nr %= N;
            if(nc > 0) nc %= N;
            if(nr < 0) nr = N - Math.abs(nr);
            if(nc < 0) nc = N - Math.abs(nc);
			
            fb.r = nr;
            fb.c = nc;
            
			Queue<FireBall> q = mBall.getOrDefault(nr+" "+nc, new LinkedList<>());
			q.add(fb);
			mBall.put(nr+" "+nc, q);
		}
		
		for(String str : mBall.keySet()) {
			String[] s = str.split(" ");
			int r = Integer.parseInt(s[0]);
			int c = Integer.parseInt(s[1]);
			
			Queue<FireBall> q = mBall.get(str);
			
			if(q.size()==1) {
				balls.add(q.poll());
				continue;
			}
			
			boolean isEven = true;
			boolean isOdd = true;
			
			int totD = 0;
			int totM = 0;
			int totS = 0;
			int cnt = q.size();
						
			while(!q.isEmpty()) {
				FireBall fb = q.poll();
				
				totM += fb.m;
				totS += fb.s;
				
				if(fb.d%2==0)
					isOdd = false;
				else
					isEven = false;
			}
			
			int nM = totM / 5;
			int nS = totS / cnt;
			
			if(nM <= 0)
				continue;
			
			if(isOdd || isEven) {
				for(int i=0; i<7; i+=2) {
					balls.add(new FireBall(r,c,nM, nS, i));
				}
			}else {
				for(int i=1; i<8; i+=2) {
					balls.add(new FireBall(r,c,nM, nS, i));
				}
			}
			
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x>0 && x<=N && y>0 && y<=N;
	}
}
