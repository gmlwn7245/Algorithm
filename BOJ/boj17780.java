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
	// ��
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
		// ��ǥ p,q
		int p,q;
		
		// ���� dir
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
		
		// ��� -> �÷���
		// ������ -> ���� ���� �ٲ�
		// �Ķ��� -> �̵������� �ݴ�� �ϰ� �� ĭ �̵� (ü������ ����� ��쵵 ����)
		// �̵��Ϸ��� ĭ�� �Ķ��� -> ���⸸ �ٲ�
		// 0 : ��� * 1 : ������ * 2 : �Ķ���
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				chess[i][j] = Integer.parseInt(st.nextToken());
				deque[i][j] = new ArrayDeque<>();
			}
		}
		
		// �̵�����
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
		
		// N : ü������ ũ��
		// K : ���� ����
		while(cnt <= 1000) {
			for(int i=0; i<K; i++) {
				Node n = nodes.get(i);
				
				int p = n.p;
				int q = n.q;
				
				// ���� �Ʒ��� �ƴϸ� continue;
				if(deque[p][q].getFirst() != i)
					continue;
				
				// ���� ��ǥ
				int nextP = p + dir_p[n.dir];
				int nextQ = q + dir_q[n.dir];
				
				// 0 : -> // 1 : <- // 2 : up // 3 :down
				
				// ü������ ����ų� ���� ��ġ�� �Ķ����� ��� (ü���� ��� �� �ֱ� ������ ���� ���� ������)
				if(nextP < 0 || nextP >= N || nextQ < 0 || nextQ >= N || chess[nextP][nextQ]== BLUE) {
					int newDir = n.dir;
					
					//���� �ݴ��
					if(newDir % 2 == 0)
						newDir += 1;
					else
						newDir -= 1;
					
					nextP = p + dir_p[newDir];
					nextQ = q + dir_q[newDir];
					
					// �ٶ󺸴� ������ BLUE �� ���
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
	
	// �Ųٷ� ����
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
		
		// �� �ؿ� �ִ� ��� ���� ����
		nodes.set(i, new Node(nextP, nextQ, n.dir));
	}
}