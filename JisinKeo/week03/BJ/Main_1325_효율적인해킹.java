package JisinKeo.week03.BJ;

import java.io.*;
import java.util.*;

/**
 * 메모리 : 305008 KB
 * 시간 : 7884ms
 */
public class Main_1325_효율적인해킹 {
    static int n, m;
    static ArrayList<Integer>[] graph;

    static boolean[] visited;

    static int[] count;

    public static void BFS(int v){
        ArrayDeque<Integer> deque = new ArrayDeque<>(); // LinkedList보다는 ArrayDeque가 속도 면에서 빠르다.
        visited[v] = true;
        deque.add(v);

        while(!deque.isEmpty()){
            int node = deque.poll();
            for(int next : graph[node]){
                if(!visited[next]){ // 해당 컴퓨터를 방문하지 않았으면
                    visited[next] = true;
                    count[next]++;
                    // graph[a].add(b); 으로 값을 저장해 주었기 때문에 graph[node]에 해당하는 next값을
                    // 인덱스로 하는 count 배열을 1 증가시켜준다.
                    // 초반에 graph[a].add(b) 다음과 같이 저장을 했기 때문에 BFS로 따라가면서 count++;를 해주었는데
                    // 이렇게 하면 BFS로 계속 탐색하는 과정에서 시간초과가 난다.
                    deque.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];

        count = new int[n+1];

        for(int i = 1; i < n+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b); // 원래는 반대로 저장을 해주었으나, 이렇게 저장을 하면 시간 단축에 도움이 된다.
            // 예제 입력 1 저장 후 : [null, [], [], [1, 2], [3], [3]]
        }

        for(int i = 1; i < n+1; i++){
            visited = new boolean[n+1];
            BFS(i);
        }

        int max = 0;

        for(int i = 1; i < n+1; i++){
            max = Math.max(count[i], max);
        }

        for(int i = 1; i < n+1; i++){
            if(max == count[i]) bw.write(i + " ");
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
