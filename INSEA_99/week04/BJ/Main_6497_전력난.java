package INSEA_99.week04.BJ;

import java.io.*;
import java.util.*;

/* 메모리 : 324468 KB
 * 시간 : 1576 ms
 * 
 * idea1 : 최소 스패닝 트리 프림 알고리즘 사용
 * Soveld! -> 크루스칼이 더 빠른거같아요
 * 
 */

public class Main_6497_전력난 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int n, m, totalWeight, minGraphLength;	// 길의 수, 집의 수, 가중치 합, 최소 길이
	static List<EdgeInfo>[] adj;	// 인접 리스트
	
	/* 메인 */
	public static void main(String[] args) throws IOException {
		while(input()) {
			run();	// 실행
			output(); // 출력
		}
		bw.flush();
		bw.close();
		br.close();
		return;
	}
	
	/***
	 * 멤버 변수로 간선 weight과 간선의 도착지 nextNode를 가지고 있음
	 * compare는 간선 weight로 이루어짐
	 * @author 인바다
	 *
	 */
	static class EdgeInfo implements Comparable<EdgeInfo>{
		int weight;	// 간선 가중치
		int nextNode;	// 간선 목적지
		EdgeInfo(int weight, int nextNode) {
			this.weight = weight;
			this.nextNode = nextNode;
		}
		
		@Override
		public int compareTo(EdgeInfo o) {	// weight
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	/* 입력  */
	static boolean input() throws IOException {
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());	// 집의 수
		n = Integer.parseInt(st.nextToken());	// 길의 수 
		if(m == 0 && n== 0) return false;
		
		/* 초기화 */
		totalWeight = 0;	// 가중치 합
		minGraphLength = 0;	// 최소 길이
		adj = new ArrayList[n + 1];
		for(int i = 0; i < n; ++i) adj[i] = new ArrayList<>();
		
		for(int i = 0; i < n; ++i) {	// 인접 리스트
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			totalWeight += w;
			adj[a].add(new EdgeInfo(w, b));
			adj[b].add(new EdgeInfo(w, a));
		}
		return true;
	}
	
	/* 출력  */
	static void output() throws IOException{
		bw.write(totalWeight - minGraphLength + "\n");	// 출력				
	}
	
	/* 실행 */
	static void run() {
		PriorityQueue<EdgeInfo> pq = new PriorityQueue<>();	// 프림 알고리즘에 사용될 우선순위 큐
		boolean[] visited = new boolean[n];
		
		pq.add(new EdgeInfo(0, 0));	// 시작 지점 넣기
		int left = m;	// 방문해야 하는 노드 수
		while(!pq.isEmpty() && left != 0) {	// 더 진행 할 것이 없거나 방문 다 했을 경우 종료
			EdgeInfo now = pq.poll();	// 현재 노드
			if(visited[now.nextNode]) continue;	// 방문한 경우 pass
			visited[now.nextNode] = true;	// 방문 표	시
			minGraphLength += now.weight;	// 길이 업데이트
			left--;	// 방문해야하는 노드 업데이트
			for(EdgeInfo next : adj[now.nextNode]) {	// 현재 노드와 연결된 간선 정보 업데이트
				if(visited[next.nextNode]) continue;	// 이미 방문한 노드와 연결된 간선은 패스
				pq.add(next);
			}
		}
	}
}
