package KIMSEI1124.week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1325_효율적인해킹 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    /* 컴퓨터 정보 */
    private static List<List<Integer>> computerList = new ArrayList<>();
    private static int[] hackingComputerQuantity;

    /* 그래프 입력 */
    private static int computerCount; // 노드의 개수
    private static int networkCount; // 간선의 개수

    /* 정답 */
    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    private static void input() throws IOException {
        StringTokenizer st;
        /* 네트워크 정보 입력 */
        st = new StringTokenizer(br.readLine());
        computerCount = Integer.parseInt(st.nextToken());
        networkCount = Integer.parseInt(st.nextToken());
        hackingComputerQuantity = new int[computerCount + 1];

        /* 컴퓨터 생성 */
        for (int i = 0; i <= computerCount; i++) {
            computerList.add(new ArrayList<>());
        }

        /* 컴퓨터 정보 저장 */
        for (int i = 0; i < networkCount; i++) {
            st = new StringTokenizer(br.readLine());
            int computerId = Integer.parseInt(st.nextToken());
            int trustComputerId = Integer.parseInt(st.nextToken());

            /* 컴퓨터에서 신뢰할 수 있는 컴퓨터를 저장한다. */
            computerList.get(computerId).add(trustComputerId);
        }
    }

    private static void solve() {
        for (int i = 1; i <= computerCount; i++) {
            bfs(i);
        }
    }

    private static void bfs(int startComputerId) {
        /* 해킹 시작 전 초기화 작업 */
        boolean[] visited = new boolean[computerCount + 1];
        Deque<Integer> queue = new ArrayDeque<>();

        /* 처음 해킹하는 컴퓨터 작업 */
        queue.addLast(startComputerId);
        visited[startComputerId] = true;

        while (!queue.isEmpty()) {
            /* 해킹할 컴퓨터를 방문하였다고 방문 체크를 한 후 수량을 증가 */
            int computerId = queue.pollFirst();
            hackingComputerQuantity[computerId]++;

            /* 네트워크로 해킹할 컴퓨터 탐색 */
            for (int nextComputerId : computerList.get(computerId)) {
                /* 이미 해킹을 진행한 컴퓨터이면 종료 */
                if (visited[nextComputerId]) {
                    continue;
                }
                visited[nextComputerId] = true;
                queue.addLast(nextComputerId);
            }
        }
    }

    private static void print() {
        int maxHackingQuantity = 0;
        for (int i = 1; i <= computerCount; i++) {
            int hackingQuantity = hackingComputerQuantity[i];
            if (hackingQuantity < maxHackingQuantity) {
                continue;
            }
            /* 갱신되면 새로 초기화 진행 */
            if (hackingQuantity > maxHackingQuantity) {
                answer = new StringBuilder();
                maxHackingQuantity = hackingQuantity;
            }
            /* 오름차순으로 정답에 추가 */
            answer.append(i).append(" ");
        }

        System.out.println(answer);
    }
}
