package Week09;

/**
 * 테스트 1 〉	통과 (2.63ms, 75.4MB)
 * 테스트 2 〉	통과 (1.90ms, 76.9MB)
 * 테스트 3 〉	통과 (1.75ms, 75.6MB)
 * 테스트 4 〉	통과 (1.19ms, 74.8MB)
 * 테스트 5 〉	통과 (1.55ms, 73.8MB)
 * 테스트 6 〉	통과 (2.00ms, 88.1MB)
 * 테스트 7 〉	통과 (1.44ms, 69.6MB)
 * 테스트 8 〉	통과 (4.89ms, 76.4MB)
 * 테스트 9 〉	통과 (1.34ms, 75.4MB)
 * 테스트 10 〉통과 (1.31ms, 74.7MB)
 * 테스트 11 〉통과 (1.26ms, 77.6MB)
 * 테스트 12 〉통과 (1.48ms, 74.5MB)
 * 테스트 13 〉통과 (1.74ms, 69.4MB)
 * 테스트 14 〉통과 (1.44ms, 75.4MB)
 * 테스트 15 〉통과 (1.76ms, 72.9MB)
 * 
 * # 접근 : 우선순위 큐
 * 
 * 			1. 곡 정보를 저장하는 클래스, 장르의 정보를 저장하는 클래스를 만든다.
 * 			2. 장르마다 곡을 우선순위 큐에 저장한다.
 */

import java.io.*;
import java.util.*;

class Solution_베스트앨범 {
    
    /* 곡 정보 저장 */
    class Song implements Comparable<Song> {
        int num;    // 고유 번호
        int play;   // 재생된 횟수
        
        public Song(int num, int play){
            this.num = num;
            this.play = play;
        }
        
        // 재생 횟수 내림차순, 고유 번호 오름차순
        public int compareTo(Song o){
            if(play == o.play){
                return num - o.num;
            }
            return o.play - play;
        }
    }
    
    /* 각 장르의 정보 저장 */
    class genreInfo{
        int totPlays;               // 각 장르의 총 재생 횟수
        PriorityQueue<Song> pq = new PriorityQueue<>();     // 장르에 해당하는 곡 정보 저장
        
        void setInfo(int plays, Song songInfo){
            totPlays += plays;      // 재생 횟수 추가
            pq.add(songInfo);       // 곡 정보 저장
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer;
        List<genreInfo> list = new ArrayList<>();           // 장르 정보 리스트
        HashMap<String, Integer> map = new HashMap<>();     // list에서 저장될 각 장르의 인덱스 저장
        int cnt = 0;                                        // 인덱스 저장
        
        for(int i = 0; i < genres.length; i++){
            if(!map.containsKey(genres[i])){
                map.put(genres[i], cnt++);                  // 새로운 장르의 인덱스 추가
                list.add(new genreInfo());                  // 새로운 장르의 정보 추가
            }
            // 곡 정보를 장르에 맞게 저장
            list.get(map.get(genres[i])).setInfo(plays[i], new Song(i, plays[i]));
        }
        
        // 총 재생 횟수 내림차순
        Collections.sort(list, ((o1, o2) -> o2.totPlays - o1.totPlays));
        
        // 베스트 앨범에 곡 수록
        List<Integer> ansList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            int cnt2 = 0;
            while(!list.get(i).pq.isEmpty() && cnt2 < 2){
                Song cur = list.get(i).pq.poll();
                ansList.add(cur.num);
                cnt2++;
            }
        }
        
        answer = new int[ansList.size()];
        for(int i = 0; i < ansList.size(); i++){
            answer[i] = ansList.get(i);
        }
        
        return answer;
    }
}