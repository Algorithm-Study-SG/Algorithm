package KIMSEI1124.week09;

import java.util.*;

public class Solution_네트워크 {

    private static List<List<Integer>> list;
    private static boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        init(n, computers);
        return answer;
    }

    private void init(int n, int[][] computers) {
        list = new ArrayList<>();
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            if (i == 0) {
                continue;
            }
            List<Integer> edge = list.get(i - 1);
            for (int j = 0; j < n; j++) {
                if (i - 1 != j && computers[i - 1][j] == 1) {
                    edge.get(j + 1);
                }
            }
            System.out.println(list);
        }
    }
}