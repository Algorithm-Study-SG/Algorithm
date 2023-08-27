package Week04;

/**
 * 메모리 : 26316 KB
 * 시간 : 436 ms
 * 
 * # 접근 : 거북이가 좌표평면 상에서 동서남북으로 이동하므로 delta를 사용해서 좌표값을 변경해준다.
 *         회전이 90도씩 일어나므로 delta의 순서를 북동남서로 설정해서 변경이 용이하게 한다.
 *         좌표의 한계선이 지정되어있지 않으므로 따로 2차원 배열을 그리지 않고, 좌표값만 가져간다.
 *         직사각형은 x,y의 최솟값과 최댓값을 경계로 하는 영역이므로 각 실행 단계마다 x, y의 최댓값, 최솟값을 저장한다.
 *         
 * # 코딩 컨벤션 보고 수정한 내용
 *         1. 제어문 키워드와 여는 소괄호 사이에 공백 삽입 (if, for, ... )
 *         2. 메서드 이름은 동사/전치사로 시작
 *         3. 하나의 선언문에는 하나의 변수만 다룬다
 *         
 *         (참고 자료 : https://naver.github.io/hackday-conventions-java/#method-verb-preposition)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_8911_거북이 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder answer = new StringBuilder();
	private static int[] dx = {0, 1, 0,-1};							// 북 동 남 서
	private static int[] dy = {1, 0,-1, 0};
	
	private static void solve(String command) {
		int x = 0;
		int y = 0;
		int maxX = 0;												// 초기 좌표가 (0,0)이므로 0으로 초기화
		int minX = 0;										
		int maxY = 0;
		int minY = 0;
		int delta = 0;
		
		for (int i = 0; i < command.length(); i++) {					// 명령 수행
			if (command.charAt(i) == 'F') {							// 전진
				x += dx[delta];
				y += dy[delta];
			} else if (command.charAt(i) == 'B') {					// 후진
				x += dx[(delta + 2) % 4];
				y += dy[(delta + 2) % 4];
			} else if (command.charAt(i) == 'L') {					// 왼쪽 90도
				delta = (delta + 4 - 1) % 4;
			} else if (command.charAt(i) == 'R') {					// 오른쪽 90도
				delta = (delta + 1) % 4;
			} else {
				System.out.println("Error !!");
				return;
			}
			
			maxX = Math.max(x, maxX);
			minX = Math.min(x, minX);
			maxY = Math.max(y, maxY);
			minY = Math.min(y, minY);
		}
		
		answer.append((maxX - minX) * (maxY - minY)).append("\n");
	}

	public static void main(String[] args) throws IOException {
		int test_case = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= test_case; t++) {
			String command = br.readLine();
			solve(command);
		}
		
		System.out.print(answer);
	}
}
