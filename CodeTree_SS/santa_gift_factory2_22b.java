package CodeTree_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


class Box {
	int id, belt;
	Box prev=this, next=this;
	public Box(int id, int belt) {
		this.id = id;
		this.belt = belt;
	}
	
	public void print() {
		System.out.print("["+id+"]");
	}
}

public class santa_gift_factory2_22b {
	/* Belt 번호, 개수 */
	public static HashMap<Integer, Integer> boxCnt = new HashMap<>();
	
	/* Box 번호, 객체 */
	public static HashMap<Integer, Box> box = new HashMap<>();
	public static Box[] head, tail;
	public static int n, m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int q = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<q; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			
			int order = Integer.parseInt(st.nextToken());
			int m_src, m_dst;
			switch(order) {
			case 100:	// 초기 설정
				n = Integer.parseInt(st.nextToken());
				m = Integer.parseInt(st.nextToken());
				
				head = new Box[n+1];
				tail = new Box[n+1];
				
				for(int j=1; j<=m; j++) {
					int belt_num = Integer.parseInt(st.nextToken());
					Box b= new Box(j, belt_num);
					box.put(j, b);
					push(b, belt_num);
				}
				
				break;
			case 200:	// 물건 모두 옮기기 src to dst
				m_src = Integer.parseInt(st.nextToken());
				m_dst = Integer.parseInt(st.nextToken());
				
				sb.append(move(m_src, m_dst));
				
				break;
			case 300:	// 앞 물건만 교체하기
				m_src = Integer.parseInt(st.nextToken());
				m_dst = Integer.parseInt(st.nextToken());
				
				sb.append(changeHead(m_src, m_dst));
				
				break;
				
			case 400:	// 물건 나누기
				m_src = Integer.parseInt(st.nextToken());
				m_dst = Integer.parseInt(st.nextToken());
				
				sb.append(divided(m_src, m_dst));
				
				break;
			case 500:
				int box_num = Integer.parseInt(st.nextToken());
				
				sb.append(getBoxInfo(box_num));
				
				break;
			case 600:
				int belt_num = Integer.parseInt(st.nextToken());
				
				sb.append(getBeltInfo(belt_num));
				
				break;
			}
			printMap(str);
			if(i != q-1)
				sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static int getBeltInfo(int b_num) {
		int a = -1, b = -1, c = boxCnt.getOrDefault(b_num, 0);
		
		if(head[b_num]!=null) {
			a = head[b_num].id;
			b = tail[b_num].id;
		}
			
		return a + b * 2 + c * 3;
	}
	
	public static int getBoxInfo(int p_num) {
		Box bb = box.get(p_num);
		int a = -1, b = -1;
		if(bb.prev != bb)
			a = bb.prev.id;
		
		if(bb.next != bb)
			b = bb.next.id;
		
		return a + 2 * b;
	}
	
	public static int divided(int src, int dst) {
		int cnt = boxCnt.getOrDefault(src, 0)/2;
		System.out.println(cnt);
		Box b = head[src];
		b.belt = dst;
		while(cnt-- > 1) {
			b = b.next;
			b.belt = dst;
		}
		System.out.println(boxCnt.getOrDefault(src, 0)+" "+ boxCnt.getOrDefault(dst, 0));
		
		boxCnt.put(src, (boxCnt.get(src)-cnt));
		boxCnt.put(dst, (boxCnt.get(dst)+cnt));
		
		System.out.println(boxCnt.get(src)+" "+ boxCnt.get(dst));
		
		Box h = b.next;
		
		if(head[dst]== null) {
			head[dst] = head[src];
			tail[dst] = b;
			b.next = b;
		}else {
			head[dst].prev = b;
			b.next = head[dst];
			head[dst]=head[src];
		}
		
		head[src] = h;
		h.prev = h;
		
		return boxCnt.getOrDefault(dst, 0);
	}
	
	public static int changeHead(int src, int dst) {
		Box b1 = head[src];
		Box b2 = head[dst];
		
		if(b1 == null && b2 == null) {
			return 0;
		}else if(b1 == null) {
			remove(b2);
			push(b2, src);
		}else if(b2 == null) {
			remove(b1);
			push(b1, dst);
		}else {
			remove(b1);
			remove(b2);
			
			head[src].prev = b2;
			b2.next = head[src];
			head[src] = b2;
			b2.belt = src;
			
			head[dst].prev = b1;
			b1.next = head[dst];
			head[dst] = b2;
			b1.belt = dst;
		}
		
		return boxCnt.getOrDefault(dst, 0);
	}
	
	public static int move(int src, int dst) {
		if(boxCnt.getOrDefault(src,0)==0)
			return 0;
		
		Box b = head[src];
		while(b.next != b) {
			b.belt = dst;
			b = b.next;
		}
		b.belt = dst;
		
		if(head[src]!=null) {
			int cnt = boxCnt.getOrDefault(src,0);
			head[dst].prev = tail[src];
			tail[src].next = head[dst];
			head[dst] = head[src];
			
			head[src]=tail[src]=null;
			boxCnt.put(src, 0);
			boxCnt.put(dst, boxCnt.getOrDefault(dst,0)+cnt);
		}
		
		return boxCnt.get(dst);
	}
	
	public static void remove(Box b) {
		int b_num = b.belt;
		
		if(head[b_num]==tail[b_num]) {
			head[b_num]=tail[b_num]=null;
		}else if(head[b_num]==b) {
			head[b_num] = b.next;
			b.next.prev = b.next;
		}else if(tail[b_num]==b) {
			tail[b_num] = b.prev;
			b.prev.next = b.prev;
		}else {
			b.prev.next = b.next;
			b.next.prev = b.prev;
		}
		
		boxCnt.put(b.belt, boxCnt.get(b.belt)-1);
		b.prev = b; b.next =b;
	}
	
	public static void push(Box b, int belt_num) {
		if(head[belt_num]==null) {
			head[belt_num] = tail[belt_num]= b;
		}else {
			tail[belt_num].next = b;
			b.prev = tail[belt_num] = b;
			tail[belt_num] = b;
		}
		b.belt = belt_num;
		boxCnt.put(belt_num, boxCnt.getOrDefault(belt_num, 0)+1);
	}
	
	public static void printMap(String str) {
		System.out.println("========PRINT MAP "+str);
		for(int i=1; i<=n; i++) {
			Box b = head[i];
			if(b == null) {
				System.out.println("NULL");
				continue;
			}
			while(b.next != b) {
				b.print();
				b = b.next;
			}
			b.print();
			System.out.println();
		}
	}
	
}
