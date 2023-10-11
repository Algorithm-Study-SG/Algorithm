package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2170_선_긋기 {
	/*
	 * start가 작은 값부터 꺼내서 end값을 갱신해준다
	 * 시간 : 1776 ms
	 * 메모리 : 272940 KB
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		PriorityQueue<Loc> pq = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			Loc loc = new Loc();
			loc.start = start;
			loc.end = end;
			pq.offer(loc);
		}

		Loc loc = pq.poll();
		int start = loc.start;
		int end = loc.end;
		int sum = 0;
		while (!pq.isEmpty()) {
			loc = pq.poll();
			// 끝나는 위치가 시작보다 크다면 끝나는 위치 갱신
			if (end >= loc.start) {
				if (end < loc.end) {
					end = loc.end;
				}
			} else {
				// 끝나는 위치가 시작보다 작으면 값을 저장하고 시작과 끝 세팅
				sum += end - start;
				start = loc.start;
				end = loc.end;
			}
		}

		// 마지막으로 저장했던 값을 추가
		sum += end - start;
		System.out.println(sum);

		br.close();
	}

	// 정렬을 하기위해 클래스 선언
	static class Loc implements Comparable<Loc> {
		int start;
		int end;

		@Override
		public int compareTo(Loc o) {
			return Integer.compare(this.start, o.start);
		}
	}

}
