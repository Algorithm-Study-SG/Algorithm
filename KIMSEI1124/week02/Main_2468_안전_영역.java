import java.io.*;
import java.util.*;

public class Main_2468_안전_영역 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int ans;
    static int N;
    static int[][] area;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        // input
        ans = 0;
        N = Integer.parseInt(br.readLine());
        area = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int height = Integer.parseInt(st.nextToken());
                area[i][j] = height;
            }
        }

        // solve
        for (int i = 0; i <= 100; i++) { // 빗물의 높이
            int count = 0;
            visited = new boolean[N][N];
            for (int j = 0; j < N; j++) { // y
                for (int k = 0; k < N; k++) { // x
                    if (area[j][k] > i && visited[j][k] == false) { // 조건에 맞으면 `count`를 1 증가하고 탐색
                        count++;
                        dfs(i, j, k);
                    }
                }
            }
            ans = Math.max(ans, count);
        }

        // output
        System.out.println(ans);
    }

    static int[] dy = { -1, 0, 0, 1 };
    static int[] dx = { 0, -1, 1, 0 };

    static public void dfs(int height, int y, int x) {
        if (area[y][x] <= height || visited[y][x] == true) { // 이미 방문하였거나 구역의 높이가 탐색 높이보다 낮을 경우 종료
            return;
        }
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) { // 4방향 탐색
            int new_y = y + dy[i];
            int new_x = x + dx[i];
            if (new_y < 0 || new_y >= N || new_x < 0 || new_x >= N) { // 예외 방지
                continue;
            }
            dfs(height, new_y, new_x);
        }
    }
}