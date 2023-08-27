package Week04;

/**
 * 메모리 : 314420 KB
 * 시간 : 1504 ms
 * 
 * # 접근 : 전형적인 MST 문제라고 생각했다.
 *         최대한 절약하기 위해서는 최소한으로 비용을 사용해야하기 때문이다.
 *         "우주선과의 교감" 문제에서 Prim 알고리즘을 인접행렬로 구현했었기 때문에
 *         이번에는 인접리스트를 사용한 우선순위큐로 구현해봤다. (확실히 구현하기 훨씬 편하다)
 *         
 *         각 노드의 인접노드를 인접리스트로 저장했다.
 *         노드를 우선순위큐에 넣기 위해서 compareTo를 오버라이드했다.
 *         구하고자 하는 값이 절약가능한 최대 비용이기 때문에, 총 비용 - 최소 비용으로 정답을 도출했다. 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_6497_전력난 {
	
	static class Node implements Comparable<Node>{
		int to;
		int dist;
		
		public Node(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {	// priority queue 사용하기 위해
			return dist - o.dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());		// 집의 수, 길의 수 입력
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			if(M==0 && N==0) break;	// 입력 종료
			
			boolean[] isVisited = new boolean[M];
			List<Node>[] nodes = new List[M];
			for(int i = 0; i < M; i++) {
				nodes[i] = new ArrayList<Node>();
			}
			
			int totalCost = 0;								// 전체 비용 저장
			for(int i = 0; i < N; i++) {					// 길 정보 입력
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());
				
				nodes[a].add(new Node(b, dist));
				nodes[b].add(new Node(a, dist));
				totalCost += dist;
			}
			
			// 0번 노드 시작
			PriorityQueue<Node> pq = new PriorityQueue<>();	// 방문된 노드들과 가장 가까운 노드부터 앞에 오게 된다.
			pq.addAll(nodes[0]);							// 0번 노드에 연결된 모든 길 pq에 추가
			isVisited[0] = true;
			int cost = 0;									// 최단 경로의 비용 저장
			int cnt = 1;									// 모두 방문했을때 pq에서 poll() 연산 안 하기 위한 변수
			
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				if(isVisited[cur.to]) continue;
				
				isVisited[cur.to] = true;
				cnt++;
				cost += cur.dist;
				pq.addAll(nodes[cur.to]);
				
				if(cnt == N) break;
			}
			
			ans.append(totalCost - cost).append("\n");
		}
		
		System.out.print(ans);
	}
}
