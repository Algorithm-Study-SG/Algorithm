package JisinKeo.week03.BJ;

import java.io.*;
import java.util.*;

/**
 * 메모리 : 56488 KB
 * 시간 : 240 ms
 */
public class Main_1039_교환 {
    static String n;
    static int k, result;

    static boolean[][] visited;

    /**
     * 주어진 두 위치의 숫자를 교환하는 함수
     * @param i 첫 번째 위치
     * @param j 두 번째 위치
     */
    public static void swap(int i, int j){
        char[] numArr = n.toCharArray(); // 문자열을 문자 배열로 변환
        char temp = numArr[i];
        numArr[i] = numArr[j];
        numArr[j] = temp;
        n = new String(numArr); // 문자 배열을 다시 문자열로 변환
    }

    /**
     * 숫자의 자리를 교환하며 최대값을 찾는 DFS 함수
     * @param v 현재까지의 교환 횟수
     */
    public static void DFS(int v) {
        // 교환 횟수가 k에 도달하면 현재 숫자를 최대값과 비교
        if(v == k){
            result = Math.max(result, Integer.parseInt(n));
            return;
        }

        // 모든 숫자의 자릿수에 대해 교환을 시도
        for(int i = 0; i < n.length() - 1 ; i++){
            for(int j = i+1; j < n.length(); j++){
                // 맨 앞자리가 0이 되면 안되므로 continue로 스킵
                if (i == 0 && n.charAt(j) == '0') continue;

                swap(i, j);

                int temp = Integer.parseInt(n);

                if(!visited[temp][v]){ // 현재 숫자(num)로 v+1번 교환한 상태가 아니면 다음 교환 수행
                    visited[temp][v] = true;
                    DFS(v+1);
                }

                swap(i, j);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = st.nextToken();
        k = Integer.parseInt(st.nextToken());

        result = -1;

        visited = new boolean[1000001][11];  // 숫자의 최대값이 1000000이고 k의 최대값이 10이므로

        DFS(0);

        System.out.println(result);
    }
}
