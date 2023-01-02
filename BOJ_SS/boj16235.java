package BOJ_SS;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj16235 {
	static class Tree implements Comparable<Tree>{
		int r, c;
		int age;
		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
		
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}		
	}
	private static int N, M, K;
	private static int[][] map, A;	// ¾çºÐ Map
	private static Deque<Tree> trees = new LinkedList<>();
	private static Deque<Tree> temp = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		
		map = new int[N+1][N+1];
		A = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = 5;
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			trees.add(new Tree(r,c,age));
		}
		
		
		for(int i=0; i<K; i++) {
			getYear();
		}
		
		bw.write(trees.size()+"");
		bw.flush();
		bw.close();
	}
	
	public static void getYear() {
		Deque<Tree> dead = new LinkedList<>();
		temp.clear();
		//Spring
		while(!trees.isEmpty()) {
			Tree t = trees.poll();
			
			if(map[t.r][t.c]<t.age) {
				dead.add(t);
				continue;
			}
			
			map[t.r][t.c]-=t.age;
			t.age += 1;
			temp.add(t);
		}
		
		// Summer
		while(!dead.isEmpty()) {
			Tree t = dead.poll();
			int nut = t.age / 2;
			map[t.r][t.c] += nut;
		}
		
		// Autumn
		for(Tree t : temp) {
			if(t.age % 5==0)
				makeTree(t.r, t.c);
		}
		
		trees.addAll(temp);

		// Winter
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}
	
	public static void makeTree(int r, int c) {
		int[] dx = {1,-1,0};
		int[] dy = {1,-1,0};
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(i==2 && j==2)
					continue;
				
				int nr = r + dx[i];
				int nc = c + dy[j];
				
				if(!isInArea(nr, nc))
					continue;
				
				trees.addFirst(new Tree(nr, nc, 1));
			}
		}
	}
	
	public static boolean isInArea(int x, int y) {
		return x>0 && x<=N && y>0 && y<=N;
	}
}
