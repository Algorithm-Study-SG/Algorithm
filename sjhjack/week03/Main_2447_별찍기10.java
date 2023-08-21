package Week03;

/**
 * 메모리 : 67488 KB
 * 시간 : 496 ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2447_별찍기10 {
	
	static int[][] star;
	
	static void recursive(int startRow, int endRow, int startCol, int endCol) {
		if(startRow==(endRow-1) && startCol==(endCol-1)) return;	// 크기가 1인 정사각형이면 재귀 끝
		
		int len = endRow-startRow;									// 한 변의 길이
		for(int i = startRow+len/3; i<startRow+len*2/3; i++) {		// 5번 영역 표시
			for(int j = startCol+len/3; j<startCol+len*2/3; j++) {
				star[i][j] = 1;
			}
		}
		
		recursive(startRow, startRow+len/3, startCol, startCol+len/3);			// 1번 영역
		recursive(startRow, startRow+len/3, startCol+len/3, startCol+len*2/3);	// 2번 영역
		recursive(startRow, startRow+len/3, startCol+len*2/3, endCol);			// 3번 영역
		
		recursive(startRow+len/3, startRow+len*2/3, startCol, startCol+len/3);	// 4번 영역
		recursive(startRow+len/3, startRow+len*2/3, startCol+len*2/3, endCol);	// 6번 영역
		
		recursive(startRow+len*2/3, endRow, startCol, startCol+len/3);			// 7번 영역
		recursive(startRow+len*2/3, endRow, startCol+len/3, startCol+len*2/3);	// 8번 영역
		recursive(startRow+len*2/3, endRow, startCol+len*2/3, endCol);			// 9번 영역
	}

	static void print(int N) {
		StringBuilder ans = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(star[i][j] == 1) ans.append(" ");	// 5번 영역은 빈칸
				else ans.append("*");					// 나머지는 *
			}
			ans.append("\n");
		}
		
		System.out.print(ans);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		star = new int[N][N];
		
		recursive(0, N, 0, N);
		print(N);
	}

}