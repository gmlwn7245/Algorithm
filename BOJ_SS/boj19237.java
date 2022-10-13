package BOJ_SS;

import java.util.*;

class Point {
	public int x,y,dir;
	// ���� ���� 1~4 , �켱���� 0~3
	public int[][] priory = new int[10][10];
	public Point (int x, int y) {
		this.x = x;
		this.y = y;
		this.dir = 0;
	}
}

public class boj19237 {
   private static int n=0, m=0, k=0, aliveCnt=0;
   
   private static int[][] sharkCnt;
   private static int[][] sharkMap;
   
   // ���� 1 �Ѱܳ� 0
   private static HashMap<Integer, Integer> isAliveShark = new HashMap<>();
   private static Point[] p;
   
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      
      n = sc.nextInt();
      m = sc.nextInt();
      k = sc.nextInt();
      
      sharkMap = new int[n][n];
      sharkCnt = new int[n][n];
      p = new Point[m+1];
      aliveCnt = m;
      
      for(int i=0; i<n; i++) {
    	  for(int j=0; j<n; j++) {
    		  int num = sc.nextInt();
    		  if(num!=0) {
    			  p[num] = new Point(i,j);
    			  sharkMap[i][j] = num;
    			  sharkCnt[i][j] = k;
    		  }
    	  }
      }
      
      // �� ����� ���� ����
      for(int i=1; i<=m; i++) {
    	  Point point = p[i];
    	  point.dir = sc.nextInt();
    	  
    	  // ��� ���� �ʱⰪ
    	  isAliveShark.put(i, 1);
      }
    
      
      // �� ���� �켱����
      for(int i=1; i<=m; i++) {		
    	  Point point = p[i];
    	  
    	  //�켱���� 4���� ����
    	  //��1 �Ʒ�2 ��3 ��4
    	  for(int k=0; k<4; k++) {	
    		  point.priory[1][k] = sc.nextInt();
		  }
    	  for(int k=0; k<4; k++) {	
    		  point.priory[2][k] = sc.nextInt();
		  }
    	  for(int k=0; k<4; k++) {	
    		  point.priory[3][k] = sc.nextInt();
		  }
    	  for(int k=0; k<4; k++) {	
    		  point.priory[4][k] = sc.nextInt();
		  }
      }
      
      // ##################��� �̵�####################
      int cnt = 0;
      while(cnt<1000) {
    	  if(aliveCnt==1)
    		  break;
    	  
    	  int[][] moveMap = new int[n][n];
    	  int[] moveDir = new int[m+1];
    	  
    	  for(int i=1; i<=m; i++) {
    		  
    		  // �Ѱܳ� ����� ���
    		  if(isAliveShark.get(i)==0)
    			  continue;
    		  
    		  Point point = p[i];
    		  
    		  // ���� ���⿡ ���� �켱���� ������
    		  int[] dirs = point.priory[point.dir];
    		  
    		  // �ڽ��� ������ �ִ� ##����## ����
    		  ArrayList<Integer> myArea = new ArrayList<>();
    		  
    		  // �̵��� ĭ �־����� Ȯ�ο�
    		  boolean isAbleToMove = false;
    		  
    		  // �켱������ ���� Ž��
    		  for(int j=0; j<4; j++) {
    			  int[] nextPoint = getNextPoint(point.x, point.y, dirs[j]);
    			  int nextX = nextPoint[0];
    			  int nextY = nextPoint[1];
    			  
    			  if(!isInArea(nextX, nextY))
    				  continue;    			  
    			  
    			  // �� ������ ���
    			  if(sharkMap[nextX][nextY]==i) {
    				  myArea.add(dirs[j]);
    				  continue;
    			  }
    			  
    			  // �ƹ��͵� ���� ��� - �̵� ����
    			  if(sharkMap[nextX][nextY]==0) {
    				  isAbleToMove = true;
    				  // �̹� �켱���� ���� �� �ش� ��ǥ�� �̵� ��ȹ�� ���
    				  if(moveMap[nextX][nextY]!=0) {
    					  aliveCnt-=1;
    					  isAliveShark.put(i, 0);
    					  break;
    				  }
    				  moveMap[nextX][nextY] = i;
    				  moveDir[i] = dirs[j];
    				  break;
    			  }
    		  }
    		  
    		  if(isAbleToMove)
    			  continue;
    		  
    		  if(myArea.size()==0) {
    			  moveMap[point.x][point.y] = i;
    			  moveDir[i] = point.dir;
    			  continue;
    		  }
    			  
    		  
    		  int[] nextPoint = getNextPoint(point.x, point.y, myArea.get(0));
			  int nextX = nextPoint[0];
			  int nextY = nextPoint[1];
			  
			  if(moveMap[nextX][nextY]!=0) {
				  aliveCnt-=1;
				  isAliveShark.put(i, 0);
				  continue;
			  }
			  
			  moveMap[nextX][nextY] = i;
			  moveDir[i] = myArea.get(0);
    	  }
    	  
    	  removeSharkCnt();
    	  
    	  for(int i=0; i<n; i++) {
    		  for(int j=0; j<n; j++) {
    			  int sharkNum = moveMap[i][j];
    			  if(sharkNum==0)
    				  continue;
    			  
    			  sharkCnt[i][j] = k;
    			  sharkMap[i][j] = sharkNum;
    			  
    			  Point point = p[sharkNum];
    			  point.dir = moveDir[sharkNum];
    			  point.x = i;
    			  point.y = j;
    		  }
    	  }
    	  
    	  
    	  
    	  cnt++;
      }
      
      if(aliveCnt>1)
    	  System.out.println(-1);
      else
    	  System.out.println(cnt);
      
   }
   
   public static int[] getNextPoint(int x, int y, int dir) {
	   int nextX = x;
	   int nextY = y;
	   
	   /*
	    * ����
	    * 1 �� 
	    * 2 �Ʒ�
	    * 3 ����
	    * 4 ������
	    * */
	   
	   switch(dir) {
	   case 1 : {
		   nextX -=1;
		   break;
	   }
	   case 2 : {
		   nextX += 1;
		   break;
	   }
	   case 3 : {
		   nextY -= 1;
		   break;
	   }
	   case 4 : {
		   nextY += 1;
		   break;
	   }
	   }
	   
	   return new int[] {nextX, nextY};
   }
   
   // ���� cnt 1���� ���ֱ�
   public static void removeSharkCnt() {
	   for(int i=0; i<n; i++)
		   for(int j=0; j<n; j++) {
			   if(sharkCnt[i][j]==0)
				   continue;
			   
			   sharkCnt[i][j] -= 1;
			   
			   if(sharkCnt[i][j]==0)
				   sharkMap[i][j] = 0;
		   }
   }

   
   // ��ġ���� ����°�
   public static boolean isInArea(int x, int y) {
	   return x>=0 && x<n && y>=0 && y<n;
   }
 }