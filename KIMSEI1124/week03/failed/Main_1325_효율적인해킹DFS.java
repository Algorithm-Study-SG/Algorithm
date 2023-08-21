package KIMSEI1124.week03.failed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main_1325_효율적인해킹DFS {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final List<List<Integer>> computerNetworks = new ArrayList<>();
    private static boolean[] visited;
    private static int[] hackingComputerQuantity;
    private static int computerCount;
    private static int networkCount;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    private static void input() throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        computerCount = Integer.parseInt(st.nextToken());
        networkCount = Integer.parseInt(st.nextToken());
        hackingComputerQuantity = new int[computerCount + 1];

        for (int i = 0; i <= computerCount; i++) {
            computerNetworks.add(new ArrayList<>());
        }

        for (int i = 0; i < networkCount; i++) {
            st = new StringTokenizer(br.readLine());
            int computerId = Integer.parseInt(st.nextToken());
            int trustComputerId = Integer.parseInt(st.nextToken());
            computerNetworks.get(computerId).add(trustComputerId);
        }
    }

    private static void solve() {
        for (int i = 1; i <= computerCount; i++) {
            visited = new boolean[computerCount + 1];
            visited[i] = true;
            dfs(i);
        }
    }

    private static void dfs(int computerId) {
        for (int nextComputerId : computerNetworks.get(computerId)) {
            if (!visited[nextComputerId]) {
                visited[nextComputerId] = true;
                hackingComputerQuantity[nextComputerId]++;
                dfs(nextComputerId);
            }
        }
    }

    private static void print() {
        StringBuilder answer = new StringBuilder();
        int maxHackingQuantity = 0;
        for (int i = 1; i <= computerCount; i++) {
            int hackingQuantity = hackingComputerQuantity[i];
            if (hackingQuantity < maxHackingQuantity) {
                continue;
            }
            if (hackingQuantity > maxHackingQuantity) {
                answer = new StringBuilder();
                maxHackingQuantity = hackingQuantity;
            }
            answer.append(i).append(" ");
        }
        System.out.println(answer);
    }
}
