import java.util.*;

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
