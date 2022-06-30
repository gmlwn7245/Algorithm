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
		
		// n��° int[] => (k���, �Ÿ�)
		arr = new ArrayList[N+1];
		
		// ��� ���� ���Ͽ� ArrayList ����
		for(int i=1; i<=N; i++) {
			arr[i] = new ArrayList<>();
		}
		
		// P��° ArrayList�� q������ �Ÿ� r ������ �־���
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
		
		// �湮�� ���� ǥ��
		boolean[] visited =  new boolean[N+1];
		// �ڱ� �ڽ�
		visited[v]=true;
		
		
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		
		// BFS ������� Ž��
		while(!q.isEmpty()) {
			int que = q.poll();
			
			// ArrayList�� �ִ� ���,�Ÿ� ������
			for(int[] a : arr[que]) {
				// �湮 X && USADO k �̻��� ���
				if(!visited[a[0]] && a[1] >= k) {
					// �湮���� true�� �ٲٰ� q�� �־���
					visited[a[0]] = true;
					q.add(a[0]);
					// ���ǿ� �´� ��� cnt ���� �ø���
					cnt++;
				}
			}
		}
		return cnt;
	}
}
