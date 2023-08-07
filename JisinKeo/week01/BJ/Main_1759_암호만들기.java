package JisinKeo.week01.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1759_암호만들기 {

    static int l, c;

    static char[] list;

    static char[] result;

    static StringBuilder sb;

    public static void DFS(int v, int i){
        if(v == l){
            int consonant_count = 0; // 모음
            int vowel_count = 0; // 자음
            for(int k = 0; k < l; k++){
                if(result[k] == 'a' || result[k] == 'e' || result[k] == 'i' || result[k] == 'o' || result[k] == 'u'){
                    consonant_count++;
                }
                else vowel_count++;
            }
            sb = new StringBuilder();
            if(consonant_count >= 1 && vowel_count >= 2){
                for(int k = 0; k < l; k++){
                    sb.append(result[k]);
                }
                System.out.println(sb);
            }
            return;
        }
        if(i == c) return;
        result[v] = list[i];
        DFS(v+1, i+1);
        DFS(v, i+1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        list = new char[c];

        for(int i = 0; i < c; i++){
            list[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(list);

        result = new char[l];

        for(int i = 0; i <= c - l; i++){
            int v = 0;
            result[v] = list[i];
            DFS(v+1, i+1);
        }

    }
}
