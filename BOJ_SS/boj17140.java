package BOJ_SS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj17140 {
	private static int R,C,K,ans=0;
	private static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
				
		String[] rck = br.readLine().split(" ");
		R = Integer.parseInt(rck[0])-1;
		C = Integer.parseInt(rck[1])-1;
		K = Integer.parseInt(rck[2]);
		
		arr = new int[100][100];
		
		for(int i=0; i<3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		if(arr[R][C]!=K)
			bfs();
		
		bw.write(ans+"");
		bw.flush();
		bw.close();		
	}
	
	public static void bfs() {
		int cnt = 1;
		int r=3, c=3;
		
		while(cnt <= 100) {
			// 행 정렬
			if(r >= c) {
				int maxCnt = 0;

				for(int i=0; i<r; i++) {
					HashMap<Integer, Integer> hm = new HashMap<>();
					for(int j=0; j<c; j++) {
						int num = arr[i][j];
						
						if(num==0)
							continue;
						
						hm.put(num, hm.getOrDefault(num, 0)+1);
					}
					
					ArrayList<int[]> sArr = new ArrayList<>();
					for(int num : hm.keySet()) {
						sArr.add(new int[] {num, hm.get(num)});
					}
					
					Collections.sort(sArr, new Comparator<int[]>() {

						@Override
						public int compare(int[] o1, int[] o2) {
							if(o1[1]==o2[1])
								return o1[0]-o2[0];
							
							return o1[1]-o2[1];
						}
						
					});
					
					int idx = 0;
					for(int[] nums : sArr) {
						arr[i][idx++] = nums[0];
						arr[i][idx++] = nums[1];
						
						if(idx==100)
							break;
					}
					maxCnt = Math.max(maxCnt, idx);
					
					while(idx<100) {
						arr[i][idx++] = 0;
					}
				}
				
				c = maxCnt;
			}else {	//열 정렬
				int maxCnt = 0;
				
				for(int j=0; j<c; j++) {
					HashMap<Integer, Integer> hm = new HashMap<>();
					for(int i=0; i<r; i++) {
						int num = arr[i][j];
						
						if(num==0)
							continue;
						
						hm.put(num, hm.getOrDefault(num, 0)+1);
					}
					
					ArrayList<int[]> sArr = new ArrayList<>();
					for(int num : hm.keySet()) {
						sArr.add(new int[] {num, hm.get(num)});
					}
					
					Collections.sort(sArr, new Comparator<int[]>() {

						@Override
						public int compare(int[] o1, int[] o2) {
							if(o1[1]==o2[1])
								return o1[0]-o2[0];
							
							return o1[1]-o2[1];
						}
						
					});
					
					int idx = 0;
					for(int[] nums : sArr) {
						arr[idx++][j] = nums[0];
						arr[idx++][j] = nums[1];
						
						if(idx==100)
							break;
					}
					maxCnt = Math.max(maxCnt, idx);
					
					while(idx<100) {
						arr[idx++][j] = 0;
					}
					
					
				}
				r = maxCnt;
			}
			
			if(arr[R][C]==K)
				break;
			
			cnt++;
		}
		
		if(cnt > 100)
			ans = -1;
		else
			ans = cnt;
	}
}
