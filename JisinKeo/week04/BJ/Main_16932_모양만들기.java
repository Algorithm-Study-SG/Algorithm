package JisinKeo.week04.BJ;

import java.awt.*;
import java.io.*;
import java.util.*;

/**
 *  메모리 : 251200 KB
 *  시간 : 1276 ms
 */

/**
 *  최적화 과정
 *  1. 모든 0 위치에 대해 해당 위치의 값을 1로 변경한 후 BFS를 통해 크기를 계산 -> 시간 초과
 *  2. BFS를 수행하며 각 영역의 크기를 구하고 좌표마다 그 크기를 새로운 배열에 저장해주었다. -> 중복되는 영역들의 크기를 합산하는 문제
 *  3. arrId[][]라는 배열을 선언하여 각 영역마다 고유한 ID를 지정해주었다. Set 자료구조를 통해 해당 크기의 id가 이미 set에 있다면 연산을 진행하지 않는다 -> 시간 초과
 *  4. set보다는 map이 빠르므로 map<arrId, count>로 각 영역의 고유 id와 크기를 저장해주었다. -> 시간 초과 (88%)
 *  5. BFS를 사용하여 각 모양의 크기를 미리 계산하고, 이 정보를 shapeSizeMap에 저장한다.
 *     각 0인 위치에서의 최대 크기를 빠르게 계산하기 위해, 해당 위치에 인접한 모양들의 크기를 더하면서 동시에 중복을 체크한다. -> 맞았습니다
 */
public class Main_16932_모양만들기 {

    static int n, m;
    static int[][] arr, arrId;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Map<Integer, Integer> shapeSizeMap = new HashMap<>();

    public static int BFS(int i, int j, int shapeId) {
        ArrayDeque<Point> deque = new ArrayDeque<>();
        deque.add(new Point(i, j));
        visited[i][j] = true;
        int count = 1;
        arrId[i][j] = shapeId;

        while (!deque.isEmpty()) {
            Point point = deque.pollFirst();
            int x = point.x;
            int y = point.y;
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (arr[nx][ny] == 1 && !visited[nx][ny]) {
                        deque.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                        arrId[nx][ny] = shapeId;
                        count++;
                    }
                }
            }
        }
        return count; // 크기 반환
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        arrId = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int shapeId = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    int shapeSize = BFS(i, j, shapeId);
                    shapeSizeMap.put(shapeId, shapeSize);
                    shapeId++; // 고유 영역을 항상 다르게 해야 하기 때문에
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    Set<Integer> adjacentShapes = new HashSet<>();
                    int sum = 1;

                    for (int k = 0; k < 4; k++) { // 고유 영역을 찾는 과정
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] == 1) {
                            adjacentShapes.add(arrId[nx][ny]);
                        }
                    }

                    for (int shape : adjacentShapes) { // 고유 영역 for 문
                        sum += shapeSizeMap.get(shape); // 고유 영역에 대한 크기는 map에 저장되어있음
                    }

                    result = Math.max(result, sum); // sum이 최대값이면 값 업데이트
                }
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
