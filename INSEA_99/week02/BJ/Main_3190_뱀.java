package INSEA_99.week02.BJ;

import java.io.*;
import java.util.*;

/***
 * 'Dummy' 라는 도스게임이 있다. 이 게임에는 뱀이 나와서 기어다니는데, 사과를 먹으면 뱀 길이가 늘어난다. 
 * 뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다
 * 
 * 사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.
 * 
 * @author 인바다
 *
 */

public class Main_3190_뱀{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static final int MAX = 103;	// 지역 한 변 길이 최대
	
	static final int dr[] = {0, 1, 0, -1};	// 우 하 좌 상
	static final int dc[] = {1, 0, -1, 0};
	
	static int N, K, L, D, overSec;	// 보드 크기, 사과 개수, 방향 변환 횟수, 현재 방향, 게임 끝나는 시간
	static Coordi head = new Coordi();	// 머리 좌표 저장
	static Coordi tail = new Coordi();	// 꼬리 좌표 저장
	static boolean done;	// 게임이 끝났는지 확인
	static boolean apple[][] = new boolean[MAX][MAX];	// 사과 정보
	static int snake[][] = new int[MAX][MAX];	// 뱀 방향 정보
	static List<Direction> dirInfo = new ArrayList<>();	// 방향 변환 정보
	
	public static void main(String[] args) throws IOException {
		input();	// 입력
		run();		// 프로그램 실행
		bw.write(overSec+"");
		bw.flush();
		bw.close();
	}
	
	static class Coordi {	// 좌표 저장
		int r, c;
		public void setCoordi(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Direction {	// 방향 변환 정보
		int sec; 
		int rotationDir;
		Direction(int sec, int rotationDir){
			this.sec = sec; this.rotationDir = rotationDir;
		}
	}
	
	public static void input() throws IOException {	// 입력받기
		head.setCoordi(1, 1);	// 머리, 꼬리 좌표 초기화
		tail.setCoordi(1, 1);
		
		N = Integer.parseInt(br.readLine());

		for(int r = 1; r <= N; ++r) {	// 뱀 방향 정보 초기화
			Arrays.fill(snake[r], -1);
		}
		snake[1][1] = 0;	
		
		// 사과 정보 저장
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			apple[r][c] = true;
		}
		
		// 방향 변환 정보 저장
		L = Integer.parseInt(br.readLine());
		for(int i = 0; i < L; ++i) {
			st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			char rotationDir = st.nextToken().charAt(0);
			// dr, dc가 우 하 좌 상이므로 90도는 +1, -90도는 -1로 동작할 수 있도록 dir 정보 저장
			dirInfo.add(new Direction(sec, rotationDir == 'D' ? 1 : -1));	
		}
		dirInfo.add(new Direction(-1, 0));	// 마지막 방향을 유지하며 게임이 끝날때까지 전진
	}
	
	public static void run() {	// 프로그램 실행
		for(int i = 0; i <= L; ++i) {
			move(dirInfo.get(i));	// 방향 전환이 일어날때까지 전진
			if(done) return;
		}
	}
	
	public static void move(Direction nowDirInfo) {	// 방향 전환이 일어날때까지 전진
		int remainingSec = nowDirInfo.sec - overSec; // 방향 유지 잔여 시간
		int tailDir;	// 꼬리 방향(머리와 꼬리가 동일하면 머리의 방향을 사용, 아니면 snake에 저장된 방향 사용)
		while(remainingSec-- != 0) {
			overSec ++;	// 현재 시간
			
			// 꼬리 방향(머리와 꼬리가 동일하면 머리의 방향을 사용, 아니면 snake에 저장된 방향 사용)
			if(head.r == tail.r && head.c == tail.c) { tailDir = D; }	
			else {tailDir = snake[tail.r][tail.c];}
			
			// head 움직이기
			
			head.setCoordi(head.r + dr[D],  head.c + dc[D]);
			int hr = head.r;
			int hc = head.c;
			if(hr < 1 || hr > N || hc < 1 || hc > N || snake[hr][hc] != -1) {	// 게임 종료
				done = true;
				return;
			}
			snake[hr][hc] = D;	// 머리 방향 저장
			
			if(!apple[hr][hc]) {	// 사과가 없다면 꼬리 움직이기
				snake[tail.r][tail.c] = -1;	// 현재 꼬리 없는 것으로 취급
				tail.setCoordi(tail.r + dr[tailDir], tail.c + dc[tailDir]);	// 꼬리 정보 저장
			}
			apple[hr][hc] = false;
		}
		D = getDir(D + nowDirInfo.rotationDir);	// 방향 틀기
		snake[head.r][head.c] = D;	// 틀어진 방향 저장
	}
	
	public static int getDir(int d) {	// 방향 전환
		if(d < 0) return 4 + d;
		else return d % 4;
	}
}
