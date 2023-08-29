package JisinKeo.week04.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  메모리 : 248767 KB
 *  시간 : 916 ms
 */
public class Main_6497_전력난 {

    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static int find(int x){
        if(parent[x] != x) parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    public static void union(int x, int y){
        int a = find(x);
        int b = find(y);

        parent[b] = a;
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        while(true) {

            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) break;

            parent = new int[m];

            for (int i = 0; i < m; i++) {
                parent[i] = i;
            }

            List<Edge> edges = new ArrayList<>();

            int sum = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                sum += c; // 입력받는 간선의 가중치를 하나씩 더 해준다.
                edges.add(new Edge(a, b, c));
            }

            Collections.sort(edges);

            for (Edge edge : edges) {
                if (find(edge.src) != find(edge.dest)) {
                    sum -= edge.weight; // 총 가중치에서 mst에 포함되는 간선의 가중치를 뺀다.
                    union(edge.src, edge.dest); // mst 만들기
                }
            }

            System.out.println(sum);

        }
    }
}
