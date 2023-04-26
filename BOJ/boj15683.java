package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj15683 {
	static class CCTV {
		int x, y, num;
		public CCTV(int x, int y, int num) {
			this.x = x; this.y = y; this.num = num;
		}
	}
	
	public static int N, M, K=0, ans=0;
	public static int[][] map;
	public static ArrayList<CCTV> cctv = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		ans = N * M;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n == 0 || n == 6) {
					map[i][j]=n;
					continue;
				}
				map[i][j]=-1;
				cctv.add(new CCTV(i,j,n));
				K++;
			}
		}
		
		dfs(0, new ArrayList<>());
		
		System.out.println(ans);
		
	}
	
	public static void dfs(int dep, ArrayList<Integer> dirs) {
		if(dep == K) {
			boolean[][] tmp = new boolean[N][M];
			
			for(int i=0; i<K; i++) {
				int cctvNum = cctv.get(i).num;
				int dir = dirs.get(i);
				getCnt(dir, i, tmp);
				
				if(cctvNum == 2) {
					getCnt(dir+2, i, tmp);
				}else if(cctvNum == 3) {
					getCnt((dir+1)%4, i, tmp);
				}else if(cctvNum == 4) {
					getCnt((dir+1)%4, i, tmp);
					getCnt((dir+2)%4, i, tmp);
				}else if(cctvNum == 5){
					for(int j=1; j<=3; j++)
						getCnt(dir+j, i, tmp);
				}
			}
			
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(!tmp[i][j] && map[i][j]==0)
						cnt++;
				}
			}
			
			ans = Math.min(ans, cnt);
			
			return ;
		}
		
		int cctvNum = cctv.get(dep).num;
		
		if(cctvNum == 2) {
			dirs.add(0);
			dfs(dep+1, dirs);
			dirs.remove(dirs.size()-1);
			
			dirs.add(1);
			dfs(dep+1, dirs);
			dirs.remove(dirs.size()-1);
		}else if(cctvNum == 5) {
			dirs.add(0);
			dfs(dep+1, dirs);
			dirs.remove(dirs.size()-1);
		}else {
			for(int i=0; i<4; i++) {
				dirs.add(i);
				dfs(dep+1, dirs);
				dirs.remove(dirs.size()-1);
			}
		}
	}
	
	public static void getCnt(int dir, int cctvNum, boolean[][] tmp) {
		CCTV now = cctv.get(cctvNum);
		
		int x = now.x, y = now.y;
		
		if(dir == 0) {
			while(--x >=0 && map[x][y]!= 6) {
				tmp[x][y]=true;
			}
		}else if(dir == 1) {
			while(++y < M && map[x][y]!= 6) {
				tmp[x][y]=true;
			}
		}else if(dir == 2) {
			while(++x < N && map[x][y]!= 6) {
				tmp[x][y]=true;
			}
		}else {
			while(--y >=0 && map[x][y]!= 6) {
				tmp[x][y]=true;
			}
		}
	}
}
