package trail_error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj17471 {
	public static boolean div = false;
	public static int N, sum = 0, res = 1001;
	public static int[] people, area;
	public static HashSet<Integer>[] nearby;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nearby = new HashSet[N+1];
		people = new int[N+1];
		area = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			people[i]=Integer.parseInt(st.nextToken());
			sum += people[i];
			nearby[i]=new HashSet<Integer>();
		}
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			while(len-- > 0) {
				nearby[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		int idx = 1;
		for(int i=1; i<=N; i++) {
			if(idx == 3 && area[i]==0) {
				System.out.println(-1);
				return;
			}else if(area[i]==0) {
				canDiv(i, idx++);
			}
		}
		
		if(idx == 3) {
			int s = 0;
			for(int i=1; i<=N; i++) {
				if(area[i]==1)
					s+=people[i];
			}
			System.out.println(Math.abs(sum - s * 2));
			return ;
		}
		
		boolean[] visited = new boolean[N+1];
		visited[1]=true;
		dfs(visited, 1, people[1]);
		
		System.out.println(res);
	}
	
	public static void canDiv(int n, int idx) {
		area[n]=idx;
		
		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int i : nearby[now]) {
				if(area[i]==idx)
					continue;
				
				area[i]=idx;
				q.add(i);
			}
		}
	}
	
	public static boolean isConnected(boolean[] visited) {
		boolean[] check = new boolean[N+1];
		Queue<Integer> q = new LinkedList<Integer>();
		
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				q.add(i);
				check[i]=true;
				break;
			}
		}
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int j : nearby[now]) {
				if(visited[j] || check[j])
					continue;
				
				check[j]=true;
				q.add(j);
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(!visited[i]&&!check[i])
				return false;
		}
		return true;
	}
	
	
	public static void dfs(boolean[] visited, int last, int tot) {
		if(!isConnected(visited))
			return ;
		
		int nowRes = Math.abs(sum - 2*tot);
		
		if(nowRes == 4) {
			for(boolean b : visited) {
				if(b)
					System.out.print("1 ");
				else
					System.out.print("0 ");
				
			}
			System.out.println();
		}
		res = Math.min(res, nowRes);
		
		for(int i : nearby[last]) {
			if(visited[i])
				continue;
			int nextRes = Math.abs(sum - (tot+people[i])*2);
			if(nowRes < nextRes)
				continue;
			
			visited[i]=true;
			dfs(visited, i, tot+people[i]);
			visited[i]=false;
		}
	}
}
