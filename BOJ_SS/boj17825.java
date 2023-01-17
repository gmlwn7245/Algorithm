package BOJ_SS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class boj17825 {
	static class Horse {
		int num = 0;
		int loc = 0;
		boolean isRed = true;
		boolean isFinished = false;
		
		public Horse() {}
		
		public Horse(int num, int loc, boolean isRed, boolean isFinished) {
			this.num = num;
			this.loc = loc;
			this.isRed = isRed;
			this.isFinished = isFinished;
		}
	}
	
	private static int maxNum = 0;				// 최댓값 저장
	private static int[] dice = new int[10];	// 주사위 정보
	private static int[] red = new int[22];		// red 칸
	private static int[] blue = new int[16];	// blue 칸
	private static Horse[] horse = new Horse[4];	// 현재 위치 기록
	private static Stack<Horse>[] hist = new Stack[4];	// 이전 위치 기록
	private static int[] order = new int[10];
	private static boolean check= false;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i<10; i++)
			dice[i] = sc.nextInt();
			
		for(int i=0; i<=20; i++)
			red[i]=i*2;
		
		for(int i=0; i<=3; i++) {
			blue[i]=10+i*3;
			blue[4+i]=20+i*2;
			blue[7+i]=29-i;
		}
		
		blue[7]=30;
		
		for(int i=0; i<=3; i++) {
			blue[11+i]=25+i*5;
		}
		
		for(int i=0; i<4; i++) {
			horse[i] = new Horse();
			hist[i] =  new Stack<Horse>();
		}
			
		dfs(0,0);
		System.out.println(maxNum);
	}
	
	public static void dfs(int tot, int cnt) {
		if(cnt == 10) {
			maxNum = Math.max(maxNum, tot);
			return ;
		}
		
		// 주사위 숫자
		int moveCnt = dice[cnt];
		
		for(int i=0; i<4; i++) {
			// 이미 끝난 경우 탐색 X
			if(horse[i].isFinished)
				continue;
			
			// 충돌하는 말이 없어 이동가능한 경우 (=>이미 상태 반영됨)
			if(getNext(i, moveCnt)) {
				// 다음 턴 탐색
				dfs(tot+horse[i].num, cnt+1);
				
				// 다시 돌려놓기
				getBack(i);
			}
		}
	}
	
	public static boolean getNext(int idx, int moveCnt) {
		Horse h = horse[idx];
		
		// 바로 반영되지 않게 따로 변수 선언
		int num = h.num;
		int loc = h.loc;
		boolean isRed = h.isRed;
		boolean isFinished = false;
		
		// 미리 History에 넣어줌 (=> 새 객체 생성)
		hist[idx].push(new Horse(num, loc, isRed, isFinished));
		
		/* 이동 전 칸 조정 */		
		// 빨간 선일 경우
		if(isRed) {
			// RB 경계인 위치 체크
			switch(loc) {
			case 5:isRed=false;loc=0;break;
			case 10:isRed=false;loc=4;break;
			case 15:isRed=false;loc=7;break;
			}
		}else {	// 파란 선일 경우
			// 중간으로 넘어갈 경우 체크
			switch(loc) {
			case 3:loc=10;break;
			case 6:loc=10;break;
			}
		}
		
		/* 이동 */
		// 빨간 선인 경우
		if(isRed) {
			loc += moveCnt;
			
			// 골인 or 지났을 경우 
			if(loc >= 21) {
				loc = 21;
				isFinished = true;
			}
				
			num = red[loc];
			
			// 40 칸을 밟았을 때 blue 루트로 통일
			if(loc==20) {
				isRed = false;
				loc = 14;
			}
		}else { // 파란 선인 경우
			if(loc <= 3) {
				if(loc+moveCnt > 3) {
					moveCnt -= 3-loc;
					loc = 10;
				}
			}else if(loc <= 6) {
				if(loc + moveCnt > 6) {
					moveCnt -= 6-loc;
					loc = 10;
				}
			}
			
			loc += moveCnt;
			
			// 골인 or 지났을 경우
			if(loc >= 15) {
				loc = 15;
				isFinished = true;
			}
			
			num = blue[loc];
		}
		
		// 도착한 경우 or 이동할 위치에 다른 말이 없는 경우
		if(isFinished || isNotOverlapped(loc, isRed)) {
			horse[idx] = new Horse(num, loc, isRed, isFinished);
			return true;
		}
		
		hist[idx].pop();
		return false;
	}
	
	public static boolean isNotOverlapped(int loc, boolean isRed) {
		for(Horse h : horse) {
			
			// 이미 도착해있는 말은 비교하지 X
			if(h.isFinished)
				continue;
			
			// 같은 색상에서 같은 위치일 경우
			if(h.isRed == isRed && h.loc == loc) {
				if(h.loc == loc)
					return false;
			}	
		}
		return true;
	}
	
	public static void getBack(int idx) {
		// 이전 기록 가져와서 덮어쓰기
		horse[idx] = hist[idx].pop();
	}
	
}
