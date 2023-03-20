package BOJ_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj23290 {
	static class Fish {
		int x, y, dir;
		public Fish(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	public static int sharkX, sharkY, fishCnt=0, moveDic=555;
	public static int[] dx = {0,0,-1,-1,-1,0,1,1,1};
	public static int[] dy = {0,-1,-1,0,1,1,1,0,-1};
	public static HashSet<Fish> now = new HashSet<>();
	public static HashSet<Fish> copy = new HashSet<>();
	public static HashSet<Fish>[][] fishMap = new HashSet[5][5];
	public static ArrayList<String> sharkMove = new ArrayList<>();
	
	public static HashMap<Integer, boolean[][]> fishSmell = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			Fish fish = new Fish(x,y, dir);
			
			now.add(fish);
		}
		
		st = new StringTokenizer(br.readLine());
		sharkX = Integer.parseInt(st.nextToken());
		sharkY = Integer.parseInt(st.nextToken());
		
		
		for(int s=0; s<S; s++) {
			/* Map 초기화 */
			for(int i=1; i<=4; i++)
				for(int j=1; j<=4; j++)
					fishMap[i][j]= new HashSet<Fish>();
			
			/* 물고기 복사 */
			for(Fish f : now)
				copy.add(new Fish(f.x, f.y, f.dir));
			
			/* 물고기 이동 */
			moveFish(s);
			
			/* 물고기 Map에 추가 */
			for(Fish f: now)
				fishMap[f.x][f.y].add(f);
			
			/* 상어 이동 */
			moveShark(0, sharkX, sharkY, "", new ArrayList<>());
			
			/* 이동한 곳 물고기 제거 및 냄새 넣기*/
			boolean[][] smells = new boolean[5][5];
			for(String point : sharkMove) {
				int x = point.charAt(0)-'0';
				int y = point.charAt(2)-'0';
				smells[x][y]=true;
			
				for(Fish f : fishMap[x][y]) {
					now.remove(f);
				}
					
				fishMap[x][y].clear();
			}
			
			fishSmell.put(s, smells);
			
			
			/* 두 턴 전 냄새 삭제 */
			if(s > 2)
				fishSmell.remove(s-2);
			
			/* 물고기 복제 */
			now.addAll(copy);
			copy.clear();
		}
		
		System.out.println(now.size());
		
	}
	
	public static void moveShark(int cnt, int x, int y, String move, ArrayList<int[]> maps) {
		if(cnt == 3) {
			int fishCnt2 = 0;
			for(int i=0; i<3; i++) {
				int[] step = maps.get(i);
				fishCnt2 += fishMap[step[0]][step[1]].size();
			}
			
			if(fishCnt < fishCnt2 || (fishCnt == fishCnt2 && moveDic > Integer.parseInt(move))) {
				sharkX = x; sharkY = y;
				moveDic = Integer.parseInt(move);
				sharkMove.clear();
				for(int[] p : maps)
					sharkMove.add(p[0]+" "+p[1]);
			}
			return ;
		}
		
		// 상좌하우
		int[] dx = {0, -1, 0, 1, 0};
		int[] dy = {0, 0, -1, 0, 1};
		
		for(int i=1; i<=4; i++) {
			int nextX = x + dx[i], nextY = y + dy[i];
			
			if(!isInArea(nextX, nextY))
				continue;
			
			move += i;
			maps.add(new int[] {nextX, nextY});
			
			moveShark(cnt+1, nextX, nextY, move, maps);
			
			maps.remove(cnt);
			move.substring(0, move.length()-1);
		}
	}
	
	public static void moveFish(int turn) {
		for(Fish f : now) {
			for(int i=0; i<8; i++) {
				if(canMove(turn, f))
					break;
				f.dir = getDir(f.dir);
			}
		}
	}
	
	public static boolean canMove(int turn, Fish f) {
		int nowDir = f.dir;
		int nextX = f.x + dx[nowDir], nextY = f.y + dy[nowDir];
		
		if(!isInArea(nextX, nextY))
			return false;
		
		if(sharkX==nextX && sharkY == nextY)
			return false;
		
		if(turn > 1 && fishSmell.get(turn-1)[nextX][nextY])
			return false;
		
		if(turn > 2 && fishSmell.get(turn-2)[nextX][nextY])
			return false;
		
		f.x = nextX;
		f.y = nextY;
		
		return true;
	}
	
	public static int getDir(int dir) {
		if(dir == 1)
			return 8;
		return dir-1;
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=1 && x<=4 && y>=1 && y<=4;
	}
}
