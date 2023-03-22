package BOJ_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj23289 {
	static class Fan {
		int x, y, dir;
		
		public Fan (int x, int y, int dir) {
			this.x=x;this.y=y;this.dir=dir;
		}
	}
	
	public static int R, C, K;
	public static int[][] map, hot, temp;
	public static boolean[][][] wall;
	public static ArrayList<Fan> fans = new ArrayList<Fan>();
	public static ArrayList<int[]> checkList = new ArrayList<int[]>();
	
	public static void main(String[] args) throws IOException {
		init();
		
		int choco = 0;
		while(choco <= 100 && !checkAll()) {
			activeFan();
			
			sum();
			
			distribute();
			
			choco++;
			
			side();
		}
		System.out.println(choco);
	}
	
	public static void printMap(int[][] map, String Message) {
		System.out.println("========="+Message+"=======");
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++)
				System.out.print(map[i][j]+" ");
			System.out.println();
		}	
	}
	
	/* 4. ���� üũ */
	public static boolean checkAll() {
		for(int[] point : checkList) {
			int x = point[0], y = point[1];
			if(hot[x][y]<K)
				return false;
		}
		return true;
	}
	
	/* 3. �ٱ��� ���� */
	public static void side() {
		for(int i=0; i<R; i++) {
			if(hot[i][0]!=0)
				hot[i][0]--;
			if(hot[i][C-1]!=0)
				hot[i][C-1]--;
		}
		
		for(int i=1; i<C-1; i++) {
			if(hot[0][i]!=0)
				hot[0][i]--;
			if(hot[R-1][i]!=0)
				hot[R-1][i]--;
		}
			
	}
	
	/* 2. �µ� ���� */
	
	public static void distribute() {
		int[][] change = new int[R][C];
		
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++) {
				int now = hot[i][j];
				
				for(int d=0; d<4; d++) {
					if(wall[i][j][d])
						continue;
					if(i==2 && j==4)
						System.out.print("");
					
					int nx=i+dx[d], ny=j+dy[d];
					
					if(!isInArea(nx, ny))
						continue;
					
					int next = hot[nx][ny];
					
					if(now > next) {
						int res = (now-next)/4;
						change[i][j] -= res;
						change[nx][ny] += res;
					}
				}
			}
		
		
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++)
				hot[i][j]+=change[i][j];
	}
	
	/* 1. ��ǳ�� �ٶ� ���� */
	// �ٶ� ����
	public static void sum() {
		
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++)
				hot[i][j] += temp[i][j];
		temp = new int[R][C];
	}
	
	public static void activeFan() {
		for(Fan f : fans) {
			// ���� 0������ 1���� 2�� 3�Ʒ�
			int x = f.x, y = f.y, dir = f.dir;
			//System.out.println(x+" "+y+" "+dir);
			
			if(wall[x][y][f.dir])
				continue;
			
			if(dir == 0)
				wind(f, 2, 3);
			else if(dir == 1)
				wind(f, 3, 2);
			else if(dir == 2)
				wind(f, 1, 0);
			else
				wind(f, 0, 1);
		}
	}
	
	
	// ���� 0������ 1���� 2�� 3�Ʒ�
	public static int[] dx = {0,0,-1,1};
	public static int[] dy = {1,-1,0,0};
	
	public static void wind(Fan f, int left, int right) {
		int[][] winds = new int[R][C];
		int x = f.x+dx[f.dir], y = f.y+dy[f.dir], dir = f.dir;
		int cnt = 0;
		
		winds[x][y]=5;
		
		// 5Level, �� �Է��̱⵵ ��
		for(int i=4; i>=1; i--, cnt+=2) {
			if(!isInArea(x,y))
				break;
			
			// ���
			if(winds[x][y]!=0)
				spread(winds, i, x, y, dir, left, right);
			
			// ���ʰ� ������
			int rx=x,ry=y,lx=x,ly=y;
			
			for(int j=0; j<cnt; j++) {
				rx += dx[right]; ry+=dy[right];
				lx += dx[left]; ly+=dy[left];
				
				if(isInArea(rx,ry) && winds[rx][ry]!=0)
					spread(winds, i, rx, ry, dir, left, right);
				if(isInArea(lx,ly) && winds[lx][ly]!=0)
					spread(winds, i, lx, ly, dir, left, right);
			}
			x+=dx[dir]; y+=dy[dir];
		}
		
		//printMap(winds," Winds ");
		
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++)
				temp[i][j] += winds[i][j];
	}
	
	public static void spread(int[][] winds, int num, int x, int y, int dir, int left, int right) {
		
		// ������
		int nx = x+dx[dir], ny=y+dy[dir];
		if(isInArea(nx, ny) && !wall[x][y][dir])
			winds[nx][ny]=num;
		
		// ���� �밢��
		int lx = nx+dx[left], ly=ny+dy[left];
		if(isInArea(lx, ly) && !wall[x][y][left] && !wall[x+dx[left]][y+dy[left]][dir])
			winds[lx][ly]=num;
		
		// ������ �밢��
		int rx = nx+dx[right] , ry = ny+dy[right];
		if(isInArea(rx, ry) && !wall[x][y][right] && !wall[x+dx[right]][y+dy[right]][dir])
			winds[rx][ry]=num;
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<R && y>=0 && y<C;
	}
	
	
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		hot = new int[R][C];
		temp = new int[R][C];
		wall = new boolean[R][C][4];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num > 0 && num <= 4)
					fans.add(new Fan(i,j,num-1));
				else if(num == 5)
					checkList.add(new int[] {i,j});
			}
		}
		
		// ���� 1������ 2���� 3�� 4�Ʒ�
		
		int W = Integer.parseInt(br.readLine());
		
		// �� ����
		for(int i=0; i<W; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1, y = Integer.parseInt(st.nextToken())-1;
			int k = Integer.parseInt(st.nextToken());
			
			if(k==0) {
				int nx = x-1, ny = y;
				if(!isInArea(nx, ny))
					continue;
				wall[x][y][2]=true;	// �ش� ��ǥ�� ��
				wall[nx][ny][3]=true;	// �� ��ǥ�� �Ʒ�
			} else {
				int nx = x, ny = y+1;
				if(!isInArea(nx, ny))
					continue;
				wall[x][y][0]=true;	// �ش� ��ǥ�� ������
				wall[nx][ny][1]=true;	// ������ ��ǥ�� ����
			}
		}
		
	}
}

