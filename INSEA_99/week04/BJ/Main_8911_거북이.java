package INSEA_99.week04.BJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.StringTokenizer;

/* 메모리 : 23776 KB
 * 시간 : 408 ms
 * 
 * idea1 : 구현
 * 
 * [naver code convention java를 보고 바꾼 점] 
 * 1. import는 * 사용하는 것이 아닌 사용하는 애들만 하기
 * 2. import 구절은 그룹 사이에 빈줄 삽입 (그룹은 규칙 있음 ex. java. , javax., org. , ...)
 * 		ex) before : import java.io.InputStreamReader;
 * 					 import java.io.OutputStreamWriter;
 * 					 import java.util.StringTokenizer;
 * 			after  : import java.io.InputStreamReader;
 * 					 import java.io.OutputStreamWriter;
 * 					 
 * 					 import java.util.StringTokenizer;
 * 3. 자료형 선언은 다른 줄 이용
 * 		ex) before : int d, r, c;
 * 			after  : int d;
 * 					 int r;
 * 					 int c;
 * 4. 임시 변수 외에는 1글자 사용 금지
 * 		ex) int t -> int testcase
 * 5. else, catch finaly, do-while의 닫는 중괄호 위치
 * 	  (+ 조건/반복문 한 줄로 끝낼 수 있더라도 중괄호 사용하기)
 * 		ex) before : if(true){
 * 					 	a = 1;	
 * 					 } 
 * 					 else {
 * 						a = 0
 * 					 }
 * 			after  : if(true){
 * 					 	a = 1;	
 * 					 } else {
 * 						a = 0
 * 					 }
 * 6. 메소드 사이에 빈 줄 삽입
 * 		ex) before : public void setX() {}
 * 					 public void setY() {}
 * 			after  : public void setX() {}
 * 
 * 					 public void setY() {}
 * 7. 제어문 키워드와 여는 소괄호 사이, 식별자와 여는 소괄호 사이에 공백 삽입
 * 		ex) before : public void setX() {
 * 					     if(x == 0) {
 * 			   		         x = -1;
 * 					 	 }
 * 					 }
 * 			after  : public void setX () {
 * 					     if (x == 0) {
 * 			   		         x = -1;
 * 					 	 }
 * 					 }
 * 			
 * 8. 주석 기호 전후 공백 삽입 멀찍히 ! 
 * 	(naver에는 그냥 공백이라고 해서 탭 말고 공백해야하나 했는데 다른 곳에서는 다 멀찍하게 쓰라고)
 * 		ex) input();		// 입력 받기
 * 
 */

public class Main_8911_거북이 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static final int dr[] = {-1, 0, 1, 0};	 // 상 우 좌 하
	static final int dc[] = {0, 1, 0, -1}; 

	static int direction;		// 현재 거북이가 바라보는 방향
	static int row; 			// 현재 거북이 행
	static int column;			// 현재 거북이 열
	static int[] infoRCMinMax;	// 거북이 행, 열의 min, max 값 저장 배열
	static String cmd;			// 거북이 명령어
	
	/* 메인 */
	public static void main (String[] args) throws IOException {
		int testcase = Integer.parseInt(br.readLine());
		while (testcase-- != 0) {
			input();		// 입력
			run();			// 거북이 이동
			output();		// 출력	
		}
		bw.flush();
		bw.close();
		return;
	}
	
	/* 입력 */
	static void input () throws IOException {
		/* 변수 초기화 */
		row = 0;
		column = 0;
		direction = 0;
		infoRCMinMax = new int[4];
		
		cmd = br.readLine();		// 거북이 명령어 입력
	}
	
	/* 출력 */
	static void output() throws IOException{
		bw.write(getArea() + "\n");		
	}
	
	/* 실행 */
	static void run() {
		for (int i = 0; i < cmd.length(); ++i) {		// 명령 실행
			int nowCmd = cmd.charAt(i);	
			if (nowCmd == 'F') {						// 앞으로 이동
				row += dr[direction];
				column += dc[direction];
				setMinMax();
			} else if (nowCmd == 'B') {					// 뒤로 이동
				row -= dr[direction];
				column -= dc[direction];
				setMinMax();
			} else if (nowCmd == 'L') {					// 왼쪽으로 이동
				direction = (direction+3);
			} else if (nowCmd == 'R') {					// 오른쪽으로 이동
				direction = (++direction)%4;
			}
		}
	}
	
	/***
	 * 현재 거북이의 위치를 기반으로 행, 열의 min, max 값을 저장한다
	 */
	static void setMinMax () {
		infoRCMinMax[0] = Math.min(infoRCMinMax[0], row);
		infoRCMinMax[1] = Math.max(infoRCMinMax[1], row);
		infoRCMinMax[2] = Math.min(infoRCMinMax[2], column);
		infoRCMinMax[3] = Math.max(infoRCMinMax[3], column);
	}
	
	/***
	 * 행, 열의 min, max 값을 사용하여 면적을 반환한다.
	 * return 거북이 이동을 포함하는 최소 직사각형의 면적
	 */
	static int getArea () {
		int rLength = infoRCMinMax[1] - infoRCMinMax[0];
		int cLength = infoRCMinMax[3] - infoRCMinMax[2];
		int area = rLength * cLength;
		return area;
	}
}
