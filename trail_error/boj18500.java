package trail_error;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/*
 * �������� �̳׶� ���� �κи� �����
 * �� �κ��� ������� ���� �Ф�
 */
class Node {
	public int x, y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class boj18500 {
	private static int r,c,n,fallMinLev;
	private static int[][] map;
	private static boolean[][] nodeMap;
	private static int[] dx = {-1,0,0};
	private static int[] dy = {0,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		 
		r = sc.nextInt();
		c = sc.nextInt();
		map = new int[r][c];
		
		for(int i=0; i<r; i++) {
			String str= sc.next();
			for(int j=0; j<c; j++) {
				char ch = str.charAt(j);
				System.out.print(ch);
				if(ch=='.')
					continue;
				else
					map[i][j]=1;
				
			}
			System.out.println();
		}
		
		n = sc.nextInt();
		
		for(int i=0; i<n; i++) {
			int h = sc.nextInt();
			System.out.println(r-h);
			deleteM(i%2, r-h);
			printMap();
		}
	}
	
	public static void printMap() {
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				char ch = map[i][j]==1? 'x':'.';
				System.out.print(ch+" ");
			}
			System.out.println();
		}
	}
	
	// �̳׶� ����
	// (r,c)
	public static void deleteM(int dir, int h) {
		boolean mineralExisted = false;
		int minX=-1, minY=-1;
		
		// 0 ���� 1 ������
		if(dir == 0) {
			for(int i=0; i<c; i++) {
				if(map[h][i]==1) {
					mineralExisted = true;
					map[h][i] = 0;
					minX = h; minY = i;
					break;
				}
			}
		}else if(dir == 1) {
			for(int i=c-1; i>=0; i--) {
				if(map[h][i]==1) {
					mineralExisted = true;
					map[h][i] = 0;
					minX = h; minY = i;
					break;
				}
			}
		}
		
		// �� �� �� or �̳׶��� ���� ���
		if(h==0 || !mineralExisted)
			return ;
		
		
		// �̳׶��� �������� �ϴ��� �˻�
		if(!canFall(minX, minY))
			return ;
		
		ArrayList<Node> arr = getNodes(minX-1, minY);
		getMinLength(arr);
		moveNode(arr);
	}
	
	public static void moveNode(ArrayList<Node> arr) {
		for(Node n : arr) {
			System.out.println(n.x+fallMinLev + " ," + n.y);
			map[n.x + fallMinLev][n.y] = 1;
		}
	}
	
	public static void getMinLength(ArrayList<Node> arr) {
		fallMinLev = r;
		int lev = 0;
		for(Node n : arr) {
			for(int i=n.x+1;i<r; i++) {
				if(nodeMap[i][n.y])
					break;
				
				if(map[i][n.y]==0)
					lev++;
				else if(lev < fallMinLev) {
					fallMinLev = lev;
					break;
				}
			}
		}
	}
	
	// BFS ������� Ž�� && ������ x,y
	public static ArrayList<Node> getNodes(int x, int y) {
		ArrayList<Node> arr = new ArrayList<>();
		nodeMap = new boolean[r][c];
		
		Queue<int[]> nodes = new LinkedList<>();
		nodes.add(new int[] {x, y});
		arr.add(new Node(x,y));
		
		while(!nodes.isEmpty()) {
			int[] now = nodes.poll();
			
			for(int i=0; i<3; i++) {
				int nextX = now[0] + dx[i];
				int nextY = now[1] + dy[i];
				
				if(!isInArea(nextX, nextY))
					continue;
				
				if(map[nextX][nextY]==0)
					continue;
				
				arr.add(new Node(nextX, nextY));
				nodes.add(new int[] {nextX, nextY});
				map[nextX][nextY] = 0;
				nodeMap[nextX][nextY]=true;
			}
		}
		
		return arr;
	}
	
	
	// �������� �ϴ� ����
	public static int fallLevel(int x, int y) {
		int lev = 0;
		
		for(int i=x-1; i<r; i++) {
			if(map[i][y]==0)
				lev++;
			else
				break;
		}
		
		return lev;
	}
	
	
	public static boolean canFall(int x, int y) {		
		if(map[x-1][y]==0)
			return false;
		
		// ����
		for(int i=y-1; i>=0; i--) {
			if(map[x-1][i]==0) {
				continue;
			}
			if(map[x-1][i]==1 && map[x][i]==1)
				return false;
			
			
			
		}
		
		// ������
		for(int i=y+1; i<c; i++) {
			if(map[x-1][i]==0){
				continue;
			}
			if(map[x-1][i]==1 && map[x][i]==1)
				return false;			
		}
		
		return true;
	}

	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<r && y>=0 && y<c;
	}
}
