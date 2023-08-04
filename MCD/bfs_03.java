package MCD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_03 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
				
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> arr = hm.getOrDefault(a, new ArrayList<Integer>());
			arr.add(b);
			hm.put(a, arr);
			
			arr = hm.getOrDefault(b, new ArrayList<Integer>());
			arr.add(a);
			hm.put(b, arr);
		}
		
		int coco = Integer.parseInt(br.readLine());
		int cnt = 0;
		
		HashSet<Integer> visited = new HashSet<>();
		visited.add(coco);
		
		Queue<Integer> q = new LinkedList<>();
		q.add(coco);
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int i : hm.getOrDefault(now, new ArrayList<Integer>())) {
				if(visited.contains(i))
					continue;
				
				visited.add(i);
				cnt++;
								
				q.add(i);
			}
		}
		
		bw.write(cnt+"");
		
		bw.flush();
		bw.close();				
	}
}
