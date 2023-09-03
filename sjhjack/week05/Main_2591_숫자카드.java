package Week05;

/**
 * 메모리 : 14264 KB
 * 시간 : 124 ms
 * 
 * # 접근 : 두 자리수를 어떻게 만들지가 가장 중요한 요소라고 생각했다.
 *         
 *         1. 한 자리수를 놓는 개수마다 두 자리수가 올 수 있는 경우의 수를 중복 조합으로 구했다.
 *            -> 가능한 두 자리수 조합의 개수를 점화식으로 찾는 것 실패
 *         
 *         2. DP로 모든 경우의 수를 보자
 *            -> 두 자리수가 N개일 때와 N-1개일 떄의 연관성을 찾음
 *            -> 찾은 규칙으로 점화식을 세우기 위해 DP 테이블을 postfix로 구성
 *            -> bottom-up 방식으로 구현
 *            -> 성공
 *         
 *         ++ 놓쳤던 점
 *         1. 0이 오는 경우를 놓쳤다. (ex) 1110011, 110, 13502, ...
 *         2. 범위 밖인 두자리 수가 오는 경우를 놓쳤다. (ex) 99, 87, ...
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2591_숫자카드 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = br.readLine();
		
		int n = num.length();
		char[] s = num.toCharArray();
		int[][] two = new int[n-1][2];		// two[i][0] : 나올 수 있는 i번째 두자리수 , two[i][1] : 해당 숫자가 범위 내에 있으면 1 아니면 0
		
		if(num.charAt(0)-'0' == 0) {		// 첫 숫자가 0이면 불가능
			System.out.print(0);
			return;
		}
		if(n == 1) {						// 한 자리수면 경우의 수 1
			System.out.print(1);			// (0인 경우는 바로 위의 if에서 걸림)
			return;
		}

		/* 가능한 두 자리수 모두 찾기 */
		for(int i = 0; i < n-1; i++) {
			if(i+2<n) {
				if(s[i+1] == '0' && s[i+2] == '0') {	// 00이 나오는 경우 불가능
					System.out.print(0);
					return;
				} else if(s[i+2] == '0') {				// i+2자리가 0일 때, i와 i+1을 두자리수로 묶으면 0 하나만 남아서 안됨
					continue;
				} 
			}
			if(i+1 < n) {								// 다음 숫자가 0이면 현재 숫자와 무조건 묶여야하므로 숫자 자체를 아예 빼버림
				if(s[i+1] == '0') {
					if(s[i]-'0' > 3) {					// 10, 20, 30만 가능
						System.out.print(0);
						return;
					}
					i += 1;
					continue;
				}
			}
			
			two[i][0] = (s[i]-'0')*10 + (s[i+1]-'0');	// 두자리수 저장
		}
		
		/* 저장한 두자리 수의 범위 확인 : 1 ≤ 숫자 ≤ 34 이면 1 아니면 0 */
		for(int i = 0; i < two.length; i++) {
			two[i][1] = (two[i][0] > 0 && two[i][0] <= 34) ? 1 : 0;
		}
		
		int[][] postfix = new int[n/2][n-1];			// post fix sum
		postfix[0][n-2] = two[n-2][1];
		for(int i = n-3; i >= 0; i--) {					// 1행 초기화
			postfix[0][i] = postfix[0][i+1] + two[i][1];
		}
		
		int ans = postfix[0][0] + 1;					// 모두 한 자리수인 경우 +1
		for(int i = 1; i < n/2; i++) {					// postfix[i][0] : 두 자리수 i개일 때 가능한 경우의 수
			for(int j = n-2; j >= 0; j--) {
				if(j+2 > n-2) {
					postfix[i][j] = 0;
				} else if(two[j][1] == 0) {				// 현재 두자리수가 범위 밖이면 경우의수 0 이므로 이전 경우의수 그대로 받음
					postfix[i][j] = postfix[i][j+1];
				} else {
					postfix[i][j] = postfix[i-1][j+2] + postfix[i][j+1];
				}
			}
			
			ans += postfix[i][0];
		}
		
		System.out.print(ans);
		
	}

}
