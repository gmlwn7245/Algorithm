package CodeTree_SS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


// LinkedList로 다시 풀기
public class santa_gift_factory_22b {
	public static class Box {
		int id, w, belt;
		Box prev=this;
		Box next=this;
		public Box(int id, int w, int belt) {
			this.id = id;
			this.w = w;
			this.belt = belt;
		}
	}
	public static int q, n, m, cnt;
	public static boolean[] beltBroken;
	public static HashMap<Integer, Box> id_hm = new HashMap<Integer, Box>();
	public static Box[] head, tail;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		q = Integer.parseInt(br.readLine());
		
		establish(br.readLine());
		StringBuilder sb = new StringBuilder();
		System.out.println();
		//printMap("first");
		
		for(int i=1; i<q; i++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int num = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			
			
			if(num == 200) {	// w 이하 하차
				sb.append(offload(idx));
			}else if(num == 300) {	// id 물품 제거 
				sb.append(remove(idx));
			}else if(num == 400) {	// id 뒤에 물품 당기기
				sb.append(check(idx));
			}else if(num == 500) {	// 해당 벨트 고장
				sb.append(breakdown(idx-1));
			}
			
			if(i!=q-1)
				sb.append("\n");
			//printMap(s);
		}
		System.out.println(sb.toString());
	}
	
	public static int breakdown(int idx) {
		if(beltBroken[idx])
			return -1;
		
		beltBroken[idx]=true;
		
		int nextBelt = idx+1;
		for(int i=1; i<m; i++) {
			if(nextBelt==m)
				nextBelt = 0;
			
			if(!beltBroken[nextBelt])
				break;
			nextBelt++;
		}
		
		while(head[idx]!=null) {
			Box b = head[idx];
			removeBox(b);
			
			b.belt = nextBelt;
			pushBox(b);
		}
		
		return idx+1;
	}
	
	public static int check(int id) {
		if(!id_hm.containsKey(id))
			return -1;
		
		Box b = id_hm.get(id);
		int belt = b.belt;
		if(b!=head[belt]) {
			head[belt].prev = tail[belt];
			tail[belt].next = head[belt];
			
			head[belt] = b;
			tail[belt] = b.prev;
			
			head[belt].prev = head[belt];
			tail[belt].next = tail[belt];
		}
		
		return b.belt+1;
	}
	
	public static int remove(int id) {
		if(!id_hm.containsKey(id))
			return -1;
		
		Box b = id_hm.get(id);
		removeBox(b);
		
		return id;
	}
	
	public static void removeBox(Box b) {
		int idx = id_hm.get(b.id).belt;
		
		if(head[idx]==tail[idx]) {
			head[idx]=tail[idx]=null;
		}else if(head[idx]==b) {
			head[idx]=b.next;
			b.next.prev = b.next;
		}else if(tail[idx]==b) {
			tail[idx]=b.prev;
			b.prev.next = b.prev;
		}else {
			b.next.prev = b.prev;
			b.prev.next = b.next;
		}
		
		b.next = b;
		b.prev = b;
		
		id_hm.remove(b.id);
	}
	
	public static void pushBox(Box b) {
		int idx = b.belt;
		id_hm.put(b.id, b);
		
		if(head[idx]==null) {
			head[idx]=tail[idx]=b;
		}else {
			tail[idx].next = b;
			b.prev = tail[idx];
			tail[idx] = b;
		}
	}
	
	public static long offload(int weight) {
		long sum = 0;
		
		for(int i = 0; i<m; i++) {
			if(!beltBroken[i] && head[i]!=null) {
				Box b = head[i];
				removeBox(b);
				
				if(b.w <= weight) {
					sum += b.w;
				}else {
					pushBox(b);
				}
			}
		}

		return sum;
	}

	
	public static void establish(String str) {
		StringTokenizer st = new StringTokenizer(str);
		Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int cnt = n/m;
		
		head = new Box[m]; tail = new Box[m];
		beltBroken = new boolean[m];
		
		int[] id = new int[n];
		int[] w = new int[n];
		
		for(int i=0; i<n; i++) 
			id[i]=Integer.parseInt(st.nextToken());
		for(int i=0; i<n; i++) 
			w[i]=Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n; i++) {
			int belt = i/cnt;
			
			Box b = new Box(id[i], w[i], belt);
			id_hm.put(b.id, b);
			pushBox(b);
		}
		
	}
	
	public static void printMap(String s) {
		System.out.println("=====BELT "+s);
		for(Box b : head) {
			if(b == null) {
				System.out.println("NULL");
				continue;
			}
				
			
			while(b.next != b) {
				System.out.print("("+b.id+","+b.w+") ");
				b = b.next;
			}
			System.out.print("("+b.id+","+b.w+") ");
			System.out.println();
		}
	}
}
