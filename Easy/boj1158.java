package Easy;

import java.util.ArrayList;
import java.util.Scanner;

public class boj1158 {
	private static ArrayList<Integer> arr = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		int n = sc.nextInt();
		int k = sc.nextInt();
		int idx = k-1;
		
		for(int i=1; i<=n; i++) {
			arr.add(i);
		}
		
		for(int i=0; i<n-1; i++) {
			sb.append(arr.get(idx)+", ");
			arr.remove(idx);
			idx = (idx + k - 1) % arr.size();
		}
		sb.append(arr.get(idx)+">");
		
		System.out.println(sb.toString());
	}
}
