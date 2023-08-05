package JisinKeo.week01.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_11286_절댓값힙 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> numbers = new PriorityQueue<>((o1, o2) -> {
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);
            if (abs1 == abs2)
                return o1 > o2 ? 1 : -1;
            return abs1 - abs2;
        });

        for(int i = 0; i < n; i++){
            int x = Integer.parseInt(br.readLine());
            if(x == 0){
                if(numbers.isEmpty()){
                    System.out.println(0);
                } else {
                    int cur = numbers.remove();
                    System.out.println(cur);
                }

            } else {
                numbers.add(x);
            }
        }
    }

}
