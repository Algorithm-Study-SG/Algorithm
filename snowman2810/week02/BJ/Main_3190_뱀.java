package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_3190_뱀 {

	static HashSet<Apple> apple = new HashSet<>();
	static Deque<Snake> snake = new ArrayDeque<>();
	static int n;
	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(stk.nextToken()) - 1;
			int c = Integer.parseInt(stk.nextToken()) - 1;
			apple.add(new Apple(r, c));
		}
		int l = Integer.parseInt(br.readLine());
		int move[] = new int[l];
		char turn[] = new char[l];

		for (int i = 0; i < l; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			move[i] = Integer.parseInt(stk.nextToken());
			turn[i] = stk.nextToken().charAt(0);
		}

		int time = 0;
		int r = 0;
		int c = 0;
		int d = 0;
		int count = 0;
		snake.addFirst(new Snake(0, 0));
		while (true) {
			// 움직일 방향 정하기
			if (count < move.length && move[count] == time) {
				if (turn[count] == 'L') {
					d--;
				} else if (turn[count] == 'D') {
					d++;
				}
				count++;
			}

			if (d < 0) {
				d = 3;
			} else {
				d %= 4;
			}
			r += dr[d];
			c += dc[d];

			// 머리에 새로 생성
			snake.addFirst(new Snake(r, c));

			// 새로 생성된 머리로 인해 뱀이 죽는가
			if (isDead()) {
				break;
			}

			// 사과를 먹지 않으면 꼬리를 삭제
			if (apple.contains(new Apple(r, c))) {
				apple.remove(new Apple(r, c));
			} else {
				snake.removeLast();
			}

			time++;
		}

		System.out.println(time + 1);

		br.close();
	}

	// 죽었는지 확인하는 메소드
	public static boolean isDead() {
		Snake head = snake.pollFirst();
		int r = head.getR();
		int c = head.getC();

		// 머리를 빼내서 기존에 존재하는지, 범위를 벗어나는지 확인
		if (r >= 0 && r < n && c >= 0 && c < n) {
			if (!snake.contains(head)) {
				snake.addFirst(head); // 살아있으면 빼둔 머리값을 다시 입력
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}

	}

	// 뱀의 정보를 저장하기 위한 클래스 선언
	static class Snake {
		private int r;
		private int c;

		public Snake(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public int getR() {
			return r;
		}

		public int getC() {
			return c;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + r;
			result = prime * result + c;
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
			Snake other = (Snake) obj;
			if (r != other.r)
				return false;
			if (c != other.c)
				return false;
			return true;
		}
	}

	// 사과의 정보를 저장할 클래스 선언
	static class Apple {
		private int r;
		private int c;

		public Apple(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + r;
			result = prime * result + c;
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
			Apple other = (Apple) obj;
			if (r != other.r)
				return false;
			if (c != other.c)
				return false;
			return true;
		}

	}

}