package BOJ_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj21611 {
	public static long ans=0;
	public static int N, M, sharkX, sharkY;
	public static int[][] map;
	
	public static HashMap<Integer, Integer> exp = new HashMap<>();
	
	public static int[] dx = {0, -1, 1, 0, 0};
	public static int[] dy = {0, 0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		sharkX = (N+1)/2-1;
		sharkY = (N+1)/2-1;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			destory(d,s);
			collectBall();
			
			while(remove()!=0) {
				setAns();
			}
			
			setBall();
			newMap();
			
		}
		System.out.println(ans);
	}
	
	public static void setAns() {
		for(int n : exp.keySet()) {
			ans += n * exp.get(n);
		}
			
	}
	
	public static void setBall() {
		int last = -1;
		int cnt = 0;
		if(q.size()==0)
			return ;
		
		Queue<Integer> newQ = new LinkedList<>();
		while(!q.isEmpty()) {
			if(q.peek()==last) {
				q.poll();
				cnt++;
			}else {
				if(last != -1) {
					newQ.add(cnt);
					newQ.add(last);
				}
				
				last = q.poll();
				cnt = 1;
			}
		}
		
		newQ.add(cnt);
		newQ.add(last);
		
		q.addAll(newQ);
	}
	
	public static int remove() {
		int last = -1;
		int cnt = 0;
		Queue<Integer> newQ = new LinkedList<>();
		exp = new HashMap<>();
		
		while(!q.isEmpty()) {
			if(q.peek()==last) {
				q.poll();
				cnt++;
			}else {
				if(cnt < 4) {
					for(int i=0; i<cnt; i++)
						newQ.add(last);
				}else {
					exp.put(last, exp.getOrDefault(last, 0)+cnt);
				}
				
				last = q.poll();
				cnt = 1;
			}
			//System.out.println(last);
		}
		
		if(cnt < 4) {
			for(int i=0; i<cnt; i++)
				newQ.add(last);
		}else {
			exp.put(last, exp.getOrDefault(last, 0)+cnt);
		}
		
		q.addAll(newQ);
		return exp.keySet().size();
	}
	
	public static void printMap() {
		System.out.println("====PRINT MAP====");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void newMap() {
		int x = sharkX, y = sharkY;
		map = new int[N][N];
		
		boolean notArea = false;
		int level = 1;
		while(isInArea(x,y) && !q.isEmpty()) {
			// 왼쪽 이동
			for(int i=0; i<level; i++) {
				y--;
				if(!isInArea(x,y) || q.isEmpty()) {
					notArea = true;
					break;
				}
				map[x][y] = q.poll();
			}
			if(notArea) break;
			
			// 아래 이동
			for(int i=0; i<level; i++) {
				x++;
				if(!isInArea(x,y) || q.isEmpty()) {
					notArea = true;
					break;
				}
				map[x][y] = q.poll();
			}
			if(notArea) break;
			
			level++;
			
			// 오른쪽 이동
			for(int i=0; i<level; i++) {
				y++;
				if(!isInArea(x,y) || q.isEmpty()) {
					notArea = true;
					break;
				}
				map[x][y] = q.poll();
			}
			if(notArea) break;
			
			
			// 위로 이동
			for(int i=0; i<level; i++) {
				x--;
				if(!isInArea(x,y) || q.isEmpty()) break;
				map[x][y] = q.poll();
			}
			level++;
		}
		q.clear();
	}
	
	public static Queue<Integer> q = new LinkedList<>();
	public static void collectBall() {
		int x = sharkX, y = sharkY;
		q.clear();
		
		boolean notArea = false;
		int level = 1;
		while(isInArea(x,y)) {
			
			// 왼쪽 이동
			for(int i=0; i<level; i++) {
				y--;
				if(!isInArea(x,y)) {
					notArea = true;
					break;
				}
				if(map[x][y]!=0)
					q.add(map[x][y]);
			}
			if(notArea) break;
			
			// 아래 이동
			for(int i=0; i<level; i++) {
				x++;
				if(!isInArea(x,y)) {
					notArea = true;
					break;
				}
				if(map[x][y]!=0)
					q.add(map[x][y]);
			}
			if(notArea) break;
			
			level++;
			
			// 오른쪽 이동
			for(int i=0; i<level; i++) {
				y++;
				if(!isInArea(x,y)) {
					notArea = true;
					break;
				}
				if(map[x][y]!=0)
					q.add(map[x][y]);
			}
			if(notArea) break;
			
			// 위로 이동
			for(int i=0; i<level; i++) {
				x--;
				if(!isInArea(x,y)) break;
				if(map[x][y]!=0)
					q.add(map[x][y]);
			}
			
			level++;
		}
	}
	
	
	public static void destory(int d, int s) {
		int sx = sharkX+dx[d], sy = sharkY+dy[d];
	
		while(isInArea(sx, sy) && (s--)>0) {
			map[sx][sy]=0;
			sx+=dx[d];sy+=dy[d];
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
}
