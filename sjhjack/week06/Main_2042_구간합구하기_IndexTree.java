package Week06;

/**
 * 메모리 : 94752 KB
 * 시간 : 564 ms
 * 
 * # 접근 : 인덱스 트리
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2042_구간합구하기_IndexTree {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M, K;
	static int start;
	static long[] indexTree;
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int depth = (int)Math.ceil(Math.log(N)/Math.log(2));		// 트리의 깊이
		indexTree= new long[1 << (depth+1)];						// 최대 2^{depth+1}-1개의 노드 필요
		start = 1 << depth;											// 첫번째 리프 노드의 인덱스
		
		for(int i = 0; i < N; i++) {								// 리프 노드 초기화
			indexTree[start + i] = Long.parseLong(br.readLine());
		}
		for(int i = start-1; i >= 1; i--) {							// 구간합 업데이트
			indexTree[i] = indexTree[i*2] + indexTree[i*2 + 1];
		}
	}
	
	/* 값 변경 */
	static void setTree(int target, long value) {
		int index = start + target - 1;								// 바꾸려는 target번째 값의 인덱스
		long gap = value - indexTree[index];						// 업데이트 해야될 값의 차이
		
		indexTree[index] = value;
		for(int i = index/2; i >= 1; i /= 2) {						// 영향 받는 구간합 업데이트(부모 노드)
			indexTree[i] += gap;									// 값의 차이만큼 변경
		}
	}
	
	/* 목표 구간합 구하기 */
	static long getSum(int targetStart, int targetEnd) {
		int left = start + targetStart - 1;							// 목표 구간의 시작 인덱스
		int right = start + targetEnd - 1;							// 목표 구간의 마지막 인덱스
		long sum = 0L;
		
		while(left <= right) {
			if(left % 2 == 1) sum += indexTree[left];
			if(right % 2 == 0) sum += indexTree[right];
			
			left = (left + 1) / 2;
			right = (right - 1) / 2;
		}
		
		return sum;
	}
	
	static void solve() throws IOException {
		StringBuilder ans = new StringBuilder();
		
		for(int i = 0; i < (M + K); i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(cmd == 1) {
				long c = Long.parseLong(st.nextToken());
				setTree(b, c);
			} else {
				int c = Integer.parseInt(st.nextToken());
				ans.append(getSum(b, c)).append("\n");
			}
		}
		
		System.out.print(ans);
	}
	
	public static void main(String[] args) throws IOException {
		input();
		solve();
	}

}
