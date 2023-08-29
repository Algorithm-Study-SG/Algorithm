package INSEA_99.week04.BJ;

import java.io.*;
import java.util.*;
import java.awt.Point;

/* 메모리 : 224848KB
 * 시간 : 956ms
 * 
 * idea1 : 일단 모양을 찾고, 0을 바꾸며 이어지는 그룹의 크기를 합하여 최대 크기를 구한다.
 */

public class Main_16932_모양_만들기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static final int[] dr = {1, 0, -1, 0};	// 상 우 하 좌 이동
	static final int[] dc = {0, 1, 0, -1};
	
	static int n, m, groupCnt, maxCnt;	// 행 크기, 열 크기, group 수, 그룹 최대 크기
	static int[][] arr, visited;	// 영역 정보 저장, 영역 별 그룹 정보 저장
	static Map<Integer, Integer> sizeOfgroups = new HashMap<>(); // 그룹 크기 저장
	
	/* 메인 */
	public static void main(String[] args) throws IOException {
		input();	// 입력 받기
		run();	// 실행
		output(); // 출력
		return;
	}
	
	/* 입력  */
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new int[n][m];
		for(int r = 0; r < n; ++r) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < m; ++c) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
	}
	
	/* 출력  */
	static void output() throws IOException{
		bw.write(maxCnt + "");	// 출력			
		bw.flush();
		bw.close();
	}
	/* 실행 */
	static void run() {
		for(int r = 0; r < n; ++r) {	// 영역 구하기
			for(int c = 0; c < m; ++c) {
				if(arr[r][c] == 0 || visited[r][c] != 0) continue;	// 이미 방문했거나 영역이 아니면 continue
				groupCnt++;	// 새로운 그룹
				int groupSize = bfs(r, c);	// 새로운 그룹 크키
				sizeOfgroups.put(groupCnt, groupSize);	// 그룹 크기 정보에 저장
			}
		}
		
		for(int r = 0; r < n; ++r) {	// 0을 1로 교체하여 크기 구하기
			for(int c = 0; c < m; ++c) {
				if(arr[r][c] == 0) {	// 0을 1로 교체하기 위해
					int tempCnt = convert(r, c);	// 새롭게 구한 영역 크기
					maxCnt = maxCnt > tempCnt ? maxCnt : tempCnt;	// 최대 영역 크기 업데이트
				}
			}
		}
	}
	
	static int bfs(int r, int c) {
		Deque<Point> dq = new ArrayDeque<>();	// bfs를 위한 deque
		dq.add(new Point(r, c));	// 시작 지점 추가
		visited[r][c] = groupCnt;	// 시작 지점 group 할당
		
		int groupSize = 0;	// 그룹 크기 저장
		while(!dq.isEmpty()) {
			groupSize++;
			r = dq.getFirst().x;	// 현재 위치
			c = dq.getFirst().y;
			dq.removeFirst();
			for(int d = 0; d < 4; ++d) {	// 새로운 위치 이동 가능 확인
				int nr = r + dr[d];	// 새로운 위치
				int nc = c + dc[d];
				if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue; // 맵을 벗어난 경우
				if(visited[nr][nc] != 0 || arr[nr][nc] == 0) continue;	// 이미 방문한 영역이거나 영역이 아닌 경우
				visited[nr][nc] = groupCnt; 	// 영역에 그룹 할당
				dq.addLast(new Point(nr, nc));	// deque에 추가
			}
		}
		return groupSize;	// 그룹 크기 반환
	}
	
	static int convert(int r, int c) {
		int cnt = 1;	// 새로운 영역 크기
		Set<Integer> addedGroup = new HashSet<>();	// 변환으로 인해 추가된 그룹 저장
		
		for(int d = 0; d < 4; ++d) {
			int nr = r + dr[d];	// 주변 그룹 확인
			int nc = c + dc[d];
			if(nr < 0 || nr >= n || nc < 0 || nc >= m || arr[nr][nc] == 0) continue;	// 맵을 벗어났거나 그룹이 아닌 경우
			
			int group = visited[nr][nc];	// 현재 그룹
			if(addedGroup.contains(group)) continue;	// 이미 방문한 그룹인 경우
			addedGroup.add(group);	// 방문한 그룹에 추가
			cnt += sizeOfgroups.get(group);	// 방문한 그룹 크기 더하기
		}
		return cnt;	// 새로운 그룹 크기 반환
	}
}
