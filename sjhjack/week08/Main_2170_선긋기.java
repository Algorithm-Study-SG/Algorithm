package Week08;

/**
 * [문제] https://www.acmicpc.net/problem/2170
 * 
 * 메모리 : 274144 KB
 * 시간 : 1764 ms
 * 
 * # 접근 : 정렬
 *         
 *         1. 먼저 시작하고, 먼저 끝나는 선부터 탐색
 *         -> Solved !!
 *         		-> 시작점 오름차순, 끝나는 점 내림차순으로 정렬하면 연산 줄어들 것 같다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2170_선긋기 {
	
	static int N;
	static PriorityQueue<Line> pq = new PriorityQueue<>();
	
	/* 선 담는 class */
	static class Line implements Comparable<Line> {
		int start;
		int end;
		
		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Line o) {
			return this.start > o.start ? 1 : (this.start == o.start ? this.end - o.end : -1);
		}	// 시작점 오름차순, 끝점 오름차순
	}

	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			pq.add(new Line(start, end));
		}
	}
	
	static void solve() {
		Line init = pq.poll();
		
		// 초기 값 할당
		int start = init.start;
		int end = init.end;
		int sum = end - start;
		
		// 모든 선 탐색
		while(!pq.isEmpty()) {
			Line cur = pq.poll();
			
			if(end < cur.start) {		// 선이 겹치지 않는 경우
				start = cur.start;
				end = cur.end;
				
				sum += end - start;
			} else if(end < cur.end){	// 선이 겹치는 경우
				sum += cur.end - end;
				end = cur.end;
			}
		}
		
		System.out.print(sum);
	}
	
	public static void main(String[] args) throws IOException {
		input();
		solve();
	}

}
