package Week08;

/**
 * [문제] https://www.acmicpc.net/problem/1744
 * 
 * 메모리 : 14252 KB
 * 시간 : 128 ms
 * 
 * # 접근 1 : DFS
 *           -> 조합을 제대로 구하지 못함
 *           -> Failed
 * 
 * # 접근 2 : 그리디, 우선순위큐 
 *           1. 양수, 음수로 나누어서 생각 (각각 절댓값이 큰 수부터 곱해간다)
 *           2. 0, 1이 있는 경우 고려
 *           -> Solved !!
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1744_수묶기 {

	static PriorityQueue<Integer> plus = new PriorityQueue<>((o1,o2) -> o2-o1);		// 양수 내림차순
	static PriorityQueue<Integer> minus = new PriorityQueue<>((o1,o2) -> o1-o2);	// 음수,0 오름차순
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if(x > 0) plus.add(x);
			else minus.add(x);
		}
	}
	
	static void solve() {
		int sum = 0;
		
		while(plus.size() >= 2) {
			int a = plus.poll();
			int b = plus.poll();
			
			if(b == 1) {							// (x,1)일 때, x*1 < x+1 이므로 곱해주면 손해
				sum += a + b;
				break;
			}
			sum += a * b;
		}
		while(!plus.isEmpty()) sum += plus.poll();	// 1이 여러개 남은 경우 -> while로 모두 더해줘야 함
		
		while(minus.size() >= 2) {
			int a = minus.poll();
			int b = minus.poll();
			
			if(b == 0) break;						// (x,0) 쌍이면 곱해서 0이 되므로 따로 더해줄 필요는 없다
			sum += a * b;
		}
		if(!minus.isEmpty()) sum += minus.poll();	// minus = [x] 한 가지 경우 뿐 -> x만 더해주면 됨
		
		System.out.print(sum);
	}
	
	public static void main(String[] args) throws IOException {
		input();
		solve();
	}
}
