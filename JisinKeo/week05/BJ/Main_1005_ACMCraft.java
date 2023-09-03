package JisinKeo.week05.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 메모리 : 272712 KB
 * 시간 : 1412 ms
 */
public class Main_1005_ACMCraft {

    static List<Integer>[] graph, calculate;
    static int[] sum, d, dp;

    static int N, K, W;

    public static int BFS(){
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for(int i = 1; i < N+1; i++){
            if(sum[i] == 0){
                dp[i] = d[i];
                deque.add(i);
            }
        }

        while(!deque.isEmpty()){
            int size = deque.size();
            for(int i = 0; i < size; i++){
                int node = deque.pollFirst();
                for(int cur : graph[node]){
                    sum[cur]--;
                    if(sum[cur] == 0){
                        deque.add(cur);
                        int max = 0;
                        for(int curr : calculate[cur]){
                            if(dp[curr] > max) max = dp[curr];
                        }
                        dp[cur] = max + d[cur];
                        if(cur == W) {
                            return dp[cur];
                        }
                    }
                }
            }

        }

        return d[W];

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N+1];

            for(int i = 1; i < N+1; i++){
                graph[i] = new ArrayList<>();
            }

            calculate = new ArrayList[N+1];

            for(int i = 1; i < N+1; i++){
                calculate[i] = new ArrayList<>();
            }

            d = new int[N+1];

            st = new StringTokenizer(br.readLine());

            for(int i = 1; i < N+1; i++){
                d[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                calculate[b].add(a);
            }

            sum = new int[N+1];
            dp = new int[N+1];

            for(int i = 1; i < N+1; i++){
                for(int cur : graph[i]){
                    sum[cur]++;
                }
            }

            W = Integer.parseInt(br.readLine());

            int result = BFS();

            System.out.println(result);
        }

    }
}
