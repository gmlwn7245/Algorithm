package BOJ_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj17837 {
	static class Horse {
		int x, y, dir;
		public Horse(int x, int y, int dir) {
			this.x =x; this.y = y; this.dir =dir;
		}
	}
	
	public static int[] dx = {0,0,0,-1,1};
	public static int[] dy = {0,1,-1,0,0};
	
	public static int N, K;
	public static int[][] map;
	public static Horse[] horses;
	public static Stack<Integer>[][] place;
	
	public static void main(String[] args) throws IOException {
		init();
		
		int turn = 0;
		
		while(++turn <= 1000) {
			boolean check = false;
			for(int i=0; i<K; i++) {
				Horse h = horses[i];
				if(place[h.x][h.y].size()>=4) {
					check = true;
					break;
				}
				
				int nx = h.x + dx[h.dir];
				int ny = h.y + dy[h.dir];
				
				if(!isInArea(nx, ny) || map[nx][ny]==2) {
					h.dir = setDir(h.dir);
					nx = h.x + dx[h.dir];
					ny = h.y + dy[h.dir];
					
					// ¹æÇâ ¹Ù²å´Âµ¥ ÆÄ¶õ»ö Ä­ÀÎ °æ¿ì
					if(!isInArea(nx, ny) || map[nx][ny]==2)
						continue;
				}
				
				moveWhite(h, i, nx, ny);
				//System.out.println(nx+" "+ny+" "+place[nx][ny].size());
				
				if(place[nx][ny].size() >= 4) {
					check = true;
					break;
				}
				
				// ÇÏ¾á»ö Ä­
				if(map[nx][ny]==0)
					moveWhite(h,i,nx,ny);
				
				// »¡°£»ö Ä­
				if(map[nx][ny]==1) 
					moveRed(h,i,nx,ny);
			}
			
			if(check)
				break;
		}
		
		if(turn > 1000)
			turn = -1;
		
		System.out.println(turn);
	}
	
	public static void moveRed(Horse h, int idx, int nx, int ny) {
		Stack<Integer> stack = place[h.x][h.y];
		Queue<Integer> temp = new LinkedList<>();
		while(stack.peek() != idx) {
			temp.add(stack.pop());
		}
		temp.add(stack.pop());
		
		while(!temp.isEmpty()) {
			int num = temp.poll();
			horses[num].x = nx;
			horses[num].y = ny;
			place[nx][ny].push(num);
		}
	}
	
	public static void moveWhite(Horse h, int idx, int nx, int ny) {
		Stack<Integer> stack = place[h.x][h.y];
		Stack<Integer> temp = new Stack<>();
		while(stack.peek() != idx) {
			temp.add(stack.pop());
		}
		temp.add(stack.pop());
		
		while(!temp.isEmpty()) {
			int num = temp.pop();
			horses[num].x = nx;
			horses[num].y = ny;
			place[nx][ny].push(num);
		}
	}
	
	public static int setDir(int dir) {
		if(dir==1 || dir==3)
			return dir+1;
		else
			return dir-1;
	}
	
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	
	public static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		horses = new Horse[K];
		place = new Stack[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				place[i][j] = new Stack<Integer>();
			}
		}
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			
			horses[i] = new Horse(x,y,dir);
			place[x][y].push(i);
		}
	}
}
