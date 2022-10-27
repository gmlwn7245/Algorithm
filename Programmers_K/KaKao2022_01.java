package Programmers_K;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 두 큐 합 같게 만들기
public class KaKao2022_01 {
	public static void main(String[] args) {
		int[] queue1 = {3, 2, 7, 2};
		int[] queue2 = {4, 6, 5, 1};
		
		long sum1 = 0, sum2 = 0;
		int qLen = queue1.length;
		
		ArrayList<Integer> queue = new ArrayList<>();
		
		for(int i : queue1) {
			sum1 += i;
			queue.add(i);
		}
	
		for(int i : queue2) {
			sum2 += i;
			queue.add(i);
		}
		
		if((sum1+sum2)%2!=0)
			System.out.println("no 1==>-1");
		
		else if(sum1 == sum2)
			System.out.println("no 1==>0");
	
		else {
			int start1=0, start2=qLen, end = queue.size(), changeCnt = 0;
			boolean isChange = false;
			while(sum1!=sum2) {
				if(isChange && start1==0 || start1 == start2) {
					System.out.println(-1);
					
					break;
				}
				
				if(sum1 > sum2) {
					int n = queue.get(start1);
					sum1 -= n;
					sum2 += n;
					
					start1 = (start1+1) % end;
					isChange = true;
				}else {
					int n = queue.get(start2);
					
					sum2 -= n;
					sum1 += n;
					
					start2 = (start2+1) % end;
				}
				changeCnt++;
			}
		}
	}
}
