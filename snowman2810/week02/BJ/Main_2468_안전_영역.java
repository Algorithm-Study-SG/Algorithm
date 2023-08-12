package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_2468_안전_영역 {

	static int input[][]; // 입력받을 배열
	static HashSet<safe> selected; // 해당 좌표를 이미 방문했는지 체크

	static int ans = 0; // 답을 저장할 변수
	static int depth = 0; // 비의 높이를 저장할 변수

	// 4방향 탐색을 위한 준비
	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());

		input = new int[n][n];
		selected = new HashSet<>();

		int max = 0; // 비가 어느 높이까지 올라갈지를 체크하기 위해 가장 높은 지역을 찾는다

		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				input[i][j] = Integer.parseInt(stk.nextToken());
				max = Math.max(max, input[i][j]);
			}
		}

		for (int d = 0; d < max; d++) {
			selected.clear(); // 비의 높이마다 값이 달라지기 때문에 초기화
			int count = 0; // 중복을 제외하고 몇번이나 다시 찾았는가를 확인

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// 비의 높이보다 높고 선택이 안되었으면 검색
					if (input[i][j] > depth && !selected.contains(new safe(i, j))) {
						search(i, j);
						count++;
					}
				}
			}

			ans = Math.max(ans, count);
			depth++;

		}

		System.out.println(ans);

		br.close();

	}

	public static void search(int r, int c) {
		selected.add(new safe(r, c)); // 방문체크
		for (int d = 0; d < 4; d++) { // 4방향 탐색
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < input.length && nc >= 0 && nc < input.length) { // 배열의 범위 안에서
				if (input[nr][nc] > depth) { // 해당 좌표가 비의 높이보다 크면
					if (!selected.contains(new safe(nr, nc))) { // 해당 좌표를 방문한적이 없으면
						search(nr, nc); // 검색
					}

				}
			}
		}
	}

	static class safe { // HashSet을 사용해서 좌표가 같으면 같다고 표시하기위해 hashCode와 equals 생성
		private int r;
		private int c;

		public safe(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			safe other = (safe) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}

	}

}
