package BOJ_SS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj14503 {
	private static int n,m,r,c,d,cleanCnt=0;
	// 방향 0북 1동 2남 3서
	private static int[] dx = {-1,0,1,0};
	private static int[] dy = {0,1,0,-1};
	private static int[][] map;
	private static boolean[][] isClean;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		
		// 반시계 방향으로 회전
		d = sc.nextInt();
		
		map = new int[n][m];
		isClean = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		bfs();
		System.out.println(cleanCnt);
	}
	
	public static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r,c});
		
		int nowDir = d;
		
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int nowX = now[0];
			int nowY = now[1];
			//System.out.println("clean ==> ("+nowX+","+nowY+")");
			
			// 현재 자리 청소
			if(!isClean[nowX][nowY]) {
				isClean[nowX][nowY] = true;
				cleanCnt++;
			}
			
			int leftDir = nowDir;
			
			int i=0;
			for(i=0; i<4; i++) {
				// 왼쪽 방향
				leftDir = (leftDir+3) % 4;
				int nextX = nowX + dx[leftDir];
				int nextY = nowY + dy[leftDir];
				
				// 다음 좌표가 범위 밖 or 벽인 경우 -> 다시 회전
				if(!isInArea(nextX,nextY) || map[nextX][nextY]==1) {
					continue;
				}
				
				// 왼쪽에 청소하지 않은 공간 존재
				if(!isClean[nextX][nextY] && map[nextX][nextY]==0) {
					nowDir = leftDir;
					queue.add(new int[] {nextX, nextY});
					break;
				}
			}
			
			if(i==4) {
				int backDir = (nowDir + 2) % 4;
				int backX = nowX + dx[backDir];
				int backY = nowY + dy[backDir];
				
				// 뒤가 벽이 아닐 경우
				if(map[backX][backY]==0) {
					queue.add(new int[] {backX, backY});
				}
			}
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<n && y>=0 && y<m;
	}
}
