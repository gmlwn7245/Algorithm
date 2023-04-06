package trail_error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//��ǥ���� ������ �ִ� Ŭ���� �ϳ� ����
class Position  {
    int dis,x, y;

    public Position(int dis,int x, int y) {
        this.dis=dis;
        this.x = x;
        this.y = y;
    }

    //x y ��ǥ���� ������ Ȯ���ϱ�
    public boolean isSame(Position p) {
        return this.x == p.x && this.y == p.y;
    }


}

public class Ref2 {

    //�ʿ��� �� -> �⺻�迭, ������ ��ġ �迭, ��� ��ġ �迭, ���̽�ķ�� �迭(?)

    static int [][] arr;
    static Position [] store; //������ ��ǥ ���� �迭
    static Position [] people; //��� ��ǥ �迭
    static int n; //���� ũ��
    static int m; //��� ��
    static int [] dx = {-1, 0, 0, 1}; //�� �� �� ��
    static int [] dy = {0, -1, 1, 0};

   public static void main(String[] args) throws IOException {

       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       n = Integer.parseInt(st.nextToken());
       m = Integer.parseInt(st.nextToken());

       arr = new int [n][n];

       //�迭 ä���
       for(int i=0; i<n; i++) {
           st = new StringTokenizer(br.readLine());
           for(int j=0; j<n; j++) {
               arr[i][j] = Integer.parseInt(st.nextToken());
           }
       }
       //�������� ��ǥ�� ��
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
