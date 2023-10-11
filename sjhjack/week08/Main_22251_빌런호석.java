package Week08;

/**
 * [문제] https://www.acmicpc.net/problem/22251
 * 
 * 메모리 : 52360 KB
 * 시간 : 280 ms
 * 
 * # LED 순서는 아래와 같다.
 *     1
 *   2   3
 *     4
 *   5   6
 *     7
 * 
 * # 접근 : 브루트포스
 * 		   - 마땅한 풀이법이 떠오르지 않았고, 시간복잡도를 계산해본 결과 브루트포스로 구현해도 널널해서 시도했다.
 * 
 * 		   1. 각 숫자가 LED에 나타나는 모습을 맵핑해놓는다.
 * 		   2. 각 숫자가 다른 숫자로 바뀌기 위해 필요한 LED 반전 횟수를 비트 연산으로 구한다.
 * 		      (맨 밑에 주석처리 해놓은 예시 출력문을 확인해보면 이해하는데 도움이 된다.)
 * 		   3. 1층 ~ N층까지 순차적으로 탐색하며 경우의 수를 계산한다.
 * 			-> Solved !!
 * 
 * 	++ 참고 사이트
 *     - [자바 진수변환] https://dpdpwl.tistory.com/92
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_22251_빌런호석 {
	
	static int N, K, P, X;
	static int[] now;
	static HashMap<Integer, Integer> map = new HashMap<>();
	static int[][] digitCnt = new int[10][10];
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());			// 최대 층
		K = Integer.parseInt(st.nextToken());			// 디스플레이 자리수
		P = Integer.parseInt(st.nextToken());			// 반전시킬 수 있는 최대 LED 수
		X = Integer.parseInt(st.nextToken());			// 현재 층
		
		now = new int[K];
		
		// 현재 층을 int 배열에 한 자리씩 저장
		int idx = K - 1;								// 모자른 자리는 0으로 두기위해 뒤에서부터 저장
		while(X > 0) {
			now[idx--] = X % 10;
			X /= 10;
		}		
	}
	
	/* 각 숫자를 다른 숫자로 바꾸기 위해 필요한 LED 반전 횟수 구하기 */
	static void init() {
		// 각 숫자가 LED에 나타나는 모습을 map으로 매칭
		map.put(0, Integer.parseInt("1110111", 2));
		map.put(1, Integer.parseInt("0010010", 2));
		map.put(2, Integer.parseInt("1011101", 2));
		map.put(3, Integer.parseInt("1011011", 2));
		map.put(4, Integer.parseInt("0111010", 2));
		map.put(5, Integer.parseInt("1101011", 2));
		map.put(6, Integer.parseInt("1101111", 2));
		map.put(7, Integer.parseInt("1010010", 2));
		map.put(8, Integer.parseInt("1111111", 2));
		map.put(9, Integer.parseInt("1111011", 2));
		
		// i -> j 로 바꾸기 위해 필요한 LED 반전 횟수 구하기
		for(int i = 0; i <= 8; i++) {
			for(int j = i+1; j <= 9; j++) {
				int flip = map.get(i) ^ map.get(j);
				int cnt = 0;
				
				while(flip > 0) {						// Integer.bitCount(flip) 으로 간단하게 구할 수 있다고 한다.
					if((flip & 1) > 0) {				// 반전이 필요한 위치 = 비트 1
						cnt++;
					}
					flip >>= 1;
				}
				
				digitCnt[i][j] = cnt;
				digitCnt[j][i] = cnt;
			}
		}
	}
	
	/* 경우의 수 구하기 */
	static void solve() {
		int ans = 0;									// 정답
		int tmp = 0;									// i를 연산하기 위함
		int gap = 0;									// 자리수 차이
		int len = 1;									// 바꾸려는 숫자의 자리수
		int x = 10;										// 자리수 구하기 위한 값
		int[] next;										// 바꾸려는 숫자 저장
		
		for(int i = 1; i <= N; i++) {					// X -> i로 바꾸는 경우의 수 탐색
			if(i / x > 0) {								// 자리수가 증가한 경우
				x *= 10;
				len++;
			}
			
			tmp = i;
			gap = K - len;
			next = new int[K];
			
			for(int j = 0; j < gap; j++) {				// 모자른 자리수는 0으로 채우기
				next[j] = 0;
			}
			for(int j = K-1; j >= gap; j--) {			// 뒤에서부터 저장
				next[j] = tmp % 10;
				tmp /= 10;
			}
			
			int cnt = 0;								// 반전시켜야 하는 LED 총 개수
			boolean flag = true;						// X -> X로 바꾸는 경우를 제외하기 위한 변수
			for(int j = 0; j < next.length; j++) {
				if(flag && now[j] != next[j]) flag = false;
				cnt += digitCnt[now[j]][next[j]];
			}
			
			if(!flag && cnt <= P) ans++;
		}
		
		System.out.print(ans);
	}

	public static void main(String[] args) throws IOException {
		input();
		init();
		solve();
	}

}

//System.out.println(Integer.parseInt("1010", 2));
//System.out.println(Integer.parseInt("0010010", 2) ^ Integer.parseInt("1011101", 2));
//System.out.println(Integer.toBinaryString(Integer.parseInt("0010010", 2) ^ Integer.parseInt("1011101", 2)));
//System.out.println(1 >> 1);

