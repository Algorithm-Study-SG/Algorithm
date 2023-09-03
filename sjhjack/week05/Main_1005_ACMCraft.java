package Week05;

/**
 * 메모리 : 237380 KB
 * 시간 : 792 ms
 * 
 * # 접근 : 이전 단계가 완료되어야 다음 건물을 지을 수 있다 -> 트리 구조
 *         이전 단계가 "모두" 완료되어야 다음 건물을 지을 수 있고, 이로 인해 사이클이 존재할 수 없다 -> 위상정렬
 *         
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1005_ACMCraft {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder ans = new StringBuilder();
	
	static int T, N, K, W;
	static Node[] tree;
	
	/* 건물의 정보 저장 */
	static class Node{
		int time;											// 건설에 걸리는 시간
		int maxTime;										// 이전까지 걸린 시간
		int parentCnt;										// 완료되어야 하는 이전 단계 건물 수 (indegree)
		int cnt;											// 완료된 이전 단계 건물 수
		List<Integer> siblings = new ArrayList<>();			// 건설 후 지을 수 있는 건물들
		
		/* 이전까지 걸린 시간 갱신 */
		private void setTime(int t) {
			maxTime = t > maxTime ? t : maxTime;
		}
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		tree = new Node[N+1];
		for(int i = 1; i <= N; i++) tree[i] = new Node();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			tree[i].time = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int sibling = Integer.parseInt(st.nextToken());
			
			tree[parent].siblings.add(sibling);
			tree[sibling].parentCnt++;
		}
		
		W = Integer.parseInt(br.readLine());
	}
	
	static void BFS() {
		Queue<Integer> q = new ArrayDeque<>();
		
		/* 처음에 지어야하는 건물 모두 추가 */
		for(int i = 1; i <= N; i++) {
			if(tree[i].parentCnt == 0) {
				tree[i].maxTime = tree[i].time;
				tree[i].time = 0;
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			Node cur = tree[q.poll()];
			
			for(int i = 0; i < cur.siblings.size(); i++) {
				int nodeNum = cur.siblings.get(i);
				Node next = tree[nodeNum];
				
				next.setTime(cur.maxTime + cur.time);		// 건설 시간 갱신
				if(++next.cnt == next.parentCnt) {			// 다음 건물 건설 가능한 경우
					q.add(nodeNum);
				}
			}
		}
		
		ans.append(tree[W].maxTime + tree[W].time).append("\n");
	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			input();
			BFS();	
		}
		
		System.out.print(ans);
	}

}
