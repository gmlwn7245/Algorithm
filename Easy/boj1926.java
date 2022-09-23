package Easy;

import java.io.IOException;
import java.util.*;

public class boj1926 {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static boolean[][] visited;
	static int n,m,cnt=0,max=0;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		visited = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++){
				int num = sc.nextInt();
				if(num == 0)
					visited[i][j] = true;
			}
		}
		sc.close();
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++){
				if(!visited[i][j]) {
					bfs(i,j);
					
				}
			}
		}
		
		System.out.println(cnt);
		System.out.println(max);
	}
	
	public static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {x,y});
		int area=0;
			
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			int currentX = point[0];
			int currentY = point[1];
			
			if(visited[currentX][currentY])
				continue;
			
			visited[currentX][currentY] = true;
			
			for(int i=0; i<4; i++) {
				int nextX = currentX + dx[i];
				int nextY = currentY + dy[i];
				
				if(nextX<0 || nextX>=n || nextY<0 || nextY>=m)
					continue;
				
				if(visited[nextX][nextY])
					continue;
				
				queue.add(new int[] {nextX, nextY});
					
				
			}
			area++;
		}
		
		if(area > max)
			max = area;
		
		cnt++;
	}
}
