package BOJ_SS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// https://www.acmicpc.net/problem/17825

public class boj17825 {
	static class Horse {
		int num=0;
		boolean isRed = true;
		boolean isFinished = false;
	}
	
	private static int endNum = 40, maxNum = 0;
	private static int[] dice = new int[10];
	private static int[] red = new int[22];
	private static int[] blue = new int[16];
	private static Horse[] horse = new Horse[4];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i<10; i++)
			dice[i] = sc.nextInt();
			
		for(int i=0; i<=21; i++)
			red[i]=i;
		
		for(int i=0; i<=3; i++) {
			blue[i]=10+i*3;
			blue[3+i]=20+i*2;
			blue[6+i]=29-i;
		}
		
		blue[7]=30;
		
		for(int i=0; i<=4; i++) {
			blue[11+i]=25+i*5;
		}
		
		for(int i=0; i<4; i++)
			horse[i] = new Horse();
		
	}
	
	public static void dfs(int tot, int cnt) {
		if(cnt == 10) {
			
			return ;
		}
		
		int moveCnt = dice[cnt];
		for(int i=0; i<4; i++) {
			if(horse[i].isFinished)
				continue;
			
			getNext(i, moveCnt);
		}
	}
	
	public static void getNext(int idx, int moveCnt) {
		Horse h = horse[idx];
		
		// 빨간 선일 경우
		if(h.isRed) {
			switch(h.num) {
			case 10:h.isRed=false;h.num=0;break;
			case 20:h.isRed=false;h.num=4;break;
			case 30:h.isRed=false;h.num=7;break;
			}
		}
		
		if(h.isRed) {
			if(h.num + moveCnt >= 21) {
				h.num = 45;
				h.isFinished = true;
			}else {
				h.num += moveCnt * 2;
			}
		}
		
	}
	
	public static void getBack(int idx, int moveCnt) {
		
	}
	
}
