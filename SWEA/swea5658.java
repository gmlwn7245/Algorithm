package SWEA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
*/
class swea5658
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder forAns = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			HashSet<String> hs = new HashSet<>();
			Queue<Character> q = new LinkedList<>();
			int unit = N/4;
			
			String str = br.readLine();
			for(int i=0;i<str.length(); i++) {
				q.add(str.charAt(i));
			}
			
			for(int j=0; j<N; j++) {
				int cnt = 0;
				StringBuilder sb = new StringBuilder();
				for(int i=0; i<N; i++) {
					char ch = q.poll();
					sb.append(ch);
					q.add(ch);
					
					if(++cnt == unit) {
						hs.add(sb.toString());
						//System.out.println(sb.toString()+" "+Integer.parseInt(sb.toString(), 16));
						sb.setLength(0);					
						cnt = 0;
					}
				}
				
				// 한칸 밀기
				char first = q.poll();
				q.add(first);
			}
				
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			for(String s : hs) {
				pq.add(Integer.parseInt(s, 16));
			}
			
			int ans = 0;
			while(K-- > 0) {
			ans = pq.poll();
			}
			
			forAns.append("#"+test_case+" "+ans);
			if(test_case != T)
			forAns.append("\n");
		}
		System.out.println(forAns.toString());
	}
}