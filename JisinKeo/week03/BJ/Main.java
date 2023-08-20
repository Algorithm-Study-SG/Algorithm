package JisinKeo.week03.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static String n;
    static int k, result;

    static boolean[][] visited;

    public static void swap(int i, int j){
        char[] numArr = n.toCharArray();
        char temp = numArr[i];
        numArr[i] = numArr[j];
        numArr[j] = temp;
        n = new String(numArr);
    }

    public static void DFS(int v){
        if(v == k){
            result = Math.max(result, Integer.parseInt(n));
            return;
        }

        for(int i = 0; i < n.length() - 1 ; i++){
            for(int j = i+1; j < n.length(); j++){

                if (i == 0 && n.charAt(j) == '0') continue;

                swap(i, j);

                int temp = Integer.parseInt(n);

                if(!visited[temp][v]){
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

        visited = new boolean[1000001][11];

        DFS(0);

        System.out.println(result);
    }
}
