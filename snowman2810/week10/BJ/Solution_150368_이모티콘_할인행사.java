import java.util.*;

// 정확성  테스트
// 테스트 1 〉	통과 (0.12ms, 74.6MB)
// 테스트 2 〉	통과 (0.09ms, 81.9MB)
// 테스트 3 〉	통과 (0.21ms, 80.3MB)
// 테스트 4 〉	통과 (0.99ms, 72.4MB)
// 테스트 5 〉	통과 (1.06ms, 76MB)
// 테스트 6 〉	통과 (0.89ms, 73.6MB)
// 테스트 7 〉	통과 (3.25ms, 78.5MB)
// 테스트 8 〉	통과 (1.05ms, 74.1MB)
// 테스트 9 〉	통과 (8.71ms, 76.3MB)
// 테스트 10 〉	통과 (4.37ms, 79.7MB)
// 테스트 11 〉	통과 (33.58ms, 93.4MB)
// 테스트 12 〉	통과 (10.93ms, 78.4MB)
// 테스트 13 〉	통과 (58.38ms, 83.5MB)
// 테스트 14 〉	통과 (72.03ms, 89.9MB)
// 테스트 15 〉	통과 (8.99ms, 75.6MB)
// 테스트 16 〉	통과 (14.39ms, 84.2MB)
// 테스트 17 〉	통과 (0.14ms, 71.1MB)
// 테스트 18 〉	통과 (2.32ms, 77.4MB)
// 테스트 19 〉	통과 (0.07ms, 75.7MB)
// 테스트 20 〉	통과 (0.06ms, 75.5MB)

class Solution_150368_이모티콘_할인행사 {

    static int maxService;  // 최대 가입자수
    static int maxSum;      // 최대 금액
    static int[] emo;       // 이모티콘
    static int count;       // 이모티콘의 개수
    static int[] discount;  // 할인율
    static int[][] user;    // 사용자
    static int length;      // 사용자의 수

    public int[] solution(int[][] users, int[] emoticons) {
        init(users, emoticons);

        search(0, 0);

        int[] answer = { maxService, maxSum };
        return answer;
    }

    // 초기세팅
    void init(int[][] users, int[] emoticons) {
        maxService = 0;
        maxSum = 0;
        user = users;
        emo = emoticons;
        count = emo.length;
        length = user.length;
        discount = new int[emo.length];
        Arrays.fill(discount, 40);
    }

    void search(int start, int service) {

        // 탐색시 마다 현재 가입자의 수와 금액을 탐색한다
        int nowService = 0;
        int nowSum = 0;
        for (int i = 0; i < length; i++) {
            int nowMoney = 0;
            for (int j = 0; j < count; j++) {
                if (discount[j] >= user[i][0]) {
                    nowMoney += emo[j] * (100 - discount[j]) / 100;
                }
            }
            if (nowMoney >= user[i][1]) {
                nowService++;
            } else {
                nowSum += nowMoney;
            }
        }

        // 가입자의 최고 수치가 갱신이 된다면 값들을 수정
        if (nowService > maxService) {
            maxService = nowService;
            maxSum = nowSum;
        } else if (nowService == maxService) {
            // 가입자가 같은 경우 금액을 수정
            maxSum = Math.max(maxSum, nowSum);
        }

        // 할인폭을 수정하면서 탐색
        for (int i = start; i < count; i++) {
            if (discount[i] >= 20) {
                discount[i] -= 10;
                search(i, nowService);
                discount[i] += 10;
            }
        }

    }

}
