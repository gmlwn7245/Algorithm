package CodeTree_SS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class tower_23a {
	public static class Top implements Comparable<Top>{
		// 좌표, 공격력
		int x, y;
		public Top(int x, int y) {
			this.x = x; this.y = y;
		}
		@Override
		public int compareTo(Top o) {
			if(aLevel[x][y] == aLevel[o.x][o.y]) {
				if(aTurn[x][y] == aTurn[o.x][o.y]) {
					if(x+y == o.x+o.y) 
						return o.y - y;
					return (o.x+o.y) - (x+y);
				}
				return aTurn[o.x][o.y] - aTurn[x][y];
			}
			return aLevel[x][y] - aLevel[o.x][o.y];
		}
	}
	
	/* 전역 변수 값들 */
	public static int N, M;
	public static int[][] aTurn; // 공격한 turn
	public static int[][] aLevel; // 공격력 (0인 경우 접근 X)
	public static ArrayList<Top> tops = new ArrayList<>(); // 포탑 모음
	public static Top attacker, defender;
	public static boolean[][] participated;
	public static boolean isAttacked = false;
	
	// 1. 공격자 선정
	public static void setAttacker(int k) {
		Collections.sort(tops);
		
		attacker = tops.get(0);
		defender = tops.get(tops.size()-1);
	}
	
	// 2. 공격자의 공격
	public static void doAttack() {
		
		// 공격력
		int dmg = aLevel[attacker.x][attacker.y]/2;
		
		// 참여한 노드
		participated = new boolean[N][M];
		
		// 레이저 공격 성공시
		if(laser()) {
			//System.out.println("LASER");
			
			// 역추적
			Queue<Top> q = new LinkedList<>();
			q.add(parent[defender.x][defender.y]);
			
			while(!q.isEmpty()) {
				Top now = q.poll();
				if(now.x == attacker.x && now.y == attacker.y) break;
				
				aLevel[now.x][now.y]-=dmg;
				if(aLevel[now.x][now.y]<0) aLevel[now.x][now.y]=0;
				
				participated[now.x][now.y]=true;
				q.add(parent[now.x][now.y]);
			}
			
			
			
		}else {
			
			//System.out.println("PORT");
			int[] dx = {0,1,0,-1,1,1,-1,-1};
			int[] dy = {1,0,-1,0,-1,1,-1,1};
			
			for(int i=0; i<8; i++) {
				int nx = (defender.x + dx[i]+N)%N;
				int ny = (defender.y + dy[i]+M)%M;
				
				// 막다른 길
				if(aLevel[nx][ny]==0) continue;
				if(nx == attacker.x && ny == attacker.y) continue;
				// if(participated[nx][ny]) continue;
				if(aLevel[nx][ny]<=dmg) aLevel[nx][ny]=0;
				else aLevel[nx][ny]-=dmg;
				
				participated[nx][ny]=true;
			}
		}
		
		participated[attacker.x][attacker.y]=participated[defender.x][defender.y]=true;
		
		aLevel[defender.x][defender.y] -= aLevel[attacker.x][attacker.y];
		if(aLevel[defender.x][defender.y] < 0) aLevel[defender.x][defender.y] = 0;
	}
	
	public static Top[][] parent;
	public static boolean laser() {
		// 우선순위
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0};
		
		parent = new Top[N][M];
		boolean visited[][] = new boolean[N][M];
		
		Queue<Top> q = new LinkedList<>();
		q.add(attacker);
		visited[attacker.x][attacker.y] = true;
		while(!q.isEmpty()) {
			Top now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = (now.x + dx[i]+N)%N;
				int ny = (now.y + dy[i]+M)%M;
				
				// 막다른 길
				if(aLevel[nx][ny]<=0) continue;
				// 이미 방문
				if(visited[nx][ny]) continue;
				
				// 부모 설정해줌
				parent[nx][ny] = new Top(now.x, now.y);
				visited[nx][ny]= true;
				
				// 도착지점인 경우
				if(defender.x == nx && defender.y == ny) {
					return true;
				}
				
				q.add(new Top(nx, ny));
			}
		}		
		
		return false;
	}
	
	// 3. 포탑 부서짐
	public static void removeTop() {
		for(int i=tops.size()-1; i>=0; i--) {
			Top now = tops.get(i);
			
			if(aLevel[now.x][now.y]<=0) {
				tops.remove(i);
			}
		}
	}
	
	// 4. 포탑 재정비
	public static void remindTop() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(aLevel[i][j]==0) continue;
				if(participated[i][j]) continue;
				
				aLevel[i][j]++;
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int K;
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	aTurn = new int[N][M];
    	aLevel = new int[N][M];
    	
    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<M; j++) {
    			int level = Integer.parseInt(st.nextToken());
    			aLevel[i][j] = level;
    			if(level != 0) tops.add(new Top(i,j));
    		}
    			
    	}
    	
    	for(int k=1; k<=K; k++) {
    		setAttacker(k);
    		
    		//System.out.println("Attacker : "+attacker.x+" "+attacker.y+" "+aLevel[attacker.x][attacker.y]);
    		//System.out.println("Defender : "+defender.x+" "+defender.y+" "+aLevel[defender.x][defender.y]);
    		
    		if(attacker.x == defender.x && attacker.y == defender.y) {
    			//aLevel[attacker.x][attacker.y]++;
    			break;
    		}
    		
    		// 공격력 증가
    		aLevel[attacker.x][attacker.y] += N+M;
    		aTurn[attacker.x][attacker.y] = k;
    		
    		doAttack();
    		removeTop();
    		remindTop();
    		// printMap(k);
    	}
    	
    	Collections.sort(tops);
		Top t = tops.get(tops.size()-1);
		
		System.out.println(aLevel[t.x][t.y]);
    }
    
    public static void printMap(int t) {
    	System.out.println("========="+t+"=PRTMAP");
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<M; j++) {
    			System.out.print(aLevel[i][j]+" ");
    		}
    		System.out.println();
    	}
    }
}
