package JisinKeo.week01.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15686_치킨배달 {

    static int n, m;
    static int[][] chicken;

    static ArrayList<int[]> home, shop;
    static int answer;

    public static void close(int v, ArrayList<int[]> chosenShops){

        if(chosenShops.size() == m){
            int sum = 0;
            for(int[] h : home){
                int minDistance = Integer.MAX_VALUE;
                for(int[] shop : chosenShops) {
                    minDistance = Math.min(minDistance, Math.abs(shop[0] - h[0]) + Math.abs(shop[1] - h[1]));
                }
                sum += minDistance;
            }
            System.out.println(sum);
            answer = Math.min(answer, sum);
            return;
        }

        if(v == shop.size()) return;

        chosenShops.add(shop.get(v));
        close(v+1, chosenShops);
        chosenShops.remove(shop.get(v));
        close(v+1, chosenShops);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        home = new ArrayList<>();
        shop = new ArrayList<>();

        chicken = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                chicken[i][j] = Integer.parseInt(st.nextToken());
                if(chicken[i][j] == 1) home.add(new int[]{i, j});
                if(chicken[i][j] == 2) shop.add(new int[]{i, j});
            }
        }

        answer = Integer.MAX_VALUE;

        close(0, new ArrayList<>());

        System.out.println(answer);
    }
}
