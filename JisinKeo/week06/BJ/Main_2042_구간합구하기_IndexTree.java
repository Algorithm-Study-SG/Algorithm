package JisinKeo.week06.BJ;

import java.io.*;
import java.util.*;

/**
 * 인덱스 트리 - 배열을 기반으로 동작
 * 메모리 : 93928 KB
 * 시간 : 728 ms
 */
public class Main_2042_구간합구하기_IndexTree {
    static long[] arr, tree;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

        arr = new long[N + 1];
        tree = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
            update(i, arr[i]);
        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(b, diff);
            } else {
                System.out.println(sum((int)c) - sum(b - 1));
            }
        }
    }

    static void update(int idx, long val) {
        while (idx <= N) {
            tree[idx] += val;
            idx += (idx & -idx);
        }
    }

    static long sum(int idx) {
        long result = 0;
        while (idx > 0) {
            result += tree[idx];
            idx -= (idx & -idx);
        }
        return result;
    }
}
