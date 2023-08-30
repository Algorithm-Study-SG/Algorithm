package INSEA_99.week05.BJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.StringTokenizer;

public class Main_14430_자원_캐기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int n, m;		// 영역 행, 열 크기
	static int[][] area;	// 영역 정보

	public static void main(String[] args) throws IOException {
		input(); // 입력 받기
		run(); // 실행
		output(); // 출력
		return;
	}
	
	/* 입력 */
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());					// 영역 행, 열 크기
		m = Integer.parseInt(st.nextToken());
		area = new int[n+1][m+1];								// 영역 초기화
		for (int r = 1; r <= n; ++r) {							// 영역 정보 업데이트
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= m; ++c) {
				area[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
	}

	/* 출력 */
	static void output() throws IOException {
		bw.write(area[n][m] + "");
		bw.flush();
		bw.close();
	}

	/* 실행 */
	static void run() {
		for (int r = 1; r <= n; ++r) {							// 행마다 열 이동하며 왼쪽, 윗값 중 큰거 저장
			for (int c = 1; c <= m; ++c) {
				area[r][c] += Math.max(area[r-1][c], area[r][c-1]);
			}
		}
	}
}
