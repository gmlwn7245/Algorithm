package BOJ_SS;

import java.util.*;

public class boj16236 {
	private static boolean isRoad;
	private static int n , minDist, minX, minY;
	private static int[] dx = {0,-1,0,1};
	private static int[] dy = {-1,0,1,0};
	private static int[][] map;
	private static Node shark;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int num = sc.nextInt();
				if(num == 9) {
					shark = new Node(i,j);
					continue;
				}
				map[i][j]=num;
			}
		}
		
		int eatCnt = 0;
		int time = 0;
		while(true) {
			// 초기 설정
			minDist = Integer.MAX_VALUE;
			minX = -1;
			minY = -1;
			
			// 먹이 탐색
			bfs();
			
			// 먹을 수 있는 먹이가 없음
			if(minX == -1 && minY == -1)
				break;
			
			// 위치 값 갱신
			shark.x = minX;
			shark.y = minY;
			time += minDist;
			map[minX][minY] = 0;
			
			// 먹은 물고기 수 갱신
			eatCnt++;
			
			// 먹은 물고기 수 == 상어 크기
			if(eatCnt == shark.size) {
				shark.size ++;
				eatCnt = 0;
			}
		}
		System.out.println(time);
	}
	
	// 현재 좌표에서 가장 먼저 먹을 먹이 탐색
	public static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(shark.x, shark.y));
		
		int[][] dist = new int[n][n];
		
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				
				if(!isInArea(nextX, nextY) || !isAbleToMove(nextX, nextY) || dist[nextX][nextY]!=0)
					continue;
				
				int nextDist = dist[now.x][now.y] + 1;
				dist[nextX][nextY] = nextDist;
				
				
				// 먹을 수 있을 때
				if(isEatable(nextX, nextY)) {
					if(nextDist > minDist)
						continue;
					
					// 최단거리일때
					if(nextDist == minDist) {
						// Y가 더 작으면 최단 좌표 바꾸기
						
						if(minX > nextX) {
							minX = nextX;
							minY = nextY; 
						} else if(minX == nextX && nextY < minY) {
							minX = nextX;
							minY = nextY;
						}
					}
					
					// 최단거리보다 작을 때
					else if(nextDist < minDist) {
						minX = nextX;
						minY = nextY;
						minDist = nextDist;
					}
				}
				queue.add(new Node(nextX, nextY));
			}
		}
	}
	
	// 범위 내에 있는 좌표
	public static boolean isInArea(int x, int y) {
		return x >=0 && y >= 0 && x < n && y < n;
	}
	
	// 지나갈 수 있는 좌표
	public static boolean isAbleToMove(int x, int y) {
		return map[x][y] <= shark.size;
	}
	
	// 먹을 수 있는 좌표
	public static boolean isEatable(int x, int y) {
		return map[x][y] != 0 && map[x][y] < shark.size;
	}
	
}



class Node {
	public int x, y, size = 2;
	
	public Node (int x, int y){
		this.x = x;
		this.y = y;
	}
}
