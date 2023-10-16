package Week08;

/**
 * [문제] https://www.acmicpc.net/problem/2109
 * 
 * 메모리 : 20336 KB
 * 시간 : 340 ms
 * 
 * # 접근 : 정렬 + 그리디
 *         
 *        1. (데드라인, 가격) 둘 모두 오름차순으로 정렬
 *        2. 데드라인에 적은 가격부터 추가한다.
 *        3. 데드라인에 이미 가격이 있다면, 왼쪽으로 한 칸 밀고 현재 가격을 넣는다.
 *        4. 기존 가격을 왼쪽으로 밀 때는 가격이 크기 전까지 민다.
 *        5. 배열에 저장되어 있는 값을 모두 합해서 정답을 구한다.
 *        -> Solved !!
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2109_순회강연 {
	
	static int N;
	static int[] duration = new int[10001];						// 각 index날짜에 얻을 가격 저장
	static PriorityQueue<Offer> pq = new PriorityQueue<>();		// 제안 내용 저장
	
	/* 강연 요청 담는 class */
	static class Offer implements Comparable<Offer>{
		int price;
		int deadline;
		
		public Offer(int price, int deadline) {
			this.price = price;
			this.deadline = deadline;
		}

		@Override
		public int compareTo(Offer o) {							// 날짜 오름차순 , 가격 오름차순
			return this.deadline > o.deadline ? 1 : (this.deadline == o.deadline ? this.price - o.price : -1);  
		}
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int price = Integer.parseInt(st.nextToken());
			int deadline = Integer.parseInt(st.nextToken());
			
			pq.add(new Offer(price, deadline));
		}
	}
	
	public static void solve() {
		while(!pq.isEmpty()) {
			Offer cur = pq.poll();
			int tmp = duration[cur.deadline];
			duration[cur.deadline] = cur.price;
			
			for(int i = cur.deadline-1; i > 0; i--) {			// 왼쪽으로 하나씩 밀기
				if(tmp == 0) break;								// 0을 굳이 옮길 필요가 없다.
				if(tmp >= duration[i]) {
					int tmp2 = duration[i];
					duration[i] = tmp;
					tmp = tmp2;
				}
			}
		}
		
		int sum = 0;
		for(int i = 1; i <= 10000; i++) {
			sum += duration[i];
		}
		
		System.out.print(sum);
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
	}

}
