package trail_error;

import java.util.*;
import java.io.*;

public class Ref {

	static class Node {
		int id;
		int w;
		int b_num;
		Node left, right;

		public Node(int id, int w, int b_num) {
			this.id = id;
			this.w = w;
			this.b_num = b_num;
		}

		@Override
		public String toString() {
			return "[id=" + id + ", w=" + w + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			return id == other.id;
		}

	}

	static final String ENTER = "\n";
	static int N, M;
	static Map<Integer, Node> info;
	static Node[] head;
	static Node[] tail;
	static boolean[] broken;
	// 물건하차 -> 맨 앞 물건 제거
	// 물건제거 -> 벨트 중 어떤 위치여도 제거

	private static void operate1(int[] ID, int[] W) {
		// TODO Auto-generated method stub
		info = new HashMap<>();
		head = new Node[M + 1];
		tail = new Node[M + 1];
		broken = new boolean[M + 1];

		Node[] nodes = new Node[N];
		int size = N / M;

		for (int i = 0; i < N; i++) {
			int b_num = i / size + 1;
			nodes[i] = new Node(ID[i], W[i], b_num);
			info.put(ID[i], nodes[i]);
		}

		for (int i = 0; i < N; i++) {
			int b_num = i / size + 1;
			int idx = i % size;

			if (idx == 0) {
				head[b_num] = nodes[i];
				head[b_num].right = nodes[i + 1];
			} else if (idx == size - 1) {
				tail[b_num] = nodes[i];
				tail[b_num].left = nodes[i - 1];
			} else {
				nodes[i].right = nodes[i + 1];
				nodes[i].left = nodes[i - 1];
			}
		}

	}

	private static Ref.Node remove(Ref.Node removed) {
		// TODO Auto-generated method stub
		info.remove(removed.id);

		int b_num = removed.b_num;
		if (head[b_num].equals(tail[b_num])) {
			head[b_num] = tail[b_num] = null;
		} else if (head[b_num].equals(removed)) {
			head[b_num] = head[b_num].right;
			head[b_num].left = null;
		} else if (tail[b_num].equals(removed)) {
			tail[b_num] = tail[b_num].left;
			tail[b_num].right = null;
		} else {
			removed.left.right = removed.right;
			removed.right.left = removed.left;
		}
		removed.left = null;
		removed.right = null;

		return removed;
	}

	private static void pushBack(Ref.Node added, int b_num) {
		// TODO Auto-generated method stub
		added.b_num = b_num;
		info.put(added.id, added);

		if (tail[b_num] == null) {
			head[b_num] = tail[b_num] = added;
		} else {
			tail[b_num].right = added;
			added.left = tail[b_num];
			tail[b_num] = added;
		}
	}

	private static long operate2(int w_max) {
		long sum = 0;

		for (int b = 1; b <= M; b++) {
			if (!broken[b] && head[b] != null) {
				Node removed = remove(head[b]);
				if (removed.w <= w_max) {
					sum += removed.w;
				} else {
					pushBack(removed, b);
				}
			}
		}
		return sum;
	}

	private static int operate3(int r_id) {
		Node removed = info.get(r_id);
		if (removed == null)
			return -1;
		remove(removed);
		return r_id;
	}

	private static int operate4(int f_id) {
		Node target = info.get(f_id);
		if (target == null)
			return -1;

		int b = target.b_num;
		if (!head[b].equals(target)) {

			head[b].left = tail[b];
			tail[b].right = head[b];

			head[b] = target;
			tail[b] = target.left;

			head[b].left = null;
			tail[b].right = null;

		}

		return b;
	}

	private static int operate5(int b_num) {
		if (broken[b_num])
			return -1;

		int next_belt = b_num;

		for (int plus = 1; plus < M; plus++) {
			next_belt++;
			if (next_belt > M)
				next_belt = 1;

			if (!broken[next_belt])
				break;
		}

		while (head[b_num] != null) {
			pushBack(remove(head[b_num]), next_belt);
		}

		broken[b_num] = true;
		return b_num;
	}

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int Q = Integer.parseInt(br.readLine());

			StringBuilder sb = new StringBuilder();
			for (int q = 1; q <= Q; q++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken());

				switch (op) {
				case 100:
					N = Integer.parseInt(st.nextToken());
					M = Integer.parseInt(st.nextToken());

					int[] ID = new int[N];
					for (int i = 0; i < N; i++)
						ID[i] = Integer.parseInt(st.nextToken());

					int[] W = new int[N];
					for (int i = 0; i < N; i++)
						W[i] = Integer.parseInt(st.nextToken());

					operate1(ID, W);

					break;
				case 200:
					int w_max = Integer.parseInt(st.nextToken());

					sb.append(operate2(w_max)).append(ENTER);

					break;
				case 300:
					int r_id = Integer.parseInt(st.nextToken());
					sb.append(operate3(r_id)).append(ENTER);
					break;
				case 400:
					int f_id = Integer.parseInt(st.nextToken());
					sb.append(operate4(f_id)).append(ENTER);
					break;
				case 500:
					int b_num = Integer.parseInt(st.nextToken());
					sb.append(operate5(b_num)).append(ENTER);
					break;
				}
//				
			}
			System.out.print(sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
