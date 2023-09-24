package INSEA_99.week06.BJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * idea1 : 방문 여부를 String으로 저장하고 bfs 실행
 * 
 *	75932 KB
 *	700 ms  
 */

public class Main_1525_퍼즐 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { 1, 0, 0, -1 };

	static String TARGET = "123456780";

	static int t;	// 이동 횟수
	static Set<String> visited = new HashSet<>();	// 방문 정보 저장
	static Deque<String> dq = new ArrayDeque<>();	// bfs를 위한 queue

	/* 메인 */
	public static void main(String[] args) throws IOException {
		input(); // 입력 받기
		run(); // 실행
		output(); // 출력
		return;
	}

	/* 입력 */
	static void input() throws IOException {
		String temp = "";	// 시작 정보 저장
		for (int r = 0; r < 3; ++r) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 3; ++c) {
				temp += st.nextToken();
			}
		}
		dq.add(temp);
		visited.add(temp);
		br.close();
	}

	/* 출력 */
	static void output() throws IOException {
		bw.write(t + ""); // 출력
		bw.flush();
		bw.close();
	}

	/* 실행 */
	static void run() {
		bfs();
	}

	static void bfs() {
		while (!dq.isEmpty()) {		// 이동할 수 있는 경우가 없을 때까지 실행
			int qsize = dq.size();		// bfs 깊이 마다 실행
			for (int s = 0; s < qsize; ++s) {
				String now = dq.poll();	
				if (now.equals(TARGET)) {	// 찾았으면 종료
					return;
				}
				int emptyIndex = now.indexOf('0');	// 0 위치 찾기
				int r = emptyIndex / 3;
				int c = emptyIndex % 3;

				for (int i = 0; i < 4; i++) {	// 0 다른 위치로 이동
					int nr = r + dr[i];
					int nc = c + dc[i];

					if (nr < 0 || nr >= 3 || nc < 0 || nc >= 3) {
						continue;
					}

					int newEmptyIndex = nr * 3 + nc;
					String next = swap(now, emptyIndex, newEmptyIndex);
					if (visited.contains(next)) {	// 방문한 경우 continue
						continue;
					}
					visited.add(next);
					dq.add(next);
				}
			}
			t++;
		}
		t = -1;
	}

	/**
	 * 교환된 정보를 반환한다
	 * @param string 현재 정보
	 * @param idx1	교환할 위치
	 * @param idx2	교환할 위치
	 * @return 교환된 정보
	 */
	static String swap(String string, int idx1, int idx2) {
		char[] c = string.toCharArray();
		char temp = c[idx1];
		c[idx1] = c[idx2];
		c[idx2] = temp;
		return new String(c);
	}
}
