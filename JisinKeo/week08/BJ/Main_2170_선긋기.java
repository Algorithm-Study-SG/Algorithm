package JisinKeo.week08.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리 : 280892 KB
 * 시간 : 2760 ms
 */
public class Main_2170_선긋기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] lines = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        // x값을 기준으로 정렬
        Arrays.sort(lines, (a, b) -> a[0] - b[0]);

        int start = lines[0][0];
        int end = lines[0][1];
        int totalLength = 0;

        for (int i = 1; i < n; i++) {
            // 현재 선이 이전 선과 겹치지 않을 때
            if (lines[i][0] > end) {
                totalLength += (end - start);
                start = lines[i][0];
                end = lines[i][1];
            } else {
                // 겹치는 경우 끝나는 지점만 갱신
                end = Math.max(end, lines[i][1]);
            }
        }
        totalLength += (end - start);  // 마지막 선의 길이를 더한다.

        System.out.println(totalLength);
    }
}
