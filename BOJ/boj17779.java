package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj17779 {
	public static int N,ans=Integer.MAX_VALUE;
	public static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(int d1 = 1; d1<=j; d1++) {
					for(int d2 = 1; j+d2<N; d2++) {
						if(i+d1+d2+1 >= N)
							break;
						makeRegion(i,j,d1,d2);
					}
				}
			}
		}
	
		System.out.println(ans);
		
	}
	
	public static void makeRegion(int x, int y, int d1, int d2) {
		int[][] region = new int[N][N];
		int h = d1+d2-1;
		
		
		
		// xy 윗부분
		for(int i=0; i<x; i++) {
			int num = 1;
			for(int j=0; j<N; j++) {
				region[i][j]=num;
				if(j==y)
					num = 2;
			}
		}
		
		
		// 5구역 경계 및 바깥
		region[x][y]=5;

		// 1구간
		for(int i=0; i<d1; i++) {
			region[x+i][y-i]=5;
			for(int j=0; j<y-i; j++)
				region[x+i][j]=1;
		}
			
		// 2구간
		for(int i=0; i<=d2; i++) {
			region[x+i][y+i]=5;
			for(int j=N-1; j>y+i; j--)
				region[x+i][j]=2;
		}
			
		// 3구간
		for(int i=0; i<=d2; i++) {
			region[x+d1+i][y-d1+i]=5;
			for(int j=0; j<y-d1+i; j++)
				region[x+d1+i][j]=3;
		}
			
		
		// 4구간
		for(int i=1; i<=d1; i++) {
			region[x+d2+i][y+d2-i]=5;
			for(int j=N-1; j>y+d2-i; j--)
				region[x+d2+i][j]=4;
		}
		
		int ly = y-d1+d2, lx = x+d1+d2;
		// 바깥쪽
		for(int i=lx+1; i<N; i++) {
			int num = 4;
			for(int j=N-1; j>=0; j--) {
				region[i][j]=num;
				if(j==ly)
					num = 3;
			}
		}
		
		int[] cnt = new int[6];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int r = region[i][j];
				if(r!=0)
					cnt[r] += map[i][j];
				else
					cnt[5] += map[i][j];
			}
		}
		
		
		
		int min = cnt[1], max = cnt[1];
		for(int i=1; i<=5; i++) {
			min = Math.min(min, cnt[i]);
			max = Math.max(max, cnt[i]);
		}
		
		//printMap(region);
		//System.out.println("Diff => "+(max-min));
		
		ans = Math.min(ans, max-min);

	}
	
	public static void printMap(int[][] maps) {
		System.out.println("====PRINT");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(maps[i][j]+" ");
			}
			System.out.println();
		}
	}
}
