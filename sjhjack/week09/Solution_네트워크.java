package Week09;

/**
 * 테스트 1 〉  통과 (0.05ms, 87.9MB)
 * 테스트 2 〉  통과 (0.05ms, 78.5MB)
 * 테스트 3 〉  통과 (0.09ms, 79.5MB)
 * 테스트 4 〉	  통과 (0.08ms, 78.3MB)
 * 테스트 5 〉  통과 (0.04ms, 75.9MB)
 * 테스트 6 〉  통과 (0.20ms, 72.6MB)
 * 테스트 7 〉  통과 (0.06ms, 80MB)
 * 테스트 8 〉  통과 (0.13ms, 85.6MB)
 * 테스트 9 〉	  통과 (0.14ms, 80MB)
 * 테스트 10 〉 통과 (0.11ms, 69.5MB)
 * 테스트 11 〉 통과 (0.34ms, 74.4MB)
 * 테스트 12 〉 통과 (0.29ms, 80.8MB)
 * 테스트 13 〉 통과 (0.20ms, 85.8MB)
 * 
 * # 접근 : BFS
 * 
 */

import java.io.*;
import java.util.*;

class Solution_네트워크 {
    
    int BFS(int n, int[][] arr){
        boolean[] isVisited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        int cnt = 0;	// 네트워크 개수
        
        for(int i = 0; i < n; i++){
            if(isVisited[i]) continue;
            isVisited[i] = true;
            q.add(i);
            cnt++;
            
            // i번째 컴퓨터와 같은 네트워크에 속한 컴퓨터 탐색
            while(!q.isEmpty()){
                int cur = q.poll();
                
                for(int j = 0; j < n; j++){
                    if(isVisited[j] || arr[cur][j] == 0) continue;
                    isVisited[j] = true;
                    q.add(j);
                }
            }
        }
        
        return cnt;
    }
    
    public int solution(int n, int[][] computers) throws IOException {
        int answer = BFS(n, computers);
        
        return answer;
    }
}