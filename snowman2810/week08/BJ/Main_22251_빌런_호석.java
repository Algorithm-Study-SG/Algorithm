package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_22251_빌런_호석 {
	/*
	 * 한 숫자가 몇번의 led교체로 다른 숫자로 바꿀 수 있는지를 모두 구한 후에 계산하자
	 * 시간 : 288 ms
	 * 메모리 : 18168 KB
	 */

	static boolean[][] numbers = { { true, true, true, false, true, true, true }, // 0
			{ false, false, true, false, false, true, false }, // 1
			{ true, false, true, true, true, false, true }, // 2
			{ true, false, true, true, false, true, true }, // 3
			{ false, true, true, true, false, true, false }, // 4
			{ true, true, false, true, false, true, true }, // 5
			{ true, true, false, true, true, true, true }, // 6
			{ true, false, true, false, false, true, false }, // 7
			{ true, true, true, true, true, true, true }, // 8
			{ true, true, true, true, false, true, true } // 9
	};

	// 0에서 led 4개를 바꾸면
	// [현재숫자][led 변경횟수] 가능한 결과의 수 ex)[0] [3] -> 4
	static int[][] totalIndex = new int[10][7];
	// [현재숫자][led 변경횟수][가능한 결과의 수] 가능한 결과 ex) [0] [3] [0~4] -> 3, 2, 7, 5
	static int[][][] totalNumber = new int[10][7][10];

	// 방문체크
	static boolean[] visited;

	// 몇번째 위치의 값을 가져오는데 편하기 위해서 스트링 사용
	static String n; // 최대층
	static int k; // led의 개수
	static int p; // led를 바꿀수 있는 최대수
	static String x; // 현재 층

	static int totalSum = 0; // 최종 결과

	public static void main(String[] args) throws IOException {
		fill();
		input();
		length();
		search(0, p, false, true);

		System.out.println(totalSum - 1); // 자기자신도 등장하기 때문에 빼준다
	}

	// 기본입력
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = st.nextToken();
		k = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		x = st.nextToken();
		br.close();
	}

	// 최대층과 현재층의 길이를 같게끔 세팅해준다
	static void length() {
		if (n.length() > x.length()) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < n.length() - x.length(); i++) {
				sb.append("0");
			}
			sb.append(x);
			x = sb.toString();
		}
	}

	/**
	 * @param start 탐색 위치 (자리)
	 * @param count led를 바꿀 수 있는 최대값
	 * @param max   최대값과 비교를 해야하는지 확인
	 * @param zero  앞에서 0이 등장했는지 체크
	 */
	static void search(int start, int count, boolean max, boolean zero) {

		// 탐색위치가 끝까지 도착했을 때 모두 0인 경우를 제외하고 증가
		if (start == n.length()) {
			if (!zero) {
				totalSum++;
			}
			return;
		}

		for (int i = 0; i < 7; i++) { // led를 최대로 바꿀수 있을때까지 반복
			if (count - i < 0) { // 남은 횟수가 없으면 종료
				break;
			}

			// 해당 위치에 저장된 값의 개수만큼 반복
			for (int j = 0; j < totalIndex[x.charAt(start) - '0'][i]; j++) {
				int checkNumber = totalNumber[x.charAt(start) - '0'][i][j];

				boolean checkZero = false; // 값이 0인지를 확인하기 위한 변수
				if (zero) { // 입력이 true면 세팅해준다
					checkZero = true;
				}
				if (checkNumber != 0) { // 현재 확인할 값이 0이 아닌지 체크
					checkZero = false;
				}
				// 선택된 값과 최대값을 비교를 한다
				if (!max) {
					// 최대값보다 작은지를 비교
					if (checkNumber < n.charAt(start) - '0') {
						search(start + 1, count - i, true, checkZero);
					} else if (checkNumber == n.charAt(start) - '0') {
						search(start + 1, count - i, false, checkZero);
					}
				} else {
					// 처음으로 최대치보다 작은값을 선택했다면 그 뒤에 등장하는 수들은 비교할 필요가 없다
					search(start + 1, count - i, true, checkZero);
				}
			}
		}
	}

	// 각 숫자들을 채우기위해서 호출
	static void fill() {
		visited = new boolean[7];
		for (int i = 0; i < 10; i++) {
			change(i, 0, -1);
		}
	}

	// led의 상태를 변경해주는 메소드
	static void change(int number, int count, int startLoc) {
		if (count == 7) { // 숫자 하나를 표현하는 led의 최대 개수에 도달하면 탈출
			return;
		}
		boolean[] num = Arrays.copyOf(numbers[number], 7); // 미리 적어둔 숫자를 led로 표현한 값을 가져온다

		for (int i = 0; i < 7; i++) { // 선택이 되었으면 값을 바꿔준다
			if (visited[i]) {
				num[i] = !num[i];
			}
		}

		// 값을 바꾼 결과를 숫자랑 비교해서 값을 저장한다
		for (int i = 0; i < 10; i++) {
			if (Arrays.equals(numbers[i], num)) {
				// 해당 위치에 몇개의 값이 저장이 되어있는지를 확인하는 totalIndex의 값을 증가시키면서 값들을 저장
				totalNumber[number][count][totalIndex[number][count]++] = i;
			}
		}

		// 변경할 위치를 선택
		for (int i = startLoc + 1; i < 7; i++) {
			if (!visited[i]) {
				visited[i] = true;
				change(number, count + 1, i);
				visited[i] = false;
			}
		}
	}

}