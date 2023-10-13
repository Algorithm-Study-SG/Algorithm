package INSEA_99.week09.BJ;
import java.util.List;
import java.util.ArrayList;

/*
 * 정확성  테스트
 * 테스트 1 〉	통과 (0.08ms, 73MB)
 * 테스트 2 〉	통과 (0.07ms, 74.8MB)
 * 테스트 3 〉	통과 (0.17ms, 81.4MB)
 * 테스트 4 〉	통과 (0.15ms, 76.5MB)
 * 테스트 5 〉	통과 (0.04ms, 71.5MB)
 * 테스트 6 〉	통과 (0.70ms, 82.8MB)
 * 테스트 7 〉	통과 (0.26ms, 81.6MB)
 * 테스트 8 〉	통과 (0.39ms, 83.5MB)
 * 테스트 9 〉	통과 (0.33ms, 71.7MB)
 * 테스트 10 〉	통과 (0.39ms, 67.6MB)
 * 테스트 11 〉	통과 (0.94ms, 78MB)
 * 테스트 12 〉	통과 (1.30ms, 79.9MB)
 * 테스트 13 〉	통과 (0.47ms, 75.8MB)
 */

class Solution_네트워크 {
	static int length; // 노드 수
	static List<Integer>[] adj; // 간접 리스트
	static boolean[] visited;
	static int answer; // 네트워크 수

	public int solution(int n, int[][] computers) {
		init(n, computers);
		run();
		return answer;
	}

	public void init(int n, int[][] adjMatrix) {
		answer = 0;
		length = n;
		adj = new List[n];
		visited = new boolean[n];

		for (int i = 0; i < n; ++i) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (adjMatrix[i][j] == 1) {
					adj[i].add(j);
					adj[j].add(i);
				}
			}
		}
	}

	public void run() {
		for (int i = 0; i < length; ++i) { // 네트워크 탐색
			if (!visited[i]) {
				answer++;
				visited[i] = true;
				setTree(i);
			}
		}
	}

	public void setTree(int root) { // 네트워크 설정
		for (int child : adj[root]) {
			if (!visited[child]) {
				visited[child] = true;
				setTree(child);
			}
		}
	}
}