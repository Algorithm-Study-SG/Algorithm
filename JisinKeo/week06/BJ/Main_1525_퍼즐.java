package JisinKeo.week06.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 메모리 : 98768 KB
 * 시간 : 888 ms
 */
public class Main_1525_퍼즐 {
    // 상하좌우 움직이기 위한 dx, dy 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    // 목표 상태
    static String target = "123456780";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder start = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                start.append(st.nextToken());
            }
        }

        // bfs를 호출하여 결과 출력
        System.out.println(bfs(start.toString()));
    }

    public static int bfs(String start) {
        Queue<String> q = new LinkedList<>();
        Map<String, Integer> visited = new HashMap<>();

        // 초기 상태 큐에 추가 및 방문 표시
        q.offer(start);
        visited.put(start, 0);

        while (!q.isEmpty()) {
            String curr = q.poll(); // 큐에서 하나를 꺼냄

            // 현재 상태가 목표 상태라면 이동 횟수 반환
            if (curr.equals(target)) {
                return visited.get(curr);
            }

            int z = curr.indexOf('0'); // 0의 위치 찾기
            int x = z / 3;
            int y = z % 3;

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir], ny = y + dy[dir];

                // 범위 체크
                if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {
                    StringBuilder next = new StringBuilder(curr);
                    // 인접한 칸과 0의 위치를 교환
                    char temp = next.charAt(3 * nx + ny);
                    next.setCharAt(3 * nx + ny, '0');
                    next.setCharAt(z, temp);

                    String nextString = next.toString();
                    // 새로운 상태가 방문되지 않았다면 큐에 추가 및 방문 표시
                    if (!visited.containsKey(nextString)) {
                        visited.put(nextString, visited.get(curr) + 1);
                        q.offer(nextString);
                    }
                }
            }
        }
        // 목표 상태에 도달할 수 없다면 -1 반환
        return -1;
    }
}
