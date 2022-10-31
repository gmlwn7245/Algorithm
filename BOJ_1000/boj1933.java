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
			
			// ���� h, �� -h
			buildings.add(new B(lx, h));
			buildings.add(new B(rx, -h));
		}
		
		// ���� x��ǥ�� �������� + x��ǥ ���� ��� ���̷� ��������
		Collections.sort(buildings, new Comparator<B>() {
			@Override
			public int compare(B b1, B b2) {
				if(b1.x == b2.x)
					return b2.h-b1.h;
				return b1.x-b2.x;
			}
		});
		
		// ���� ���� �������� // key : ���� & value : ����
		// ���� ���� ���̰��� ó�������� ��!
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

		ArrayList<B> res = new ArrayList<>();
		for(B b : buildings) {
			// ���� x�� ���
			if(b.h > 0) {
				// ����, ����
				tm.put(b.h, tm.getOrDefault(b.h, 0)+1);
			}else {	// �� x�� ��� (���� ���� ����)
				int h = -b.h;
				
				// �ش� ���� ��(����)
				int val = tm.get(h);
				
				// �ش� ���̰� 1�� ���� ���
				if(tm.get(h)==1)
					tm.remove(h);
				else
					tm.put(h,val-1);
			}
			
			// �� ������ ��� => ���̰� 0���� ����
			if(tm.size()==0) {
				res.add(new B(b.x, 0));
				continue;
			}
			
			// ���� x ��ǥ�� ���� ���� �� ����
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
