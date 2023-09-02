package boj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14430_자원_캐기 {
	/*
	 * 메모리 : 22400 KB
	 * 시간 : 236 ms
	 */


	public static void main(String[] args) throws IOException {
		// 기본 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] input = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 해당 칸으로 올 수 있는 경로중 최대값을 저장
				int max = 0;
				
				// 범위 안에 있을 경우 max 할당
				if (i != 0) {
					max = input[i - 1][j];
				}
				
				// 범위 안에 있을 경우 max 비교 후 할당
				if (j != 0) {
					max = Math.max(max, input[i][j - 1]);
				}
				
				// 해당 칸으로 올 수 있는 경로중 최대값과 현재 있는 값을 더해서 저장
				input[i][j] += max;
			}
		}
		
		
		// 출력
		System.out.println(input[N - 1][M - 1]);

		br.close();
	}

}
