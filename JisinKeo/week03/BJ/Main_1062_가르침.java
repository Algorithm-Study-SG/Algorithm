package JisinKeo.week03.BJ;

import java.io.*;
import java.util.*;

public class Main_1062_가르침 {

    static int n, k, result;

    static String[] word;

    static boolean[] checked = new boolean[26];

    // a : 3, c : 1, i : 1, n : 1, t : 2

    public static void DFS(int v, int start){
        if(v == k){
            int count = 0;
            for(int t = 0; t < n; t++){
                boolean isAllChecked = true;
                for(int i = 0; i < word[t].length(); i++){
                    if(!checked[word[t].charAt(i) - 'a']) {
                        isAllChecked = false; // 한 문자라도 checked 배열에 없으면 false로 설정
                        break; // 해당 단어의 체크를 중단
                    }
                }
                if (isAllChecked) {
                    count++;
                }
            }
            if(count > result){
                result = count;
            }
            return;
        }
        for(int i = start; i < 26; i++){
            if(!checked[i]){
                checked[i] = true;
                DFS(v+1, i);
                checked[i] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (k < 5) { // k가 5보다 작으면 바로 0 출력 후 종료
            System.out.println(0);
            return;
        } else if (k == 26) { // k가 26이면 모든 단어를 읽을 수 있음
            System.out.println(n);
            return;
        }

        k -= 5; // 기본 문자 5개는 제외하고 나머지 글자 수를 카운트

        word = new String[n];

        for(int i = 0; i < n; i++){
            String temp = br.readLine();
            word[i] = temp.substring(4, temp.length() - 4); // "anta"와 "tica" 제외
        }

        checked[0] = true; // checked['a' - 'a']
        checked['n' - 'a'] = true;
        checked['t' - 'a'] = true;
        checked['i' - 'a'] = true;
        checked['c' - 'a'] = true;

        DFS(0, 0);

        System.out.println(result);

    }

}
