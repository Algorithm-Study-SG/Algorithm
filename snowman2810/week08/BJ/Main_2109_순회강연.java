package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2109_순회강연 {
	/*
	 * 시간값이 큰 지점에서 부터 값을 채워나가자
	 * 시간 : 360 ms
	 * 메모리 : 20076 KB
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] result = new int[10001];
		PriorityQueue<Work> pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			pq.offer(new Work(p, d));
		}

		while (!pq.isEmpty()) {
			Work work = pq.poll();

			// 꺼낸 값에 해당하는 날에 저장된 값이 없으면 저장
			if (result[work.d] == 0) {
				result[work.d] = work.p;
				continue;
			}

			// 아니라면 그 날에서 한칸씩 앞으로 가면서 탐색
			int count = work.d;
			while (count > 0) {
				// 이동한 위치의 값이 바꿀 값보다 작다면 값을 바꿔주고 해당 값을 한칸씩 앞으로 가면서 탐색
				if (result[count] < work.p) {
					int input = result[count];
					result[count] = work.p;
					count--;
					while (result[count] != 0) { // result에 값이 존재하면 반복
						int temp = result[count];
						result[count] = input;
						input = temp;
						count--;
						if (count == 0) { // 0번에 도달하면 탈출
							break;
						}
					}
					// count가 0에 도달하기전에 끝나고 result의 값이 비어있으면 저장
					if (count != 0 && result[count] == 0) {
						result[count] = input;
					}
					break;
				}
				count--;
			}
		}

		// 저장된 값들을 더한뒤 출력
		int ans = 0;
		for (int i : result) {
			ans += i;
		}

		System.out.println(ans);
		br.close();
	}

	// 우선순위큐를 사용하기 위해서 클래스 선언
	static class Work implements Comparable<Work> {
		int p;
		int d;

		public Work(int p, int d) {
			this.p = p;
			this.d = d;
		}

		@Override
		public int compareTo(Work o) {
			if (this.d != o.d) {
				// 날자를 기준으로 내림차순
				return Integer.compare(o.d, this.d);
			}
			// 같다면 강연료를 기준으로 내림차순
			return Integer.compare(o.p, this.p);
		}
	}

}
