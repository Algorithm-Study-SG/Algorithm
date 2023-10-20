package INSEA_99.week10;

/*
 * idea : 할인율 순열로 계산 완탐
 * 
 * 테스트 1 〉	통과 (0.05ms, 66.7MB)
 * 테스트 2 〉	통과 (0.05ms, 73MB)
 * 테스트 3 〉	통과 (0.17ms, 76.6MB)
 * 테스트 4 〉	통과 (0.56ms, 76.4MB)
 * 테스트 5 〉	통과 (1.02ms, 73.2MB)
 * 테스트 6 〉	통과 (0.66ms, 78.4MB)
 * 테스트 7 〉	통과 (2.77ms, 86.4MB)
 * 테스트 8 〉	통과 (1.27ms, 75.6MB)
 * 테스트 9 〉	통과 (6.05ms, 76.2MB)
 * 테스트 10 〉	통과 (3.11ms, 73.9MB)
 * 테스트 11 〉	통과 (20.77ms, 86MB)
 * 테스트 12 〉	통과 (9.83ms, 79.1MB)
 * 테스트 13 〉	통과 (44.11ms, 88.9MB)
 * 테스트 14 〉	통과 (41.19ms, 87.9MB)
 * 테스트 15 〉	통과 (6.83ms, 75.1MB)
 * 테스트 16 〉	통과 (6.01ms, 75.3MB)
 * 테스트 17 〉	통과 (0.27ms, 76.4MB)
 * 테스트 18 〉	통과 (2.29ms, 77.9MB)
 * 테스트 19 〉	통과 (0.07ms, 79.2MB)
 * 테스트 20 〉	통과 (0.04ms, 77.8MB)
 * 
 */

class Solution {
    static final int[] DISCOUNT = {10, 20, 30, 40};
    static int[][] USERS;
    static int[] EMOTICONS;
    
    static int maxPlusUsers = 0;
    static int maxTotalSales = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        USERS = users;
        EMOTICONS = emoticons;
        
        perm(0, new int[emoticons.length]);	
        answer[0] = maxPlusUsers;
        answer[1] = maxTotalSales;
        return answer;
    }
    
    /**
     * 할인율 순열
     * @param n		결정된 할인율 개수
     * @param rates	할인율
     */
    public void perm(int n, int[] rates) { 
        if(n == EMOTICONS.length) {
            cal(rates);
            return;
        }
        for(int i = 0; i < 4; ++i) {
            rates[n] = DISCOUNT[i];
            perm(n + 1, rates);
        }
    }
    
    /**
     * 할인율 바탕으로 플러스 서비스 가입자 수, 판매액 계산하여 max값과 비교
     * @param rates	할인율
     */
    public void cal(int[] rates) {
        int plusUsers = 0;
        int totalSales = 0;
        int[] sales = new int[USERS.length];
        
        for(int userIdx = 0; userIdx < USERS.length; ++userIdx) {
            for(int emoticonIdx = 0; emoticonIdx< EMOTICONS.length; ++emoticonIdx) {
                if(USERS[userIdx][0] <= rates[emoticonIdx]) {
                    int addCost = EMOTICONS[emoticonIdx] * (100 - rates[emoticonIdx]) / 100;
                    if(sales[userIdx] + addCost < USERS[userIdx][1]) {
                        sales[userIdx] += addCost;
                        totalSales += addCost;
                    } else {
                        plusUsers++;
                        totalSales -= sales[userIdx];
                        break;
                    }
                }
            }
        }
        if(maxPlusUsers == plusUsers) {
            maxTotalSales  = Math.max(maxTotalSales, totalSales); 
        } else if(maxPlusUsers < plusUsers) {
            maxPlusUsers = plusUsers;
            maxTotalSales = totalSales;
        }
    }
    
}
