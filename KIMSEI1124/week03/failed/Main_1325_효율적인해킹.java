package KIMSEI1124.week03.failed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1325_효율적인해킹 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    /* 컴퓨터 정보 */
    private static final Set<Integer> notLeafComputers = new HashSet<>();
    private static Computer[] computers;

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

        /* 네트워크 정보 초기화 */
        computers = new Computer[computerCount + 1];
        for (int i = 1; i <= computerCount; i++) {
            computers[i] = Computer.init();
        }

        /* 컴퓨터 정보 저장 */
        for (int i = 0; i < networkCount; i++) {
            st = new StringTokenizer(br.readLine());
            int computerId = Integer.parseInt(st.nextToken());
            int trustComputerId = Integer.parseInt(st.nextToken());

            /* 컴퓨터 정보에 신뢰할 수 있는 컴퓨터의 `Id`를 저장한다. */
            computers[computerId].addHackingComputer(computers[trustComputerId]);

            /* `leaf`컴퓨터가 아닌 컴퓨터들을 저장한다. */
            notLeafComputers.add(trustComputerId);
        }
    }

    private static void solve() {
        Queue<Computer> queue = initQueue();
        while (!queue.isEmpty()) {
            Computer computer = queue.poll();

            /* 자신을 해킹할 수 있는 컴퓨터에게 자신의 `quantity(해킹할 수 있는 컴퓨터의 수)` 만큼 더합니다. */
            for (Computer trustComputer : computer.getTrustComputers()) {
                trustComputer.updateHackingComputerQuantity(computer.getQuantity());
                if (trustComputer.isVisited()) {
                    continue;
                }
                trustComputer.visit();
                queue.add(trustComputer);
            }
        }
    }

    /* 해킹할 수 없는 컴퓨터들이 담겨져 있는 `queue`를 초기화합니다. */
    private static Queue<Computer> initQueue() {
        Queue<Computer> queue = new ArrayDeque<>();
        for (int i = 1; i <= computerCount; i++) {
            if (notLeafComputers.contains(i)) {
                continue;
            }
            /* 자신 말고 해킹할 수 없는 컴퓨터를 `queue`에 저장 후 방문 처리합니다. */
            Computer computer = computers[i];
            computer.visit();
            queue.add(computer);
        }
        return queue;
    }

    private static void print() {
        int maxQuantity = 0;
        for (int i = 1; i <= computerCount; i++) {
            if (computers[i].getQuantity() < maxQuantity) {
                continue;
            }
            if (computers[i].getQuantity() > maxQuantity) {
                answer = new StringBuilder();
                maxQuantity = computers[i].getQuantity();
            }
            answer.append(i).append(" ");
        }
        System.out.println(Arrays.toString(computers));
        System.out.println(answer);
    }

    private static class Computer {
        private final Set<Computer> trustComputers;
        private boolean visited;
        private int quantity;

        private Computer() {
            this.trustComputers = new HashSet<>();
            this.quantity = 1; // 자기 자신을 해킹할 수 있으므로 1로 초기값을 설정합니다.
        }

        public static Computer init() {
            return new Computer();
        }

        public void addHackingComputer(Computer computer) {
            trustComputers.add(computer);
        }

        public Set<Computer> getTrustComputers() {
            return trustComputers;
        }

        public boolean isVisited() {
            return visited;
        }

        public void visit() {
            this.visited = true;
        }

        public int getQuantity() {
            return quantity;
        }

        public void updateHackingComputerQuantity(int quantity) {
            this.quantity += quantity;
        }
    }
}
