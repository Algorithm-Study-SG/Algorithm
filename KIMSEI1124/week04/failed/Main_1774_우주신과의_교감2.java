package KIMSEI1124.week04.failed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1774_우주신과의_교감2 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder answer = new StringBuilder();
    private static int N; // ( 1 <= N <= 1,000 ) 우주신들의 개수
    private static int M; // ( 1 <= M <= 1,000 ) 통로의 개수
    private static God[] gods;
    private static Set<Integer> startGods;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(answer);
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        /* 신 배열 초기화 및 입력 */
        gods = new God[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            gods[i] = God.from(i, x, y);
        }

        /* 연결 입력 */
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            /* 연결 정보 저장 */
            gods[a].addRelatedGodById(b);
            gods[b].addRelatedGodById(a);
        }
    }

    private static void solve() {
        double totalDistance = 0D;
        /* `PQ` 후보 선정 */
        startGods = new HashSet<>();
        findStartGods(1);

        /* 신들 간의 거리 구하기 */
        getDistanceByGods();

        PriorityQueue<Gateway> gatewayQueue = new PriorityQueue<>(Comparator.comparingDouble(Gateway::getDistance));
        for (int id : startGods) {
            God god = gods[id];
            god.visit();

            for (Gateway gateway : god.getGateways()) {
                gatewayQueue.offer(gateway);
            }
        }

        while (!gatewayQueue.isEmpty()) {
            System.out.println(gatewayQueue);
            Gateway pollGateway = gatewayQueue.poll();
            God god = gods[pollGateway.getTo()];
            /* 이미 연결된 신이면 종료 */
            if (god.isVisited()) {
                continue;
            }
            god.visit();
            for (Gateway gateway : god.getGateways()) {
                gatewayQueue.offer(gateway);
            }
            totalDistance += pollGateway.getDistance();
        }
        answer.append(String.format("%.2f", totalDistance));
    }

    private static void findStartGods(int id) {
        God god = gods[id];
        god.visit();
        startGods.add(id);

        for (int relatedGodId : god.getRelatedGateways()) {
            if (gods[relatedGodId].isVisited()) {
                continue;
            }
            findStartGods(relatedGodId);
        }
    }

    private static void getDistanceByGods() {
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                /* 이미 연결되어 있으면 종료 */
                if (gods[i].hasRelatedGodById(j)) {
                    continue;
                }
                double distance = getDistance(gods[i], gods[j]);
                gods[i].addGateway(Gateway.of(i, j, distance));
                gods[j].addGateway(Gateway.of(j, i, distance));
            }
        }
    }

    /**
     * 신`A`와 신`B`의 거리를 구하여 반환한다.
     *
     * @param a 신 `A`
     * @param b 신 `B`
     * @return 신`A`와 신`B`의 거리
     */
    public static double getDistance(God a, God b) {
        return Math.sqrt(Math.pow((double) a.getX() - b.getX(), 2) + Math.pow((double) a.getY() - b.getY(), 2));
    }

    private static class God {
        private final List<Gateway> gateways;
        private final Set<Integer> relatedGateways;
        private final int id;
        private final int x;
        private final int y;
        private boolean visited;

        private God(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            gateways = new ArrayList<>();
            relatedGateways = new HashSet<>();
        }

        public static God from(int id, int x, int y) {
            return new God(id, x, y);
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public void visit() {
            visited = true;
        }

        public boolean isVisited() {
            return visited;
        }

        public void addGateway(Gateway gateway) {
            gateways.add(gateway);
        }

        public List<Gateway> getGateways() {
            return gateways;
        }

        public void addRelatedGodById(int id) {
            relatedGateways.add(id);
        }

        /**
         * 신의 인덱스 번호가 존재하는지 확인한 결과를 반환합니다.
         *
         * @param id 신의 인덱스 번호
         * @return contains(id);
         */
        public boolean hasRelatedGodById(int id) {
            return relatedGateways.contains(id);
        }

        public Set<Integer> getRelatedGateways() {
            return relatedGateways;
        }
    }

    private static class Gateway {
        private final int from;
        private final int to;
        private final double distance;

        private Gateway(int from, int to, double distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        public static Gateway of(int from, int to, double distance) {
            return new Gateway(from, to, distance);
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public double getDistance() {
            return distance;
        }

        @Override
        public String toString() {
            return "Gateway{" +
                    "from=" + from +
                    ", to=" + to +
                    ", distance=" + distance +
                    '}';
        }
    }
}
