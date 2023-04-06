package trail_error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//좌표값을 가지고 있는 클래스 하나 생성
class Position  {
    int dis,x, y;

    public Position(int dis,int x, int y) {
        this.dis=dis;
        this.x = x;
        this.y = y;
    }

    //x y 좌표값이 같은지 확인하기
    public boolean isSame(Position p) {
        return this.x == p.x && this.y == p.y;
    }


}

public class Ref2 {

    //필요한 것 -> 기본배열, 편의점 위치 배열, 사람 위치 배열, 베이스캠프 배열(?)

    static int [][] arr;
    static Position [] store; //편의점 좌표 정보 배열
    static Position [] people; //사람 좌표 배열
    static int n; //격자 크기
    static int m; //사람 수
    static int [] dx = {-1, 0, 0, 1}; //위 왼 오 아
    static int [] dy = {0, -1, 1, 0};

   public static void main(String[] args) throws IOException {

       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       n = Integer.parseInt(st.nextToken());
       m = Integer.parseInt(st.nextToken());

       arr = new int [n][n];

       //배열 채우기
       for(int i=0; i<n; i++) {
           st = new StringTokenizer(br.readLine());
           for(int j=0; j<n; j++) {
               arr[i][j] = Integer.parseInt(st.nextToken());
           }
       }
       //편의점의 좌표를 줌
       List<Position> list=  new ArrayList<>();

       boolean [][] personVisit= new boolean[n][n];
       int max=0;

       for(int i=1; i<=m; i++) {
           st = new StringTokenizer(br.readLine());
           int x = Integer.parseInt(st.nextToken())-1;
           int y = Integer.parseInt(st.nextToken())-1;
           list.clear();
           int [][]visit= new int[n][n];
           Queue<Integer> q= new LinkedList<>();
           q.add(x);
           q.add(y);
           visit[x][y]=1;
           personVisit[x][y]=true;

           while (!q.isEmpty()){
               int curX=q.poll();
               int curY=q.poll();

               for(int j=0;j<4;j++){
                   int nx=curX+dx[j];
                   int ny=curY+dy[j];
                   if(0<=nx && nx <n && 0<=ny && ny<n) {
                       if(visit[nx][ny]==0 && !personVisit[nx][ny]){
                           visit[nx][ny]=visit[curX][curY]+1;
                           q.add(nx);
                           q.add(ny);
                           if(arr[nx][ny]==1){
                               list.add(new Position(visit[nx][ny],nx,ny));
                           }

                       }

                   }

               }
           }



           Collections.sort(list,new Comparator<Position>() {
               @Override
               public int compare(Position o1, Position o2) {
                   if(o1.dis==o2.dis){
                       if(o1.x==o2.x){
                           return Integer.compare(o1.y, o2.y);
                       }
                       return Integer.compare(o1.x, o2.x);
                   }
                   return Integer.compare(o1.dis, o2.dis);
               }
           });

           Position position = list.get(0);
           max=Math.max(i+position.dis,max);
           personVisit[position.x][position.y]=true;
       }

       System.out.println(max-1);

    }

}
