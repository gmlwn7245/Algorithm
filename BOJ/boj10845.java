package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj10845 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		ArrayList<Integer> queue = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			
			
			switch(str) {
			case "push" : {
				queue.add(Integer.parseInt(st.nextToken()));
				break;
			}
			case "pop" : {
				if(queue.isEmpty())
					bw.write("-1\n");
				else {
					bw.write(queue.get(0)+"\n");
					queue.remove(0);
				}
				break;
			}
			case "size" : {
				bw.write(queue.size()+"\n");
				break;
			}
			case "empty" :{
				if(queue.isEmpty())
					bw.write("1\n");
				else
					bw.write("0\n");
				break;
			}
			case "front":{
				if(queue.isEmpty())
					bw.write("-1\n");
				else
					bw.write(queue.get(0)+"\n");
				break;
			}
			default :{
				if(queue.isEmpty())
					bw.write("-1\n");
				else
					bw.write(queue.get(queue.size()-1)+"\n");
			}
			}
		}
		
		bw.flush();
		bw.close();
	}
}
