package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2042_구간_합_구하기_IndexTree {
	/*
	 * 메모리 : 101820 KB
	 * 시간 : 616 ms
	 */

	static int length;
	static long[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		long[] input = new long[n];
		for (int i = 0; i < n; i++) {
			input[i] = Long.parseLong(br.readLine());
		}

		// 인덱스 트리 길이 찾기
		length = 1;
		while (n > length) {
			length *= 2;
		}
		// 인덱스 트리 초기화
		tree = new long[length * 2];

		// 인덱스 트리 생성
		fill(input);

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == 1) {
				// 값 변경
				long c = Long.parseLong(st.nextToken());
				change(b, c);
			} else {
				// 합 구하기
				int c = Integer.parseInt(st.nextToken());
				sb.append(find(b, c)).append("\n");
			}

		}
		System.out.println(sb);

		br.close();
	}

	// 인덱스 트리 만들기
	static void fill(long[] input) {
		for (int i = length; i < length * 2; i++) {
			if (input.length - 1 < i - length) { // 주어진 input의 길이보다 크면 종료
				return;
			}
			tree[i] = input[i - length]; // length자리부터 input값을 채워간다
			int parent = i; // 부모의 위치를 찾기위한 변수
			while (parent != 1) {
				parent /= 2;
				tree[parent] += input[i - length]; // 부모의 값 갱신
			}
		}
		return;
	}

	// 인덱스 트리 검색
	static long find(int start, int end) {
		long result = 0;
		// 왼쪽부터 탐색하는 start는 홀수면 값 더하고 부모로
		// 오른쪽부터 탐색하는 end는 짝수면 값 더하고 부모로
		// 검색 위치를 조정
		start += length - 1;
		end += length - 1;
		while (start < end) { // 위치값이 어긋날때까지 반복
			// 왼쪽부터 탐색을 하는데 위치가 짝수인 경우는 해당 값이 아닌 부모의 값을 더해주고
			// 위치가 홀수인 경우는 해당 값을 더해준다
			if ((start & 1) != 0) {
				result += tree[start];
			}

			// 오른쪽부터 탐색을 하는데 위치가 홀수인 경우는 해당 값이 아닌 부모의 값을 더해주고
			// 위치가 짝수인 경우는 해당 값을 더해준다
			if ((end & 1) == 0) {
				result += tree[end];
			}

			// 이 작업을 통해서 왼쪽 탐색 위치가 짝수면 해당 부모를 가르키고
			// 왼쪽 탐색 위치가 홀수면 자신의 부모가 아니라 한칸 뒤에 있는 부모를 가르킨다
			start = (start + 1) / 2;

			// 이 작업을 통해서 오른쪽 탐색 위치가 홀수면 해당 부모를 가르키고
			// 오른쪽 탐색 위치가 짝수면 자신의 부모가 아니라 한칸 앞에 있는 부모를 가르킨다
			end = (end - 1) / 2;
		}

		if (start == end) { // 가르키는 위치가 동일하면 값 추가
			result += tree[start];
		}
		return result;
	}

	// 인덱스 트리 값변경
	static void change(int target, long targetNumber) {
		// 검색 위치를 조정
		int loc = length + target - 1;
		long result = targetNumber - tree[loc]; // 값의 변화를 저장
		tree[loc] = targetNumber; // 해당 위치의 값 변경
		while (loc != 1) { // loc가 1이 될때까지 반복
			loc /= 2;
			tree[loc] += result; // loc의 부모를 찾아서 변화된 값 반영
		}

	}

}
