package Week04;

/**
 * 메모리 : 104592 KB
 * 시간 : 792 ms
 * 
 * # 접근 : 기본적으로 전체가 0이거나 1인 경우를 봐야하기 때문에 O(N²)은 피할 수 없다고 생각했다.
 *         숫자를 하나 바꿔서 모양이 커지려면, 1이 모여있는 그룹(모양)에 인접해있는 0을 바꿔야한다.
 *         
 *         모양이 여러개 있을 수 있으므로 그룹으로 지정해서 관리한다. (변수 이름을 그룹으로 만들었으므로 편의상 모양=그룹으로 칭한다)
 *         같은 그룹에 속한 칸들을 어떻게 묶을지 고민하다가 수업때 우석이형이 BFS를 2차원배열로 진행했던 것에서 아이디어를 얻었다.
 *         BFS+사방탐색으로 각 칸마다 속한 그룹의 번호를 저장한다.
 *         이 과정에서 그룹의 크기도 저장해둔다.
 *         그룹의 개수를 미리 알 수 없기 때문에 그룹의 크기는 ArrayList로 관리했다.
 *         
 *         모든 0에 대해서 그룹과 인접해있는 경우, 0을 1로 바꿨을 때의 크기를 계산해서 정답을 도출했다.
 *         해당 0에 대해 사방 탐색으로 인접해있는지 확인하기 때문에, 동일한 그룹의 크기를 중복해서 더하지 않도록 set으로 관리했다. (line 143)
 * 
 * ~ 놓쳤던 부분
 *  	   1. 이미 같은 그룹에 포함되었지만 중복해서 포함시켜서 그룹의 크기가 더 크게 나왔다.
 *    	     -> 같은 그룹인 경우 예외 처리 (line 95, 113)
 *  	   2. BFS + 사방탐색을 사용할때, 큐에 중복해서 입력되는 경우가 있다. (반례1 : 코드 맨 밑에 첨부)
 *  	     -> 큐에서 뽑은 직후에 예외 처리 (line 104)
 *   	   3. 2차원에서 여러칸이 같은 그룹으로 묶이는 문제라면, 같은 그룹끼리 맞닿아있거나 또는 둘러쌓인 것을 조심하자.
 */

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

public class Main_16932_모양만들기 {
	
	static int N, M;
	static int zeroCnt, oneCnt;										// 0의 개수, 1의 개수 저장
	static int[][] arr;												// 초기 배열 입력값 저장
	static int[][] group;											// 각 칸이 포함된 그룹 번호 저장 (0이면 그룹에 포함되어 있지 않음)
	static List<Integer> groupSize = new ArrayList<>();				// 각 그룹의 크기 저장 (groupSize.get(3) : 3번 그룹의 크기)
	static int[] dr = {-1, 1, 0, 0};								// 상 하 좌 우
	static int[] dc = { 0, 0,-1, 1};
	
	/**
	 * 좌표 담는 클래스
	 */
	static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	/**
	 * 입력 받기 및 배열 할당
	 */
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		group = new int[N][M];
		groupSize.add(0);											// 1번 그룹부터 사용하기 위해 0번 더미 그룹 추가
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < 2*M; j += 2) {
				arr[i][j/2] = s.charAt(j) - '0';
			}
		}
	}
	
	/**
	 * 그룹 나누기
	 */
	static void findGroup() {
		int groupNum = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				
				if(arr[i][j] == 1) {
					oneCnt++;										// 1의 개수
					
					if(group[i][j] != 0) continue;					// 이미 그룹에 속한 경우 예외 처리
					// 같은 그룹에 속한 칸 찾기 : BFS
					int cnt = 0;									// 현재 그룹의 크기
					groupNum++;										// 몇 번째 그룹인지
					Queue<Point> q = new ArrayDeque<>();
					q.add(new Point(i, j));
					
					while(!q.isEmpty()) {
						Point cur = q.poll();
						if(group[cur.row][cur.col]!=0) continue;	// 큐에 중복으로 들어가는 경우가 있기 때문에 예외처리 필요
						
						cnt++;										// 그룹 크기 구하기
						group[cur.row][cur.col] = groupNum;			// 칸 마다 그룹 번호 저장
						
						for(int d = 0; d < 4; d++) {
							int nr = cur.row + dr[d];
							int nc = cur.col + dc[d];
							// 범위 밖, 값이 0, 이미 그룹에 속한 경우 continue
							if(nr<0 || nr>=N || nc<0 || nc>=M || arr[nr][nc]==0 || group[nr][nc]!=0) continue;
							q.add(new Point(nr, nc));
						}
					}
					groupSize.add(cnt);
				} else {
					zeroCnt++;										// 0의 개수
				}
				
			}
		}
	}
	
	static int change() {
		if(zeroCnt == N*M) return 1;
		if(oneCnt == N*M) return N*M-1;
		
		int max = 0;
		Set<Integer> set = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				// 그룹에 인접한 0을 1로 바꿔보기
				if(arr[i][j] == 0) {
					int sum = 1;									// 현재의 0을 1로 바꾸는 것 포함
					set.clear();									// 사방탐색하면서 같은 그룹을 중복 탐색하지 않기 위해
					for(int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						// 범위 밖, 값이 0, 이미 크기를 더한 그룹인 경우 continue
						if(nr<0 || nr>=N || nc<0 || nc>=M || arr[nr][nc]==0 || set.contains(group[nr][nc])) continue;
						sum += groupSize.get(group[nr][nc]);
						set.add(group[nr][nc]);
					}
					max = Math.max(sum, max);
				}
			}
		}
		
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		input();
		findGroup();
		System.out.print(change());
	}

}


/*

# 반례 1

5 5
1 0 0 1 1
1 1 1 0 0
0 0 1 1 1
1 1 1 1 1
0 0 0 0 0
정답 : 15

*/

