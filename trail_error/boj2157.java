package trail_error;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



// �޸� �ʰ�! => bfs ������� ���µ�
public class boj2157 {
	static class Node {
		// ����ȣ, �����湮Ƚ��, �����⳻������
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
		
		// ��� ��ȣ, �湮��ȣ = ���� �⳻������ �ִ밪
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
				// �������� �⳻�� ���� (�׷� ���� ��� > 0)
				int fScore = skyRoad[nNum][i];
				
				// �׷� ����
				if(fScore==0)
					continue;
				
				// �ִ밪 �ƴ�
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
