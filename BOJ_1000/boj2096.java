package BOJ_1000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj2096 {
	private static int n;
	private static int[][] maxDP, minDP;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		maxDP = new int[n][3];
		minDP = new int[n][3];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 3; j++) {
				int num = Integer.parseInt(st.nextToken());

				if (i == 0) {
					maxDP[i][j] = minDP[i][j] = num;
					continue;
				}

				switch (j) {
				case 0: {
					maxDP[i][j] = num + Math.max(maxDP[i - 1][j], maxDP[i - 1][j + 1]);
					minDP[i][j] = num + Math.min(minDP[i - 1][j], minDP[i - 1][j + 1]);
					break;
				}

				case 1: {
					int max = Math.max(maxDP[i - 1][j], maxDP[i - 1][j - 1]);
					max = Math.max(max, maxDP[i - 1][j + 1]);

					int min = Math.min(minDP[i - 1][j], minDP[i - 1][j - 1]);
					min = Math.min(min, minDP[i - 1][j + 1]);

					maxDP[i][j] = num + max;
					minDP[i][j] = num + min;

					break;
				}

				case 2: {
					maxDP[i][j] = num + Math.max(maxDP[i - 1][j], maxDP[i - 1][j - 1]);
					minDP[i][j] = num + Math.min(minDP[i - 1][j], minDP[i - 1][j - 1]);
					break;
				}
				
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i=0; i<3; i++) {
			max = Math.max(maxDP[n-1][i], max);
			min = Math.min(minDP[n-1][i], min);
		}
		
		bw.write(max+" "+min);
		bw.flush();
		bw.close();
	}
}
