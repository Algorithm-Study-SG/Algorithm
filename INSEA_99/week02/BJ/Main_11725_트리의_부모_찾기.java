package INSEA_99.week02.BJ;

import java.io.*;
import java.util.*;

/***
 * 루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.
 * @author 인바다
 *
 */

public class Main_11725_트리의_부모_찾기{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static final int MAX = 100003;
	
	static int N;	// 노드 수
	static int parent[] = new int[MAX];		// i번째 노드의 부모 노드
	static boolean visit[] = new boolean[MAX];	// tree 만들 때 이미 트리 구성원인지 확인
	static ArrayList<Integer> adj[] = new ArrayList[MAX];	// 인접 리스트
	
	public static void main(String[] args) throws IOException {
		input();	// 입력
		getTree(1);		// 트리 만들기
		for(int i = 2; i <= N; ++i)
			bw.write(parent[i]+"\n");	// 부모 노드 출력
		bw.flush();
		bw.close();
	}
	
	public static void input() throws IOException {
		N= Integer.parseInt(br.readLine());
		for(int i = 1; i <= N; ++i) 
			adj[i] = new ArrayList<>();	// 인접 리스트 초기화
		
		for(int i = 1; i < N; ++i) {	// 인접 리스트 입력 받기
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
	}
	public static void getTree(int p) {
		for(int c : adj[p]) {	// p의 child 탐색
			if(visit[c]) continue;	// 이미 방문한 곳이면 continue
			visit[c] = true;	// 방문 여부와 부모 정보 업데이트
			parent[c] = p;
			getTree(c);
		}
	}
}
