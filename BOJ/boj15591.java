package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj15591 {	
	private static ArrayList<int[]>[] arr;
	private static int N,Q;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		// n번째 int[] => (k요소, 거리)
		arr = new ArrayList[N+1];
		
		// 모든 점에 대하여 ArrayList 생성
		for(int i=1; i<=N; i++) {
			arr[i] = new ArrayList<>();
		}
		
		// P번째 ArrayList에 q까지의 거리 r 데이터 넣어줌
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			arr[p].add(new int[] {q,r});
			arr[q].add(new int[] {p,r});
		}
		
		
		for(int i=1; i<=Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int res = bfs(k,v);
			bw.write(res+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static int bfs(int k, int v) {
		int cnt = 0;
		
		// 방문한 정점 표시
		boolean[] visited =  new boolean[N+1];
		// 자기 자신
		visited[v]=true;
		
		
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		
		// BFS 방식으로 탐색
		while(!q.isEmpty()) {
			int que = q.poll();
			
			// ArrayList에 있는 요소,거리 가져옴
			for(int[] a : arr[que]) {
				// 방문 X && USADO k 이상인 경우
				if(!visited[a[0]] && a[1] >= k) {
					// 방문여부 true로 바꾸고 q에 넣어줌
					visited[a[0]] = true;
					q.add(a[0]);
					// 조건에 맞는 경우 cnt 개수 올리기
					cnt++;
				}
			}
		}
		return cnt;
	}
}
