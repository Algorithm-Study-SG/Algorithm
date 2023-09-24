package Week06;

/**
 * 메모리 : 79700 KB
 * 시간 : 752 ms
 * 
 * # 접근 1 : 그룹 해싱
 *           일정 그룹을 묶어서 해싱한 후 처리 시도
 *           -> 퍼즐의 모습이 계속 바뀌므로 불가능하다고 판단
 * 
 * # 접근 2 : 구현
 *           1~8번 숫자를 차례대로 올바른 위치에 가도록 퍼즐을 이동시킨다.
 *           -> 각 숫자의 이동 경로에 따라서 경우의 수가 엄청나게 많아진다.
 *           -> 3x3의 작은 격자판이고, 모든 경우를 다 따지면 불가능하진 않겠지만 상당히 비효율적이라고 판단
 * 
 * # 접근 3 : 빈칸(0)을 이동시키며 BFS로 모든 경우의 수 탐색
 *           
 *           (접근2)에서 퍼즐을 이동시키다가 빈칸의 위치가 중요하다는 것을 느낌
 *           빈칸이 이동 가능한 모든 경우의 수 구하기 시도 
 *               -> "3주차 교환"문제에서 아이디어를 얻음
 *               -> 퍼즐을 하나의 문자열로 바꿔서 BFS 진행
 *               -> Solved !!
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1525_퍼즐 {
	
	static String ans = "123456780";								// 목표하는 퍼즐의 모습
	static Map<String, Integer> map = new HashMap<>();				// {퍼즐의 모습 : 최소 이동 횟수} 저장
	static String initString;										// 퍼즐의 초기 모습
	static int[] dir = {-3,3,-1,1};									// 상 하 좌 우 

	/* 퍼즐의 초기 모습 저장 */
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		char[] arr = new char[9];
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				arr[i*3 + j] = st.nextToken().charAt(0);
			}
		}
		
		initString = new String(arr);
	}
	
	static void BFS() {
		Queue<String> queue = new ArrayDeque<>();
		queue.add(initString);
		map.put(initString, 0);
		
		while(!queue.isEmpty()) {
			String cur = queue.poll();
			int zeroIdx = cur.indexOf("0");							// 빈칸(0)의 위치
			int cnt = map.get(cur);									// 현재 퍼즐 모습 만들기까지 최소 이동 횟수
			
			for(int d = 0; d < 4; d++) {
				int nd = zeroIdx + dir[d];
				
				// 범위 밖인 경우 continue
				if(nd < 0 || nd >= 9) continue;
				if((d == 2 && nd % 3 == 2) || (d == 3 && nd % 3 == 0)) continue;
				
				String next = swap(cur, zeroIdx, nd);				// 빈칸 이동 후 퍼즐의 모습
				if(map.get(next) == null || map.get(next) > cnt + 1) {
					map.put(next, cnt + 1);							// 최소 이동 거리 업데이트
					queue.add(next);
				}
			}
		}
		
		if(map.containsKey(ans)) System.out.print(map.get(ans));
		else System.out.print(-1);
		
	}
	
	/* 빈칸 이동 */
	static String swap(String cur, int zeroIdx, int nd) {
		char[] c = cur.toCharArray();
		
		char tmp = c[zeroIdx];
		c[zeroIdx] = c[nd];
		c[nd] = tmp;
		
		return new String(c);
	}
	
	public static void main(String[] args) throws IOException {
		input();
		BFS();
	}

}
