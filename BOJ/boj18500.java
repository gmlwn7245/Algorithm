package BOJ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
	public int x, y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class boj18500 {
	private static int r,c,n;
	private static boolean check=false;
	private static int[][] map;
	private static boolean[][] bottomMap, flyMap;
	private static int[] dx = {1,-1,0,0};
	private static int[] dy = {0,0,1,-1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		 
		r = sc.nextInt();
		c = sc.nextInt();
		map = new int[r][c];
		
		for(int i=0; i<r; i++) {
			String str= sc.next();
			for(int j=0; j<c; j++) {
				char ch = str.charAt(j);
				if(ch=='.')
					continue;
				else
					map[i][j]=1;
			}
		}
		
		n = sc.nextInt();
		
		
		for(int i=0; i<n; i++) {
			int h = sc.nextInt();
				
			deleteM(i%2, r-h);
			findBottomCluster();
			ArrayList<Node> arr = findFlyCluster();
			
			if(arr.size() ==0) 
				continue;
			
			findMinDiff(arr);
		}
		
		printMap();
	}
	
	
	// ÃÖ¼Ò ³»¸²°ª Ã£¾Æ¼­ ³»¸®±â
	public static void findMinDiff(ArrayList<Node> arr) {
		int minDiff = r;
		
		for(Node n : arr) {
			int x = n.x;
			int y = n.y;
			
			
			// Å¬·¯½ºÅÍ ¹Ø ºÎºÐÀÌ ¾Æ´Ò°æ¿ì
			if(flyMap[x+1][y])
				continue;
			
			int lev = 0;
			for(int i=x+1; i<r; i++) {				
				if(flyMap[i][y])
					break;
				
				if(map[i][y]==0)
					lev++;
				
				if(bottomMap[i][y] || i==r-1) {
					if(minDiff > lev)
						minDiff = lev;
					break;
				}
			}
		}
		
		for(Node n : arr) {
			map[n.x+minDiff][n.y]=1;
		}
	}
	
	// °øÁß¿¡ ¶°ÀÖ´Â Å¬·¯½ºÅÍ Ã£±â
	public static ArrayList<Node> findFlyCluster() {
		ArrayList<Node> arr = new ArrayList<>();
		
		for(int i=0; i<r-1; i++) {
			for(int j=0; j<c; j++) {
				if(map[i][j]==0)
					continue;
				
				if(bottomMap[i][j])
					continue;
				
				arr = getFlyCluster(i,j);
				
				return arr;
			}
		}
		
		return arr;
	}
	
	public static ArrayList<Node> getFlyCluster(int x, int y){
		ArrayList<Node> arr = new ArrayList<>();
		flyMap = new boolean[r][c];
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(x,y));
		arr.add(new Node(x,y));
		map[x][y]=0;
		flyMap[x][y]= true;
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			int nowX = n.x;
			int nowY = n.y;
			
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// ¹üÀ§ ¹Û
				if(!isInArea(nextX, nextY))
					continue;
				
				// ºóÄ­
				if(map[nextX][nextY]==0)
					continue;
				
				// ÀÌ¹Ì Å½»ö
				if(flyMap[nextX][nextY])
					continue;
				
				queue.add(new Node(nextX, nextY));
				arr.add(new Node(nextX, nextY));
				flyMap[nextX][nextY]= true;
				map[nextX][nextY]=0;
			}
		}
		
		return arr;
	}
	
	
	// ¹Ù´ÚÀÌ¶û ¿¬°áµÈ ¹Ì³×¶ö Å¬·¯½ºÅÍ Ã£±â
	public static void findBottomCluster() {
		bottomMap = new boolean[r][c];
		
		for(int i=0; i<c; i++) {
			if(map[r-1][i]==0)
				continue;
			
			if(bottomMap[r-1][i])
				continue;
			
			checkBottomCluster(r-1, i);
		}
	}
	
	// ¹Ù´Ú ¹Ì³×¶ö BFS Å½»ö
	public static void checkBottomCluster(int x, int y) {
		Queue<Node> bottom = new LinkedList<>();
		bottom.add(new Node(x,y));
		bottomMap[x][y]=true;
		
		while(!bottom.isEmpty()) {
			Node n = bottom.poll();
			
			int nowX = n.x;
			int nowY = n.y;
			
			for(int i=0; i<4; i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				
				// ¹üÀ§ ¹Û
				if(!isInArea(nextX, nextY)) 
					continue;
				
				// ºóÄ­
				if(map[nextX][nextY]==0) 
					continue;
					
				// ÀÌ¹Ì Å½»ö
				if(bottomMap[nextX][nextY]) 
					continue;
				
				bottomMap[nextX][nextY] = true;
				bottom.add(new Node(nextX, nextY));
			}
		}
	}
	
	// ¹Ì³×¶öÁö¿ì±â
	public static void deleteM(int dir, int h) {
		// ¿ÞÂÊ Â÷·Ê
		if(dir == 0) {
			for(int i=0; i<c; i++) {
				if(map[h][i]==1) {
					map[h][i]=0;
					return ;
				}
			}
		}else {
			for(int i=c-1; i>=0; i--) {
				if(map[h][i]==1) {
					map[h][i]=0;
					return ;
				}
			}
		}
		
	}
	
	public static void printMap() {
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				char ch = map[i][j]==1? 'x':'.';
				System.out.print(ch);
			}
			System.out.println();
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<r && y>=0 && y<c;
	}
}
