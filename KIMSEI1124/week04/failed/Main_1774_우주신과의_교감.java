package KIMSEI1124.week04.failed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * IDEA 1: 위상 정렬 + DFS + 크루스칼
 * 1. 통로가 연결되어 있다면 `union`으로 합치기
 * 2. 만약 합쳐져 있지 않으면 대표 집단에서 가장 가까운 신 찾기
 * 3. 찾은 후 `union`으로 합치면 해당 정점부터 시작하여 `dfs`로 탐색하며 `union`하기
 * <p>
 * 반례 발견 가장 가까운 곳으로 가지 않고 1번과 연결된 녀석들 중 가장 가까운 쪽으로 이동..
 *  결과 : 틀렸습니다...
 */

/*
 * IDEA 2: 크루스칼 알고리즘
 * 단순하게 모든 정점들의 거리를 크루스칼 알고리즘으로 구한 뒤 방문 처리된 지점을 제외하고 최소 거리를 구하기
 */
public class Main_1774_우주신과의_교감 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder answer = new StringBuilder();

    private static int N; // N(1 <= 1,000) 우주신들의 개수
    private static int M; // M(1<= M <= 1,000) 연결된 신들과의 통로
    private static God[] gods;
    private static int[] parents;
    private static List<Integer> relatedGods;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(answer);
    }

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        /* 배열 초기화 및 입력 */
        gods = new God[N + 1];
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            gods[i] = God.of(x, y);
            parents[i] = i;
        }

        /* 연결된 통로 입력 */
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            gods[a].addGateway(b);
            gods[b].addGateway(a);
        }
    }

    private static void solve() {
        double addDistance = 0D;
        /* 시작할 신의 설정 */
        relatedGods = new ArrayList<>();
        dfs(1, 1);

        /* 탐색 */
        for (int i = 2; i <= N; i++) {
            God god = gods[i];
            System.out.println(Arrays.toString(parents));
            if (god.isVisited()) {
                continue;
            }

            List<Distance> distances = new ArrayList<>();
            for (int j = 1; j <= N; j++) {
                if (find(i) == find(j)) {
                    continue;
                }
                distances.add(Distance.of(god.getDistance(gods[j])));
            }
            Collections.sort(distances);
            // addDistance += getDistance(god);
            addDistance += distances.get(0).getValue();
            dfs(i, i);
        }

        /* 소수점 둘째 자리 반올림 */
        answer.append(String.format("%.2f", addDistance).trim());
    }

    private static double getDistance(God god) {
        List<Distance> distances = new ArrayList<>();
        // for (int nextGod : relatedGods) {
        // distances.add(Distance.of(god.getDistance(gods[nextGod])));
        // }
        for (int i = 1; i <= N; i++) {
            distances.add(Distance.of(god.getDistance(gods[i])));
        }

        Collections.sort(distances);
        return distances.get(0).getValue();
    }

    private static void dfs(int start, int id) {
        God god = gods[id];

        god.visit();
        relatedGods.add(id);
        union(start, id);

        for (int next : god.getGateway()) {
            if (gods[next].isVisited()) {
                continue;
            }
            dfs(start, next);
        }
    }

    private static int find(int id) {
        if (parents[id] == id) {
            return id;
        }

        parents[id] = find(parents[id]);
        return parents[id];
    }

    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if (aParent == bParent) {
            return;
        }

        parents[bParent] = aParent;
    }

    private static class God {
        private final int x;
        private final int y;
        private final Set<Integer> gateway;
        private boolean visited;

        private God(int x, int y) {
            this.x = x;
            this.y = y;
            gateway = new HashSet<>();
        }

        public static God of(int x, int y) {
            return new God(x, y);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void visit() {
            visited = true;
        }

        public boolean isVisited() {
            return visited;
        }

        public double getDistance(God other) {
            return Math.sqrt(Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2));
        }

        public void addGateway(int id) {
            gateway.add(id);
        }

        public Set<Integer> getGateway() {
            return gateway;
        }
    }

    private static class Distance implements Comparable<Distance> {
        private final double value;

        private Distance(double value) {
            this.value = value;
        }

        public static Distance of(double value) {
            return new Distance(value);
        }

        public double getValue() {
            return value;
        }

        @Override
        public int compareTo(Distance o) {
            return Double.compare(value, o.getValue());
        }

        @Override
        public String toString() {
            return "Distance{" +
                    "value=" + value +
                    '}';
        }
    }
}
