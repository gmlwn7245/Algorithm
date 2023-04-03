package trail_error;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class test2 {

    static public int M, N, D, answer;
    // �ִ��� ���� ���� ��� ��ġ�� ��ƾ� �ϹǷ�
    // ���� �迭 gameMap | �׽�Ʈ�� ������ map
    static public int[][] gameMap, map;
    static public boolean[][] visited;
    // ������ ��ġ�� ��� ����Ʈ
    static public List<Node> castle;
    // �� ��ü�� �������� �ʰ� ��� ������ ����
    static int sR, eR;
    // �Ʒ��� �������������鼭 ��,��,�� ������ (������ �켱������ ������)
    static int dx[] = { 0, -1, 0 };
    static int dy[] = { -1, 0, 1 };


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // ### �Է� �ޱ� ###
        M = Integer.parseInt(sc.next());
        N = Integer.parseInt(sc.next());
        D = Integer.parseInt(sc.next());

        gameMap = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                gameMap[i][j] = Integer.parseInt(sc.next());
            }
        }
        // ### �Է� �Ϸ� ###

        // �־��� �� ũ�⿡ �°� ��(Castle)�� �����.
        castle = new ArrayList<Node>();
        for (int i = 0; i < N; i++) {
            castle.add(new Node(M, i));
        }

        // ���� ��ġ�� �ü����� ����Ʈ
        List<Node> archerList = new ArrayList<>();
        // ��� ��ġ�� ������ �� ���� �̿�
        combination(0, archerList);

        System.out.println(answer);
    }


    private static void combination(int idx, List<Node> archerList) {


        // 3���� �ü� ��ġ�� �����Ǿ��ٸ�
        if (archerList.size() == 3) {
            gameStart(archerList);
            return;
        }
        
        // �����ϴ� M ��ŭ�� ġŲ�� ���տ� �����ߴٸ� ����
        if (idx >= castle.size()) {
            return;
        }

        archerList.add(castle.get(idx));
        combination(idx + 1, archerList);

        // ���� ���� ���, ������ �־������� ��ȿȭ��Ű�� �ٽ� �̴´�. ���� ���ҷ� �������
        // �ְ� ���� ������ ������������ ����ȴ�. ���� �� ������ ���� �׷��Ա��� �� �ʿ�� ����.
        archerList.remove(archerList.size() - 1);
        combination(idx + 1, archerList);


    }


    private static void gameStart(List<Node> archerList) {
        List<Node> testArcherList = new ArrayList<>();
        
        // class ��ü�� �����Ǳ� ������ �����ؼ� �ùķ��̼�
        // �������� �̵��� ������ ���پ� ���������� �ü��� ���� �ö󰡴� �������� ����
        for (int i = 0; i < 3; i++) {
            Node archer = archerList.get(i);
            testArcherList.add(new Node(archer.x, archer.y));
        }
        
        // �� �ü� ��ġ���� Reset
        map = mapReset();
        Map<String, Node> target = new HashMap<String, Node>();
        // �ش� �ü� ��ġ�� ������ ���� ��
        int removeCnt = 0;


        // �ü����� ���� ����(���� row�� �� row ����)
        for (sR = M - 1; sR >= 0; sR--) {
            // ��� �Ÿ��� D �� ���� ���
            eR = sR - D + 1;
            // ������ ����� �ʰ� ������
            if (eR < 0) eR = 0;            
            
            // 3���� �ü��� ���ؼ� ���� ����� ���� ���Ѵ�.
            // ���� ���ǻ� BFS ����
            for (int i = 0; i < 3; i++) {
                Node archer = testArcherList.get(i);
                // BFS Ž���� ���� �湮ǥ�� �ʱ�ȭ
                visited = new boolean[M][N];
                
                Node enemy = BFS(archer);
                
                // ������ ���� �����Ѵٸ�
                // �ٸ� �ü��� ���ؼ��� �������ݵ� �� �����Ƿ� HashMap�� �켱 ��Ƶд�.
                if (!(enemy == null)) {
                    target.put(enemy.x + "_" + enemy.y, new Node(enemy.x, enemy.y));
                }
            }
            
            // hashMap������ �ߺ�ó���� ���ֹǷ� ���ŵ� ���� ���� count
            removeCnt += target.size();
            
            // �̵� �Ŀ� �����ݵ��� �ʵ��� ����(map)���� ����
            Iterator<String> it = target.keySet().iterator();
            while (it.hasNext()) {
                Node n = target.get(it.next());
                // ���ŵ� �� ������ ǥ��
                map[n.x][n.y] = 0;
            }
            
            printMap();
            
            // ���� ������ ���� ��� �ʱ�ȭ
            target.clear();
            // �ü����� ��ġ�� �Ű��ش�. (��ĭ�� ����)
            for (int i = 0; i < 3; i++) {
                testArcherList.get(i).x--;
            }
        }
        
        // �̹� �ü��� ��ġ�� ���� ���� ���� ��Ҵ��� Ȯ��
        answer = (answer < removeCnt) ? removeCnt : answer;
    }


    private static Node BFS(Node archer) {
        Queue<Node> queue = new LinkedList<>();
        
        // BFS ���������� ���� �ִ��� Ȯ�� (��ġ�� �ü����Լ� ���� ����� ����)
        if(map[sR][archer.y] == 1) {
            return new Node(sR, archer.y);
        }
        
        queue.offer(new Node(sR, archer.y));
        Node enemy = null;
        
        while(queue.size() > 0) {
            Node v = queue.poll();
            
            for(int i=0; i<3; i++) {
                int nX = v.x + dx[i];
                int nY = v.y + dy[i];
                
                // �迭 ������ ������� Ȯ��
                if(nX < eR || nY < 0 || nX > sR || nY >= N) {
                    continue;
                }
                
                // ������ �湮�� ���̶��
                if(visited[nX][nY]) {
                    continue;
                }
                
                // ��ݹ����� �����
                if(distance(archer, new Node(nX, nY)) > D) {
                    continue;
                }
                
                // ���� �����ϸ鼭 ��ȿ�� ���ݹ������
                if (map[nX][nY] == 1 &&
                        distance(archer, new Node(nX, nY)) <= D) {
                    return new Node(nX, nY);
                }
                
                // ���� �߰����� �������� ���� ��ȿ�� ��ݹ����̸�
                visited[nX][nY] = true; // �湮 ǥ��
                queue.offer(new Node(nX, nY));
            }
        }
        
        return enemy;
    }


    private static int[][] mapReset() {
        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = gameMap[i][j];
            }
        }
        return map;
    }

    private static int distance(Node archer, Node enemy) {
        return Math.abs(archer.x - enemy.x) + Math.abs(archer.y - enemy.y);
    }
    static class Node {
        int x, y;
        int distance;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + (x+1) + ", " + (y+1) + "]";
        }
    }
    
	public static void printMap() {
		System.out.println("=====PRINT");
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}


