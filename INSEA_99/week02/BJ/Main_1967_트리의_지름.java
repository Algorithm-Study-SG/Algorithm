package INSEA_99.week02.BJ;

import java.io.*;
import java.util.*;

/***
 * 트리에 존재하는 모든 경로들 중에서 가장 긴 것의 길이를 구하라
 * @author 인바다
 *
 */

public class Main_1967_트리의_지름{
	static private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static private BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out));
	static private StringTokenizer st;
	
	static final int MAX = 10003;
	
	static int N, maxL;		// 노드 수, 가장 긴 지름
	static Pii nodes[] = new Pii[MAX];	// 노드의 자식으로 부터 받은 가장 긴 길이들을 저장
	static List<Pii> adj[] = new List[MAX];	// 인접 리스트
	static boolean visit[] = new boolean[MAX];	// tree 만들 때 사용한 노드인지 표시
	
	public static void main(String[] args) throws IOException {
		input();	// 입력
		visit[1] = true;
		setTree(1);	// 트리만들기
		bw.write(maxL+"");	// 지름 출력
		bw.flush();
		bw.close();
	}
	
	static class Pii{
		int first = 0;	// 인접 리스트에서는 노드 번호, 가중치에선 가장 긴 반지름
		int second = 0;	// 인접 리스트에서는 가중치, 가중치에선 두번째로 긴 반지름
		Pii() {}
		Pii(int f, int s) {
			this.first = f;
			this.second = s;
		}
	}
	
	public static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 1; i <= N; ++i) {	// 초기화
			adj[i] = new ArrayList<>();
			nodes[i] = new Pii();
		}

		for(int i = 1; i < N; ++i) {	// 인접 리스트 생성
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());	
			int n2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[n1].add(new Pii(n2, w));
			adj[n2].add(new Pii(n1, w));
		}
	}
	
	public static int setTree(int p) {
		for(Pii c : adj[p]) {	// p의 자식 후보
			if(visit[c.first]) continue;
			visit[c.first] = true;	// 자식 생성
			setMax(nodes[p], c.second + setTree(c.first));	// 반지름 업데이트
		}
		maxL = maxL > nodes[p].first + nodes[p].second ?  maxL : nodes[p].first + nodes[p].second;	// 가장 긴 지름인지 확인
		return nodes[p].first;	// 본인의 가장 긴 반지름 반환
	}
	
	static void setMax(Pii node, int w) {	// 반지름 업데이트
		if(w > node.first) {
			node.second = node.first;
			node.first = w;
		}
		else if(w > node.second) node.second = w;
	}
}
