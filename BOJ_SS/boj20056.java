package BOJ_SS;

import java.util.*;

class Ball {
	public int x, y, m, d, s;
	public int nextX, nextY;
	
	public Ball(int x, int y, int m, int d, int s) {
		this.x = x;
		this.y = y;
		this.m = m;
		this.d = d;
		this.s = s;
	}
	
	public void setNext(int nextX, int nextY) {
		this.nextX = nextX;
		this.nextY = nextY;
	}
}

public class boj20056 {
	public static int n=0, m=0, k=0;
	public static int[] dx = {-1,-1,0,1,1,1,0,-1};
	public static int[] dy = {0,1,1,1,0,-1,-1,-1};
	public static ArrayList<Ball> balls = new ArrayList<>();
	public static int[][] ballCnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		
		// (r,c) : ��ġ
		// m : ����
		// d : ����
		// s : �ӷ�
		
		for(int i=1; i<=m; i++) {
			int r = sc.nextInt()-1;
			int c = sc.nextInt()-1;
			int m = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt();
			
			Ball b = new Ball(r,c,m,d,s);
			getNextPoint(b);
			balls.add(b);
		}
			
		
		// k�� �̵��ϱ�
		for(int i=0; i<k; i++) {
			
			// ���̾ �̵�
			int[][] ballCnt = new int[n][n];
			
			for(int j=0; j<balls.size(); j++) {
				Ball b = balls.get(j);
				
				// �̵��� ĭ �� ����
				getNextPoint(b);
				
				// �̵��ϴ� ��ǥ�� 1 ������
				ballCnt[b.nextX][b.nextY] += 1;
			}
			
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					int cnt = ballCnt[j][k];
					
					// �ƹ��͵� �Ͼ�� �ʴ� ĭ
					if(cnt == 0)
						continue;
					
					// ���̾ 1�� �̵��ϴ� ĭ
					if(cnt == 1) 
						moveBall(j,k);
					else	// ���̾�� 2�� �̻� �̵��ϴ� ĭ
						combBalls(j,k);
				}
			}
		}
		
		int resM = 0;
		
		for(int i=0; i<balls.size(); i++) {
			Ball b = balls.get(i);
			resM += b.m;
		}
		
		System.out.println(resM);
	}
	
	public static void moveBall(int x, int y) {
		for(int i=0; i<balls.size(); i++) {
			Ball b = balls.get(i);
			
			if(b.nextX == x && b.nextY == y) {
				b.x = x;
				b.y = y;
				return ;
			}
		}
	}
	
	public static void combBalls(int x, int y) {
		
		// ���� x,y�� �̵��ϴ� ���̾���� balls �ε���
		ArrayList<Integer> sameBalls = new ArrayList<>();
		
		int SameCnt=0;
		for(int i=0; i<balls.size(); i++) {
			Ball b = balls.get(i);
			
			if(b.nextX==x && b.nextY == y) {
				sameBalls.add(i);
				SameCnt++;
			}				
		}
		
		
		int m=0, s=0, d=0;
		
		boolean isEven = (balls.get(sameBalls.get(0))).d % 2 ==0 ? true : false;
		
		// ���ݱ��� ��� ���� ��������
		boolean isSameDir = true;
		
		for(int i=0; i<sameBalls.size(); i++) {
			int idx = sameBalls.get(i);
			m += balls.get(idx).m;
			s += balls.get(idx).s;
			
			// ���� ������ �ƴ� ��� �ؿ� ���� ����
			if(!isSameDir)
				continue;
			
			// ��� ¦���� �ƴϰų� ��� Ȧ���� �ƴ� ���
			if(isEven && balls.get(idx).d%2!=0) 
				isSameDir = false;
			else if(!isEven && balls.get(idx).d%2==0)
				isSameDir = false;
		}
		
		m /= 5;
		s /= sameBalls.size();
		
		
		// ��� ¦���̰ų� ��� Ȧ���� ���
		if(isSameDir)
			d = 0;
		else
			d = 1;
		
		
		int nSameBalls = sameBalls.size();
		
		for(int i=nSameBalls-1; i>=0; i--) {
			int idx = sameBalls.get(i);
			balls.remove(idx);
		}
		
		if(m<=0)
			return ;
		
		for(int i=0; i<4; i++) {
			Ball b = new Ball(x,y,m,d,s);
			getNextPoint(b);
			balls.add(b);
			d += 2;
		}
	}
	
	public static void getNextPoint(Ball b) {
		int corS = b.s % n;
		int nextX = b.x + n + dx[b.d] * corS;
		int nextY = b.y + n + dy[b.d] * corS;
		
		nextX %= n;
		nextY %= n;
		
		
		b.nextX = nextX;
		b.nextY = nextY;
	}
}