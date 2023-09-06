package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1005_ACM_Craft {
	/*
	 * 메모리 : 255608 KB
	 * 시간 : 936 ms -> 872 ms (종료조건 추가)
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int T = 1; T <= t; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int vertexCount = Integer.parseInt(st.nextToken()); // 정점의 개수
			int edgeCount = Integer.parseInt(st.nextToken()); // 간선의 개수
			int[] degree = new int[vertexCount + 1]; // 차수를 저장할 배열
			int[] time = new int[vertexCount + 1]; // 주어지는 시간을 저장할 배열
			int[] maxTime = new int[vertexCount + 1]; // 해당 정점에 최종적으로 저장될 시간을 저장할 배열
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= vertexCount; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}

			// 인접 리스트 생성 및 초기화
			List<Edge>[] list = new List[vertexCount + 1];
			for (int i = 0; i < vertexCount + 1; i++) {
				list[i] = new ArrayList<>();
			}

			// 인접 리스트에 주어진 간선의 정보 저장
			for (int i = 0; i < edgeCount; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				list[start].add(new Edge(start, end, time[end]));
				degree[end]++;
			}

			// 구하고자 하는 목표의 정보를 입력
			int target = Integer.parseInt(br.readLine());

			// 큐를 생성하고 진입 차수가 0인 정점들이 가르키는 정보를 큐에 입력
			Queue<Edge> queue = new ArrayDeque<>();
			for (int i = 1; i <= vertexCount; i++) {
				if (degree[i] == 0) {
					maxTime[i] = Math.max(time[i], maxTime[i]);
					for (Edge e : list[i]) {
						queue.add(e);
					}
				}
			}

			// 큐가 빌때까지 반복
			while (!queue.isEmpty()) {
				Edge edge = queue.poll();

				// 목적지에 저장된 시간과 새롭게 저장될 시간을 비교 후 더 큰값을 저장
				maxTime[edge.to] = Math.max(maxTime[edge.to], maxTime[edge.from] + edge.time);
				degree[edge.to]--; // 목적지의 차수를 감소
				if (degree[edge.to] == 0) { // 차수가 0이면
					if (edge.to == target) { // 종료조건 추가
						break;
					}
					for (Edge e : list[edge.to]) { // 해당 정점이 가르키고 있던 정점들을 큐에 추가
						queue.add(e);
					}
				}
			}

			// 출력
			System.out.println(maxTime[target]);

		}

		br.close();
	}

	static class Edge {
		int from;
		int to;
		int time;

		public Edge(int from, int to, int time) {
			super();
			this.from = from;
			this.to = to;
			this.time = time;
		}
	}

}
