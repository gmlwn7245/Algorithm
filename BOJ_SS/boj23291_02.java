package BOJ_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj23291_02 {
	public static int N, WN;
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//int T = Integer.parseInt(br.readLine());
		int T = 1;
		
		
		for(int i=1; i<=T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			WN = N+N/4;
			int K = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			map = new int[N][WN];
			for(int j=0; j<N; j++) {
				map[N-1][j]=Integer.parseInt(st.nextToken());
			}
			
			int ans = 0;
			while(getDiff() > K) {
				ans++;
				init();
				while(canFly()) {
					getPoint();
					//printMap();
				}
				
				sepFish();
				//printMap();
				
				spread();
				//printMap();
				
				flyHalf();
				sepFish();
				//printMap();
				
				map = spread2();
				//printMap();
			}
			
			System.out.println(ans);
			//ans.append("#"+i+" ");
		}
	}
	
	public static int getDiff() {
		int min = 10001, max = 0;
		for(int i=0; i<N; i++) {
			max = Math.max(max, map[N-1][i]);
			min = Math.min(min, map[N-1][i]);
		}
		return max - min;
	}
	
	/* 5. 다시 펼치기 */
	public static int[][] spread2() {
		int[][] newMap = new int[N][N+N/4];
		int half = N/2, midHalf = N/4, idx = N-1;
		
		for(int j=N-1; j>N-1-midHalf; j--) {
			Stack<Integer> stack = new Stack<>();
			for(int i=N-1; i>N-1-half; i--) {
				stack.add(map[i][j]);
			}
			
			while(!stack.isEmpty()) {
				newMap[N-1][idx--] = stack.pop();
			}
		}
		return newMap;
	}
	
	/* 4. 두 번 접기 */
	public static void flyHalf() {
		int half = N/2;
		
		Stack<Integer> stack = new Stack();
		for(int i=0; i<half; i++) {
			stack.add(map[N-1][i]);
			map[N-1][i]=0;
		}
		
		int i=0;
		while(!stack.isEmpty()) {
			map[N-2][i+half] = stack.pop();
			i++;
		}
		
		int midHalf = N/4;
		Stack<Integer> stack2 = new Stack();
		for(int j=half; j<half+midHalf; j++) {
			stack.add(map[N-1][j]);
			stack2.add(map[N-2][j]);
			map[N-1][j]=map[N-2][j]=0;
		}
		
		i=half;
		while(!stack.isEmpty()) {
			map[N-4][i+midHalf] = stack.pop();
			map[N-3][i+midHalf] = stack2.pop();
			i++;
		}
	}
	
	/* 3. 다시 펼치기 */
	public static int[] getWH() {
		int start=-1,end=-1, h = 0 ,w = 0;
		for(int j=0; j<WN; j++) {
			if(w!=0 && map[N-2][j]==0) {
				break;
			}
			if(map[N-2][j]!=0) {
				if(w == 0)
					start =j;
				w++;
				end = j;
			}	
		}
		
		for(int i=N-2; i>=0; i--) {
			if(map[i][start]!=0)
				h++;
			else
				break;
		}
		return new int[] {start,end,h};
	}
	
	public static void spread() {
		
		//while(map[N-1][0]==0) {
			int[] data = getWH();
			int start = data[0];
			int end = data[1];
			int height = data[2];
			
			int idx = start-1;
			Stack<Integer> stack = new Stack<>();
			for(int h=N-2; h>N-2-height; h--) {
				for(int j=start; j<=end; j++) {
					stack.add(map[h][j]);
					map[h][j]=0;
				}
				
				while(!stack.isEmpty()) {
					map[N-1][idx--]=stack.pop();
				}
			}
			//printMap();
		//}
	}
	
	/* 2. 물고기 분배 */
	public static int[] dx = {0,0,1,-1};
	public static int[] dy = {1,-1,0,0};
	public static void sepFish() {
		int[][] pm = new int[N][WN];
		boolean[][] visited = new boolean[N][WN];
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {N-1, N-1});
		visited[N-1][N-1]=true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int nowX = now[0], nowY=now[1];
			int nowCnt = map[now[0]][now[1]];
			
			for(int i=0; i<4; i++) {
				int nx = nowX+dx[i];
				int ny = nowY+dy[i];
	
				if(!isInArea(nx, ny))
					continue;
				
				if(map[nx][ny]==0)
					continue;
				
				if(!visited[nx][ny]) {
					q.add(new int[] {nx, ny});
					visited[nx][ny]=true;
				}
				
				int nCnt = map[nx][ny];
				if(nCnt >= nowCnt)
					continue;
				
				// 내가 큰 쪽일때만 실행
				int res = (nowCnt - nCnt)/5;
				if(res > 0) {
					pm[nowX][nowY]-= res;
					pm[nx][ny]+=res;
				}
			}
		}
		
		for(int i=0; i<N; i++)
			for(int j=0; j<WN; j++)
				map[i][j]+=pm[i][j];
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<WN;
	}
	
	/* 1. 단순 어항 정리 */
	public static void init() {
		int min = 10001;
		HashSet<Integer> hs = new HashSet<>();
		for(int i=0; i<N; i++) {
			int cnt = map[N-1][i];
			if(cnt < min) {
				min = cnt;
				hs = new HashSet<>();
				hs.add(i);
			}else if(cnt == min)
				hs.add(i);
		}
		
		for(int j : hs)
			map[N-1][j]++;
		
		map[N-2][1] = map[N-1][0];
		map[N-1][0] = 0;
	}

	public static void getPoint() {
		int start = N, end = N;
		int h = 0, w = 0;
		for(int j=0; j<N; j++) {
			int cnt = 0;
			for(int i=N-1; i>=0; i--) {
				if(map[i][j]==0)
					break;
				cnt++;
			}
			
			if(cnt > 1) {
				start = Math.min(start, j);
				h = cnt;
			}else if(cnt == 1){
				end = Math.min(end, j);
				break;
			}
		}
		
		doFly(start, end, w, h);
	}
	
	public static void doFly(int start, int end, int w, int h) {
		int level = N-2;
		for(int i=end-1; i>=start; i--) {
			Queue<Integer> q = new LinkedList<>();
			for(int j=N-1; j>N-1-h; j--) {
				if(map[j][i]==0)
					break;
				q.add(map[j][i]);
				map[j][i]=0;
			}
			
			int len = q.size();
			for(int j=end; j<end+len; j++) {
				map[level][j]=q.poll();
			}
			level--;
		}
	}
	
	public static boolean canFly() {
		for(int i=0; i<N; i++) {
			if(map[N-1][i]!=0 && map[N-2][i]==0)
				return true;
		}
		return false;
	}
	
	public static void printMap() {
		System.out.println("=============PRINT==============");
		for(int i=0; i<N; i++) {
			for(int j=0; j<WN; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}		
	}
}
