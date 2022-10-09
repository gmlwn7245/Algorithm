package BOJ_SS;

import java.util.*;

public class boj14891 {
   public static int[][] wheel;
   public static int[][] idx;
   public static int[] dirs;
   public static boolean[] isRot;
   
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
         
         checkRot(n, dir);
         
         for(int j=0; j<4; j++) {
        	 if(isRot[j])
        		 rot(j);
         }
      }
      System.out.println(totScore());
   }
   
   public static void checkRot(int n, int dir) {
	   isRot = new boolean[4];
	   dirs = new int[4];
	   // n이 짝수
	   // dir 값 : 1 시계방향 -1 반시계방향
	   if(n%2==0) {
		   dirs[0]=dir;
		   dirs[2]=dir;
		   
		   dirs[1]=-dir;
		   dirs[3]=-dir;
	   }else {
		   dirs[0]=-dir;
		   dirs[2]=-dir;
		   
		   dirs[1]=dir;
		   dirs[3]=dir;
	   }
	   
	   // 오른쪽 idx[n][0]
	   // 왼쪽 idx[n][1]
	   
	   isRot[n]=true;
	   
	   boolean notRot = false;
	   for(int i=n-1; i>=0; i--) {
		   if(notRot) {
			   isRot[i] = false;
			   continue;
		   }
			   
		   if(wheel[i][idx[i][0]]==wheel[i+1][idx[i+1][1]]) {
			   isRot[i] = false;
			   notRot = true;
		   } else {
			   isRot[i] = true;
		   }
			   
	   }
	   
	   notRot = false;
	   
	   for(int i=n+1; i<4; i++) {
		   if(notRot) {
			   isRot[i] = false;
			   continue;
		   }
			   
		   if(wheel[i][idx[i][1]]==wheel[i-1][idx[i-1][0]]) {
			   isRot[i] = false;
			   notRot = true;
		   } else {
			   isRot[i] = true;
		   }
	   }
   }
   
   public static void rot(int n) {
	   /* dir 값 */
	   // 시계방향 회전 : 1 
	   // 반시계방향 회전 : -1
	   
	   /* idx 값 */
	   // 시계방향 회전 : -1 
	   // 반시계방향 회전 : +1
	   
	   int dir = dirs[n];
	   
	   if(dir == -1) {
		   idx[n][0] = (idx[n][0]+1) % 8;
		   idx[n][1] = (idx[n][1]+1) % 8;
	   } else {
		   idx[n][0] = (idx[n][0]+7) % 8;
		   idx[n][1] = (idx[n][1]+7) % 8;
	   }
   }
   
   public static int totScore() {
	   int res = 0;
	   
	   for(int i=0; i<4; i++) {
		   int left = idx[i][1];
		   
		   int top = (left+2)%8;
		   res += wheel[i][top] * Math.pow(2, i);
	   }
	   return res;
   }
   
}