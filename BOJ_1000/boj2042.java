package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj2042 {
	private static int N,M,K;
	private static long[] tree, map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		
		tree = new long[4*N];
		map = new long[N+1];
		
		for(int i=1; i<=N; i++) {
			map[i] = Long.parseLong(br.readLine());
		}
		
		init(1,1,N);
		
		
		for(int i=0; i<M+K; i++) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			long c = Long.parseLong(str[2]);
			
			if(a==1) {
				map[b] = c;
				update(1,1,N,b,c);
			}else {
				bw.write(getSum(1,1,N,b,(int)c)+"\n");
			}
		}
		bw.flush();
		bw.close();
	}
	
	public static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = map[start];
        } else {
            init(node*2, start, (start+end)/2);
            init(node*2+1, (start+end)/2+1, end);
            tree[node] = tree[node*2] + tree[node*2+1];
        }
    }
	
	public static long getSum(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        long lsum = getSum(node*2, start, (start+end)/2, left, right);
        long rsum = getSum(node*2+1, (start+end)/2+1, end, left, right);
        
        return lsum+rsum;
    }
    
    public static void update(int node, int start, int end, int index, long val) {
        if (index < start || index > end) {
            return;
        }
        
        if (start == end) {
            map[index] = val;
            tree[node] = val;
            return;
        }
        
        update(node*2, start, (start+end)/2, index, val);
        update(node*2+1, (start+end)/2+1, end, index, val);
        
        tree[node] = tree[node*2] + tree[node*2+1];
    }
}
