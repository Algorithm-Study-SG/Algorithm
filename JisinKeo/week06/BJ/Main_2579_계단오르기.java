package JisinKeo.week06.BJ;

import java.io.*;

/**
 * 메모리 : 14184 KB
 * 시간 : 132 ms
 */
public class Main_2579_계단오르기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] stair = new int[n];

        for(int i = 0; i < n; i++){
            stair[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n];

        if (n == 1) {
            System.out.println(stair[0]);
        } else if (n==2) {
            System.out.println(stair[0] + stair[1]);
        } else {
            /**
             * stair[n-1]은 필다로 모든 dp 배열에 누적되어야 함
             */
            dp[n - 1] = stair[n - 1];
            dp[n - 2] = stair[n - 1] + stair[n - 2];
            dp[n - 3] = stair[n - 1]+ stair[n - 3];

            for (int i = n - 4; i >= 0; i--) {
                dp[i] = Math.max(dp[i + 3] + stair[i + 1], dp[i + 2]) + stair[i];
            }

            System.out.println(Math.max(dp[0], dp[1]));
        }

    }
}