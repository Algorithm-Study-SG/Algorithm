package JisinKeo.week02.BJ;

import java.io.*;
import java.util.*;

public class Main_11725_트리의부모찾기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        List<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) { // 트리 상에서 연결된 두 정점을 graph에 입력한다
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        int[] parent = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1); // 트리의 루트를 1이라고 정했을 때,
        visited[1] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int child : graph[curr]) { // curr에 해당하는 child를 이용해 값을 업데이트
                if (!visited[child]) {
                    visited[child] = true;
                    parent[child] = curr;
                    queue.add(child);
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            bw.write(parent[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}