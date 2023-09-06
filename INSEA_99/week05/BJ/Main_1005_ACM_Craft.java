package INSEA_99.week05.BJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 메모리 : 244348 KB
 * 시간 : 852 ms
 * 
 * idea1 : 위상정렬사용하고, 각 건설 시간과 실제 건설 완료 시간을 저장하여 값을 구한다.
 * Solved!
 * 
 */

public class Main_1005_ACM_Craft {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int target;		// 건설 완료 시간을 구하고자 하는 건물
	static int[] indegree, constructionTime, constructionCompletionTime;		// 진입차수, 각 건설 시간, 건설 완료 시간 저장 배열
	static List<Integer>[] adj;		// 인접리스트
	static Deque<Integer> dq;		// 위상정렬에 사용할 queue

	public static void main(String[] args) throws IOException {
		int testcases = Integer.parseInt(br.readLine());
		while (testcases-- != 0) {		// 테스트 케이스
			input();	 // 입력
			run();		 // 실행
			output(); 	 // 출력
		}
		bw.flush();
		bw.close();	
		br.close();
		return;
	}

	/* 입력 */
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); 		// 건물 수
		int k = Integer.parseInt(st.nextToken());		// 건설 규칙 수
		
		/* 변수 초기화 */
		indegree = new int[n + 1];				// 진입 차수
		constructionTime = new int[n + 1];			// 각 건설 시간
		constructionCompletionTime = new int[n + 1];		// 건설 완료 시간
		adj = new ArrayList[n + 1];			// 인접리스트
		dq = new ArrayDeque<>();			// 위상정렬에 사용할 queue
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; ++i) {			
			constructionTime[i] = Integer.parseInt(st.nextToken());		// 건설 완료 시간 업데이트
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < k; ++i) {		// 규칙 입력 받아 인접리스트, 진입차수 업데이트
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adj[x].add(y);
			indegree[y]++;
		}
		target = Integer.parseInt(br.readLine());		// 건설 완료 시간을 구해야하는 건물
		for (int i = 1; i <= n; ++i) {				// 진입차수가 없는 것을 시작점으로 삼기
			if (indegree[i] == 0) {
				constructionCompletionTime[i] = constructionTime[i];
				dq.addLast(i);
			}
		}
	}

	/* 출력 */
	static void output() throws IOException {
		bw.write(constructionCompletionTime[target] + "\n");

	}

	/* 실행 */
	static void run() {
		while (!dq.isEmpty()) {		// 모든 노드 방문할 때까지
			int now = dq.pollFirst();
			for (int next : adj[now]) {		// 현재 노드가 가리키는 노드의 차수 빼주고, 0이되면 queue에 넣어주기
				if (--indegree[next] == 0) {
					dq.addLast(next);
				}
				if (constructionCompletionTime[next] < constructionCompletionTime[now] + constructionTime[next]) {		// 저장된 건설 완료 시간보다 현재 노드를 거친 것이 더 오래걸리는 경우 업데이트
					constructionCompletionTime[next] = constructionCompletionTime[now] + constructionTime[next];
				}
			}
		}
	}
}
