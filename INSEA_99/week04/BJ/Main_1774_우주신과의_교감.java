package INSEA_99.week04.BJ;

import java.io.*;
import java.util.*;
import java.awt.Point;

/* 메모리 : 33936KB
 * 시간 : 528ms
 * 
 * idea1 : 초기 연결된 노드는 visited 처리 후 최소 스패닝 트리 프림 사용
 * 
 * --> fail! : 연결되지 않은 노드들이 초기 연결된 노드는 visited 처리로 인해 연결되지 않음
 * 
 * idea2 : 초기 연결된 노드는 인접 리스트에 추가 후 프림 알고리즘을 통한 방문 시 visited 처리
 * 
 * --> Solved! 528 ms
 * 
 * idea3 : idea2 + 노드 다 방문한 경우 종료
 * 
 * --> Solved! 308 ms
 * 
 */

public class Main_1774_우주신과의_교감 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int n, m, remaining;	// 우주 신 수, 초기 연결된 통로 수, 앞으로 방문해야 하는 신 수
	static double minTotalDistance;	// 최소 통로 길이
	static PriorityQueue<EdgeInfo> pq = new PriorityQueue<>();	// 프림 알고리즘에 사용될 우선순위 큐
	static List<Integer>[] adj;	// 초기 연결된 통로 인접 리스트
	static Point[] nodes;	// 신 좌표
	static boolean[] visited;	// 방문 여부
	
	/* 메인 */
	public static void main(String[] args) throws IOException {
		input();	// 입력 받기
		run();	// 실행
		output(); // 출력
		return;
	}
	
	/***
	 * 멤버 변수로 간선 weight과 간선의 도착지 nextNode를 가지고 있음
	 * compare는 간선 weight로 이루어짐
	 * @author 인바다
	 *
	 */
	static class EdgeInfo implements Comparable<EdgeInfo>{
		double weight;	// 간선 가중치
		int nextNode;	// 간선 목적지
		EdgeInfo(double weight, int nextNode) {
			this.weight = weight;
			this.nextNode = nextNode;
		}
		
		@Override
		public int compareTo(EdgeInfo o) {	// weight
			return Double.compare(this.weight, o.weight);
		}
	}
	
	/* 입력  */
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine()); 
		n = Integer.parseInt(st.nextToken());	// 우주 신 수
		m = Integer.parseInt(st.nextToken());	// 초기 연결된 통로 수
		remaining = n;	// 앞으로 방문해야 하는 신 수
		nodes = new Point[n+1];	// 신 좌표 초기화
		adj = new ArrayList[n+1]; // 초기 연결된 통로 인접 리스트 초기화
		visited = new boolean[n+1];	// 방문 여부 초기화
		for(int i = 1; i <= n; ++i) {	// 신 좌표 입력 받아 노드 정보 업데이트
			st = new StringTokenizer(br.readLine());	
			int r = Integer.parseInt(st.nextToken());	
			int c = Integer.parseInt(st.nextToken());
			nodes[i] = new Point(r, c);
			adj[i] = new ArrayList<>();
		}
		for(int i = 1; i <= m; ++i) {	// 초기 연결된 좌표 입력 받아 인접 리스트 생성
			st = new StringTokenizer(br.readLine());	
			int a = Integer.parseInt(st.nextToken());	
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		br.close();
	}
	
	/* 출력  */
	static void output() throws IOException{
		bw.write(String.format("%.2f", minTotalDistance) + "");	// 출력			
		bw.flush();
		bw.close();
	}
	
	/* 실행 */
	static void run() {
		pq.add(new EdgeInfo(0, 1));	// 시작 노드는 1로 설정
		while(remaining != 0) {	// 방문할 노드가 남아있다면 계속
			while(!pq.isEmpty()) {	// pq가 비어있지 않는 동안 계속
				EdgeInfo nowEdge = pq.poll();	// 방문할 노드
				if(visited[nowEdge.nextNode]) continue;	// 이미 방문했다면 continue
				minTotalDistance += nowEdge.weight;	// 거리 업데이트
				visit(nowEdge.nextNode);	// 노드와 연결된 간선 정보 추가
				break;
			}

		}
	}
	
	/***
	 * 노드에 방문하여 연결된 간선 정보 우선순위 큐에 추가
	 * @param now 방문할 노드
	 */
	static void visit(int now) {
		if(visited[now]) return;	// 이미 방문한 노드면 종료
		visited[now] = true;		// 노드 방문
		remaining--;	// 앞으로 방문해야하는 노드 개수 차감
		for(int next : adj[now]) visit(next);	// 현재 노드와 초기 연결된 노드 방문 처리
		for(int next = 1; next <= n; ++next) {	// 현재 노드와 연결된 간선 정보 업데이트
			if(visited[next]) continue;	// 이미 방문한 노드와 연결된 간선은 패스
			double distance = getDistance(nodes[now], nodes[next]);	// 현재 노드와 연결될 노드 거리 구하기
			pq.add(new EdgeInfo(distance, next));	// 현재 노드와 연결된 간선과 도착지 노드 정보 우선순위 큐에 추가
		}
	}
	
	/***
	 * 
	 * @param p1 노드 좌표
	 * @param p2 노드 좌표
	 * @return p1과 p2 사이 거리
	 */
	static double getDistance(Point p1, Point p2) {	// 두 노드의 거리 구하기
		return Math.pow(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2), 0.5);
	}
}
