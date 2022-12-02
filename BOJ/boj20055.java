package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj20055 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int totLen = 2 * N;
		
		
		int[] level = new int[totLen];
		int robCnt = 0;
		int zeroCnt = 0;
		int ans = 0;
		int load = 0;
		int unload = N-1;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<totLen; i++) {
			level[i] = Integer.parseInt(st.nextToken());
		}
		
		HashSet<Integer> rloc = new HashSet<>();
		Queue<Integer> q = new LinkedList<>();
		
		while(zeroCnt != K) {
			ans++;
						
			// 컨베이어 벨트 움직이기
			load = (load - 1 + totLen)%totLen;
			unload = (unload -1 + totLen)%totLen;
			
			if(robCnt!=0) {
				// 로봇 움직이기
				for(int i=0; i<robCnt; i++) {
					int idx = q.poll();
					rloc.remove(idx);
					
					if(idx == unload) {
						robCnt--;
						i--;
						continue;
					}
								
					int nextIdx = (idx + 1) % totLen;
					if(level[nextIdx]>0 && !rloc.contains(nextIdx)) {
						level[nextIdx]--;
						
						if(level[nextIdx]==0)
							zeroCnt++;
						
						if(nextIdx == unload) {
							robCnt--;
							i--;
							continue;
						}
						
						rloc.add(nextIdx);
						q.add(nextIdx);
					}else {
						rloc.add(idx);
						q.add(idx);
					}
				}
			}
			
			if(zeroCnt>=K)
				break;
			
			// 로봇 싣기
			if(level[load]>0 && !rloc.contains(load)) {
				level[load]--;
				if(level[load]==0)
					zeroCnt++;
				
				robCnt++;
				
				rloc.add(load);
				q.add(load);
			}
			
			if(q.isEmpty())
				continue;
			
			if(zeroCnt>=K)
				break;		
		}
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
	
	public static void printMap(int[] map) {
		for(int i : map)
			System.out.print(i+" ");
		
		System.out.println();
	}
}
