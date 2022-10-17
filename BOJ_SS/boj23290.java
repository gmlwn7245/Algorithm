package BOJ_SS;

import java.util.ArrayList;
import java.util.Scanner;

class Fish{
	public int x,y,dir;
	public Fish(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

public class boj23290 {
	private static int m,s;
	private static int sharkX=-1, sharkY=-1;
	private static int maxSum = 0, mx = -1, my = -1;
	
	private static int[] sdx = {};
	
	private static int[] dx = {0,0,-1,-1,-1,0,1,1,1};
	private static int[] dy = {0,-1,-1,0,1,1,1,0,-1};
	private static int[][] map;
	private static int[][] smell;
	private static int[][] smellCnt;
	private static ArrayList<Fish> fishes = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		m = sc.nextInt();
		s = sc.nextInt();
		map = new int[4][4];
		
		for(int i=0; i<m; i++) {
			int fx = sc.nextInt()-1;
			int fy = sc.nextInt()-1;
			int fd = sc.nextInt();
			
			fishes.add(new Fish(fx, fy, fd));
			map[fx][fy]++;
		}
		
		sharkX = sc.nextInt()-1;
		sharkY = sc.nextInt()-1;
		
		for(int i=0; i<s; i++) {
			// ���� ����
			ArrayList<Fish> copyFish = new ArrayList<>();
		
			for(Fish f : fishes) {
				copyFish.add(new Fish(f.x,f.y,f.dir));
			}
			
			// ����� �̵�
			moveFish();
			
			maxSum = 0;
			mx = sharkX;
			my = sharkY;
			
			// ��� �̵�
			
		}
	}
	
	//dfs
	public static void moveShark(int moveCnt, int fishCnt, int x, int y) {
		// ���� ���� 3ĭ �̵�
		// �����¿�� ������ ĭ���� �̵��� �� ����
		if(moveCnt == 3) {
			if(fishCnt > maxSum) {
				maxSum = fishCnt;
				mx = x;
				my = y;
			}
			return ;
		}
		
		for(int i=0; i<4; i++) {
			
		}
		
	}
	
	public static void moveFish() {
		// �� �ִ� ĭ, ����� ������ �ִ� ĭ, ������ ������ ����� ĭ �̵� �Ұ�
		// �̵��� �� �ִ� ĭ�� ������ �̵�X
		
		for(int i=0; i<fishes.size(); i++) {
			Fish f = fishes.get(i);
			
			// ����� ���� �ٲٰ� �̵�
			int nx = f.x + dx[f.dir];
			int ny = f.y + dy[f.dir];
			int newDir = f.dir;
			boolean canMove = true;
			while(!isAbleToMove(nx, ny)) {
				newDir = changeDir(f.dir);
				
				if(newDir == f.dir) {
					canMove = false;
					break;
				}
				
				nx = f.x + dx[newDir];
				ny = f.y + dy[newDir];
			}
			// ������ �� ���� ���
			if(!canMove)
				continue;
			
			
			// ������ ���
			map[f.x][f.y]--;
			map[nx][ny]++;
			
			f.dir = newDir;
			f.x = nx;
			f.y = ny;
		}
	}
	
	
	public static int changeDir(int dir) {
		if(dir == 8)
			return 1;
		else
			return dir+1;
	}
	
	public static boolean isAbleToMove(int x, int y) {
		if(x==sharkX && y==sharkY)
			return false;
		
		if(smell[x][y]!=0)
			return false;
		
		if(!isInArea(x,y))
			return false;
		
		return true;
	}
	
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<4 && y>=0 && y<4;
	}
}
