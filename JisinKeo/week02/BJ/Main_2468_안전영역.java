package JisinKeo.week02.BJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SimpleTimeZone;
import java.util.StringTokenizer;

public class Main_2468_안전영역 {

    static int n, result;

    static int[][] water, checked;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Node{
        int x;
        int y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    public static void BFS(int i, int j, int height){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(i, j));

        while(!queue.isEmpty()){
            Node node = queue.poll();

            for(int k = 0; k < 4; k++){
                int nx = node.x + dx[k];
                int ny = node.y + dy[k];
                if(nx >= 0 && nx < n && ny >= 0 && ny < n){
                    if(checked[nx][ny] == 0 && water[nx][ny] > height){
                        checked[nx][ny] = 1;
                        queue.offer(new Node(nx, ny));
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        water = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n ; j++){
                water[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max_height = 0;
        int min_height = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                max_height = Math.max(max_height, water[i][j]);
                min_height = Math.min(min_height, water[i][j]);
            }
        }

        for(int height = min_height; height <= max_height; height++){
            checked = new int[n][n];
            int count = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(checked[i][j] == 0 && water[i][j] > height){
                        checked[i][j] = 1;
                        BFS(i, j, height);
                        count++;
                    }
                }
            }
            result = Math.max(result, count);
        }

        if(result == 0) result = 1;
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
