package JisinKeo.week08.BJ;

import java.io.*;
import java.util.*;

/**
 * 메모리 : 14228 KB
 * 시간 : 128 ms
 */
public class Main_1744_수묶기 {

    static int n;

    static int[] seq;

    static List<Integer> positive = new ArrayList<>(); // 양수만 저장할 배열
    static List<Integer> negative = new ArrayList<>(); // 0, 음수만 저장할 배열

    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        seq = new int[n];
        checked = new boolean[n];

        for(int i = 0; i < n; i++){
            int temp = Integer.parseInt(br.readLine());
            if(temp > 0){
                positive.add(temp);
            } else {
                negative.add(temp);
            }
        }

        int sum = 0;
        int i = 0;

        int positive_size = positive.size(); // 양수의 개수
        int negative_size = negative.size(); // 음수의 개수

        Collections.sort(positive, Collections.reverseOrder()); // 양수는 큰 수부터
        Collections.sort(negative); // 음수는 작은 수부터 (이래야 앞 두 개를 곱했을 때 가장 큼)

        while(i < positive_size){
            if(i+1 < positive_size && positive.get(i) != 1 && positive.get(i+1) != 1){
                // 두 개의 수를 곱할 수 있으면
                sum += positive.get(i) * positive.get(i+1);
                i += 2;
            } else {
                sum += positive.get(i);
                i++;
            }
        }

        i = 0;

        while(i < negative_size){
            if(i+1 < negative_size){
                sum += negative.get(i) * negative.get(i+1); // 곱하면 양수
                i += 2;
            } else {
                sum += negative.get(i);
                i++;
            }
        }

        System.out.println(sum); // 양수 배열, 음수 배열 각각 연산해서 더한 것
    }

}