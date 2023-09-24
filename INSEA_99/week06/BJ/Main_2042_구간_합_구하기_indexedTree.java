package INSEA_99.week06.BJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.StringTokenizer;

/* 
 *	94484 KB
 *	620 ms 
 */

public class Main_2042_구간_합_구하기_indexedTree {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int n, m, k, si;		// 배열 길이, 수 변경 수, 구간 합 구하는 횟수, 리프 노드 시작 인덱스
	static long[] tree;			// 인덱스드 트리

	public static void main(String[] args) throws IOException {
		input(); // 입력
		init();
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
		si = 1;
		while (si < n) {	// leaf 노드 시작점 구하기
			si <<= 1;
		}
		tree = new long[si * 2];
		for (int i = 0; i < n; ++i) {
			tree[si + i] = Long.parseLong(br.readLine());
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
				update(b, c);
			} else {
				bw.write(query(b, (int)c) + "\n");
			}
		}
	}

	/***
	 *  트리 값 초기화
	 */
	static void init() {
		int idx = si;	// 리프 노드부터 루트노드까지 돌면서 합을 업데이트
		while (idx > 1) {
			for (int i = idx; i < idx + si; i += 2) {
				tree[i / 2] = tree[i] + tree[i + 1];
			}
			idx /= 2;
		}
	}

	static void update(int a, long b) {
		int idx = si + a - 1;		// 바꿀 값의 트리 위치
		tree[idx] = b;		// 변경
		idx /= 2;
		while (idx > 0) {	// 루트노드까지 합 업데이트
			tree[idx] = tree[idx * 2] + tree[idx*2 + 1];
			idx /= 2;
		}
	}

	static long query(int l, int r) {
		l += si - 1;	// 범위 왼쪽 트리 인덱스
		r += si - 1;	// 범위 오른쪽 트리 인덱스

		long s = 0;		// 합
		while (l <= r) {	// 범위가 역전될때까지
			if (l % 2 == 1) {	// 왼쪽 범위가 오른쪽 자식인경우 합에 더해주고 오른쪽으로 옮기기
				s += tree[l++];
			}
			if (r % 2 == 0) {	// 오른쪽 범위가 왼쪽 자식인경우 합에 더해주고 왼쪽으로 옮기기
				s += tree[r--];
			}
			l /= 2;		// 부모로 이동
			r /= 2;
		}
		return s;
	}
}
