package Programmers;

import java.util.*;

public class Kakao2022ver02 {
	static HashMap<String,String> parking = new HashMap<>();
	static HashMap<String,Integer> totTime = new HashMap<>();
	//주차 요금 계산
	public static void main(String[] args) {
		int[] fees = {180,5000,10,600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
	
		// 들어왔다 나간 모든 차
		ArrayList<String> cars = new ArrayList<String>();
		
		for(int i=0; i<records.length; i++) {
			String[] str = records[i].split(" ");
			
			// 들어온 차
			if(str[2].equals("IN")) {
				parking.put(str[1], str[0]);
				// 처음 들어온 차
				if(!cars.contains(str[1])) {
					totTime.put(str[1], 0);
					cars.add(str[1]);
				}
			}
			// 나간 차
			else {
				int time = calTime(records[i]);
				parking.remove(str[1]);
				totTime.put(str[1], totTime.get(str[1])+time);
			}
		}
		
		for(Map.Entry<String, String> e : parking.entrySet()) {
			int time = calTime("23:59 "+ e.getKey()+" OUT");
			totTime.put(e.getKey(), totTime.get(e.getKey())+time);
		}
		
		Collections.sort(cars);
		System.out.println(cars);
		int[] res = new int[cars.size()];
		for(int i = 0; i<cars.size(); i++) {
			res[i] = calFee(fees,totTime.get(cars.get(i)));
		}
	}
	
	// 시간 계산
	public static int calTime(String outRecord) {	
		String[] str = outRecord.split(" ");
		
		String in = parking.get(str[1]);
		String out = str[0];
		
		int hour = Integer.parseInt(out.substring(0,2))-Integer.parseInt(in.substring(0,2));
		int min = Integer.parseInt(out.substring(3))-Integer.parseInt(in.substring(3));
		
		return hour * 60 + min;
	}
	
	// 요금 계산
	public static int calFee(int[] fees, int time) {
		time -= fees[0];
		if(time <= 0)
			return fees[1];
		return fees[1] + (int)(Math.ceil((double)time/fees[2]) * fees[3]);
	}
}
