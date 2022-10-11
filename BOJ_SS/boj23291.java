package BOJ_SS;

import java.util.*;

public class boj23291 {
	public static int[][] map;
	public static int n,k;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		k = sc.nextInt();
		
		map = new int[n][n];
		
		for(int i=0;i<n; i++)
			map[n-1][i] = sc.nextInt();
		
		/*
		 * 			if(cnt == 3) {
				System.out.println("=====start=====");
				for(int i=0; i<n; i++) {
					System.out.print(map[n-1][i]+" ");
				}
				break;
			}
		 * */
		int cnt = 0;
		while(diff()>k) {
			minPlusOne();
			changeBowl();
			divideFish();
			unfold();
			halfFold();
			divideFish();
			unfold();
			cnt++;
		}
		System.out.println(cnt);
	}
	
	public static void halfFold() {
		Stack<Integer> stack = new Stack<>();
		
		// N/2
		for(int i=0; i<n/2; i++) {
			stack.add(map[n-1][i]);
			map[n-1][i] = 0;
		}
		
		for(int i=n/2; i<n; i++) {
			map[n-2][i] = stack.pop();
		}

		
		// N/4
		Stack<Integer> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		
		for(int i=n/2; i<(n/2+n/4); i++) {
			stack1.add(map[n-1][i]);
			stack2.add(map[n-2][i]);
			map[n-1][i] = 0;
			map[n-2][i] = 0;
		}
		
		for(int i=(n/2+n/4); i<n; i++) {
			map[n-3][i] = stack2.pop();
			map[n-4][i] = stack1.pop();
		}
		
	}
	
	public static void divideFish() {
		int[][] r = new int[n][n];
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int num = map[i][j];
				
				if(num==0)
					continue;
				for(int k=0; k<4; k++) {
					int nextX = i+dx[k];
					int nextY = j+dy[k];
					if(nextX < 0 || nextX==n || nextY < 0 || nextY==n)
						continue;
					
					int nextNum = map[nextX][nextY];
					
					if(nextNum == 0)
						continue;
					
					if(nextNum>= num)
						continue;
					
					int d = (num-nextNum)/5;
					if(d>0) {
						r[i][j] -= d;
						r[nextX][nextY] += d;
					}
				}
			}
		}
		
		
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				map[i][j]+=r[i][j];
			}
		}
	}
	
	public static void unfold() {
		int[] tower = new int[2];
		for(int i=0; i<n; i++) {
			if(map[n-2][i]!=0) {
				tower[0] = i;
				break;
			}					
		}
		for(int i=n-1; i>=0; i--) {
			if(map[n-2][i]!=0) {
				tower[1] = i;
				break;
			}
		}
		
		int h = 0;
		for(int i= n-1; i>=0; i--) {
			if(map[i][tower[0]]==0)
				break;
			h++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i=tower[0]; i<=tower[1]; i++) {
			for(int j=n-1; j>=n-h; j--) {
				int num = map[j][i];
				queue.add(num);
				map[j][i] = 0;
			}
		}
		for(int i=0; i<=tower[1]; i++) {
			int num = queue.poll();
			map[n-1][i] = num;
		}
		
	}
	
	
	public static void changeBowl() {
		if(map[n-1][0]!=0) {
			int tmp = map[n-1][0];
			map[n-1][0] = 0;
			map[n-2][1] = tmp;
			changeBowl();
		} else {
			// 쌓아져 있는 부분 확인 
			// idx 0 시작 1 끝
			
			int[] tower = new int[2];
			for(int i=0; i<n; i++) {
				if(map[n-2][i]!=0) {
					tower[0] = i;
					break;
				}					
			}
			for(int i=n-1; i>=0; i--) {
				if(map[n-2][i]!=0) {
					tower[1] = i;
					break;
				}
			}
			
			// 공중부양 가능 확인
			int h=0,w;
			
			for(int i= n-1; i>=0; i--) {
				if(map[i][tower[0]]==0)
					break;
				h++;
			}
			
			w = n-tower[1]-1;
			
			if(h>w) {
				return ;
			}
			// 공중부양
			Queue<Integer> queue = new LinkedList<>();
			for(int i=tower[1]; i>=tower[0]; i--) {
				for(int j=n-1; j>=n-h; j--) {
					int num = map[j][i];
					queue.add(num);
					map[j][i] = 0;
				}
			}
			
			// 쌓기
			int topW = tower[1]-tower[0]+1;
			for(int i=n-2; i>n-2-topW; i--) {
				for(int j=tower[1]+1; j<=tower[1]+h; j++) {
					int num = queue.poll();
					map[i][j] = num;
				}
			}
			
			changeBowl();
		}
	}
	
	public static int diff() {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int i=0; i<n; i++) {
			int num = map[n-1][i];
			if(num > max)
				max = num;
			if(num < min)
				min = num;
		}
		
		return max - min;
	}
	
	public static void minPlusOne() {
		int min = Integer.MAX_VALUE;
		for(int i=0; i<n; i++) {
			int num = map[n-1][i];
			if(num < min)
				min = num;
		}
		
		for(int i=0; i<n; i++) {
			int num = map[n-1][i];
			if(num == min)
				map[n-1][i] += 1;
			
		}
	}
	
}
