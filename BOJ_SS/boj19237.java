package BOJ_SS;

import java.util.*;

class Point {
	public int x,y,dir;
	// 현재 방향 1~4 , 우선순위 0~3
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
   
   // 있음 1 쫓겨남 0
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
      
      // 각 상어의 현재 방향
      for(int i=1; i<=m; i++) {
    	  Point point = p[i];
    	  point.dir = sc.nextInt();
    	  
    	  // 상어 유무 초기값
    	  isAliveShark.put(i, 1);
      }
    
      
      // 각 상어마다 우선순위
      for(int i=1; i<=m; i++) {		
    	  Point point = p[i];
    	  
    	  //우선순위 4개로 고정
    	  //위1 아래2 왼3 오4
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
      
      // ##################상어 이동####################
      int cnt = 0;
      while(cnt<1000) {
    	  if(aliveCnt==1)
    		  break;
    	  
    	  int[][] moveMap = new int[n][n];
    	  int[] moveDir = new int[m+1];
    	  
    	  for(int i=1; i<=m; i++) {
    		  
    		  // 쫓겨난 상어일 경우
    		  if(isAliveShark.get(i)==0)
    			  continue;
    		  
    		  Point point = p[i];
    		  
    		  // 현재 방향에 따른 우선순위 가져옴
    		  int[] dirs = point.priory[point.dir];
    		  
    		  // 자신의 냄새가 있는 ##방향## 저장
    		  ArrayList<Integer> myArea = new ArrayList<>();
    		  
    		  // 이동할 칸 있었는지 확인용
    		  boolean isAbleToMove = false;
    		  
    		  // 우선순위에 따라 탐색
    		  for(int j=0; j<4; j++) {
    			  int[] nextPoint = getNextPoint(point.x, point.y, dirs[j]);
    			  int nextX = nextPoint[0];
    			  int nextY = nextPoint[1];
    			  
    			  if(!isInArea(nextX, nextY))
    				  continue;    			  
    			  
    			  // 내 영역일 경우
    			  if(sharkMap[nextX][nextY]==i) {
    				  myArea.add(dirs[j]);
    				  continue;
    			  }
    			  
    			  // 아무것도 없는 경우 - 이동 가능
    			  if(sharkMap[nextX][nextY]==0) {
    				  isAbleToMove = true;
    				  // 이미 우선순위 높은 상어가 해당 좌표로 이동 계획한 경우
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
	    * 방향
	    * 1 위 
	    * 2 아래
	    * 3 왼쪽
	    * 4 오른쪽
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
   
   // 냄새 cnt 1개씩 없애기
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

   
   // 위치에서 벗어나는가
   public static boolean isInArea(int x, int y) {
	   return x>=0 && x<n && y>=0 && y<n;
   }
 }