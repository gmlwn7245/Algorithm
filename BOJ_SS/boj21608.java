package BOJ_SS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj21608 {
	private static int[] dx = {-1,0,0,1};
	private static int[] dy = {0,-1,1,0};
	private static int N, sCnt;
	private static int[][] map, want;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		sCnt = N * N;
		
		map = new int[N][N];
		want = new int[sCnt+1][4];
		
		HashMap<Integer, int[]> hm = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		for(int i=0; i<4; i++) {
			want[num][i] = Integer.parseInt(st.nextToken());
		}
		
		map[1][1] = num;
		hm.put(num, new int[] {1,1});
		
		for(int i=1; i<sCnt; i++) {
			String str = br.readLine();
			if(str.equals("")) {
				i--;
				continue;
			}
			st = new StringTokenizer(str);
			num = Integer.parseInt(st.nextToken());
			
			ArrayList<int[]> sArr = new ArrayList<>();
			for(int j=0; j<4; j++) {
				int w = Integer.parseInt(st.nextToken());
				want[num][j]=w;
				
				int[] fav = hm.getOrDefault(w, new int[] {-1,-1,});
				if(fav[0]==-1)
					continue;
				
				sArr.add(fav);
			}
			
			if(sArr.size()==0) {
				int[] sit = findMaxBlank();
				map[sit[0]][sit[1]] = num;
				hm.put(num, new int[] {sit[0],sit[1]});
				continue;
			}
			
			Collections.sort(sArr, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[0]==o2[0])
						return o1[1]-o2[1];
					return o1[0]-o2[0];
				}
			});
			
			int ax=sCnt,ay=sCnt;
			int fCnt = -1;
			int bCnt = -1;
			
			// sArr : 친구 좌표
			for(int[] n : sArr) {
				
				// 친구의 주변 좌표 (num의 자리 후보)
				for(int k=0; k<4; k++) {
					int nx = n[0]+dx[k];
					int ny = n[1]+dy[k];
					
					if(!isInArea(nx, ny))
						continue;
					
					if(map[nx][ny]!=0)
						continue;
					
					int bbCnt = nearbyBlank(nx, ny);
					int ffCnt = nearbyFriend(nx, ny, num);
										
					if(ffCnt > fCnt || (ffCnt == fCnt) && (bbCnt > bCnt)) {
						ax = nx;
						ay = ny;
						fCnt = ffCnt;
						bCnt = bbCnt;
					}else if(ffCnt==fCnt && bbCnt == bCnt) {
						if(ax==nx && ny < ay || nx < ax) {
							ax = nx;
							ay = ny;
							fCnt = ffCnt;
							bCnt = bbCnt;
						}
					}
				}
			}
			if(ax==sCnt && ay==sCnt) {
				int[] sit = findMaxBlank();
				
				map[sit[0]][sit[1]] = num;
				hm.put(num, new int[] {sit[0],sit[1]});
				continue;
			}
			
			map[ax][ay]=num;
			hm.put(num, new int[] {ax, ay});
			
		}
		
		//printMap();
		
		bw.write(sumAll()+"");
		bw.flush();
		bw.close();
	}
	
	public static int sumAll() {
		HashMap<Integer, Integer> ans = new HashMap<>();
		ans.put(0, 0);
		ans.put(1, 1);
		ans.put(2, 10);
		ans.put(3, 100);
		ans.put(4, 1000);
		
		int sum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int cnt = nearbyFriend(i,j, map[i][j]);
				sum += ans.get(cnt);
			}
		}
		return sum;
	}
	
	public static int nearbyFriend(int x, int y, int num) {
		int fCnt = 0;
		//매개변수 x,y는 num이 들어갈 자리 후보 중 하나
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!isInArea(nx,ny))
				continue;
			
			for(int k=0; k<4; k++) {
				if(want[num][k]==map[nx][ny]) {
					fCnt++;
				}
			}
		}
		
		return fCnt;
	}
	
	public static int nearbyBlank(int x, int y) {
		int cnt = 0;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!isInArea(nx,ny))
				continue;
			
			if(map[nx][ny]!=0)
				continue;
			
			cnt++;
		}
		return cnt;
	}
	
	public static int[] findMaxBlank() {
		int[] res = new int[2];
		int maxBlank = -1;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]!=0)
					continue;
				
				int cnt = nearbyBlank(i,j);
				
				if(cnt == 4)
					return new int[] {i,j};
				
				if(cnt > maxBlank) {
					res[0] = i;
					res[1] = j;
					maxBlank = cnt;
				}
			}
		}
		
		return res;
	}
	
	public static boolean isInArea(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	
	public static void printMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
