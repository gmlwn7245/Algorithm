package trail_error;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;


public class test {
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		
		ArrayList<Integer> arr2 = new ArrayList<>();
		arr2.addAll(arr);
		arr2.add(4);
		
		for(int i : arr2)
			System.out.println(i+" ");
	}
}
