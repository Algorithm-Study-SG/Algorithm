package Week2;

import java.io.*;
import java.util.*;

public class Main_11725_트리의부모찾기 {

	static int N;
	static Node [] tree;			// 트리
	static int [] parent;			// 각 index에 해당하는 노드의 부모 노드 저장
	static boolean [] isVisited;
	static Queue<Integer> queue = new ArrayDeque<Integer>(); 
	
	public static class Node {
		List<Integer> others = new ArrayList<>();				// 자신과 연결된 모든 노드의 번호 저장
	}								// isVisited로 방문 여부 체크하기 때문에 연결된 모든 노드를 저장해도 중복 방문은 일어나지 않는다

	public static void pushSiblings(int index) {
		int sibling;				
		
		for(int i = 0; i < tree[index].others.size(); i++) {	// 현재 노드의 모든 자식 노드 queue에 넣기
			sibling = tree[index].others.get(i);				// index 노드와 연결된 노드 번호를 받는다
			if(isVisited[sibling] == true) continue;			// 자식 -> 부모 방문 하는 경우 제외
			
			queue.offer(sibling);								// 자식 노드 추가
			parent[sibling] = index;							// 부모 노드 저장
			isVisited[sibling] = true;							// 방문 체크
		}
	}
	// 1번 노드가 root 노드로 고정되었으므로, 1번 노드부터 BFS 탐색이 가능하다.
	public static void BFS() {
		
		pushSiblings(1);					// 루트 노드의 자식 노드 queue에 넣기
		
		while(!queue.isEmpty()) {
			int current = queue.poll();		// 탐색 대상
			
			pushSiblings(current);			// 대상의 모든 자식 노드 queue에 넣기
		}
		
	}
	
	public static void print() {
		for(int i = 2; i <= N; i++) {
			System.out.println(parent[i]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		tree = new Node[N+1];
		parent = new int[N+1];
		isVisited = new boolean[N+1];
		
		for(int i = 0; i < N-1; i++) {		// tree 구현
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			if(tree[node1] == null) tree[node1] = new Node();
			if(tree[node2] == null) tree[node2] = new Node();
			// 간선에 연결된 두 노드 모두 서로의 부모가 될 수 있기 때문에 각각 연결된 노드에 추가해 준다.
			tree[node1].others.add(node2);
			tree[node2].others.add(node1);
		}
		
		BFS();
		print();
	}
	
}