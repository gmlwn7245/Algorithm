package Programmers_K;

// 징검다리 건너기
public class KaKao2019_02 {
	public static void main(String[] args) {
		int[] stones= {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k=3;
		
		int res = 0;
		
		int min = 0; 
		int max = Integer.MAX_VALUE;
		
		while(min <= max) {
			int mid = (max+min)/2;
			if(checkStone(mid, stones, k)) {
				min = mid+1;
				res = mid;
			}else {
				max = mid-1;
			}
		}
		
		System.out.println(res);
	}
	
	public static boolean checkStone(int mid, int[] stones, int k) {
		boolean isUnder = false;
		int underCnt = 0;
		for(int n : stones) {
			// mid 값보다 작을 경우
			if(n < mid) {
				if(isUnder)
					underCnt++;
				
				else {
					isUnder = true;
					underCnt = 1;
				}
				
				if(underCnt >= k)
					return false;
			} else {
				isUnder = false;
				underCnt = 0;
			}
		}
		return true;
	}
	
	
}
