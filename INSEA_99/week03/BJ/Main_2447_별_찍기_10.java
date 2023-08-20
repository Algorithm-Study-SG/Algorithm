package INSEA_99.week03.BJ;

import java.io.*;
import java.util.*;

public class Main_2447_별_찍기_10 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;


    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine()); // 크기 입력 받기
        List<String> ans =star(n);  // 결과 저장
        for(String line : ans){ // 결과 출력
            bw.write(line + '\n');
        }
        bw.flush();
        bw.close();
    }

    static List<String> star(int n) {
        if (n == 3) {   // 가장 작은 단위면 가장 작은 단위 반환
            return Arrays.asList("***", "* *", "***");
        }
        List<String> arr = star(n / 3);
        List<String> ans = new ArrayList<>();   // 정답 저장할 list
        for (String x : arr) {  // 이 전 단위 세번씩 반복
            String tempStar = "";
            for (int i = 0; i < 3; ++i) tempStar += x;
            ans.add(tempStar);
        }
        for (String x : arr) {  // 이 전 단위 공백 포함 반복
            String gap = "";
            for (int i = 0; i < n / 3; ++i) gap += " ";
            ans.add(x + gap + x);
        }
        for (String x : arr) {  // 이 전 단위 세번씩 반복
            String tempStar = "";
            for (int i = 0; i < 3; ++i) tempStar += x;
            ans.add(tempStar);
        }
        return ans;
    }
}