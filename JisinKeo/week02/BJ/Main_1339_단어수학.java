package JisinKeo.week02.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1339_단어수학 {

    static int n;

    static int[] alphabet;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        String[] words = new String[n];

        for(int i = 0; i < n; i++) {
            words[i] = br.readLine(); // 단어를 입력받는다
        }

        alphabet = new int[26]; // 자릿수를 저장할 배열

        /**
         * 아래 for문을 통해 해당 알파벳의 자릿수를 카운트한다.
         * 예시 : ABC가 있으면 alphabet[0]에 100, alphabet[1]에 10, alphabet[2]에 1이 저장된다.
         */
        for(int i = 0; i < n; i++){
            int digit = 1;
            for(int j = words[i].length() - 1; j >= 0; j--){
                int cache = words[i].charAt(j) - 'A';
                alphabet[cache] += digit;
                digit *= 10;
            }
        }

        Arrays.sort(alphabet);

        int result = 0;

        int num = 9;

        /**
         * 자릿수가 큰 순서대로
         * 9부터 해서 0까지, 차례대로 값을 할당한다.
         */

        for(int i = 25; i >= 0; i--){
            if(alphabet[i] == 0) break;
            result += (alphabet[i] * num);
            num--;
        }

        System.out.println(result);
    }
}
