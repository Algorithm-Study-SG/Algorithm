package JisinKeo.week03.BJ;

import java.io.*;
import java.util.Arrays;

/**
 * 메모리 : 25952 KB
 * 시간 : 336 ms
 */
public class Main_2447_별찍기 {

    static int N;

    static char[][] result;

    public static void calculate(int n, int i, int j){

        if (n < 3){ // n == 1과 같다
            result[i][j] = '*';
            return;
        }
        calculate(n / 3, i, j);
        calculate(n / 3, i, j + n / 3);
        calculate(n / 3, i, j + 2 * n / 3);
        calculate(n / 3, i + n / 3, j);
        // calculate(n / 3, i + n / 3, j + n / 3); 가운데는 빈 곳이므로 이것을 빼야 한다
        calculate(n / 3, i + n / 3, j + 2 * n / 3);
        calculate(n / 3, i + 2 * n / 3, j);
        calculate(n / 3, i + 2 * n / 3, j + n / 3);
        calculate(n / 3, i + 2 * n / 3, j + 2 * n / 3);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        result = new char[N][N];

        for(int i = 0; i < N; i++){
            Arrays.fill(result[i], ' ');
        }

        calculate(N, 0, 0);

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                bw.write(result[i][j]);
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }
}
