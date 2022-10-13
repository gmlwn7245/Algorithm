package BOJ_SS;

import java.util.*;

public class boj19236 {
   private static int sharkDir=0;
   private static int maxSum = 0;
   private static int[][] map = new int[4][4];
   private static HashMap<Integer, int[]> locations = new HashMap<>();
   private static HashMap<Integer, Integer> dirs = new HashMap<>();
     
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
       
      for(int i=0; i<4; i++) {
         for(int j=0; j<4; j++) {
            int num = sc.nextInt();
            int dir = sc.nextInt();
            dirs.put(num, dir);
            locations.put(num, new int[] {i,j});
            map[i][j] = num;
         }
      }
      
      // dirs : ��Ƹ��� ����� 0 
      // map : ��ĭ 0
      
      // ��� �ʱⰪ 
      // (0,0) ����⸦ �԰� ��ġ �� ������ ������
      int eatenFish = map[0][0];
      sharkDir = dirs.get(eatenFish);
      dirs.put(eatenFish, 0);
      map[0][0]=0;	
      maxSum = eatenFish;
      
      moveFish(0,0);
      dfs(0,maxSum, 0, 0, sharkDir);
      
      System.out.println(maxSum);
   }
   
   
   public static void dfs(int printCnt,int eatSum, int shX, int shY, int shD) {
	   // ���� dfs ������ ��� �⺻ ������ ����
	   int nowSharkX = shX;
	   int nowSharkY = shY;
	   int nowSharkDir = shD;
	   
	   // ���� dfs ������ HM,map ���� ���� ����
	   HashMap<Integer, int[]> locationCopy = new HashMap<>();
	   HashMap<Integer, Integer> dirsCopy = new HashMap<>();
	   int[][] mapCopy = new int[4][4];
	   
	   for(int j=1; j<=16;j++) {
		   int x = locations.get(j)[0];
		   int y = locations.get(j)[1];
		   locationCopy.put(j, new int[]{x,y});
		   dirsCopy.put(j, dirs.get(j));
	   }
	   
	   for(int j=0; j<4; j++)
		   for(int k=0; k<4; k++) {
			   int num = map[j][k];
			   mapCopy[j][k] = num;
		   }   
	   
	   
	   
	   
	   // ����� ������ ���� ����� ��ǥ
	   int[] nextShark = getNextPoint(shX, shY, shD);
	   
	   int chDir = shD;
	   int nextSharkX = nextShark[0];
	   int nextSharkY = nextShark[1];
	   
	   if(!isInArea(nextSharkX, nextSharkY)) {
		   if(eatSum > maxSum)
			   maxSum = eatSum;
		   return ;
	   }
	   

	   
	   // �ش� ���� ��� ĭ Ž��
	   for(int i=0; i<3; i++) {
		   
		   // ���� Ž�� ĭ�� ���� ���� ��� �ִ밪 ���ϰ� return
		   if(!isInArea(nextSharkX,nextSharkY)) {
			   if(eatSum > maxSum)
				   maxSum = eatSum;
			   return ;
		   }
		   
//		   System.out.println("--------��"+printCnt+"/"+i+"--------");
//		   printMap();
//		   System.out.println("========");
//		   printMaps(mapCopy);
		   
		   // ���� Ž�� ĭ�� ��ĭ�� ��� Ž�� X
		   if(map[nextSharkX][nextSharkY]==0) {
			   nextShark = getNextPoint(nextSharkX, nextSharkY, chDir);
			   nextSharkX = nextShark[0];
			   nextSharkY = nextShark[1];
			   continue;	   
		   }
			   
		   
		   // Ž�� ������ ĭ�� ����⸦ �԰� �� ������ ����
		   int eatenFish = map[nextSharkX][nextSharkY];
		   int fishDir = dirs.get(eatenFish);
		   
		   // �ش� ĭ�� ��ĭ���� ����
		   dirs.put(eatenFish, 0);
		   map[nextSharkX][nextSharkY] = 0;
		   
		   
		   // ����� �̵�
		   moveFish(nextSharkX, nextSharkY);
		   
		   // ��� 
		   dfs(printCnt+1,eatSum+eatenFish,nextSharkX, nextSharkY, fishDir);
		   
		  
		   // ������ ���� �ʱ�ȭ		   
		   for(int j=1; j<=16;j++) {
			   int x = locationCopy.get(j)[0];
			   int y = locationCopy.get(j)[1];
			   locations.put(j, new int[]{x,y});
			   dirs.put(j, dirsCopy.get(j));
		   }
		   
		   for(int j=0; j<4; j++)
			   for(int k=0; k<4; k++) {
				   int num = mapCopy[j][k];
				   map[j][k] = num;
			   }
		   
		   
//		   System.out.println("--------��"+printCnt+"/"+i+"--------");
//		   printMap();
//		   System.out.println("========");
//		   printMaps(mapCopy);
		   
		   
		   nextShark = getNextPoint(nextSharkX, nextSharkY, chDir);
		   nextSharkX = nextShark[0];
		   nextSharkY = nextShark[1];
	   }
   }
   
   
   public static void moveFish(int shX, int shY) {
      for(int i=1; i<=16; i++) {
    	  
    	 if(dirs.get(i)==0)
    		 continue;
    	 
         int[] loc = locations.get(i);
         int x = loc[0];
         int y = loc[1];
         
         
         int nextFish=0 , chDir=dirs.get(i);
         int[] nextLoc = getNextPoint(x,y,dirs.get(i));
         
         
         while(!isAbleToMove(nextLoc[0], nextLoc[1], shX, shY)) {
        	 chDir = changeDir(chDir);
        	 nextLoc = getNextPoint(x,y,chDir);
         }
         

         dirs.put(i, chDir);
    	 nextFish = getNum(x,y,chDir);
    	 
    	 // ��ĭ�ϰ��
         if(nextFish == 0) {
        	 map[nextLoc[0]][nextLoc[1]] = i;
        	 loc[0] = nextLoc[0];
        	 loc[1] = nextLoc[1];
        	 map[x][y] = 0;
         }else {
        	 changeLoc(i, nextFish);
         }
         
      }  
   }
   
   // ����� ��ȣ�� ��ġ �ٲٱ�
   public static void changeLoc(int now, int next) {       
       int[] nowLoc = locations.get(now);
       int nowX = nowLoc[0];
       int nowY = nowLoc[1];
       int nowDir = dirs.get(now);
       
       int[] nextLoc = locations.get(next);
       int nextX = nextLoc[0];
       int nextY = nextLoc[1];
       int nextDir = dirs.get(next);
       
       
       nowLoc[0] = nextX;
       nowLoc[1] = nextY;
       map[nowX][nowY] = next;
       locations.put(now, new int[] {nextX, nextY});
       
       nextLoc[0] = nowX;
       nextLoc[1] = nowY;
       map[nextX][nextY] = now;
       locations.put(next, new int[] {nowX, nowY});
       
    }
   
   public static void printMap() {
	   for(int a=0; a<4; a++) {
	    	  for(int b=0; b<4;b++) {
	        	  System.out.print(map[a][b] + " ");
	          }
	    	  System.out.println();
	      }
   }
   
   public static void printMaps(int[][] maps) {
	   for(int a=0; a<4; a++) {
	    	  for(int b=0; b<4;b++) {
	        	  System.out.print(maps[a][b] + " ");
	          }
	    	  System.out.println();
	      }
   }
   
   // 45�� ����
   public static int changeDir(int dir) {
      if(dir == 8)
         return 1;
      else
         return dir + 1;
   }
   
   
   public static int[] getNextPoint(int x, int y,int dir) {
      int nextX=x, nextY=y;
      
      switch(dir) {
      case 1:{
         nextX -= 1;
         break;
      }
      case 2: {
         nextX -= 1;
         nextY -= 1;
         break;
      }
      case 3: {
         nextY -= 1;
         break;
      }
      case 4: {
         nextX += 1;
         nextY -= 1;
         break;
      }
      case 5: {
         nextX += 1;
         break;
      }
      case 6: {
         nextX += 1;
         nextY += 1;
         break;
      }
      case 7: {
         nextY += 1;
         break;
      }
      case 8: {
         nextX -= 1;
         nextY += 1;
         break;
      }
      }
      
      return new int[] {nextX, nextY};
   }
   
   public static int getNum(int x, int y, int dir) {
	   int[] p = getNextPoint(x,y,dir);
	   return map[p[0]][p[1]];
   }
   
   public static boolean isAbleToMove(int x, int y, int shX, int shY) {
      return isInArea(x,y) && (shX != x || shY != y);
   }
   
   public static boolean isInArea(int x, int y) {
      return x>=0 && x<4 && y>=0 && y<4;
   }
}