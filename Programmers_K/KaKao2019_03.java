package Programmers_K;

import java.util.HashMap;

public class KaKao2019_03 {
	private static HashMap<Long, Long> hm = new HashMap<>();
	public static void main(String[] args) {
		long k=10;
		long[] room_number= {1,3,4,1,3,1};
		long[] answer = new long[room_number.length];

		int cnt = 0;
        for(long num : room_number){
            answer[cnt++] = findNextRoom(num);
        }
	}
	
	public static long findNextRoom(long n){
        // 빈 방인 경우
        if(!hm.containsKey(n)){
            hm.put(n, n+1);
            return n;
        }
        
        // 비어있지 않은 경우
        long next = findNextRoom(hm.get(n));
        hm.put(n, next);
        return next;
    }
}
