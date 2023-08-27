package JisinKeo.week04.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

/**
 *  메모리 : 43872 KB
 *  시간 : 760 ms
 */
public class Main_1774_우주신과의교감 {

    static int n, m;

    static int[] x, y;
    static int[] parent;

    static class Edge implements Comparable<Edge>{
        int src, dest;
        double weight;
        Edge(int src, int dest, double weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.weight < o.weight) return -1;
            if (this.weight > o.weight) return 1;
            return 0;
        }
    }

    public static int find(int x){
        if(parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public static void union(int x, int y){
        int a = find(x);
        int b = find(y);

        parent[b] = a;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        x = new int[n];
        y = new int[n];

        parent = new int[n];

        for(int i = 0; i < n; i++){
            parent[i] = i;
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int pathX = Integer.parseInt(st.nextToken()) - 1;
            int pathY = Integer.parseInt(st.nextToken()) - 1;
            union(pathX, pathY); // 이미 연결된 것들은 mst 연산에서 미리 제외
        }

        List<Edge> edges = new ArrayList<>();

        for(int i = 0; i < n; i++){ // 간선들의 가중치 연산
            for(int j = i+1; j < n; j++){
                double distance = Math.sqrt(Math.pow(x[j] - x[i], 2) + Math.pow(y[j] - y[i], 2));
                edges.add(new Edge(i, j, distance));
            }
        }

        Collections.sort(edges); // 오름차순 정렬

        double result = 0;

        for(Edge edge : edges){
            if (find(edge.src) != find(edge.dest)) {
                result += edge.weight;
                union(edge.src, edge.dest);
            }
        }

        System.out.println(String.format("%.2f",result));

    }

}
