package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9470_Strahler_순서 {

	/*
	 * 메모리 : 14012 KB
	 * 시간 : 120 ms
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int[] degree = new int[M + 1];
			int[] strahler = new int[M + 1];
			int[] maxCount = new int[M + 1];

			// 인접리스트 생성 및초기화
			List<Integer>[] list = new List[M + 1];
			for (int i = 1; i < M + 1; i++) {
				list[i] = new ArrayList<>();
			}

			// 위상정렬을 위해서 차수를 증가시키면서 저장
			for (int i = 0; i < P; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				degree[to]++;
				list[from].add(to);
			}

			// 차수가 없는 정점부터 시작하기 위해서 추가
			Queue<Integer> queue = new ArrayDeque<>();
			for (int i = 1; i < M + 1; i++) {
				if (degree[i] == 0) {
					strahler[i] = 1;
					queue.offer(i);
				}
			}

			int lastIndex = 0; // 마지막으로 처리된 정점의 위치를 저장
			while (!queue.isEmpty()) {
				int from = queue.poll();
				lastIndex = from;
				for (int to : list[from]) {
					degree[to] -= 1;

					// 목표의 strahler 숫자가 출발지의 숫자보다 작으면 큰값을 저장하고 maxCount를 초기화
					if (strahler[to] < strahler[from]) {
						strahler[to] = strahler[from];
						maxCount[to] = 0;
					} else if (strahler[to] == strahler[from]) { // 목표의 strahler 숫자가 출발지의 숫자와 같다면 maxCount 증가
						maxCount[to]++;
					}
					if (degree[to] == 0) { // 목표의 차수가 0이되었을때
						if (maxCount[to] > 0) { // strahler 숫자가 중복이 되었으면
							strahler[to]++; // 숫자를 증가
						}
						queue.offer(to); // 큐에 추가
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append(K).append(" ").append(strahler[lastIndex]); // 입력받은 테스트 번호와 마지막으로 처리된 장소의 strahler 숫자를 출력
			System.out.println(sb);
		}
		br.close();
	}

}
