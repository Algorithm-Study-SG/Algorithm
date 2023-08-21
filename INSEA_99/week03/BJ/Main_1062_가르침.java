package INSEA_99.week03.BJ;

// 736 ms

import java.io.*;
import java.util.*;
public class Main_1062_가르침 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, k, ans, cnt; // 단어 수, 글자 수, 답, 조합 별 읽을 수 있는 단어 수
    static boolean[] visited = new boolean[30];	// 글자 방문 여부 저장
    static List<List<Integer>> word = new ArrayList<>();	// 글자 정보

    public static void main(String[] args) throws IOException{
        input();
        if(k < 5) ans = 0;	// anta로 시작, tica로 끝나는게 불가능 한 경우
        else back(0, 0);
        bw.write(ans +"");
        bw.flush();
        bw.close();
    }

    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());	// 단어 수
        k = Integer.parseInt(st.nextToken());	// 사용할 글자 수
        for(int i = 0; i < n; ++i){	// 단어 입력 받기
            String w = br.readLine();
            word.add(new ArrayList<>());
            for(int j = 0; j < w.length(); ++j){
                word.get(i).add(w.charAt(j) - 'a');
            }
        }
        int[] visitredNum = {0, 2, 8, 13, 19};	// a, n, t, i, c 방문으로 처리
        for(int num : visitredNum){
            visited[num] = true;
        }
    }

    static void back(int depth, int now){	// 글자 수를 다 선택할 때까지 조합 탐색
        if(depth == k - 5){
            cnt = 0;
            for(int i = 0; i < n; ++i) count(i);	// 읽을 수 있는 단어 counting
            ans = ans > cnt ? ans : cnt;
        }
        else{
            for(int i = now; i < 27; ++i){	// 이번에 사용할 알파벳 
                if(visited[i]) continue;
                visited[i] = true;
                back(depth + 1, i);
                visited[i] = false;
            }
        }
    }

    static void count(int n){	// 읽을 수 있는 단어 counting
        for(int elem : word.get(n)){	
            if(!visited[elem]) return;	// 사용하지 않은 글자인 경우 return
        }
        cnt ++;
    }
}