package trail_error;

import java.util.*;


/*
 * 문제에서 톱니바퀴는 동시에 돌려야 함
 * 이 방법은 n 중심으로 양옆으로 하나씩 뻗어나가는 코드 
 * 
 */
public class boj14891 {
   public static int[][] wheel;
   public static int[][] idx;
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      
      wheel = new int[4][8];
      idx = new int[4][2];
      
      for(int i=0; i<4; i++) {
         String str = sc.nextLine();
         for(int j=0; j<8; j++)
            wheel[i][j] = str.charAt(j) - '0';
          
         // 오른쪽 인덱스 2 , 왼쪽 인덱스 6
         idx[i][0] = 2;
         idx[i][1] = 6;
      }
      
      int k = sc.nextInt();
      
      for(int i=0; i<k; i++) {
    	  
         int n = sc.nextInt()-1;
         int dir = sc.nextInt();
         System.out.println("======"+i+"번째=====");
         rot(n,dir);
         
         if(dir==1) dir = -1;
         else dir = 1;
         
         
         dfs(-1,n,dir);
         dfs(1,n,dir);
      }
      System.out.println(totScore());
   }
   
   public static void dfs(int order, int n, int dir) {
	   // order -1 : 왼쪽, order 1 : 오른쪽
	   if(order==-1 && n==0)
		   return ;
	   if(order==1 && n==3)
		   return ;
	   
	   if(order==1) {
		   // 현재 n의 오른쪽 값
		   int nowIdx = idx[n][0];
		   int nowRight = wheel[n][nowIdx];
		   
		   // n+1의 왼쪽 값
		   int nextIdx = idx[n+1][1];
		   int nextLeft = wheel[n+1][nextIdx];
		   
		   if(nowRight == nextLeft)
			   return ;
		   else {
			   rot(n+1, dir);
			   
			   if(dir==-1)
				   dfs(order, n+1, 1);
			   else
				   dfs(order, n+1, -1);
		   }
	   } else if(order==-1) {
		   int nowIdx = idx[n][1];
		   int nowLeft = wheel[n][nowIdx];
		   
		   int nextIdx = idx[n-1][0];
		   int nextRight = wheel[n-1][nextIdx];
		   
		   if(nowLeft == nextRight)
			   return ;
		   else {
			   rot(n-1, dir);
			   if(dir==-1)
				   dfs(order, n-1, 1);
			   else
				   dfs(order, n-1, -1);
		   }
	   }
	   
   }
   
   public static void rot(int n, int dir) {
	   System.out.println("회전->"+n+"번째 톱니바퀴 "+dir);
	   /* dir 값 */
	   // 시계방향 회전 : 1 
	   // 반시계방향 회전 : -1
	   
	   /* idx 값 */
	   // 시계방향 회전 : -1 
	   // 반시계방향 회전 : +1
	   
	   if(dir == -1) {
		   System.out.println("회전1 반시계방향 -1");
		   System.out.println(idx[n][0]+"/"+idx[n][1]);
		   idx[n][0] = (idx[n][0]+1) % 8;
		   idx[n][1] = (idx[n][1]+1) % 8;
		   System.out.println(idx[n][0]+"/"+idx[n][1]);
	   } else {
		   System.out.println("회전2 시계방향 +1");
		   System.out.println(idx[n][0]+"/"+idx[n][1]);
		   idx[n][0] = (idx[n][0]+7) % 8;
		   idx[n][1] = (idx[n][1]+7) % 8;
		   System.out.println(idx[n][0]+"/"+idx[n][1]);
	   }
   }
   
   public static int totScore() {
	   int res = 0;
	   System.out.println("최종결산!!!!!!");
	   
	   for(int i=0; i<4; i++) {
		   int left = idx[i][1];
		   
		   int top = (left+2)%8;
		   res += wheel[i][top]*Math.pow(2, i);
	   }
	   return res;
   }
   
}