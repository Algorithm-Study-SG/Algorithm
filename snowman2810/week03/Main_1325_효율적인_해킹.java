package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

// 메모리 306392KB, 시간 8764
public class Main_1325_효율적인_해킹 {
	static boolean[] selected; // 방문여부 확인
	static int[] ans; // 정점별로 횟수 확인
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>(); // 인접리스트 생성
	static Deque<Integer> queue = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken()); // 정점의 수
		int M = Integer.parseInt(stk.nextToken()); // 간선의 수
		ans = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			list.add(new ArrayList<>()); // 인접리스트 초기화
		}
		int A = 0; // 처음 들어온값
		int B = 0; // 두번째로 들어온 값
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			A = Integer.parseInt(stk.nextToken());
			B = Integer.parseInt(stk.nextToken());
			list.get(A).add(B);
		}
		for (int i = 1; i < N + 1; i++) {
			selected = new boolean[N + 1]; // 방문체크 초기화
			search(i);
		}
		int max = 0; // 최고값
		for (int i = 1; i < N + 1; i++) {
			max = Math.max(max, ans[i]); // 하나씩 비교하면서 최고값을 찾는다
		}
		for (int i = 1; i < N + 1; i++) {
			if (ans[i] == max) { // 최고값과 일치하면 sb에 추가
				sb.append(i + " ");
			}
		}
		System.out.println(sb);
		br.close();
	}

	// bfs 탐색
	private static void search(int number) {
		queue.add(number); // 처음 입력받은 값 큐에 추가
		selected[number] = true; // 방문체크
		while (!queue.isEmpty()) { // 큐에 값이 남아있으면
			int head = queue.pollFirst(); // 큐의 첫번째 값 가져오기
			for (int i : list.get(head)) { // 첫번째 값과 연결된 정점 방문
				if (!selected[i]) { // 방문한적이 없으면
					selected[i] = true; // 방문체크
					ans[i]++; // 해당 정점 개수 추가
					queue.addLast(i); // 해당 정점 큐에 추가
				}
			}
		}
	}

}
