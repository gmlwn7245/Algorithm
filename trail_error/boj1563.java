package trail_error;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

// �ð��ʰ�
public class boj1563 {
	private static int mod = 1000000, sum = 0 , N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		N++;
		
		// ������ 2�� �̻� �߰ų� �Ἦ�� �� �� �������� �� ���
		
		dfs(0,0);
		
		sum %= mod;
		bw.write(sum+"");
		bw.flush();
		bw.close();
	}
	
	public static void dfs(int oCnt, int sCnt) {
		for(int i=1; i<=3; i++) {
			int noCnt = oCnt + 1;
			int nsCnt = sCnt + i;
						
			if(nsCnt == N) {
				// sum += noCnt -1 + 1;
				sum += noCnt % mod;
				return ;
			}
			
			dfs(noCnt, nsCnt);
		}
	}
}
