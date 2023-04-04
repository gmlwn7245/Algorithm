package BOJ_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj17406 {
	public static int N, M, K,ans = Integer.MAX_VALUE;;
	public static int[][] map, tmp;
	public static int[][] order;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		order = new int[K][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			
			order[i] = new int[] {r,c,s};
		}
		
		dfs(0, new int[K], new HashSet<Integer>());
		
		System.out.println(ans);
	}
	
	public static void dfs(int dep, int[] ord, HashSet<Integer> hs) {
		if(dep == K) {
			//System.out.println(ord[0]+" "+ord[1]);
			rot(ord);
			return ;
		}
		
		for(int i=0; i<K; i++) {
			if(hs.contains(i))
				continue;
			
			hs.add(i);
			ord[dep]=i;
			dfs(dep+1, ord, hs);
			hs.remove(i);
		}
	}
	
	public static void copyMap(){
		tmp = new int[N][M];
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				tmp[i][j]=map[i][j];
	}
	
	public static void rot(int[] ord) {
		copyMap();
		
		
		for(int i : ord) {
			int[] now = order[i];
			
			int r = now[0];
			int c = now[1];
			int s = now[2];
			//System.out.println(r+" "+c+" "+s);
			
			for(int j=1; j<=s; j++) {
				rotMap(r-j, c-j, j*2);
			}
		}
		
		for(int i=0; i<N; i++) {
			int s = 0;
			for(int j=0; j<M; j++) {
				s+= tmp[i][j];
			}
			
			ans = Math.min(ans, s);
		}
	}
	
	public static void printMap() {
		System.out.println("++++PRINT");
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void rotMap(int r, int c, int s) {
		int prev = tmp[r][c];
		tmp[r][c]=tmp[r+1][c];
		// 오른쪽
		for(int i=0; i<s; i++) {
			c++;
			int temp = tmp[r][c];
			tmp[r][c] = prev;
			prev = temp;
		}
		
		// 아래
		for(int i=0; i<s; i++) {
			r++;
			int temp = tmp[r][c];
			tmp[r][c] = prev;
			prev = temp;
		}
		
		// 왼쪽
		for(int i=0; i<s; i++) {
			c--;
			int temp = tmp[r][c];
			tmp[r][c] = prev;
			prev = temp;
		}
		
		// 위쪽
		for(int i=0; i<s; i++) {
			r--;
			int temp = tmp[r][c];
			tmp[r][c] = prev;
			prev = temp;
		}
	}
}
