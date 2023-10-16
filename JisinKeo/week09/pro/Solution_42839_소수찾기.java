package JisinKeo.week09.pro;

import java.io.*;
import java.util.*;

class Solution_42839_소수찾기 {

    static Set<Integer> set = new HashSet<>();

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void DFS(String temp, String numbers) {
        if (!temp.equals("")) {
            int num = Integer.parseInt(temp);
            if (isPrime(num)) {
                set.add(num);
            }
        }

        for (int i = 0; i < numbers.length(); i++) {
            DFS(temp + numbers.charAt(i), numbers.substring(0, i) + numbers.substring(i + 1));
        }
    }

    public int solution(String numbers) {
        DFS("", numbers);
        return set.size();
    }
}
