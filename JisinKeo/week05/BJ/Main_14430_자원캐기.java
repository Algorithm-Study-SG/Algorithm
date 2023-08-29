package JisinKeo.week05.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리 : 22616 KB
 * 시간 : 236 ms
 */
public class Main_14430_자원캐기{

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(st.nextToken());

        int[][] resource = new int[n][m];
        int[][] dp = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                resource[i][j] = Integer.parseInt(st.nextToken());
                if(i == 0 && j == 0) {
                    dp[i][j] = resource[i][j];
                } else if(i == 0) {
                    dp[i][j] = resource[i][j] + dp[i][j-1];
                } else if(j == 0) {
                    dp[i][j] = resource[i][j] + dp[i-1][j];
                }
            }
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + resource[i][j];
            }
        }

        System.out.println(dp[n-1][m-1]);


    }
}