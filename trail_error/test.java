package trail_error;

import java.util.HashMap;


public class test {
	static class Box {
		int id;
		public Box(int id) {
			this.id = id;
		}
	}
	public static HashMap<Integer, Box> hm = new HashMap<>();
	public static void main(String[] args) {
		Box b = new Box(1);
		hm.put(0, b);
		b.id = 100;
		System.out.println(hm.get(0).id);
	}
	
}
