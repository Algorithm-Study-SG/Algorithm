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
            words[i] = br.readLine();
        }

        alphabet = new int[26];

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

        for(int i = 25; i >= 0; i--){
            if(alphabet[i] == 0) break;
            result += (alphabet[i] * num);
            num--;
        }

        System.out.println(result);
    }
}