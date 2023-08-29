package study.boj;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1774_우주신과의_교감 {

	/*
	 * 메모리 : 38492 KB
	 * 시간 : 556 ms
	 * 
	 * 이미 연결된 통로가 주어지는데 프림 방식을 이용하면 여러개의 트리가 만들어지고 계산하기 힘들것 같아서 크루스칼 방식을 이용해 통합해나간다
	 */

	static int[] parent;
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 정점들의 좌표를 저장
		Point[] points = new Point[N + 1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			points[i] = new Point(r, c);
		}

		// MST가 완성되는 조건을 체크
		int totalEdge = 0;

		// 서로소 집합을 생성
		make();

		// 이미 연결된 정점들의 정보를 이용해 통합
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (union(a, b)) {
				totalEdge++;
			}
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 1; i < N + 1; i++) {
			for (int j = i + 1; j < N + 1; j++) {
				if (find(i) != find(j)) { // 서로 다른 부모를 가르키고있다면

					// 거리 계산식
					double length = Math.sqrt(Math.pow(points[i].getX() - points[j].getX(), 2)
							+ Math.pow(points[i].getY() - points[j].getY(), 2));
					pq.offer(new Edge(i, j, length)); // 정점들의 번호와 길이를 추가
				}
			}
		}

		// 연결된 최소 거리를 저장
		double totalLength = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (union(edge.from, edge.to)) { // 통합이 가능하면 실행후 가중치 저장
				totalEdge++;
				totalLength += edge.length;
			}
			if (totalEdge == N) {
				break;
			}
		}

		System.out.println(String.format("%.2f", totalLength));

		br.close();
	}

	// 서로소집합 생성 및 초기화
	static void make() {
		parent = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			parent[i] = i;
		}
	}

	// 들어온 값이 해당 트리의 대표값인지를 확인
	static int find(int a) {
		if (parent[a] == a) {
			return a;
		}
		return parent[a] = find(parent[a]); // 경로압축
	}

	// 서로의 대표자가 다르면 통합
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) {
			return false;
		}
		parent[aRoot] = bRoot;
		return true;
	}

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		double length;

		public Edge(int from, int to, double length) {
			super();
			this.from = from;
			this.to = to;
			this.length = length;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.length, o.length);
		}
	}

}
