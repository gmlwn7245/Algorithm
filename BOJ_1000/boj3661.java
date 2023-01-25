package BOJ_1000;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class boj3661 {
	static class Person implements Comparable<Person>{
		int idx, limit, pay;
		
		public Person(int idx, int limit, int pay) {
			this.idx = idx;
			this.limit = limit;
			this.pay = pay;
		}

		@Override
		public int compareTo(Person o) {
			return o.limit - this.limit;
		}
	}
	private static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			String[] str = br.readLine().split(" ");
			int price = Integer.parseInt(str[0]);
			int n = Integer.parseInt(str[1]);
			
			ArrayList<Person> arr = new ArrayList<>();
			
			str = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				arr.add(new Person(j, Integer.parseInt(str[j]),0));
			}
			
			int avg = price/n;
			int[] res = new int[n];
			
			for(int j=0; j<arr.size(); j++) {
				Person p = arr.get(j);
				//System.out.println(j);
				if(p.limit <= avg) {
					res[p.idx] = p.limit;
					price -= p.limit;
					arr.remove(p);
					j--;
				}else {
					p.pay = avg;
					price -= avg;
				}
			}
			
			boolean impossible = false;
			while(price>0) {
				
				if(arr.size() == 0) {
					impossible = true;
					break;
				}
				
				// 낼 수 있는 금액이 많은 순서
				Collections.sort(arr);
				
				
				for(int j=0; j<arr.size(); j++) {
					Person p = arr.get(j);
					
					p.pay++;
					price--;
					
					if(p.pay == p.limit) {
						res[p.idx]=p.pay;
						arr.remove(p);
						j--;
					}
						
					if(price==0)
						break;
				}
			}
			
						
			if(impossible) {
				bw.write("IMPOSSIBLE\n");
				continue;
			}
			
			for(Person p : arr) {
				if(res[p.idx]==0)
					res[p.idx] = p.pay;
			}
				
			
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<n; j++)
				sb.append(res[j]+" ");
			
			bw.write(sb.toString().trim()+"\n");
		}
		
		bw.flush();
		bw.close();
	}
}
