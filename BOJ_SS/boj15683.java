package BOJ_SS;

import java.util.*;

public class boj15683 {
	public static int n, m, min, cnt=0;
	public static int[] dirs = {0,1,2,3,4,1,2}; //»ó¿ìÇÏÁÂ
	public static int[][] map;
	public static ArrayList<int[]> cctv = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 0 ºóÄ­ 
		// 6 º®
		// 1~5 CCTV ¹øÈ£ À§Ä¡
		
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		min = n * m;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				int num = sc.nextInt();
				if(num == 6) {
					map[i][j]=6;
				}else if(num!=0) {
					cctv.add(new int[] {i,j,num,0});
					cnt++;
				}
			}
		}		
		
		dfs(0);
		System.out.println(min);
	}
	
	public static void dfs(int depth) {
		if(depth == cnt) {
			int blind = cntBlind();
			if(blind < min) {
//				System.out.println("==========");
//				showMap();
//				System.out.println("¹æÇâ==>"+cctv.get(1)[3]);
				min = blind;
			}
			resetMap();
			return ;
		}
		
		int[] cctvDetail = cctv.get(depth);
		int cctvNum = cctvDetail[2]; 
		
		if(cctvNum==6)
			dfs(depth+1);
		else if(cctvNum==2) {
			cctvDetail[3] = 1;
			dfs(depth+1);
			cctvDetail[3] = 2;
			dfs(depth+1);
		}else {
			for(int i=1; i<=4; i++) {
				cctvDetail[3] = i;
				dfs(depth+1);
			}
		}
	}
	
	public static int cntBlind() {
		int visible = 0;
		for(int i=0; i<cnt; i++) {
			int[] cctvDetail = cctv.get(i);
			int x = cctvDetail[0];
			int y = cctvDetail[1];
			int num = cctvDetail[2];
			int dir = cctvDetail[3];
			
			switch(num) {
			case 1:{
				makeRoad(x,y,dir);
				break;
			}
			case 2:{
				makeRoad(x,y,dir);
				makeRoad(x,y,dir+2);
				break;
			}
			case 3:{
				makeRoad(x,y,dir);
				makeRoad(x,y,dirs[dir+1]);
				break;
			}
			case 4:{
				makeRoad(x,y,dir);
				makeRoad(x,y,dirs[dir+1]);
				makeRoad(x,y,dirs[dir+2]);
				break;
			}
			case 5:{
				makeRoad(x,y,1);
				makeRoad(x,y,2);
				makeRoad(x,y,3);
				makeRoad(x,y,4);
			}
			}
			
		}
		
		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				if(map[i][j]!=0)
					visible++;
		
		
		
		return n*m - visible;
	}
	
	public static void makeRoad(int x, int y, int dir) {
		switch(dir) {
		case 1:{ // »ó
			for(int i=x; i>=0; i--){
				if(map[i][y]==6)
					break;
				map[i][y] = -1;
			}
			break;
		}
		case 2:{  // ¿ì 
			for(int i=y; i<m; i++) {
				if(map[x][i]==6)
					break;
				map[x][i] = -1;
			}
			break;
		}
		case 3:{  // ÇÏ
			for(int i=x; i<n; i++) {
				if(map[i][y]==6)
					break;
				map[i][y] = -1;
			}
			break;
		}default:{  // ÁÂ
			for(int i=y; i>=0; i--) {
				if(map[x][i]==6)
					break;
				map[x][i] = -1;
			}
			break;
		}
		}
	}
	
	public static void resetMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++)
				if(map[i][j]!=6)
					map[i][j]=0;
		}
	}
	
	public static void showMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]==-1)
					System.out.print("# ");
				else
					System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
