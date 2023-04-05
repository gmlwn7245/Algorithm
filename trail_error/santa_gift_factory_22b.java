package trail_error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// LinkedList로 다시 풀기
public class santa_gift_factory_22b {
	public static class Box {
		int id, w;
		public Box(int id, int w) {
			this.id = id;
			this.w = w;
		}
	}
	public static int q, n, m, cnt;
	public static boolean[] beltBroken;
	public static HashMap<Integer, Integer> id_hm = new HashMap<Integer, Integer>();
	public static Queue<Box>[] belt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		q = Integer.parseInt(br.readLine());
		
		establish(br.readLine());
		StringBuilder sb = new StringBuilder();
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
	
	public static void printMap(String s) {
		System.out.println("=====BELT "+s);
		for(Queue<Box> q : belt) {
			Queue<Box> tmp = new LinkedList<>();
			while(!q.isEmpty()) {
				Box b = q.poll();
				System.out.print("("+b.id+","+b.w+")");
				tmp.add(b);
			}
			q.addAll(tmp);
			System.out.println();
		}
			
	}
	
	public static int breakdown(int id) {
		if(beltBroken[id])
			return -1;
		
		beltBroken[id]=true;
		
		int findBelt = -1;
		for(int i=id+1; i<m; i++) {
			if(!beltBroken[i]) {
				findBelt = i;
				break;
			}
		}
		if(findBelt==-1) {
			for(int i=0; i<id; i++) {
				if(!beltBroken[i]) {
					findBelt = i;
					break;
				}
			}
		}
		
		while(!belt[id].isEmpty()) {
			Box b = belt[id].poll();
			id_hm.put(b.id, findBelt);
			belt[findBelt].add(b);
		}
		
		return id+1;
	}
	
	public static int check(int id) {
		if(!id_hm.containsKey(id))
			return -1;
		
		int idx = id_hm.get(id);
		Queue<Box> tmp = new LinkedList<>();

		
		while(!belt[idx].isEmpty()) {
			if(belt[idx].peek().id == id)
				break;
			Box b = belt[idx].poll();
			tmp.add(b);
		}
		
		belt[idx].addAll(tmp);
		
		return idx+1;
	}
	
	public static int remove(int id) {
		if(!id_hm.containsKey(id))
			return -1;
		
		int idx = id_hm.get(id);
		Queue<Box> tmp = new LinkedList<>();
		while(!belt[idx].isEmpty()) {
			Box b = belt[idx].poll();
			if(b.id != id)
				tmp.add(b);
			//System.out.println(b.id);
		}
		id_hm.remove(id);
		belt[idx].addAll(tmp);
		return id;
	}
	
	public static int offload(int weight) {
		int totW = 0;
		for(int i=0; i<m; i++) {
			if(belt[i].isEmpty())
				continue;
			if(belt[i].peek().w <= weight) {
				Box b = belt[i].poll();
				totW += b.w;
				id_hm.remove(b.id);
			}else {
				Box b = belt[i].poll();
				belt[i].add(b);
			}
		}
		return totW;
	}
	
	public static void establish(String str) {
		StringTokenizer st = new StringTokenizer(str);
		Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cnt = n/m;
		
		belt = new Queue[m];
		beltBroken = new boolean[m];
		
		int[][] thing = new int[n][2];
		
		for(int j=0; j<2; j++) 
			for(int i=0; i<n; i++) 
				thing[i][j]=Integer.parseInt(st.nextToken());
		
		// id - w 순
		
		int j = 0;
		for(int i=0; i<m; i++) {
			belt[i] = new LinkedList<Box>();
			for(int k=0;k<cnt; k++) {
				Box b = new Box(thing[j][0], thing[j][1]);
				belt[i].add(b);
				id_hm.put(b.id, i);
				j++;
			}
		}
	}
}
