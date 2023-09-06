package Week05;

/**
 * 메모리 : 22628 KB
 * 시간 : 240 ms
 * 
 * # 접근 : (i,j)로 올 수 있는 경우의 수는 위쪽, 왼쪽 두 가지이다.
 *         각 좌표마다 두 경우의 수 중 더 많은 자원을 채취하는 경우를 가져가며 탐색한다.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14430_자원캐기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N+1][M+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]) + Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.print(arr[N][M]);
	}

}
