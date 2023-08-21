package INSEA_99.week03.BJ;

import java.io.*;
import java.util.*;

// 240 ms

public class Main_1039_교환 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, k, len, ans = -1;	// 시작 정수, 연산 수, 정수 길이, 정답
    static boolean visited[][]; // 연산별 정수 방문 여부 확인
    
    public static void main(String[] args) throws IOException {
        input();	// 입력 받기
        visited[n][0] = true;	// dfs
        dfs(n, 0);
        bw.write(ans + "");
        bw.flush();
        bw.close();
     }

    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());	// 시작 정수
        k = Integer.parseInt(st.nextToken());	// 연산 수
        len = (n + "").length();	// 시작 정수 길이
        visited = new boolean[1000001][k+1];	// 연산별 정수 방문 여부 확인
    }

    static void dfs(int now, int idx){
    	if(idx == k) {	// 연산 다 했으면
    		ans = ans > now ? ans : now;	// 큰 값 저장
    		return;
    	}
    	for(int i = 0; i < len - 1; ++i) {	// 교환 위치
    		for(int j = i + 1; j < len; ++j) {
    			int next = swap(now, i, j);	// 교환 후
    			if(next != -1 && !visited[next][idx + 1]) {	// 0이 아니거나 방문 하지 않았을 경우
    				visited[next][idx + 1] = true;
    				dfs(next, idx + 1);	// 계속 탐색
    			}
    		}
    	}
    }
    
    private static int swap(int x, int i, int j) {
        StringBuilder sb = new StringBuilder();
        sb.append(x);
        if(i==0 && sb.charAt(j)=='0') return -1;
        
        char tmp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, tmp);
        return Integer.parseInt(sb.toString());
    }
}