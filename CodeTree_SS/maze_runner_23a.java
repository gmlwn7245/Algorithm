package CodeTree_SS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class  maze_runner_23a {
    static class Point implements Comparable<Point> {
        int x, y;
        boolean exits=false;
        public Point(int x, int y) {
            this.x = x; this.y = y;
        }
        
        @Override
        public int compareTo(Point p) {
            if(getDist(this)==getDist(p)) {
                if(y == p.y)
                    return x - p.x;
                return y - p.y;
            }
            return getDist(this)-getDist(p);
        }
    }
    
    static int getDist(Point p) {
        return Math.abs(exit.x - p.x)+Math.abs(exit.y - p.y);
    }
    
    static int N, M, moveCnt = 0, endCnt = 0;
    static int[][] map;
    static Point exit;
    static Point[] person;
    
    // 1. 사람 이동
    static void move() {
    	// 상하좌우 우선순위
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
                
        for(Point p : person) {
        	if(p.exits) continue;
            int nowDist = getDist(p);
            
            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                if(map[nx][ny]!=0) continue;
                int nextDist = getDist(new Point(nx, ny));
                
                if(nextDist < nowDist) {              	
                	p.x = nx; p.y = ny;
                	moveCnt++;
                	break;
                }
            }
        }
    }
    
    // 2. 작은 정사각형 찾기
    static void findSquare() { 
    	int minR = N, minC = N, rect = N;
    	int maxR = 0, maxC = 0;
    	for(Point p : person) {
    		if(p.exits) continue;
    		
    		// 90도 회전
            int minX = Math.min(p.x, exit.x);
            int minY = Math.min(p.y, exit.y);
            
            int maxX = Math.max(p.x, exit.x);
            int maxY = Math.max(p.y, exit.y);
            
            // 세로길이 및 가로길이
            int resX = maxX - minX+1;
            int resY = maxY - minY+1;
            
            // 길이 통합
            int L = Math.max(resX, resY);
            
            // 길이가 더 긴 경우
            if(rect < L) continue;
            
            // 정사각형이 아닌 경우
            if(resX > resY) {
            	minY = minY-(resX-resY)>0? minY-(resX-resY):0;
            	maxY = minY + (resX)-1;
            }else if(resX < resY) {
            	minX = minX-(resY-resX)>0? minX-(resY-resX):0;
            	maxX = minX + (resY)-1;
            }
            
            // 길이가 더 작은 경우
            if(L < rect) {
            	minR = minX; minC = minY;
            	maxR = maxX; maxC = maxY;
            	rect = L;
            }else {
            	if(minR < minX) continue;
            	if(minR == minX && minC < minY) continue;
            	minR = minX; minC = minY;
            	maxR = maxX; maxC = maxY;
            }
    	}
        
        
        
        // 확정시 회전
        rot(minR, minC, maxR, maxC);
    }
    
    static void rotPoint(int minX, int minY,int maxX, int maxY, Point p) {
    	int i = p.x-minX;
    	int j = p.y-minY;
    	
    	p.x = minX+j; p.y = maxY-i;
    }
    
    static void rot(int minX, int minY, int maxX, int maxY) {
    	// 한변 길이
    	int L = (maxX-minX+1);
    	int[][] tmp = new int[L][L];
    	
    	for(int i=0; i<L; i++) {
    		for(int j=0; j<L; j++) {
    			if(map[minX+i][minY+j]>0) {
    				tmp[i][j] = map[minX+i][minY+j]-1;
    			}
    		}
    	}
    	
    	for(int i=0; i<L; i++) {
    		for(int j=0; j<L; j++) {
    			map[minX+j][maxY-i] = tmp[i][j];
    		}
    	}
    	
    	// exit 이동
    	rotPoint(minX, minY, maxX, maxY, exit);
    	    	
    	// person 이동
    	for(Point p : person) {
    		if(p.exits) continue;
    		if(p.x < minX || p.y < minY || p.x > maxX || p.y > maxY) continue;
    		rotPoint(minX, minY, maxX, maxY,p);
    	}
    }
    
    static void setEnd() {
    	for(Point p : person) {
    		if(!p.exits && p.x == exit.x && p.y == exit.y) {
    			endCnt++;
    			p.exits = true;
    		}
    	}
    }
    
    static void printMap(int t) {
    	System.out.println("=========PRINTMAP "+t);
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) {
    			System.out.print(map[i][j]+" ");
    		}
    		System.out.println();
    	}
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        person = new Point[M];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            
            person[i] = new Point(x,y);
        }
        
        st = new StringTokenizer(br.readLine());
        exit = new Point(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1);
        
        for(int k=1; k<=K; k++) {
        	move();
        	setEnd();
        	if(endCnt == M) break;
            findSquare();
            //printMap(k);
            //System.out.println("----moveCnt => "+moveCnt);
        }
        
        System.out.println(moveCnt);
        System.out.println((exit.x+1) +" "+(exit.y+1));
    }
}