package study.boj;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_16932_모양_만들기 {

	/*
	 * 메모리 : 313500 KB
	 * 시간 : 1304 ms
	 * 
	 * 처음에는 1과 연결된 모든 위치를 선택하고 bfs를 돌렸지만 시간초과가 발생해서 visited배열의 초기화 방법을 수정
	 * 
	 * 그래도 시간초과가 발생해서 0인 한 지점을 선택했을 때 사방탐색에서 두개 이상의 1이 존재한 경우에만 탐색을 실행
	 * 위의 방법으로는 연결이 하나도 안된 입력이 주어지면 탐색이 불가능해서 실패, 위의 방법을 보안했지만 시간초과가 발생
	 * 
	 * 처음에 bfs로 미리 다 카운팅을 한 후에 저장을 한 후에 한 지점을 선택해서 사방탐색으로 해당 숫자가 가르키는 저장된 카운팅값을 더한다
	 * 위의 방법으로 구현을 헀다가 하나의 그룹이 사방탐색시 중복이 발생하면 이상한 값이 나오기때문에 set을 이용해서 중복을 제거 후 더해줌
	 * 
	 */

	static int[][] input;
	static boolean[][] visited;
	static int N;
	static int M;
	static int ans;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static List<Integer> groupList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N][M];
		visited = new boolean[N][M];
		groupList = new ArrayList<>();
		ans = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		counting();

		search();

		System.out.println(ans);

		br.close();
	}

	/*
	 * 0이 아닌 지점에서 사방탐색을 돌려서 0인 지점을 찾는다
	 * 그 장소에서 다시 사방탐색을 돌려서 연결된 그룹이 있는지를 확인
	 * 그룹이 존재한다면 set에 저장 후 해당 그룹의 크기만큼 증가
	 */
	static void search() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j]) { // 0이 아닌 지점을 찾는다
					for (int d = 0; d < dr.length; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];

						// 0인 지점을찾는다
						if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
							if (!visited[nr][nc]) {
								int sum = 1; // 해당 지점을 1로 변경할 예정이기 때문에 초기값을 1로준다
								Set<Integer> set = new HashSet<>(); // 중복 제거를 위해서 사용
								for (int nd = 0; nd < dr.length; nd++) { // 0인 지점에서 사방탐색을 실행
									int ndr = nr + dr[nd];
									int ndc = nc + dc[nd];
									if (ndr >= 0 && ndr < N && ndc >= 0 && ndc < M) {
										if (input[ndr][ndc] != 0) { // 사방탐색에서 0이 아닌 지점을 찾으면 set에 저장
											set.add(input[ndr][ndc]);
										}
									}
								}
								for (int loc : set) { // set에 저장된 groutList의 위치에서 크기를 가져온다
									sum += groupList.get(loc - 2); // 숫자를 저장할 때 2부터 부여했기 때문에 2를 빼준다
								}
								ans = Math.max(ans, sum); // 최대값 저장
							}
						}
					}
				}
			}
		}
	}

	// 처음에 모든 배열을 돌면서 그룹별로 나눠서 카운팅을 실행
	static void counting() {
		Queue<Point> queue = new ArrayDeque<>();
		int groupNumber = 2; // 숫자가 0과 1이 입력으로 주어지기 때문에 2부터 시작을했다
		for (int i = 0; i < N; i++) { // 모든 배열 탐색
			for (int j = 0; j < M; j++) {
				int groupCount = 0; // 해당 그룹에 포함된 1의 개수
				boolean find = false; // 그룹을 찾았는지 여부를 확인
				if (!visited[i][j] && input[i][j] != 0) { // 방문한적이 없고 0이 아니면
					queue.offer(new Point(i, j)); // 해당지점 큐에 추가
					groupCount++; // 그룹에 포함된 1의 개수 증가
					input[i][j] = groupNumber; // 리스트에 저장될 그룹의 번호 표시
					visited[i][j] = true;
					find = true;
					while (!queue.isEmpty()) { // bfs 탐색으로 연결된 모든 위치 카운팅
						Point point = queue.poll();
						for (int d = 0; d < dr.length; d++) {
							int nr = point.x + dr[d];
							int nc = point.y + dc[d];
							if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
								if (!visited[nr][nc] && input[nr][nc] != 0) {
									groupCount++;
									input[nr][nc] = groupNumber; // 리스트에 저장될 그룹의 번호 표시
									visited[nr][nc] = true; // 방문처리
									queue.add(new Point(nr, nc));
								}
							}
						}
					}
				}
				if (find) { // 그룹이 생겼으면 값을 증가시키고 저장한다
					groupNumber++;
					groupList.add(groupCount);
				}
			}
		}
	}

}
