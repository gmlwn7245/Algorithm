package BOJ_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj17135 {
	public static int N,M,D, anemy=0, max=0;
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1)
					anemy++;
			}
		}
		
		BFS(0,0,new int[3]);
		
		System.out.println(max);
	}
	
	public static int move(int[][] tmpMap, int anemy) {
		int tmp = anemy;
		// 마지막 줄 없앰
		for(int i=0; i<M; i++)
			if(tmpMap[N-1][i]==1)
				tmp--;
							
		// 앞으로 당기기
		for(int i=N-1; i>0; i--) {
			for(int j=0; j<M; j++) {
				tmpMap[i][j]=tmpMap[i-1][j];
			}
		}
		
		// 맨 윗줄
		for(int i=0; i<M; i++)
			tmpMap[0][i]=0;
		return tmp;
	}
	
	public static void BFS(int dep, int idx, int[] arch) {
		if(dep==3) {
			getCnt(arch);
			return ;
		}
		
		for(int i=idx; i<M; i++) {
			arch[dep]=i;
			BFS(dep+1, idx+1, arch);
		}
	}
	
	public static void getCnt(int[] arch) {
		int aCnt = anemy;
		int rCnt = 0;
		
		int[][] newMap = new int[N][M];
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++)
				newMap[i][j]=map[i][j];
		
		for(int n=0; n<N; n++) {
			if(aCnt <= 0)
				break;

			ArrayList<int[]> rem = new ArrayList<>();
			for(int i : arch) {
				boolean found = false;
				
				if(newMap[N-1][i]==1) {
					rem.add(new int[] {N-1, i});
					continue;
				}
				
				for(int j=1; j<D; j++) {
					int d = j*2+1;
					int x = N-1, y=i-j;
					
					while(d-- > 0) {
						if(isInArea(x,y) && newMap[x][y]==1) {
							rem.add(new int[] {x,y});
							found = true;
							break;
						}
						
						y++;
						int res = j-Math.abs(y-i);
						x = N-1-res;
					}
					if(found)
						break;
				}
			}
			
			// 발견한 적 지우기
			for(int[] p : rem) {
				if(newMap[p[0]][p[1]]==1) {
					rCnt++;
					newMap[p[0]][p[1]]=0;
				}
			}
			
			// 맵 이동
			aCnt = move(newMap, aCnt);
			
			//printMap(newMap);
		}
			
		
		max = Math.max(max,rCnt);
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
	
	public static void printMap(int[][] tmpMap) {
		System.out.println("=====PRINT");
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(tmpMap[i][j]+" ");
			}
			System.out.println();
		}
	}
}
