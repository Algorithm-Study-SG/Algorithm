package JisinKeo.week04.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_9470_Strahler {

    static int t, k, m, p;

    static List<Integer>[] graph;

    static int[] count; // 진입 차수
    static int[] strahler; // Strahler 순서

    static int[] maxCount; // 노드로 들어오는 각 노드의 Strahler 순서가 가장 큰 것의 개수
    public static void BFS(){
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for(int i = 1; i < m+1; i++){
            if(strahler[i] == 1){
                deque.add(i);
            }
        }

        while(!deque.isEmpty()){
            int size = deque.size();
            for(int i = 0; i < size; i++){
                int node = deque.pollFirst();
                for(int cur : graph[node]){
                    // 현재 노드의 Strahler 순서와 다음 노드의 Strahler 순서를 비교
                    if(strahler[cur] < strahler[node]){
                        strahler[cur] = strahler[node];
                        maxCount[cur] = 1;
                    } else if(strahler[cur] == strahler[node]){
                        maxCount[cur]++;
                    }

                    // 진입 차수 감소 및 Strahler 순서 결정
                    if(--count[cur] == 0){
                        if(maxCount[cur] > 1){
                            strahler[cur]++;
                        }
                        deque.add(cur);
                    }

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        for (int testcase = 1; testcase <= t; testcase++) {

            st = new StringTokenizer(br.readLine());

            k = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());

            graph = new LinkedList[m+1];
            count = new int[m+1];
            strahler = new int[m+1];
            maxCount = new int[m+1];

            for(int i = 1; i < m+1; i++){
                graph[i] = new LinkedList<>();
            }

            for(int i = 0; i < p; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a].add(b);
            }

            for(int i = 1; i < m+1; i++){
                for(int node : graph[i]){
                    count[node]++;
                }
            }

            for(int i = 1; i < m+1; i++){
                if(count[i] == 0){
                    strahler[i] = 1;
                }
            }

            BFS();

            int result = 0;

            for(int i = 1; i < m+1; i++){
                if(strahler[i] > result) result = strahler[i];
            }


            System.out.println(k + " " + result);
        }
    }
}