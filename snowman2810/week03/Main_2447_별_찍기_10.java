package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//메모리 46484KB, 시간 428ms
public class Main_2447_별_찍기_10 {

	static boolean[][] output;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		output = new boolean[N][N];
		star(0, 0, N, false);
		StringBuilder sb = new StringBuilder();
		for (boolean[] out : output) {
			for (boolean b : out) {
				if (b) {
					sb.append("*");
				} else {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}

	// *이 표시될 지역을 true로 설정
	public static void star(int r, int c, int size, boolean empty) {
		int len = size / 3;
		if (size == 1) { // 사이즈가 1이면
			if (!empty) { // 빈칸이 아니라면
				output[r][c] = true; // true저장
			}
			return;
		}
		star(r, c, len, empty); // 좌상
		star(r, c + len, len, empty); // 상
		star(r, c + len * 2, len, empty); // 우상
		star(r + len, c, len, empty); // 좌
		star(r + len, c + len, len, true); // 중 가운데는 찍히면 안되기 때문에 empty에 true를 준다
		star(r + len, c + len * 2, len, empty); // 우
		star(r + len * 2, c, len, empty); // 좌하
		star(r + len * 2, c + len, len, empty); // 하
		star(r + len * 2, c + len * 2, len, empty); // 우하
	}

}
