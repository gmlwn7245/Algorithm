package Easy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj1264 {
	public static char[] lows = {'a', 'e', 'i','o', 'u'};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true) {
			String s = br.readLine();
			if(s.equals("#"))
				break;
			s = s.replaceAll("[^aAeEiIoOuU]", "");
			bw.write(String.valueOf(s.length())+"\n");
		}
		
		bw.flush();
		bw.close();
	}
}
