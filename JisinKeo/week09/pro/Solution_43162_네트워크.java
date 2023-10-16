package JisinKeo.week09.pro;

import java.util.*;

class Solution_43162_네트워크 {

    static int n;

    static boolean[] visited;

    public static void DFS(int v, int[][] computers){
        for(int i = 0; i < n; i++){
            if(v != i){
                if(computers[v][i] == 1 && !visited[i]){
                    visited[i] = true;
                    DFS(i, computers);
                }
            }
        }
    }

    public int solution(int n, int[][] computers) {

        int answer = 0;

        this.n = n;

        visited = new boolean[n];

        for(int i = 0; i < n; i++){
            if(!visited[i]){
                visited[i] = true;
                DFS(i, computers);
                answer++;
            }
        }

        return answer;
    }

}