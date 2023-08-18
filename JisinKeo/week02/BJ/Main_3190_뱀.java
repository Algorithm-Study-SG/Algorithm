package JisinKeo.week02.BJ;

import java.io.*;
import java.util.*;

public class Main_3190_뱀 {

    static int[] dx = {0, 1, 0, -1}; // 뱀은 처음에 오른쪽을 향한다
    static int[] dy = {1, 0, -1, 0};

    static int[][] map;
    static Map<Integer, String> directionChange = new HashMap<>(); // Map을 사용해 현재 시간에 대한 방향 정보를 빠르게 찾아볼 수 있다

    public static int simulateSnake(int N) {
        int time = 0;
        int direction = 0; // 시작은 오른쪽
        int headX = 1, headY = 1;

        Queue<int[]> snake = new LinkedList<>();
        snake.offer(new int[]{headX, headY});

        while (true) {
            if (directionChange.containsKey(time)) {
                if ("D".equals(directionChange.get(time))) { // 오른쪽
                    direction = (direction + 1) % 4;
                } else {
                    direction = (direction - 1 + 4) % 4; // 왼쪽
                }
            }

            // nx, ny : 뱀의 머리가 다음에 이동할 위치의 x, y 좌표
            int nx = headX + dx[direction];
            int ny = headY + dy[direction];

            if (nx <= 0 || nx > N || ny <= 0 || ny > N) { // 벽에 부딪힘
                time++;
                break;
            }

            /**
             * map[i][j] = 0 : 아무 것도 없는 빈 칸
             * map[i][j] = 1 : 사과가 있는 칸
             * map[i][j] = 2 : 뱀의 몸통이나 머리가 있는 칸
             */

            if (map[nx][ny] != 2) { // 해당 위치에 뱀의 몸통이 없으면
                if (map[nx][ny] == 0) { // 사과도 없고 뱀의 몸통도 없으면
                    int[] tail = snake.poll();
                    map[tail[0]][tail[1]] = 0; // 뱀이 움직이고 사과도 안 먹었으니 꼬리는 지운다
                }

                snake.offer(new int[]{nx, ny}); // nx, ny로 이동했으니 위치를 snake에 저장
                map[nx][ny] = 2; // 해당 위치에 뱀이 있음을 표시
                headX = nx;
                headY = ny;
            } else {
                time++;
                break; // 뱀의 머리와 몸통이 만남
            }

            time++;
        }

        return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = 1; // 사과 위치 저장
        }

        int L = Integer.parseInt(br.readLine());

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();

            directionChange.put(time, direction);
        }

        int result = simulateSnake(N);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
