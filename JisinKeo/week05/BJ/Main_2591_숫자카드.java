package JisinKeo.week05.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2591_숫자카드 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String number = br.readLine();

        int len = number.length();
        int[] dp = new int[len];

        dp[0] = 1; // 첫 번째 경우는 무조건 1개

        // 문자열의 길이가 2 이상일 경우, 두 번째 위치의 문자를 확인
        if (len > 1) {
            int twoDigits = Integer.parseInt(number.substring(0, 2));
            // 두 번째 문자까지를 합쳤을 때 10에서 34 사이면 가능한 카드 배열이 존재
            if (twoDigits >= 10 && twoDigits <= 34) {
                dp[1] = 1;
            }
            // 두 번째 문자가 0이 아니면 첫 번째 문자의 경우의 수를 더합니다
            if (number.charAt(1) != '0') {
                dp[1] += dp[0];
            }
        }

        // 세 번째 문자부터 마지막 문자까지 반복
        for (int i = 2; i < len; i++) {
            // 현재 문자가 0이 아니면 이전 문자의 경우의 수를 더합니다
            if (number.charAt(i) != '0') {
                dp[i] += dp[i - 1];
            }
            int twoDigits = Integer.parseInt(number.substring(i - 1, i + 1));
            // 현재 문자와 이전 문자를 합쳤을 때 10에서 34 사이면 가능한 카드 배열이 존재
            if (twoDigits >= 10 && twoDigits <= 34) {
                dp[i] += dp[i - 2];
            }
        }

        // 마지막 위치의 경우의 수를 출력
        System.out.println(dp[len - 1]);
    }
}