package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1976 {
	private static int N,M;
	private static int[][] map;
	private static HashSet<Integer> hs = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int first = -1;
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken())-1;
			hs.add(num);
			
			if(first == -1)
				first = num;
		}
		
		if(bfs(first))
			bw.write("YES");
		else
			bw.write("NO");
		bw.flush();
		bw.close();
	}
	
	public static boolean bfs(int first) {
		boolean[] visited = new boolean[N];
		Queue<Integer> q = new LinkedList<>();
		q.add(first);
		hs.remove(first);
		visited[first]=true;
		
		while(!q.isEmpty()) {
			int n = q.poll();
			
			for(int i=0; i<N; i++) {
				if(visited[i])
					continue;
				
				if(map[i][n]==0)
					continue;
				
				visited[i]=true;
				q.add(i);
				hs.remove(i);
			}
		}
		
		if(hs.isEmpty())
			return true;
		return false;
	}
}
