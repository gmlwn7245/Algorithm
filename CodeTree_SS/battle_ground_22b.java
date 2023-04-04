package CodeTree_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class battle_ground_22b {
	static class Player {
		int x, y,d,s,gun=0;
		public Player(int x, int y, int d, int s) {
			this.x =x;this.y=y;this.d=d;this.s=s;
		}
	}
	public static int N, M, K;
	public static int[] point;
	public static int[][] ground;
	public static PriorityQueue<Integer>[][] guns;
	// 0 위 1 오른 2 아래 3 왼
	public static int[] dx = {-1,0,1,0};
	public static int[] dy = {0,1,0,-1};
	public static Player[] players;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		guns = new PriorityQueue[N][N];
		players = new Player[M+1];
		point = new int[M+1];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				guns[i][j] = new PriorityQueue<>(Collections.reverseOrder());
				int gun = Integer.parseInt(st.nextToken());
				if(gun != 0)
					guns[i][j].add(gun);
			}
		}
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			players[i]=new Player(x,y,d,s);
		}
		//setMap();
		//printMap();

		for(int i=0; i<K; i++) {
			move();

			//printMap();
		}
		
		StringBuilder sb = new StringBuilder();
		for(int j=1; j<=M; j++)
			sb.append(point[j]+" ");
		System.out.println(sb.toString().trim());
	}
	
	public static void printMap() {
		System.out.println("=====PRINT PLAYER====");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(ground[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println("----PRINT PLAYER----");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(guns[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("STACK_____");
		for(int i=1; i<=M; i++) {
			System.out.println(i+" : "+players[i].gun+" "+players[i].s);
		}
	}
	
	public static void move() {
		setMap();
		for(int i=1; i<=M; i++) {
			Player p = players[i];
			int nx = p.x + dx[p.d];
			int ny = p.y + dy[p.d];
			
			if(!isInArea(nx, ny)) {
				p.d = (p.d+2)%4;
				nx = p.x + dx[p.d];
				ny = p.y + dy[p.d];
			}
			
			// Player 이미 있는 경우
			if(ground[nx][ny]!=0) {
				ground[p.x][p.y]=0;
				p.x = nx; p.y = ny;
				battle(i, ground[nx][ny]);
			}else {	// Player가 없는 경우
				// 총 넣기
				if(p.gun != 0) {
					guns[nx][ny].add(p.gun);
					p.gun = 0;
				}
				
				// 가장 크기가 큰 총 GET
				if(!guns[nx][ny].isEmpty())
					p.gun = guns[nx][ny].poll();
				
				ground[p.x][p.y]=0;
				p.x = nx; p.y = ny;
				ground[nx][ny]=i;
			}
		}
	}
	
	public static void battle(int idx1, int idx2) {
		Player p1 = players[idx1], p2 = players[idx2];
		
		int power1 = p1.gun + p1.s;
		int power2 = p2.gun + p2.s;
		
		Player winner=null, loser=null;
		int win=0, lose=0;
		
		if(power1 < power2) { 	//2승 1패
			winner = p2; loser = p1;
			win = idx2; lose=idx1;
			point[idx2] += power2-power1;
		}else if(power1 > power2) { 	//1승 2패
			winner = p1; loser = p2;
			win = idx1; lose=idx2;
			point[idx1]+= power1-power2;
		}else {
			// 초기 공격력으로 판단
			if(p1.s > p2.s) {
				winner = p1; loser = p2;
				win = idx1; lose=idx2;
			}else {
				winner = p2; loser = p1;
				win = idx2; lose=idx1;
			}
		}
		
		
		/* 패자 */
		
		// 총 내려놓기
		if(loser.gun != 0) {
			guns[loser.x][loser.y].add(loser.gun);
			loser.gun = 0;
		}
		
		int nx = 0, ny = 0;
		
		for(int i=0; i<4; i++) {
			nx = loser.x + dx[loser.d];
			ny = loser.y + dy[loser.d];
			if(isInArea(nx,ny) && ground[nx][ny]==0)
				break;
			loser.d = (loser.d+1)%4;
		}
		
		ground[winner.x][winner.y]=win;
		loser.x = nx; loser.y = ny;
		ground[nx][ny]=lose;
		
		
		// 가장 크기가 큰 총 GET
		if(!guns[nx][ny].isEmpty())
			loser.gun = guns[nx][ny].poll();
		
		/* 승자 */
			
		// 총 넣기
		nx = winner.x; ny = winner.y;
		if(winner.gun != 0) {
			guns[nx][ny].add(winner.gun);
			winner.gun = 0;
		}
		
		// 가장 크기가 큰 총 GET
		if(!guns[nx][ny].isEmpty())
			winner.gun = guns[nx][ny].poll();
	
	}
	
	public static void setMap() {
		ground = new int[N][N];
		for(int i=1; i<=M; i++) {
			Player p = players[i];
			ground[p.x][p.y]=i;
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
}
