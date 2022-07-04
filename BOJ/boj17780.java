package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj17780 {
	// 색
	private static int WHITE = 0;
	private static int RED = 1;
	private static int BLUE = 2;
	
	// 0 : -> // 1 : <- // 2 : up // 3 :down
	private static int[] dir_p = {0, 0, -1, 1};
	private static int[] dir_q = {1, -1, 0, 0};
	
	private static int[][] chess;
	private static Deque<Integer>[][] deque;
	private static ArrayList<Node> nodes;
	
	static class Node{
		// 좌표 p,q
		int p,q;
		
		// 방향 dir
		int dir;
		
		public Node(int p, int q, int dir)
		{
			this.p = p;
			this.q = q;
			this.dir = dir;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		chess = new int[N+1][N+1];
		deque = new ArrayDeque[N+1][N+1];
		nodes = new ArrayList<>();
		
		// 흰색 -> 올려줌
		// 빨간색 -> 말의 순서 바꿈
		// 파란색 -> 이동방향을 반대로 하고 한 칸 이동 (체스판을 벗어나는 경우도 동일)
		// 이동하려는 칸이 파란색 -> 방향만 바꿈
		// 0 : 흰색 * 1 : 빨간색 * 2 : 파란색
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				chess[i][j] = Integer.parseInt(st.nextToken());
				deque[i][j] = new ArrayDeque<>();
			}
		}
		
		// 이동방향
		// 1 : -> // 2 : <- // 3 : up // 4 :down
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken())-1;
			int q = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken())-1;
			
			nodes.add(new Node(p,q,dir));
			deque[p][q].offer(i);			
		}
		
		
		int cnt = 1;
		
		// N : 체스판의 크기
		// K : 말의 개수
		while(cnt <= 1000) {
			for(int i=0; i<K; i++) {
				Node n = nodes.get(i);
				
				int p = n.p;
				int q = n.q;
				
				// 가장 아래가 아니면 continue;
				if(deque[p][q].getFirst() != i)
					continue;
				
				// 다음 좌표
				int nextP = p + dir_p[n.dir];
				int nextQ = q + dir_q[n.dir];
				
				// 0 : -> // 1 : <- // 2 : up // 3 :down
				
				// 체스판을 벗어나거나 현재 위치가 파란색인 경우 (체스판 벗어날 수 있기 때문에 제일 먼저 지정함)
				if(nextP < 0 || nextP >= N || nextQ < 0 || nextQ >= N || chess[nextP][nextQ]== BLUE) {
					int newDir = n.dir;
					
					//방향 반대로
					if(newDir % 2 == 0)
						newDir += 1;
					else
						newDir -= 1;
					
					nextP = p + dir_p[newDir];
					nextQ = q + dir_q[newDir];
					
					// 바라보는 방향이 BLUE 인 경우
					if(nextP < 0 || nextP >= N || nextQ < 0 || nextQ >= N ||chess[nextP][nextQ] == BLUE) {
						nextP = p;
						nextQ = q;
					} else {
						int c = chess[nextP][nextQ];
						move(p, q, nextP, nextQ, c, i, n);
					}
					
					nodes.set(i, new Node(nextP, nextQ, newDir));
				}else{
					int c = chess[nextP][nextQ];
					move(p, q, nextP, nextQ,c, i, n);
				}
				
				if(deque[nextP][nextQ].size() >= 4) {
					bw.write((int)cnt +"");
					bw.flush();
					bw.close();
					return ;
				}
			}
			cnt++;
		}
		
		bw.write("-1");
		bw.flush();
		bw.close();
		
		System.out.println(cnt);
	}
	
	// 거꾸로 쌓음
	static void move(int p, int q, int nextP, int nextQ, int c, int i, Node n) {
		if(c == WHITE) {
			while(!deque[p][q].isEmpty()) {
				int idx = deque[p][q].pollFirst();
				nodes.set(idx, new Node(nextP,nextQ,nodes.get(idx).dir));
				deque[nextP][nextQ].offer(idx);
			}
		}else {
			while(!deque[p][q].isEmpty()) {
				int idx = deque[p][q].pollLast();
				nodes.set(idx, new Node(nextP,nextQ,nodes.get(idx).dir));
				deque[nextP][nextQ].offer(idx);
			}
		}
		
		// 맨 밑에 있던 노드 방향 조정
		nodes.set(i, new Node(nextP, nextQ, n.dir));
	}
}