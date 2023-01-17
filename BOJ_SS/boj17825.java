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
	
	private static int maxNum = 0;				// �ִ� ����
	private static int[] dice = new int[10];	// �ֻ��� ����
	private static int[] red = new int[22];		// red ĭ
	private static int[] blue = new int[16];	// blue ĭ
	private static Horse[] horse = new Horse[4];	// ���� ��ġ ���
	private static Stack<Horse>[] hist = new Stack[4];	// ���� ��ġ ���
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
		
		// �ֻ��� ����
		int moveCnt = dice[cnt];
		
		for(int i=0; i<4; i++) {
			// �̹� ���� ��� Ž�� X
			if(horse[i].isFinished)
				continue;
			
			// �浹�ϴ� ���� ���� �̵������� ��� (=>�̹� ���� �ݿ���)
			if(getNext(i, moveCnt)) {
				// ���� �� Ž��
				dfs(tot+horse[i].num, cnt+1);
				
				// �ٽ� ��������
				getBack(i);
			}
		}
	}
	
	public static boolean getNext(int idx, int moveCnt) {
		Horse h = horse[idx];
		
		// �ٷ� �ݿ����� �ʰ� ���� ���� ����
		int num = h.num;
		int loc = h.loc;
		boolean isRed = h.isRed;
		boolean isFinished = false;
		
		// �̸� History�� �־��� (=> �� ��ü ����)
		hist[idx].push(new Horse(num, loc, isRed, isFinished));
		
		/* �̵� �� ĭ ���� */		
		// ���� ���� ���
		if(isRed) {
			// RB ����� ��ġ üũ
			switch(loc) {
			case 5:isRed=false;loc=0;break;
			case 10:isRed=false;loc=4;break;
			case 15:isRed=false;loc=7;break;
			}
		}else {	// �Ķ� ���� ���
			// �߰����� �Ѿ ��� üũ
			switch(loc) {
			case 3:loc=10;break;
			case 6:loc=10;break;
			}
		}
		
		/* �̵� */
		// ���� ���� ���
		if(isRed) {
			loc += moveCnt;
			
			// ���� or ������ ��� 
			if(loc >= 21) {
				loc = 21;
				isFinished = true;
			}
				
			num = red[loc];
			
			// 40 ĭ�� ����� �� blue ��Ʈ�� ����
			if(loc==20) {
				isRed = false;
				loc = 14;
			}
		}else { // �Ķ� ���� ���
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
			
			// ���� or ������ ���
			if(loc >= 15) {
				loc = 15;
				isFinished = true;
			}
			
			num = blue[loc];
		}
		
		// ������ ��� or �̵��� ��ġ�� �ٸ� ���� ���� ���
		if(isFinished || isNotOverlapped(loc, isRed)) {
			horse[idx] = new Horse(num, loc, isRed, isFinished);
			return true;
		}
		
		hist[idx].pop();
		return false;
	}
	
	public static boolean isNotOverlapped(int loc, boolean isRed) {
		for(Horse h : horse) {
			
			// �̹� �������ִ� ���� ������ X
			if(h.isFinished)
				continue;
			
			// ���� ���󿡼� ���� ��ġ�� ���
			if(h.isRed == isRed && h.loc == loc) {
				if(h.loc == loc)
					return false;
			}	
		}
		return true;
	}
	
	public static void getBack(int idx) {
		// ���� ��� �����ͼ� �����
		horse[idx] = hist[idx].pop();
	}
	
}
