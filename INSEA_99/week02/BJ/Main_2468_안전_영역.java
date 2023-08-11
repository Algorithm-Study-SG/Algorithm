

package INSEA_99.week02.BJ;

import java.io.*;
import java.util.*;

/***
 * 어떤 지역의 높이 정보가 주어졌을 때, 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 계산하는 프로그램을 작성
 * @author 인바다
 *
 */

public class Main_2468_안전_영역{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static final int MAX = 103;	// 지역 한 변 길이 최대
	
	static final int dr[] = {0, 1, 0, -1};
	static final int dc[] = {1, 0, -1, 0};
	
	static int N, S = Integer.MAX_VALUE, D, H, maxAreaCnt;	// 단어 수, 탐색 높이 시작, 탐색 높이 끝, 탐색 높이, 최대 지역 수
	static int A[][] = new int[MAX][MAX];	// 지역 높이 정보
	static boolean visit[][];	// 지역 개수를 위한 BFS시 사용할 방문 정보 저장
	
	public static void main(String[] args) throws IOException {
		input();
		for(H = S - 1; H <= D; ++H) {	// 높이 별 지역 수 탐색
			visit = new boolean[N][N];	// BFS 방문 정보 초기화
			int areaCnt = 0;	// 지역 수
			for(int r = 0; r <N; ++r) {
				for(int c = 0; c <N; ++c) {
					if(visit[r][c] || A[r][c] <= H) continue;	// 이미 방문했거나 잠긴 지역이면 pass
					areaCnt++;
					BFS(r, c); // 새로운 지역 탐색
				}	
			}
			maxAreaCnt = maxAreaCnt > areaCnt ? maxAreaCnt : areaCnt;	// 최대 지역 수 저장
		}
		
		bw.write(maxAreaCnt+"");
		bw.flush();
		bw.close();
	}
	
	static class Pii {	// 좌표 저장
		int r, c;
		Pii(int r, int c){this.r = r; this.c = c;}
	}
	
	public static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int r = 0; r <N; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c <N; ++c) {	
				A[r][c] = Integer.parseInt(st.nextToken());	// 높이 저장
				S = S < A[r][c] ? S : A[r][c];	// 높이 탐색 시작 지점
				D = D > A[r][c] ? D : A[r][c];	// 높이 탐색 끝 지점
			}
		}
	}
	
	public static void BFS(int sr, int sc) {	// 지역 BFS로 탐색
		Deque<Pii> dq = new ArrayDeque<>();
		dq.add(new Pii(sr, sc));
		visit[sr][sc] = true;
		
		while(!dq.isEmpty()) {	// 지역을 다 탐색할 때 까지
			int qsize = dq.size();
			for(int s = 0; s < qsize; ++s) {
				int r = dq.getFirst().r;	// 현재 좌표
				int c = dq.getFirst().c;
				dq.pollFirst();
				for(int i = 0; i < 4; ++i) {
					int nr = r + dr[i];	// 앞으로 갈 좌표 후보
					int nc = c + dc[i];
					// 지역을 벗어나거나 잠겼거나 이미 방문한경우 pass
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || A[nr][nc] <= H || visit[nr][nc]) continue;
					visit[nr][nc] = true;
					dq.add(new Pii(nr, nc));
				}
			}
		}
	}
}
