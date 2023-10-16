package INSEA_99.week08.BJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/*
 *	14440 KB	148 ms 
 *  idea: 각 숫자별 바꾸어야 하는 수를 저장하고, 1부터 k자리수 까지 구한 수 p가 가능한 횟수인지 판단
 * 
 */

public class Main_22251_빌런_호석 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static final int[][] ALPHABET_CONVERT_INFO = { { 0, 4, 3, 3, 4, 3, 2, 3, 1, 2 }, { 4, 0, 5, 3, 2, 5, 6, 1, 5, 4 },
			{ 3, 5, 0, 2, 5, 4, 3, 4, 2, 3 }, { 3, 3, 2, 0, 3, 2, 3, 2, 2, 1 }, { 4, 2, 5, 3, 0, 3, 4, 3, 3, 2 },
			{ 3, 5, 4, 2, 3, 0, 1, 4, 2, 1 }, { 2, 6, 3, 3, 4, 1, 0, 5, 1, 2 }, { 3, 1, 4, 2, 3, 4, 5, 0, 4, 3 },
			{ 1, 5, 2, 2, 3, 2, 1, 4, 0, 1 }, { 2, 4, 3, 1, 2, 1, 2, 3, 1, 0 } };

	static int n, k, p, x, outcomes;

	/* 메인 */
	public static void main(String[] args) throws IOException {
		input(); // 입력 받기
		run(); // 실행
		output(); // 출력
		return;
	}

	/* 입력 */
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		br.close();
	}

	/* 출력 */
	static void output() throws IOException {
		bw.write(outcomes - 1 + ""); // 출력
		bw.flush();
		bw.close();
	}

	/* 실행 */
	static void run() {
		convertFloor(0, 1, 0, 0);
	}

	/**
	 * 
	 * @param idx		자릿수 개수
	 * @param digit		자리수를 위한 수 ex) idx = 1 -> digit = 1, idx = 2 -> digit = 10
	 * @param floor		만들어진 층 수
	 * @param usingP	현재까지 사용한 p
	 */
	static void convertFloor(int idx, int digit, int floor, int usingP) {
		if (usingP > p || floor > n) {	// 사용 가능한 p가 초과했거나 floor가 n층 초과한 경우 종료
			return;
		}
		if (idx == k) {		// k번째 자리수 층인 경우 종료
			if (floor != 0) {	// 0이 아니면 추가
				outcomes++;				
			}
			return;
		}
		for (int i = 0; i <= 9; ++i) {	// 현재 자리수를 0~9 중 설정
			convertFloor(idx + 1, digit * 10, i * digit + floor, usingP + ALPHABET_CONVERT_INFO[x / digit % 10][i]);
		}

	}
}
