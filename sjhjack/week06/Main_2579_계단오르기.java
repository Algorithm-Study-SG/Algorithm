package Week06;

/**
 * 메모리 : 14280 KB
 * 시간 : 124 ms
 * 
 * 접근 : DP
 *       
 *       n번 계단에 도달 가능한 경우의 수
 *       1. 점프
 *       	n-2번 칸에 어떻게 도착하든 상관 없다 (큰 값을 취한다)
 *       2. 걸어서
 *          n-1번 칸에 점프해서 도착한 경우에만 가능하다.
 *       
 *       2차원 DP로 구현
 *          -> Solved !!
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2579_계단오르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[][] dp = new int[N+1][2];	// 0:점프x , 1:점프o 해서 도착
		dp[1][0] = arr[1];
		dp[1][1] = arr[1];
		
		for(int i = 2; i <= N; i++) {
			dp[i][0] = dp[i-1][1] + arr[i];
			dp[i][1] = Math.max(dp[i-2][0], dp[i-2][1]) + arr[i];
		}
		
		System.out.print(Math.max(dp[N][0], dp[N][1]));
	}

}