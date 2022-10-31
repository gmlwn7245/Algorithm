package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class boj1933 {
	static class B{
		int x,h;
		public B(int x, int h) {
			this.x =x;
			this.h =h;
		}
	}
	
	private static int n;
	private static ArrayList<B> buildings = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int lx = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int rx = Integer.parseInt(st.nextToken());
			
			// 시작 h, 끝 -h
			buildings.add(new B(lx, h));
			buildings.add(new B(rx, -h));
		}
		
		// 빌딩 x좌표로 오름차순 + x좌표 같을 경우 높이로 내림차순
		Collections.sort(buildings, new Comparator<B>() {
			@Override
			public int compare(B b1, B b2) {
				if(b1.x == b2.x)
					return b2.h-b1.h;
				return b1.x-b2.x;
			}
		});
		
		// 높이 기준 내림차순 // key : 높이 & value : 개수
		// 제일 높은 높이값이 처음값으로 들어감!
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

		ArrayList<B> res = new ArrayList<>();
		for(B b : buildings) {
			// 시작 x일 경우
			if(b.h > 0) {
				// 높이, 개수
				tm.put(b.h, tm.getOrDefault(b.h, 0)+1);
			}else {	// 끝 x일 경우 (높이 제거 해줌)
				int h = -b.h;
				
				// 해당 높이 값(개수)
				int val = tm.get(h);
				
				// 해당 높이가 1개 있을 경우
				if(tm.get(h)==1)
					tm.remove(h);
				else
					tm.put(h,val-1);
			}
			
			// 다 없어진 경우 => 높이가 0으로 변함
			if(tm.size()==0) {
				res.add(new B(b.x, 0));
				continue;
			}
			
			// 현재 x 좌표의 가장 높은 값 구함
			res.add(new B(b.x, tm.firstKey()));
		}
		
		
		bw.write(res.get(0).x + " "+ res.get(0).h+ " ");
		int prev = res.get(0).h;
		
		for(B b : res) {
			if(b.h==prev)
				continue;
			
			bw.write(b.x + " "+ b.h+" ");
			prev = b.h;
		}
		
		
		bw.flush();
		bw.close();
	}
}
