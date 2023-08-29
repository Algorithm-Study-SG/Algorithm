package INSEA_99.week04.BJ;

import java.io.*;
import java.util.*;
import java.awt.Point;

/* 메모리 : 16048KB
 * 시간 : 152ms
 * 
 * idea1 : 트리의 지름 문제 처럼 dfs로 트리를 만들며 들어오는 값의 최댓값과 두번째 최댓값을 저장함. 
 * 		     이를 이용하여 순서를 구함 
 */

public class Main_9470_Strahler_순서 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int k, m, p;	// k는 테스트 케이스 번호, m은 노드의 수, p는 간선의 수
	static Point[] nodes;	// node의 최댓값과 두번째 최대값을 저장
	static List<Integer>[] adj;	// 인접리스트
	static boolean visited[];	// 트리 생성시 방문 여부 표시
	
	/* 메인 */
	public static void main(String[] args) throws IOException {
		int t = Integer.parseInt(br.readLine());
		while(t-- != 0) {
			input();	// 입력받기
			run();	// 순서구하기
			bw.write(k + " " + nodes[m].x + "\n");						
		}
		bw.flush();
		bw.close();
		return;
	}
	
	/* 입력구하기 */
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		nodes = new Point[m+1];
		visited = new boolean[m+1];
		adj = new ArrayList[m+1];
		for(int i = 1; i <= m; ++i) {
			nodes[i] = new Point(0, 0);
			adj[i] = new ArrayList<>();
		}
		for(int i = 0; i < p; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[b].add(a);
		}
	}
	
	/* 실행 */
	static void run() {
		visited[m] = true;
		setTree(m);
	}
	
	/***
	 * 자식들에게 받은 값들 중 최댓값과 두번째 최댓값을 저장
	 * @param parent : root 노드
	 * @return 본인의 최댓값 부모에게 전달
	 */
	static int setTree(int parent) {
		for(int child : adj[parent]) {	// 자식들 탐색
			if(!visited[child]) {	// 방문한적이 없다면 업데이트
				visited[child] = true;
				setTree(child);
			}
			updateMax(nodes[parent], nodes[child].x);	// 자식의 최댓값을 이용하여 본인 정보 업데이트
		}
		if(nodes[parent].x == nodes[parent].y) nodes[parent].x++; // 최댓값과 두번째 최댓값이 같으면 순서 증가
		return nodes[parent].x;	// 본인 최댓값 전달
	}
	static void updateMax(Point node, int num) {	// 들어온 값을 본인의 최댓값과 비교하여 정보 업데이트
		if(node.x < num) {	// 최댓값과 비교
			node.y = node.x;
			node.x = num;
		}
		else if(node.y < num) {	// 두번째 최댓값과 비교
			node.y = num;
		}
	}
}
