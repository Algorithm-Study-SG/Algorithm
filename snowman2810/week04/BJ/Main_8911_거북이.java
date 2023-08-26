package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_8911_거북이 {

	/*
	 * 메모리 : 33432 KB 
	 * 시간 : 776 ms
	 */

	// 움직일 방향을 미리 저장
	static int[] directionR = { -1, 0, 1, 0 };
	static int[] directionC = { 0, 1, 0, -1 };

	// 거북이의 현재 위치를 저장
	static int currentR;
	static int currentC;

	// 움직일 방향을 결정할 정보
	static int direction;

	// 가장 작은값과 큰값을 저장
	static int minR;
	static int minC;
	static int maxR;
	static int maxC;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCount = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= testCount; test_case++) {
			// 값들을 초기화
			currentR = 0;
			currentC = 0;
			direction = 0;
			minR = 0;
			minC = 0;
			maxR = 0;
			maxC = 0;

			// 공백으로 나뉘는 문자열이 아니여서 String에 입력을 받고 한칸씩 움직여서 메소드호출
			String read = br.readLine();
			for (int i = 0; i < read.length(); i++) {
				move(read.charAt(i));
			}

			// 거북이의 이동이 끝나면 넓이계산
			int answer = (maxR - minR) * (maxC - minC);

			// 출력
			System.out.println(answer);
		}
		br.close();
	}

	static void move(char command) {
		switch (command) {
		case 'F': // 전진
			currentR += directionR[direction];
			currentC += directionC[direction];
			calculate();
			break;
		case 'B': // 후진
			currentR += directionR[direction] * -1;
			currentC += directionC[direction] * -1;
			calculate();
			break;
		case 'L': // 좌측회전
			direction = (direction + directionR.length - 1) % directionR.length;
			break;
		case 'R': // 우측회전
			direction = (direction + 1) % directionR.length;
			break;
		}
	}

	// 위치정보를 갱신해준다
	static void calculate() {
		minR = Math.min(minR, currentR);
		minC = Math.min(minC, currentC);
		maxR = Math.max(maxR, currentR);
		maxC = Math.max(maxC, currentC);
	}

}
