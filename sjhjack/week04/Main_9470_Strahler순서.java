package Week04;

/**
 * 메모리 : 14100 KB
 * 시 간 : 132 ms
 * 
 * # 접근 1 : BFS
 *      BFS와 DFS 모두 가능할 것 같았고, 최근에 덜 사용한 BFS를 사용해보기로 했다.
 *      진입 차수가 0인 노드들을 모두 큐에 넣는다.
 *      큐에서 노드를 하나씩 꺼내며, 인접 노드들의 스트롤러 순서를 업데이트 해준다.
 *      
 *      but ..
 *      인접 노드의 스트롤러 순서가 다 업데이트 되기 전에, 
 *      인접 노드의 인접 노드를 계속 타고가며 업데이트가 진행되는 불필요한 과정이 있었고,
 *      중복 방문으로 인한 반례도 존재했다. (나중에 문제를 잘못 읽었다는 것을 알았기 때문에 해당 반례는 중복때문이 아닐 수도 있다.)
 *      간선을 한번만 이용하기 위해서는 위상정렬이 필요하다고 생각했다.
 *      (반례는 코드 맨 아래에 첨부)
 *      
 * # 접근 2 : 위상정렬
 *      물은 위에서 아래로 흐르는 성질이 있다.
 *      사이클이 존재하려면 아래에서 위로 흐르는 지역이 있거나 인위적으로 물을 끌어올려야 한다.
 *      문제에서 인위적인 조건은 없으므로 사이클이 존재하지 않는 유향 그래프로 생각했다.
 *      
 *      위상정렬을 진행하며 스트롤러 순위 값을 업데이트 해줬다.
 *      
 *      ++ 문제를 잘 읽자 !! (line 57)
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main_9470_Strahler순서 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder ans = new StringBuilder();
	static int K, M, P;
	static Node[] tree;
	
	static class Node{
		int strahler;												// 현재 노드로 들어온 가장 큰 스트롤러 순위 값
		int totStra;												// 가장 큰 스트롤러 순위 값의 개수에 따른 최종 순위 값
		int inDegree;												// 진입 차수
		List<Integer> next = new ArrayList<>();						// 인접 노드 저장
		
		public void set(int n) {
			if(n > strahler) {										// 최대 값 업데이트
				strahler = n;
				totStra = n;
			} else if(n == strahler) {								// 최종 값 업데이트
				totStra = strahler + 1;								// 이 부분을 문제에서 잘못 읽어서 totStra++ 로 구현했었다.
			} else return;
		}
	}
	
	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());						// testcase 번호
		M = Integer.parseInt(st.nextToken());						// node 수
		P = Integer.parseInt(st.nextToken());						// 간선 수
		tree = new Node[M+1];										// 1~M번 인덱스 사용
		for(int i = 1; i <= M; i++) tree[i] = new Node();
		
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			tree[from].next.add(to);								// 인접 노드 저장
			tree[to].inDegree++;									// 진입 차수 업데이트
		}
	}
	
	static void sol() {
		Deque<Integer> q = new ArrayDeque<>();
		for(int i = 1; i <= M; i++) {								// 진입 차수 0인 노드 큐에 추가
			if(tree[i].inDegree == 0) {
				q.add(i);
				tree[i].strahler = 1;
				tree[i].totStra = 1;
			}
		}
		
		while(!q.isEmpty()) {										// 위상정렬
			int cur = q.poll();
			int size = tree[cur].next.size();
			
			for(int i = 0; i < size; i++) {
				int next = tree[cur].next.get(i);
				if(--tree[next].inDegree == 0) q.add(next);			// 간선 제거
				tree[next].set(tree[cur].totStra);					// 스트롤러 순위 업데이트
			}
		}
		
		ans.append(K).append(" ").append(tree[M].totStra).append("\n");
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());					// testcase 수
		
		for(int test = 1; test <= T; test++) {
			input();
			sol();
		}
		
		System.out.print(ans);
	}

}

/*
1
1 10 16
1 4
1 5
2 4
2 5
2 8
2 6
2 7
3 6
3 7
4 8
7 8
8 9
8 10
9 10
6 10
5 8

정답 : 1 4
*/
