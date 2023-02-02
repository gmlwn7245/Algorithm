package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class boj23288 {
	private static int N,M,dir=0,sum=0;
	private static int top = 1, x=1, y=1;
	private static int[] row = new int[] {3,6,4};
	private static int[] col = new int[] {5,6,2};
	
	// 동0 서1 남2 북3
	private static int[] dx = {0,0,1,-1};
	private static int[] dy = {1,-1,0,0};
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		int K = Integer.parseInt(str[2]);
		
		map = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			str = br.readLine().split(" ");
			for(int j=1; j<=M; j++) 
				map[i][j] = Integer.parseInt(str[j-1]);
		}
		//printMap();
		for(int i=0; i<K; i++) {
			moveDice();
			bfs();
		}
		
		System.out.println(sum);
	}
	
	public static void printMap() {
		System.out.println("=====PRINT DICE====");
		System.out.print(top+" ");
		for(int i=0; i<3; i++)
			System.out.print(row[i]+" ");
		System.out.println();
		for(int i=0; i<3; i++)
			System.out.println(col[i]);
	}
	
	public static void bfs() {
		int val = map[x][y];
		
		boolean[][] visited = new boolean[N+1][M+1];
		visited[x][y]=true;
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			cnt++;
			
			for(int i=0; i<4; i++) {
				int nx = now[0]+dx[i];
				int ny = now[1]+dy[i];
				
				// 범위 밖
				if(!isInArea(nx, ny))
					continue;
				
				// 값 불일치
				if(map[nx][ny]!=val)
					continue;
				
				// 이미 방문한 곳
				if(visited[nx][ny])
					continue;
				
				visited[nx][ny]=true;
				q.add(new int[] {nx, ny});
			}
		}
		
		sum += cnt * val;
	}
	
	public static void moveDice() {		
		x += dx[dir];
		y += dy[dir];
		
		setDice();		
		setDir();
		
		
	}
	
	// 방향에 맞게 주사위 값 바꾸기
	public static void setDice() {
		switch(dir) {
		case 1:{
			int tmp = row[0];
			row[0]=row[1];
			row[1]=row[2];
			row[2]=top;
			top = tmp;
			col[1] = 7-top;
			break;
		}
		case 0:{
			int tmp = row[2];
			row[2]=row[1];
			row[1]=row[0];
			row[0]=top;
			top = tmp;
			col[1] = 7-top;
			break;
		}
		case 3:{
			int tmp = col[0];
			col[0]=col[1];
			col[1]=col[2];
			col[2]=top;
			top = tmp;
			row[1] = 7-top;
			break;
		}
		case 2:{
			int tmp = col[2];
			col[2]=col[1];
			col[1]=col[0];
			col[0]=top;
			top = tmp;
			row[1] = 7-top;
			break;
		}
		}
	}
	
	// 다음 방향 정하기
	public static void setDir() {
		// 동0 서1 남2 북3
		
		// 시계 : 동0 - 남2 - 서1 - 북3
		// 반시계 : 동0 - 북3 - 서1 - 남2
		int bottom = 7-top;
		int num = map[x][y];
		
		if(bottom > num) { //시계
			if(dir < 2)	// 동, 서
				dir+=2;
			else if(dir==2)	// 남
				dir = 1;
			else	// 북
				dir = 0;
		}else if(bottom < num) { //반시계
			if(dir > 1)	// 남, 북
				dir-=2;
			else if(dir==0)	// 동
				dir = 3;
			else	// 서
				dir = 2;
		}
		
		while(!isInArea(x+dx[dir], y+dy[dir])) {
			if(dir==0 || dir==2)
				dir+=1;
			else
				dir-=1;
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x>0 && y>0 && x<=N && y<=M;
	}
}
