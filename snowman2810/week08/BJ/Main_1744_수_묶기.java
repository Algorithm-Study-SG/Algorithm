package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1744_수_묶기 {
	/*
	 * 양수끼리 큰값을 곱하고 음수끼리 큰값을 곱해서 더하자
	 * 시간 : 128 ms
	 * 메모리 : 14276 KB
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1보다 큰 값들을 저장하는 pq
		PriorityQueue<Integer> maxPq = new PriorityQueue<>((o1, o2) -> {
			return Integer.compare(o2, o1);
		});

		// 0보다 작은 갑들을 저장하는 pq
		PriorityQueue<Integer> minPq = new PriorityQueue<>((o1, o2) -> {
			return Integer.compare(o1, o2);
		});

		int n = Integer.parseInt(br.readLine());
		int result = 0; // 결과값 저장
		boolean zero = false; // 0이 존재하는지 확인

		// 값을 저장한다
		for (int i = 0; i < n; i++) {
			int input = Integer.parseInt(br.readLine());
			if (input > 1) {
				maxPq.offer(input);
			}
			if (input == 1) {
				result++;
			}
			if (input == 0) {
				zero = true;
			}
			if (input < 0) {
				minPq.offer(input);
			}
		}

		// 가장 작은값 두개를 곱해서 더해준다
		while (!minPq.isEmpty()) {
			int minFirst = minPq.poll();
			if (minPq.isEmpty()) {
				// 값이 홀수개가 되었을때 0이 등장한적이 없다면 값을 더해준다
				if (!zero) {
					result += minFirst;
				}
				break;
			}
			int minSecond = minPq.poll();
			result += minFirst * minSecond;
		}
		
		// 가장 큰 두개를 곱해서 더해준다
		while (!maxPq.isEmpty()) {
			int maxFirst = maxPq.poll();
			// 홀수개가 되었으면 더해준다
			if (maxPq.isEmpty()) {
				result += maxFirst;
				break;
			}
			int maxSecond = maxPq.poll();
			result += maxFirst * maxSecond;
		}
		System.out.println(result);
		br.close();
	}

}
