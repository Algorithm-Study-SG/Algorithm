package JisinKeo.week02.BJ;

import java.io.*;
import java.util.*;

public class Main_1967_트리의지름 {
    static class Node { // 노드의 위치와 거리를 저장, static class Node
        int index, distance;

        Node(int index, int distance) {
            this.index = index; // 현재 노드의 인덱스
            this.distance = distance; // 시작 노드로부터의 거리
        }
    }

    static List<Node>[] graph; // Node 객체의 리스트를 요소로 갖는 배열 / 각 리스트는 그래프의 한 노드에 연결된 다른 노드들의 정보를 저장
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        graph = new ArrayList[N + 1]; // 노드의 번호는 1로 시작, 인덱스 0은 사용 X

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>(); // i번 노드에 연결된 노드들의 정보를 저장할 리스트
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 부모 노드의 번호
            int b = Integer.parseInt(st.nextToken()); // 자식 노드의 번호
            int w = Integer.parseInt(st.nextToken()); // 간선의 가중치

            graph[a].add(new Node(b, w));
            graph[b].add(new Node(a, w));
        }
        /**
         * 아이디어 : 루트 노드 1에서 가장 먼 거리의 노드를 구하고, 다시 그 노드로부터 가장 먼 거리를 구하면 답이 될 것이다 (BFS를 2번)
         */

        Node farthestNode = bfs(1);  // 임의의 노드에서 가장 먼 노드를 찾음

        Node resultNode = bfs(farthestNode.index); // 찾은 노드에서 다시 가장 먼 노드를 찾음

        bw.write(resultNode.distance + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static Node bfs(int start) {
        visited = new boolean[graph.length];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        visited[start] = true;

        Node maxNode = new Node(start, 0);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.distance > maxNode.distance) {
                maxNode = curr; // 거리가 가장 큰 노드 갱신
            }

            for (Node child : graph[curr.index]) {
                if (!visited[child.index]) { // 만약 해당 자식 노드가 아직 방문되지 않았다면
                    visited[child.index] = true; //  큐에 추가하고 방문했다고 표시
                    queue.add(new Node(child.index, curr.distance + child.distance));
                }
            }
        }

        return maxNode; // 시작 노드로부터 가장 멀리 떨어진 노드
    }
}
