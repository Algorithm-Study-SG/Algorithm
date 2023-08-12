package Week2;

import java.io.*;
import java.util.*;

public class Main_3190_뱀 {
	
	static int N, K, L;
	static int [] head = {1, 1}, tail = {1, 1};
	static int [] dr = {0,1,0,-1};	// 오른쪽, 아래, 왼쪽, 위
	static int [] dc = {1,0,-1,0};
	static boolean [][] isSnake;	// 뱀이 위치한 곳 : true
	static boolean [][] map;		// 사과 : true
	static int [] lTime;			// 방향 전환 시간
	static char [] lDirection;		// 방향 전환 방향
	static Queue<Integer> queue = new LinkedList<Integer>();	// 방향 전환 순서 저장
	static List<Pos> turnPos = new LinkedList<Pos>();			// 방향 전환 좌표 저장
	
	public static class Pos {		// 좌표
		int row;
		int col;
		
		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
	}
	
	public static void move() {
		int t = 0, headDelta = 0, tailDelta = 0, index = 0;
		isSnake[1][1] = true;
		
		while(true) {
			t++;						// 시간 증가
			head[0] += dr[headDelta];	// 머리 이동
			head[1] += dc[headDelta];
			// 벽 or 몸에 닿으면 끝
			if(head[0]>N || head[0]<1 || head[1]>N || head[1]<1 || isSnake[head[0]][head[1]]) {
				System.out.println(t);
				return;
			}
			
			isSnake[head[0]][head[1]] = true;		// 머리 위치 true
			
			if(map[head[0]][head[1]]) {				// 사과 먹은 경우 : 꼬리 위치 변경 x
				map[head[0]][head[1]] = false;		// 사과 먹기
			}
			else {									// 사과 못 먹은 경우 : 꼬리 한 칸 이동
				isSnake[tail[0]][tail[1]] = false;	// 기존 꼬리 자리 false
				// 머리가 방향 전환했던 좌표에 꼬리가 도착한 경우
				if(!turnPos.isEmpty() && tail[0] == turnPos.get(0).row && tail[1] == turnPos.get(0).col) {
					tailDelta = queue.poll();		// 꼬리 방향 전환
					turnPos.remove(0);				// 좌표 제거
				}
				
				tail[0] += dr[tailDelta];			// 꼬리 한 칸 이동
				tail[1] += dc[tailDelta];
			}			
			
			// 이동 끝나면 방향 바꾸기
			if(index < lTime.length && t == lTime[index]) {					// 1 ≤ L ≤ 100 이므로 lTime의 길이는 무조건 1 이상
				if(lDirection[index] == 'D') headDelta = (headDelta+1)%4;	// 오른쪽 전환
				else headDelta = (4+headDelta-1)%4;							// 왼쪽 전환
				
				index++;
				queue.add(headDelta);										// 방향 전환 순서 저장 (머리가 간 길을 따라가기 위해서)
				turnPos.add(new Pos(head[0], head[1]));						// 방향 전환한 좌표 저장
			}
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		isSnake = new boolean[N+1][N+1];
		map = new boolean[N+1][N+1];
		
		for(int i = 0; i < K; i++) {	// 사과 좌표 입력
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			
			map[row][col] = true;
		}
		
		L = Integer.parseInt(br.readLine());
		lTime = new int[L];
		lDirection = new char[L];
		
		for(int i = 0; i < L; i++) {	// 방향 전환 입력
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			String s = st.nextToken();
			
			lTime[i] = t;
			lDirection[i] = s.charAt(0);
		}
		
		move();
		
	}

}