package INSEA_99.week08.BJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 14272 KB, 136 ms
 * 음수, 양수 개수가 짝수면 가장 큰 애들끼리 차례로 곱해서 더함
 * 양수가 홀수면 가장 작은 수 제거하고 답에 더해놓음
 * 음수는 홀수이고 0이 있으면 가장 작은 음수 없애기
 * 
 */

public class Main_1744_수_묶기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int n, sum;
	static boolean havingZero = false;
	static PriorityQueue<Integer> positivePriorityQueue = new PriorityQueue<>();
	static PriorityQueue<Integer> negativePriorityQueue = new PriorityQueue<>((o1, o2) -> {
		return Integer.compare(o2, o1);
	});

	/* 메인 */
	public static void main(String[] args) throws IOException {
		input(); // 입력 받기
		run(); // 실행
		output(); // 출력
		return;
	}

	/* 입력 */
	static void input() throws IOException {
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; ++i) {
			int x = Integer.parseInt(br.readLine());
			if (x < 0) {
				negativePriorityQueue.add(x);	// 음수 저장
			} else if (x > 0) {
				positivePriorityQueue.add(x);	// 양수 저장
			} else {
				havingZero = true;
			}
		}
		br.close();
	}

	/* 출력 */
	static void output() throws IOException {
		bw.write(sum + ""); // 출력
		bw.flush();
		bw.close();
	}

	/* 실행 */
	static void run() {
		if (havingZero && negativePriorityQueue.size() % 2 != 0) {	// 0이 있고 음수가 홀수면 가장 작은 음수 제거
			negativePriorityQueue.poll();
		}
		getSum(positivePriorityQueue);
		getSum(negativePriorityQueue);

	}

	static void getSum(PriorityQueue<Integer> pq) {
		if (pq.size() % 2 == 1) {	// 홀수면 하나 제거
			sum += pq.poll();
		}
		while (!pq.isEmpty()) {
			int x = pq.poll();
			int y = pq.poll();
			if (x == 1 || y == 1) {		// 둘 다 1이면 더한게 크므로 더한 값을 정답에 더함
				sum += x + y;
			} else {
				sum += x * y;			// 그 외 곱한 값 정답에 더함
			}
		}
	}
}