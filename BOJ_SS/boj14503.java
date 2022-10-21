package BOJ_SS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj14503 {
	private static int n,m,r,c,d,cleanCnt=0;
	// ���� 0�� 1�� 2�� 3��
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
		
		// �ݽð� �������� ȸ��
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
			
			// ���� �ڸ� û��
			if(!isClean[nowX][nowY]) {
				isClean[nowX][nowY] = true;
				cleanCnt++;
			}
			
			int leftDir = nowDir;
			
			int i=0;
			for(i=0; i<4; i++) {
				// ���� ����
				leftDir = (leftDir+3) % 4;
				int nextX = nowX + dx[leftDir];
				int nextY = nowY + dy[leftDir];
				
				// ���� ��ǥ�� ���� �� or ���� ��� -> �ٽ� ȸ��
				if(!isInArea(nextX,nextY) || map[nextX][nextY]==1) {
					continue;
				}
				
				// ���ʿ� û������ ���� ���� ����
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
				
				// �ڰ� ���� �ƴ� ���
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
