package trail_error;import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;


//시간초과 & 메모리초과
public class boj1933 {
	static class B {
		int lx, rx, h;
		public B(int lx, int rx, int h) {
			this.lx = lx;
			this.rx = rx;
			this.h = h;
		}
	}
	private static int n;
	// 왼쪽x, 높이, 오른쪽x
	private static HashMap<Integer,Integer> hm = new HashMap<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		
		int limit = 0;
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int lx = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int rx = Integer.parseInt(st.nextToken());
			
			for(int j=lx; j<rx; j++) {
				if(!hm.containsKey(j))
					hm.put(j, h);
				else {
					int max = Math.max(hm.get(j), h);
					hm.put(j, max);
				}
			}
			limit = Math.max(rx, limit);
		}
		
		ArrayList<Integer> keySet = new ArrayList<>(hm.keySet());
		Collections.sort(keySet);
		
		for(int idx : keySet) {
			if(!hm.containsKey(idx-1)) {
				bw.write(idx + " "+ hm.get(idx)+" ");
			}else if(hm.get(idx-1)!=hm.get(idx)) {
				bw.write(idx + " "+ hm.get(idx)+" ");
			}
		}
		
		bw.write(limit + " "+ 0);
		bw.flush();
		bw.close();
	}
}
