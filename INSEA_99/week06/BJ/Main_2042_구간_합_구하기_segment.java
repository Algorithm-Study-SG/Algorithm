package INSEA_99.week06.BJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_2042_구간_합_구하기_segment {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int n, m, k;			// 배열 길이, 수 변경 수, 구간 합 구하는 횟수
	static long[] arr, tree;	// 배열 값, 세그먼트 트리

	public static void main(String[] args) throws IOException {
		input(); // 입력
		init(1, 1, n);
		run(); // 실행
		output(); // 출력
		return;
	}

	/* 입력 */
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new long[n + 1];	
		tree = new long[n * 4];
		for (int i = 1; i <= n; ++i) {
			arr[i] = Long.parseLong(br.readLine());
		}
	}

	/* 출력 */
	static void output() throws IOException {
		bw.flush();
		bw.close();
		br.close();
	}

	/* 실행 */
	static void run() throws IOException {
		for (int i = 0; i < m + k; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (a == 1) {
				update(1, b, 1, n, c);
			} else {
				bw.write(query(1, b, (int)c, 1, n) + "\n");
			}
		}
	}

	/**
	 * bottom up 합 업데이트
	 * @param idx 현재 트리 인덱스
	 * @param start	트리 시작 인덱스
	 * @param end 트리 끝 인덱스
	 * @return	현재 트리 인덱스 값
	 */
	static long init(int idx, int start, int end) {
		if (start == end) {
			tree[idx] = arr[start];
			return tree[idx];
		}
		int mid = (start + end) / 2;
		return tree[idx] = init(idx * 2, start, mid) + init(idx * 2 + 1, mid + 1, end);
	}

	/**
	 * 값을 변경하고 합을 bottom up 업데이트
	 * @param idx 현재 트리 인덱스
	 * @param place	값 변환할 배열 인덱스
	 * @param start	트리 시작 인덱스
	 * @param end 트리 끝 인덱스
	 * @param value	변환할 값
	 */
	static void update(int idx, int place, int start, int end, long value) {
		if (start > place || end < place) {
			return;
		}
		if (start == place && end == place) {
			tree[idx] = value;
			return;
		}
		int mid = (start + end) / 2;
		update(idx * 2, place, start, mid, value);
		update(idx * 2 + 1, place, mid + 1, end, value);
		tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
	}

	/**
	 * 찾는 범위에 있으면 값 반환
	 * @param idx 현재 트리 인덱스
	 * @param left 배열 시작 인덱스
	 * @param right 배열 끝 인덱스
	 * @param start 트리 시작 인덱스
	 * @param end 트리 시작 인덱스
	 * @return 트리 시작 끝 합
	 */
	static long query(int idx, int left, int right, int start, int end) {
		if (start > right || end < left) {
			return 0;
		}
		if (start >= left && end <= right) {
			return tree[idx];
		}
		int mid = (start + end) / 2;
		return tree[idx] = query(idx * 2, left, right, start, mid) + query(idx * 2 + 1, left, right, mid + 1, end);
	}
}
