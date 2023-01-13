package BOJ_1000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj2602 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        
        String[] arr = {br.readLine(), br.readLine()};
        int ans = 0;
        for (int idx = 0; idx <= 1; idx++) {
            int[][] cnt = new int[s.length()+1][arr[0].length()+1];
            Arrays.fill(cnt[0], 1);
            for (int i = 1; i <= s.length(); i++) {
                String cur = arr[(i - idx) & 1];
                for (int j = 1; j <= cur.length(); j++)
                    cnt[i][j] = cnt[i][j - 1] + (cur.charAt(j - 1) == s.charAt(i - 1) ? cnt[i - 1][j - 1] : 0);
            }
            ans += cnt[s.length()][arr[0].length()];
        }
        System.out.println(ans);
    }
}