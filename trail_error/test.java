package trail_error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class test {
	static class Box {
		int id;
		public Box(int id) {
			this.id = id;
		}
	}
	public static HashMap<Integer, Box> hm = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		if(str.charAt(0)=='#')
			System.out.println("SUCCESS");
	}
	
}
