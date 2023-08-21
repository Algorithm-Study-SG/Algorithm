package Week03;

/**
 * 메모리 : 142916 KB
 * 시간 : 8792 ms
 * 
 * A가 B를 신뢰한다.
 * # 접근 1 : B를 해킹하면 A도 해킹되므로 B를 부모, A를 자식 노드로 생각하여 트리 생성
 * 			 i번째 노드의 값은 i번째 컴퓨터를 해킹할 수 있는 컴퓨터의 수
 * 			 -> 3%에서 시간초과로 인해 진행 불가능하다고 판단
 * 
 * # 접근 2 : A를 부모, B를 자식 노드로 생각하여 트리 생성
 * 			 i번째 노드의 값은 i번째 컴퓨터가 해킹할 수 있는 컴퓨터의 수
 * 			 -> 접근1보다 정답 도출할 때 훨씬 편하고, 코드가 보기 쉬워짐
 * 			 -> 처음으로 3%의 늪에서 벗어나 희망을 봄
 * 			 최대한 함수 호출을 줄이고, 최적화 진행
 * 			 -> 성공
 * 
 * 재귀 횟수는 동일한 것 같은데 시간이 다르게 걸리는 이유는 알아내지 못했음
 */

// 필요한것만 import해서 시간 절약
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main_1325_효율적인해킹 {				// public을 빼면 시간 절약
	
	static boolean [] isVisited;
	static int [] treeCnt;				// 각 컴퓨터가 해킹당하면 신뢰관계에 의해 같이 해킹되는 컴퓨터의 개수가 저장된다. (해킹할 수 있는 컴퓨터 수)
	static int max = Integer.MIN_VALUE;
	static List<Integer> [] tree;
	
	static void dfs(int cur) {
		treeCnt[cur]++;					// cur컴퓨터는 68 line의 i번째 컴퓨터를 해킹할 수 있는 컴퓨터이다.
		isVisited[cur] = true;
		
		// 함수 호출로 인한 시간 초과 방지하기 위해 이 코드는 사용하지 않고 for-each 사용
//		for(int i = 0; i < tree[cur].size(); i++) {
//			int parent = tree[cur].get(i);
//			if(isVisited[parent]) continue;
//			
//			dfs(parent);
//		}
		
		for(int idx : tree[cur]) {	// cur 컴퓨터를 해킹할 수 있는 모든 컴퓨터 탐색
			if(!isVisited[idx]) {	// cur 컴퓨터를 해킹할 수 있는 컴퓨터 -> i 컴퓨터도 연쇄적으로 해킹할 수 있다.
				dfs(idx);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder ans = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[N+1];
		treeCnt = new int[N+1];
		tree = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) tree[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {		// 트리 생성
			st = new StringTokenizer(br.readLine());
			int child = Integer.parseInt(st.nextToken());	// child가 parent를 신뢰한다.
			int parent = Integer.parseInt(st.nextToken());
			
			tree[child].add(parent);		// child가 해킹될 수 있는 경우의 수
			
		}
		
		for(int i = 1; i <= N; i++) {
//			Arrays.fill(isVisited, false);	// 시간 줄이기 위해 사용 X
			isVisited = new boolean[N+1];
			dfs(i);							// i번째 노드를 해킹할 수 있는 모든 노드의 값이 1씩 추가된다.
		}
		
		for(int i = 1; i <= N; i++) {		// 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터 찾기
			max = treeCnt[i] > max ? treeCnt[i] : max;
		}
		
		for(int i = 1; i < treeCnt.length; i++) {	// 답이 여러개인 경우 오름차순으로 출력
			if(treeCnt[i] == max) ans.append(i + " ");
		}
		System.out.println(ans);

	}

}