package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_1525_퍼즐 {
	/*
	 * 메모리 : 121732 KB
	 * 시간 : 712 ms
	 * 
	 * 
	 * 문제 접근방법
	 * 
	 * BFS + 2차원 배열
	 * 최소 이동 횟수를 구하라는 문제여서 BFS로 풀 생각을 하였으나
	 * 2차원 배열로 문제를 풀려고 생각해보니 바뀐 0의 위치를 기억해줘야한다
	 * 
	 * DFS + 2차원 배열
	 * BFS로 접근하기 힘들것 같아서 DFS로 생각을 하였으나 DFS로 탐색을 할 경우
	 * 2차원 배열의 정보와 바꾸려는 수를 저장하고 이미 등장한 적이 있는지를 생각하기에 너무 복잡해서 포기
	 * 
	 * BFS + String
	 * 클래스를 하나 생성해서 현재 위치정보를 String로 저장하고 변경할 값을 함께 중복검사를 해준다
	 * String과 변경할 값이 등장한 적이 있다면 큐에 추가를 안해준다 (이미 탐색한 지점, 방향이기 때문에)
	 * 
	 */

	static Set<Puzzle> set;
	static Queue<Puzzle> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = "";
		String answer = "123456780";
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				input += st.nextToken();
			}
		}
		if (answer.equals(input)) {
			System.out.println(0);
			return;
		}

		set = new HashSet<>();
		queue = new ArrayDeque<>();

		// 큐에 집어넣기 위한 작업
		move(input, 0);

		while (!queue.isEmpty()) { // 큐가 빌때까지 반복
			Puzzle puzzle = queue.poll();
			// 큐에서 꺼낸 후에 문자의 위치를 바꿔준다
			String temp = change(puzzle.current, puzzle.current.indexOf('0'), puzzle.des);
			
			// 바꾼 문자가 정답과 같다면 탈출
			if (temp.equals(answer)) {
				System.out.println(puzzle.moveCount + 1);
				return;
			}
			// 아니라면 다시 해당 위치에서 사방탐색을 실행
			move(temp, puzzle.moveCount + 1);

		}

		// 여기까지 도달하면 정답에 갈 수 없는 상황이다
		System.out.println("-1");

		br.close();
	}

	// String로 표현된 값에서 0의 위치와 0과 바뀔 수 있는 위치를 찾는다
	static void move(String input, int moveCount) {
		if (input.indexOf('0') == 0) {
			check(input, 1, moveCount);
			check(input, 3, moveCount);
			return;
		}
		if (input.indexOf('0') == 1) {
			check(input, 0, moveCount);
			check(input, 2, moveCount);
			check(input, 4, moveCount);
			return;
		}
		if (input.indexOf('0') == 2) {
			check(input, 1, moveCount);
			check(input, 5, moveCount);
			return;
		}
		if (input.indexOf('0') == 3) {
			check(input, 0, moveCount);
			check(input, 4, moveCount);
			check(input, 6, moveCount);
			return;
		}
		if (input.indexOf('0') == 4) {
			check(input, 1, moveCount);
			check(input, 3, moveCount);
			check(input, 5, moveCount);
			check(input, 7, moveCount);
			return;
		}
		if (input.indexOf('0') == 5) {
			check(input, 2, moveCount);
			check(input, 4, moveCount);
			check(input, 8, moveCount);
			return;
		}
		if (input.indexOf('0') == 6) {
			check(input, 3, moveCount);
			check(input, 7, moveCount);
			return;
		}
		if (input.indexOf('0') == 7) {
			check(input, 4, moveCount);
			check(input, 6, moveCount);
			check(input, 8, moveCount);
			return;
		}
		if (input.indexOf('0') == 8) {
			check(input, 5, moveCount);
			check(input, 7, moveCount);
			return;
		}
	}

	// 이미 방문한 적이 있는지를 확인 후 큐에 넣어준다
	static void check(String input, int des, int moveCount) {
		Puzzle puzzle = new Puzzle(input, des, moveCount);
		if (!set.contains(puzzle)) {
			queue.offer(puzzle);
			set.add(puzzle);
		}
	}

	// String의 위치를 바꿔주는 함수
	static String change(String input, int zeroLoc, int targetLoc) {
		// setCharAt을 이용하기 위해 선언, 원래는 그냥 String에서 값을 바꿔보려고 하였으니 실패해서 검색으로 찾음
		StringBuilder sb = new StringBuilder();
		sb.append(input);
		sb.setCharAt(zeroLoc, input.charAt(targetLoc)); // 0의 위치에 바꿀위치에 있던 값을 저장
		sb.setCharAt(targetLoc, '0'); // 바꿀 위치의 값을 0으로 저장
		return sb.toString();
	}

	// 값을 저장하기 위해 클래스 선언
	static class Puzzle {
		String current;
		int des;
		int moveCount;

		public Puzzle(String current, int des, int moveCount) {
			this.current = current;
			this.des = des;
			this.moveCount = moveCount;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((current == null) ? 0 : current.hashCode());
			result = prime * result + des;
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
			Puzzle other = (Puzzle) obj;
			if (current == null) {
				if (other.current != null)
					return false;
			} else if (!current.equals(other.current))
				return false;
			if (des != other.des)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Puzzle [current=" + current + ", des=" + des + ", moveCount=" + moveCount + "]";
		}

	}

}
