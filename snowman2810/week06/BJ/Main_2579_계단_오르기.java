package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2579_계단_오르기 {
	/*
	 * 메모리 : 14260 KB
	 * 시간 : 120 ms
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] stair = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
		// dp 초기화
		int[] dp = new int[n + 1];

		for (int i = 1; i < n + 1; i++) {
			if (i >= 3) { // i가 3이상이면
				dp[i] = Math.max(stair[i - 1] + dp[i - 3], dp[i - 2]) + stair[i]; // 기본 점화식
			} else if (i >= 2) { // i가 2이상이면
				dp[i] = Math.max(stair[i - 1], dp[i - 2]) + stair[i];
			} else { // i가 1이면
				dp[i] = Math.max(stair[i - 1], 0) + stair[i];
			}
		}

		// 한줄로 표현
//		for (int i = 1; i < n + 1; i++) {
//			dp[i] = Math.max(stair[i - 1] + (i >= 3 ? dp[i - 3] : 0), (i >= 2 ? dp[i - 2] : 0)) + stair[i];
//		}

		System.out.println(dp[n]);

		br.close();
	}

}
