package INSEA_99.week08.BJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 20356 KB 324 ms
 * 
 * 비용, 날 순으로 우선순위를 정해서 값 할당
 * 
 */

public class Main_2109_순회공연 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static final int MAX = 10001;

	static int n, earnedMoney; // 대학 수, 가장 많이 번 돈
	static int[] payment = new int[MAX]; // 날짜 별 최대로 벌 수 있는 돈
	static PriorityQueue<Pair> pq = new PriorityQueue<>(); // pay순 그리고 day순으로 우선순위

	/* 메인 */
	public static void main(String[] args) throws IOException {
		input(); // 입력 받기
		run(); // 실행
		output(); // 출력
		return;
	}

	static class Pair implements Comparable<Pair> {
		int pay;
		int day;

		Pair() {
		}

		public Pair(int pay, int day) {
			this.pay = pay;
			this.day = day;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.pay == o.pay) {
				return Integer.compare(o.day, this.day);
			}
			return Integer.compare(o.pay, this.pay);
		}
	}

	/* 입력 */
	static void input() throws IOException {
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			int pay = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());
			pq.add(new Pair(pay, day));
		}
		br.close();
	}

	/* 출력 */
	static void output() throws IOException {
		bw.write(earnedMoney + ""); // 출력
		bw.flush();
		bw.close();
	}

	/* 실행 */
	static void run() {
		while (!pq.isEmpty()) { // 다 체크할 때까지
			Pair now = pq.poll();
			for (int d = now.day; d > 0; --d) { // max day부터 1까지 할당 체크
				if (payment[d] < now.pay) { // 최대로 할당 가능한 날이 있는 경우
					earnedMoney += now.pay - payment[d];
					payment[d] = now.pay;
					break;
				}
			}
		}
	}
}