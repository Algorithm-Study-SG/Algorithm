package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2591_숫자카드 {
	/*
	 * 메모리 : 14164 KB
	 * 시간 : 124 ms
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int[] dp = new int[input.length()];
		dp[0] = 1; // 초기값 으로는 0이 주어지지 않기 때문에 1로 설정
		dp[1] = 1; // 두 번째 값으로 어떤 값이 주어진다고 해도 최소가 1이기 때문에 설정

		// 두 번째 값이 0이 아니고 첫 숫자와 두 번째 숫자의 조합이 34이하라면 경우의 수를 하나 추가해준다
		if (input.charAt(1) != '0' && (input.charAt(0) - '0') * 10 + (input.charAt(1) - '0') <= 34) {
			dp[1] += 1;
		}
		
		// 세 번째로 들어오는 숫자부터 비교
		for (int i = 2; i < input.length(); i++) {
			
			// 들어오는 숫자가 0이 아니면 n-1번째 경우의 수를 포함해준다
			if (input.charAt(i) != '0') {
				dp[i] += dp[i - 1];
			}
			
			// 이전 숫자가 0이 아니고 현재 숫와 이루어진 조합이 34 이하면 n-2번째 경우의 수를 포함해준다
			if (input.charAt(i - 1) != '0' && (input.charAt(i - 1) - '0') * 10 + (input.charAt(i) - '0') <= 34) {
				dp[i] += dp[i - 2];
			}
		}
		// 출력
		System.out.println(dp[input.length() - 1]);

		br.close();
	}

}
