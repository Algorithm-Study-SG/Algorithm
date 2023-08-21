package INSEA_99.week03.BJ;

import java.io.*;
import java.util.*;

// 304476 KB
// 7128 ms
// b->a 로 돌리니 시간초과 발생...
/*

static void run() {
	for(int i = 1; i <= n; ++i) {	// 노드들의 해킹 가능 컴퓨터 수 dfs로 탐색하며 업데이트
		visited = new boolean[n+1];
		visited[i] = true;
		int children = setTree(i);
		if(maxHacking <= children) {	// 현재가 가장 많이 해킹 가능한 경우
			if(maxHacking < children) {	// 최대 해킹 수 업데이트 되는 경우
				maxHacking = children;	// 최대 해킹 수 업데이트
				maxHackingIdx.clear();	// 최대 해킹 가능한 컴퓨터 인덱스 초기화
			}
			maxHackingIdx.add(i);	// 현재 컴퓨터 추가
		} 
	}
} 
 */


/***
 * 이 회사의 컴퓨터의 신뢰하는 관계가 주어졌을 때, 
 * 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력하는 프로그램을 작성
 * @author 인바다
 *
 */

public class Main_1325_효율적인_해킹{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static final int MAX = 10003;
	
	static int n, m;
	static boolean visited[];
	static List<Integer>[] adj;
	static int[] HackingComputers;
	static int maxHackingCnt;

	
	public static void main(String[] args) throws IOException {
		input();	// 입력
		run();	// 가장 많은 해킹 가능 컴퓨터 탐색
		output();	// 출력
		bw.flush();
		bw.close();
		return;
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		HackingComputers = new int[n+1];
		adj = new ArrayList[n+1];
		for(int i = 1; i<=n;++i) adj[i] = new ArrayList<>();
		for(int i = 0; i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
		}
	}
	
	static void output() throws IOException{
		for(int i = 1; i<=n;++i) {
			if(HackingComputers[i] == maxHackingCnt) {
				bw.write(i + " ");
			}
		}
	}
	
	static void run() {
		for (int i = 1; i <= n; i++) {
			visited = new boolean[n + 1];
            bfs(i);
		}
		for (int i = 1; i <= n; i++) {
			maxHackingCnt = maxHackingCnt > HackingComputers[i]? maxHackingCnt : HackingComputers[i];
		}
	}
	
	static void bfs(int node) {
		Deque<Integer> q = new ArrayDeque<>();
        q.add(node);
        visited[node] = true;
        while (!q.isEmpty()) {
            int now = q.poll();
            for(int next : adj[now]){
                if(!visited[next]){
                    q.add(next);
                    HackingComputers[next]++;
                    visited[next] = true;
                }
            }

        }
	}
}
