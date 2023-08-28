package KIMSEI1124.week04.failed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_9470_Strahler_순서 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder answer = new StringBuilder();
    private static int K; // (1 <= K <= 1,000) 테스트 케이스 번호
    private static int M; // (2 <= M <= 1,000) 노드의 수
    private static int P; // 간선의 수
    private static List<List<Integer>> river;
    private static Set<Integer> notStart;
    private static boolean[] visited;
    private static int result; // 가장 큰 하천계의 크기

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int i = 1; i <= t; i++) {
            input();
            solve();
            answer.append(K).append(" ").append(result).append("\n");
        }
        System.out.println(answer);
    }

    private static void input() throws IOException {
        StringTokenizer st;

        /* 테스트 케이스 입력 */
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        /* 하천 초기화 및 입력 */
        river = new ArrayList<>();
        notStart = new HashSet<>();
        visited = new boolean[M + 1];
        for (int i = 0; i <= M; i++) {
            river.add(new ArrayList<>());
        }

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            notStart.add(to);
            river.get(from).add(to);
        }
    }

    private static void solve() {
        /* 시작 Queue 등록하기 */
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= M; i++) {
            if (notStart.contains(i)) {
                continue;
            }
            queue.offer(i);
        }

        /* Depth 탐색 */
        while (!queue.isEmpty()) {
            int loop = queue.size();
            while (loop-- > 0) {
                int id = queue.poll();
                visited[id] = true;
                for (int nextId : river.get(id)) {
                    if (visited[nextId]) {
                        continue;
                    }
                    queue.add(nextId);
                }
            }
            result++;
        }
    }
}
