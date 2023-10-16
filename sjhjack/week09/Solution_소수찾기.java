package Week09;

/**
 * 테스트 1 〉	통과 (0.82ms, 84.8MB)
 * 테스트 2 〉	통과 (16.27ms, 81.5MB)
 * 테스트 3 〉	통과 (0.35ms, 74.2MB)
 * 테스트 4 〉	통과 (8.76ms, 74.9MB)
 * 테스트 5 〉	통과 (123.93ms, 94.4MB)
 * 테스트 6 〉	통과 (0.40ms, 79.5MB)
 * 테스트 7 〉	통과 (0.58ms, 76.3MB)
 * 테스트 8 〉	통과 (110.49ms, 83.4MB)
 * 테스트 9 〉	통과 (0.30ms, 77.8MB)
 * 테스트 10 〉통과 (18.10ms, 81.4MB)
 * 테스트 11 〉통과 (8.68ms, 74.8MB)
 * 테스트 12 〉통과 (4.98ms, 76.2MB)
 * 
 * # 접근 : 순열
 * 
 * 			1. 순열을 통해 가능한 모든 숫자 생성
 * 			2. 1부터 생성한 숫자의 최대값까지의 모든 숫자를 소수 판별
 * 				-> 숫자들이 모두 클 경우 유리하다고 생각했음
 * 			3. 생성한 숫자들의 소수 판별
 */

import java.io.*;
import java.util.*;

class Solution_소수찾기 {
      
    int[] arr;										// 종이 조각에 적혀있는 숫자 저장
    boolean[] isVisited;							// dfs visit 배열
    List<Integer> list = new ArrayList<>();			// 만들 수 있는 숫자 리스트
    Set<Integer> set = new HashSet<>();				// 숫자 중복 체크
    
    void dfs(int[] tmp, int cnt){
        if(cnt > 0){								// 최소 1 ~ 최대 종이 조각 개수만큼 길이의 숫자 생성
            int num = 0;
            for(int i = 0; i < cnt; i++){
                num = num * 10 + tmp[i];			// 숫자 생성
            }

            if(!set.contains(num)){					// 중복 체크
                set.add(num);
                list.add(num);
            }
        }
        
        if(cnt == arr.length){						// 최대 길이에 도달
            return;
        }
        
        for(int i = 0; i < arr.length; i++){		// 순열 생성
            if(isVisited[i]) continue;
            isVisited[i] = true;
            
            tmp[cnt] = arr[i];
            dfs(tmp, cnt+1);
            
            isVisited[i] = false;
        }
    }
    
    public int solution(String numbers) {
        int answer = 0;
        
        arr = new int[numbers.length()];
        isVisited = new boolean[numbers.length()];
        
        for(int i = 0; i < numbers.length(); i++) {
            arr[i] = numbers.charAt(i) - '0';			// 종이 조각에 적혀있는 숫자 저장
        }
        
        dfs(new int[arr.length], 0);					// 만들 수 있는 모든 숫자 생성
        
        Collections.sort(list);							// 오름차순 정렬
        
        int max = list.get(list.size() - 1);
        boolean[] isNotPrime = new boolean[max + 1];	// false : 소수
        
        // 0 ~ max 값 소수 판별
        isNotPrime[0] = true;
        isNotPrime[1] = true;
        for(int i = 2; i < Math.sqrt(max); i++){
            for(int j = i*2; j <= max; j+=i){
                isNotPrime[j] = true;
            }
        }
        
        // 생성한 숫자가 소수인지 확인
        for(int num : list){
            if(!isNotPrime[num]){
                answer++;
            }
        }
        
        return answer;
    }
}