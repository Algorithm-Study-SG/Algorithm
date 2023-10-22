package Week10;

/**
 *	테스트 1 〉	통과 (0.05ms, 85.4MB)
 *	테스트 2 〉	통과 (0.05ms, 75.5MB)
 *	테스트 3 〉	통과 (0.12ms, 75.7MB)
 *	테스트 4 〉	통과 (0.43ms, 73.7MB)
 *	테스트 5 〉	통과 (1.38ms, 73.5MB)
 *	테스트 6 〉	통과 (0.81ms, 73MB)
 *	테스트 7 〉	통과 (3.51ms, 74.3MB)
 *	테스트 8 〉	통과 (1.08ms, 76.4MB)
 *	테스트 9 〉	통과 (8.49ms, 73.6MB)
 *	테스트 10 〉	통과 (4.40ms, 86MB)
 *	테스트 11 〉	통과 (23.82ms, 80MB)
 *	테스트 12 〉	통과 (9.49ms, 77.4MB)
 *	테스트 13 〉	통과 (38.03ms, 83.9MB)
 *	테스트 14 〉	통과 (33.54ms, 92.7MB)
 *	테스트 15 〉	통과 (7.98ms, 68MB)
 *	테스트 16 〉	통과 (5.82ms, 87.1MB)
 *	테스트 17 〉	통과 (0.13ms, 75.2MB)
 *	테스트 18 〉	통과 (2.58ms, 78MB)
 *	테스트 19 〉	통과 (0.05ms, 77MB)
 *	테스트 20 〉	통과 (0.04ms, 75.6MB)
 *
 *  # 접근 : 중복 순열
 */

import java.io.*;
import java.util.*;

class Solution_이모티콘할인행사 {
    
    final int[] discount = {10, 20, 30, 40};
    int[] discPerm;         // 각 이모티콘에 적용할 할인율 저장
    int[][] userCopy;       // users 배열 얕은 복사
    int[] emoticonCopy;     // emoticons 배열 얕은 복사
    
    int userCnt, emtCnt, price, count;
    
    /* 각 이모티콘에 적용할 할인율 중복 순열로 구하기 */
    void permutation(int cnt){
        if(cnt == emtCnt){            
            calc();
            return;
        }
        
        for(int i = 0; i < 4; i++){
            discPerm[cnt] = discount[i];
            permutation(cnt + 1);
        }
    }
    
    /* 이모티콘 판매 가격 계산 */
    void calc(){
        int tmp = 0, sum = 0, cnt = 0;		
        int[] userPay = new int[userCnt];	// 각 이용자가 낼 가격
        
        for(int i = 0; i < emtCnt; i++){
            // 부동소수점 형변환에 조심하자
            // tmp = (int)(emoticonCopy[i] * (1 - 0.01 * discPerm[i]));	// -> Failed..
        	
        	// 각 이모티콘에 할인가 적용 후 가격
            tmp = (emoticonCopy[i] * (100 - discPerm[i])) / 100;		
            
            // 각 이용자 별 기준에 따른 구매 비용 합산
            for(int j = 0; j < userCnt; j++){
                if(userCopy[j][0] > discPerm[i]) continue;   
                userPay[j] += tmp;
            }
        }
        
        for(int i = 0; i < userCnt; i++){
            if(userPay[i] >= userCopy[i][1]){
                cnt++;
            } else {
                sum += userPay[i];
            }
        }
        
        if(count < cnt){
            count = cnt;
            price = sum;
        } else if (count == cnt){
            if(price < sum){
                price = sum;
            }
        }
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        userCnt = users.length;
        emtCnt = emoticons.length;
        
        discPerm = new int[emtCnt];
        userCopy = users;
        emoticonCopy = emoticons;
        
        permutation(0);
        
        return new int[]{count, price};
    }
}