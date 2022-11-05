package trail_error;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



// 메모리 초과! => bfs 방법에서 나는듯
public class boj2157 {
	static class Node {
		// 노드번호, 누적방문횟수, 누적기내식점수
		int nNum, vNum, fSum;
		public Node(int nNum, int vNum, int fSum) {
			this.nNum = nNum;
			this.vNum = vNum;
			this.fSum = fSum;
		}
	}
	public static int N,M,K;
	public static int[][] skyRoad;
	public static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 노드 번호, 방문번호 = 누적 기내식점수 최대값
		dp = new int[N+1][M+1];
		skyRoad = new int[N+1][N+1];
		
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a > b)
				continue;
			
			skyRoad[a][b] = Math.max(c, skyRoad[a][b]);
		}
		
		bfs();
		
		int max = 0;
		for(int i=1; i<=M; i++) {
			max = Math.max(max , dp[N][i]);
		}
		bw.write(max);
		bw.flush();
		bw.close();
	}
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(1, 1, 0));
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			int nNum = now.nNum;
			int vNum = now.vNum;
			int fSum = now.fSum;
			
			for(int i=nNum+1; i<=N; i++) {
				// 더해지는 기내식 점수 (항로 있을 경우 > 0)
				int fScore = skyRoad[nNum][i];
				
				// 항로 없음
				if(fScore==0)
					continue;
				
				// 최대값 아님
				if(fSum+fScore < dp[i][vNum+1])
					continue;
				
				dp[i][vNum+1] = fSum+fScore;
				
				if(i == N || vNum+1 >= M)
					continue;
				
				q.add(new Node(i, vNum+1, dp[i][vNum+1]));
			}
		}
	}
}
