package BOJ_1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class boj1253 {
	private static ArrayList<Integer> nums = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		String s = bf.readLine();
		String[] arr = s.split(" ");
		
		for(String str : arr) {
			nums.add(Integer.parseInt(str));
		}
		
		// Á¤·Ä
		Collections.sort(nums);
		
		int goodCnt = 0;
		for(int i=0; i<nums.size(); i++) {
			int targetNum = nums.get(i);
			int left = 0;
			int right = nums.size()-1;
			while(left<right) {
				if(left == i) left++;
				else if(right == i) right--;
				else {
					int sum = nums.get(left) + nums.get(right);
					if(sum == targetNum) {
						goodCnt ++;
						break;
					}
					
					if(sum > targetNum) right --;
					else if(sum < targetNum) left++;
				}				
			}
			
		}
		System.out.println(goodCnt);
	}
}
