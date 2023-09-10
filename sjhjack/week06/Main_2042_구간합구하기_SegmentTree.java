package Week06;

/**
 * 메모리 : 93888 KB
 * 시간 : 596 ms
 * 
 * # 접근 : 세그먼트 트리
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2042_구간합구하기_SegmentTree {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, K;
	static SegmentTree segTree;
	static int start, depth;
	
	static class SegmentTree{
		long[] tree;
		
		public SegmentTree(int N) {
			depth = (int)Math.ceil(Math.log(N)/Math.log(2));			// 트리 깊이 구하기
			
			start = 1 << depth;											// 가장 첫번째 리프 노드의 인덱스
			tree = new long[1 << (depth + 1)];							// 최대 2^{depth+1}-1개의 노드 필요
		}
		
		/* 세그먼트 트리 초기화 */
		public void initTree() {
			for(int i = start - 1; i >= 1; i--) {						// 구간 합 저장
				tree[i] = tree[i * 2] + tree[i * 2 + 1];
			}
		}
		
		/* 값 변경 */
		public void setNode(int target, long value) {					// value의 범위 조심하자
			int index = start + target - 1;								// 바꾸려는 target번째 값의 인덱스
			long gap = value - tree[index];
			
			tree[index] = value;
			for(int i = index / 2; i >= 1; i /= 2) {					// 부모 노드(영향 받는 구간합) 업데이트
				tree[i] += gap;
			}
		}

		/**
		 * 구간 합 구하기
		 * 
		 * @param node : 현재 노드의 인덱스
		 * @param sumStart : 현재 노드의 구간합 시작점
		 * @param sumEnd : 현재 노드의 구간합 종점
		 * @param targetStart : 목표 구간합 시작점
		 * @param targetEnd : 목표 구간합 종점
		 * @return : 구간합
		 */
		public long getSum(int node, int sumStart, int sumEnd, int targetStart, int targetEnd) {
			if(targetStart > sumEnd || targetEnd < sumStart) {			// 목표구간이 구간합 범위 밖인 경우
				return 0;
			} else if(targetStart <= sumStart && sumEnd <= targetEnd) {	// 구간합에 목표구간이 포함되는 경우
				return tree[node];
			} 
			// 좌,우 자식노드 탐색 후 구간합 리턴
			return getSum(node*2, sumStart, (sumStart+sumEnd)/2, targetStart, targetEnd)
					+ getSum(node*2+1, (sumStart+sumEnd)/2+1, sumEnd, targetStart, targetEnd);
		}
	}
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		segTree = new SegmentTree(N);
		for(int i = 0; i < N; i++) {									// 리프 노드 초기화
			segTree.tree[start + i] = Long.parseLong(br.readLine());
		}
		
		segTree.initTree();
	}
	
	static void solve() throws IOException {
		StringBuilder ans = new StringBuilder();
		
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(cmd == 1) {
				long c = Long.parseLong(st.nextToken());				// 값의 범위 -> long타입 필요
				segTree.setNode(b, c);
			} else {
				int c = Integer.parseInt(st.nextToken());
				long sum = segTree.getSum(1, 1, 1<<depth, b, c);
				
				ans.append(sum).append("\n");
			}
		}
		
		System.out.print(ans);
	}

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

}
