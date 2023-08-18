package Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1967_트리의지름 {

	static int max = 0;
	static Node [] tree;

	static int dfs(int cur) {
		if(tree[cur] == null) {		// 리프 노드인 경우, 정보가 들어오지 않았기 때문에 노드가 생성되어 있지 않다 (null)
			return 0;				// 자식이 없으므로 가중치 0 리턴
		}
		
		int curMaxFirst = 0, curMaxSecond = 0, tmp;
		
		for(int i = 0; i < tree[cur].index; i++) {	// 첫번째, 두번째로 긴 간선을 찾는다
			// tmp : 현재 노드의 각 자식 노드들의 리프 노드까지 간선 길이 + 각 자식 노드까지의 간선 가중치
			//       즉, 현재 노드에서 각 자식을 선택했을때 리프 노드까지의 길이
			tmp = dfs(tree[cur].siblings.get(i)) + tree[cur].weights.get(i);
			if(tmp > curMaxFirst) {
				curMaxSecond = curMaxFirst > curMaxSecond ? curMaxFirst : curMaxSecond;
				curMaxFirst = tmp;
			}
			else {
				if(tmp > curMaxSecond) curMaxSecond = tmp;
			}
		}
		
		max = (curMaxFirst + curMaxSecond) > max ? (curMaxFirst + curMaxSecond) : max;
		
		return curMaxFirst > curMaxSecond ? curMaxFirst : curMaxSecond;
	}
	
	public static class Node {
		int index = 0;
		List<Integer> siblings = new ArrayList<Integer>();	// 자식 노드들의 번호 저장
		List<Integer> weights = new ArrayList<Integer>();	// 자식 노드로 가는 간선의 가중치 저장
		
		public void add(int i, int w) {
			siblings.add(i);
			weights.add(w);
			index++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int cur, sibling, weight;		// 현재 노드 번호, 자식 노드 번호, 간선 가중치
		
		int N = Integer.parseInt(br.readLine());
		tree = new Node[N+1];
		
		for(int i = 1; i <= N-1; i++) {	// tree 정보 입력
			st = new StringTokenizer(br.readLine());
			
			cur = Integer.parseInt(st.nextToken());
			sibling = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			if(tree[cur] == null) tree[cur] = new Node();
			tree[cur].add(sibling, weight);
			
		}
		
		dfs(1);
		
		System.out.println(max);
		
	}

}