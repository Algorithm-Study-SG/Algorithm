package JisinKeo.week03.BJ;

import java.io.*;
import java.util.*;

public class Main_1039_교환 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int temp = n;
        int digits = 0;

        while(temp != 0){
            temp /= 10;
            digits++;
        }

        if(digits < k){
            System.out.println("-1");
            System.exit(0);
        }

        int[] arr = new int[digits];

        temp = n;
        int index = digits - 1;

        while(temp != 0){
            arr[index] = temp % 10;
            temp /= 10;
            index--;
        }

        for(int t = 0; t < k; t++){

            int minIndex = 0, maxIndex = 0;

            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < arr[minIndex]) {
                    minIndex = i;
                }
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }

            temp = arr[minIndex];
            arr[minIndex] = arr[maxIndex];
            arr[maxIndex] = temp;

            System.out.println(Arrays.toString(arr));
        }



    }
}
