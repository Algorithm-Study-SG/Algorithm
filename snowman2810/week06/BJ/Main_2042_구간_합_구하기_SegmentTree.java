package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2042_구간_합_구하기_SegmentTree {
	/*
	 * 메모리 : 102140 KB
	 * 시간 : 588 ms
	 */

	static int length;
	static long[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		long[] input = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			input[i] = Long.parseLong(br.readLine());
		}

		// 세그먼트 트리 길이 찾기
		length = 1;
		while (n > length) {
			length *= 2;
		}
		// 세그먼트 트리 초기화
		tree = new long[length * 2];

//		// 세그먼트 트리 생성
		fill(input, 1, 1, n);

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == 1) {
				// 값 변경
				long c = Long.parseLong(st.nextToken());
				change(1, b, c, 1, n);
			} else {
				// 합 구하기
				int c = Integer.parseInt(st.nextToken());
				sb.append(find(1, b, c, 1, n)).append("\n");
			}
		}
		System.out.println(sb);

		br.close();
	}

	// 세그먼트 트리 만들기
	static long fill(long[] input, int loc, int start, int end) {
		if (loc > length * 2) { // 범위를 벗어나면 종료
			return 0;
		}
		if (start > end || end < start) { // 범위를 벗어나면 종료
			return 0;
		}
		if (start == end) { // 탐색하는 범위가 한칸이면
			return tree[loc] = input[start]; // 해당 트리위치에 입력받은 값을 저장하고 리턴
		}
		int mid = (end - start) / 2 + start; // 중간위치
		// 이진트로리 구성이 되어있기 때문에 자신의 위치 *2와 *2+1로 자식을 탐색
		tree[loc] += fill(input, loc * 2, start, mid); // 왼쪽 자식의 값을 더한다
		tree[loc] += fill(input, loc * 2 + 1, mid + 1, end); // 오른쪽 자식의 값을 더한다

		return tree[loc]; // 현재의 값 리턴
	}

	// 세그먼트 트리 검색
	static long find(int loc, int targetStart, int targetEnd, int rangeStart, int rangeEnd) {
		if (loc > length * 2) { // 범위를 벗어나면 종료
			return 0;
		}
		long result = 0;
		if (targetStart > rangeEnd || targetEnd < rangeStart) { // 연관이 없음
			return 0;
		} else if (targetStart <= rangeStart && rangeEnd <= targetEnd) { // 모두 포함
			return tree[loc];
		} else { // 부분 포함
			// 부분만 포함이 된다면 모두 포함이 될때까지 쪼갠다
			int mid = (rangeEnd - rangeStart) / 2 + rangeStart; // 중간위치
			result += find(loc * 2, targetStart, targetEnd, rangeStart, mid);
			result += find(loc * 2 + 1, targetStart, targetEnd, mid + 1, rangeEnd);
		}
		return result;
	}

	// 세그먼트 트리 값변경
	static long change(int loc, int targetLoc, long targetNumber, int rangeStart, int rangeEnd) {
		long result = 0;
		if (loc > length * 2) { // 범위를 벗어나면 종료
			return 0;
		}
		if (rangeStart == rangeEnd && rangeStart == targetLoc) { // 탐색하는 범위가 한칸이고 찾고자하는 위치와 일치하면
			result = targetNumber - tree[loc];
		} else {
			// 일치하지 않으면 정확하게 일치할때까지 쪼갠다
			int mid = (rangeEnd - rangeStart) / 2; // 중간위치
			if (targetLoc <= rangeStart + mid) { // 탐색 범위 조정
				result = change(loc * 2, targetLoc, targetNumber, rangeStart, mid + rangeStart);
			} else if (targetLoc <= rangeEnd) { // 탐색 범위 조정
				result = change(loc * 2 + 1, targetLoc, targetNumber, mid + rangeStart + 1, rangeEnd);
			}
		}
		// 값을 찾을때 까지 재귀호출 진행후 값이 값이 바뀌면 결과값에 저장해서 돌아가면서 값 갱신
		tree[loc] += result;
		return result;
	}

}
