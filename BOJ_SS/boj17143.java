package BOJ_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj17143 {
	static class Shark implements Comparable<Shark> {
		int r, c, s, d, z;
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r; this.c = c; this.s = s; this.d = d; this.z = z;
		}

		@Override
		public int compareTo(Shark o) {
			return o.z - this.z;
		}
	}
	
	public static int R,C,M;
	public static HashSet<Shark> hs = new HashSet<>();
	public static int[] dx = {0,-1,1,0,0};
	public static int[] dy = {0,0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Shark[][] shark = new Shark[R][C];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			Shark sh = new Shark(r,c,s,d,z);
			hs.add(sh);
			shark[r][c]=sh;
		}
		
		int totSize = 0;
		for(int i=0; i<C; i++) {
			for(int j=0; j<R; j++) {
				if(shark[j][i]!=null) {
					Shark sh = shark[j][i];
					hs.remove(sh);
					totSize += sh.z;
					shark[j][i]=null;
					break;
				}
			}
			
			//printMap(shark, "Turn "+(i+1)+"  --bef");
			
			shark = new Shark[R][C];
			HashSet<Shark> tmp = new HashSet<>();
			
			for(Shark sh : hs) {
				Shark s = moveShark(sh);
				int r = s.r, c = s.c;
				// 비어있지 않으면
				if(shark[r][c]!=null) {
					// 크기가 더 작을 경우
					if(shark[r][c].z < s.z) {
						tmp.remove(shark[r][c]);
						shark[r][c] = s;
						tmp.add(s);
					}
				}else {		
					// 비어있을 경우
					shark[r][c] = s;
					tmp.add(s);
				}
			}
			
			hs.clear();
			//printMap(shark, "Turn "+(i+1)+"  --aft");
			
			hs.addAll(tmp);
			//System.out.println(hs.size());
		}
		System.out.println(totSize);
	}
	
	public static Shark moveShark(Shark sh) {
		// 1위 2아래 3오 4왼
		int r=sh.r, c=sh.c, s = sh.s, d = sh.d, z = sh.z;
		
		if(d < 3){
			s %= (R-1)*2;
			while(s-- > 0) {
				if(r == 0 && d == 1)
					d = 2;
				if(r == R-1 && d == 2)
					d = 1;
				r += dx[d];
			}
		}else {
			s %= (C-1)*2;	
			while(s-- > 0) {
				if(c == 0 && d == 4)
					d = 3;
				if(c == C-1 && d == 3)
					d = 4;
				c += dy[d];
			}
		}
		
		return new Shark(r,c,sh.s,d,z);
	}
	
	public static void printMap(Shark[][] shark, String msg) {
		System.out.println("========"+msg);
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(shark[i][j]==null)
					System.out.print("0 ");
				else
					System.out.print(shark[i][j].z+" ");
			}
			System.out.println();
		}
	}
	
}
