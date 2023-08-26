package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_6497_전력난 {

	/*
	 * 메모리 : 295428 KB
	 * 시간 : 1212 ms
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테스트 케이스가 계속 주어지기때문에 조심하자
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			if (V == 0 && E == 0) { // 종료하는 조건
				break;
			}

			// 인접리스트 생성, 초기화
			List<Edge>[] list = new List[V];
			for (int i = 0; i < V; i++) {
				list[i] = new ArrayList<>();
			}

			// 들어오는 간선의 비용 총합
			int totalWeight = 0;
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				totalWeight += weight;

				// 양방향 그래프
				list[from].add(new Edge(to, weight));
				list[to].add(new Edge(from, weight));
			}

			boolean[] visited = new boolean[V];
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			visited[0] = true;
			for (Edge edge : list[0]) { // 출발지에서 갈 수 있는 모든 정점들을 입력
				pq.offer(edge);
			}

			int sumWeight = 0;
			int edgeCount = 0;
			while (!pq.isEmpty()) {
				Edge edge = pq.poll();
				if (visited[edge.to]) { // 중복방문 체크
					continue;
				}
				visited[edge.to] = true;
				sumWeight += edge.weight; // MST에서의 비용 저장
				edgeCount++; // 선택된 간선의 수 증가

				// 방문 안한 정점으로 가는 간선이 존해하면 추가
				for (Edge e : list[edge.to]) {
					if (!visited[e.to]) {
						pq.offer(e);
					}
				}

				// MST가 완성이 되었으면 탈출
				if (edgeCount == V - 1) {
					break;
				}
			}
			System.out.println(totalWeight - sumWeight);
		}
		br.close();
	}

	static class Edge implements Comparable<Edge> {
		int to;
		int weight;

		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

}
