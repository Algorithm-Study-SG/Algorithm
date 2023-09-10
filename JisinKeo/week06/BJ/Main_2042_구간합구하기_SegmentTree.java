package JisinKeo.week06.BJ;

import java.io.*;
import java.util.*;

/**
 * 세그먼트 트리 - 이진 트리 형태
 * 메모리 : 117268 KB (세그먼트 트리는 4배의 메모리를 필요로 해서 IndexTree 풀이보다 메모리가 크다)
 * 시간 : 752 ms
 */
public class Main_2042_구간합구하기_SegmentTree {
    static long[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

        arr = new long[N + 1];
        tree = new long[N * 4]; // 세그먼트 트리의 크기는 원래 배열의 4배가 될 수 있음

        // 배열 값 초기화
        for (int i = 1; i <= N; i++) { // 연산을 쉽도록 하기 위해 인덱스를 1부터
            arr[i] = Long.parseLong(br.readLine());
        }

        init(1, 1, N);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(1, 1, N, b, diff);
            } else {
                System.out.println(sum(1, 1, N, b, (int)c));
            }
        }
    }

    // 세그먼트 트리 초기화 함수
    static long init(int node, int start, int end) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
    }

    // 세그먼트 트리 업데이트 함수
    static void update(int node, int start, int end, int idx, long diff) {
        if (idx < start || idx > end) return;
        tree[node] += diff; // 해당 원소를 포함하고 있는 모든 구간 합 노드 갱신
        if (start != end) {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, idx, diff);
            update(node * 2 + 1, mid + 1, end, idx, diff);
        }
    }

    // 구간 합 쿼리 함수
    static long sum(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
    }
}
