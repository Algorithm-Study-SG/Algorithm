import java.util.*;

class Solution_42579_베스트앨범 {
    public int[] solution(String[] genres, int[] plays) {
        
        // 장르를 인덱스로 전환하는 맵
        Map<String,Integer> genreToLoc=new HashMap<>();
        
        // 장르의 인덱스로 장르별 재생 횟수를 저장하는 맵
        Map<Integer,Integer> genrelocAndPlay=new HashMap<>();
        
        // 가장 큰 값을 구하기 위해 우선순위큐를 이용
        PriorityQueue<Song>[] pqs=new PriorityQueue[101];
        
        // 장르별로 다른 인덱스를 주기 위해서 생성
        int index=0;
        for(int i=0;i<genres.length;i++){
            
            // 들어온 장르가 없다면
            if(!genreToLoc.containsKey(genres[i])){
                
                // 장르와 인덱스를 추가
                genreToLoc.put(genres[i],index);
                
                // 우선순위큐를 초기화
                pqs[index]=new PriorityQueue<>((o1,o2)->{
                    return o2.play-o1.play;
                });
                
                // 인덱스 증가
                index++;
            }
            // 장르에 재생 횟수를 누적
            genrelocAndPlay.put(genreToLoc.get(genres[i]),genrelocAndPlay.getOrDefault(genreToLoc.get(genres[i]),0)+plays[i]);
            
            // 장르별 pq에 추가
            pqs[genreToLoc.get(genres[i])].offer(new Song(plays[i], i));
            
        }
        
        // 장르별 누적 재생 횟수를 정렬하기 위해서 생성
        List<genre> list = new ArrayList<>();
        
        // 리스트에 추가
        for(Map.Entry<Integer,Integer> e : genrelocAndPlay.entrySet()){
            list.add(new genre(e.getKey(), e.getValue()));
        }
        
        // 정렬
        list.sort((o1,o2)->{
            return o2.play-o1.play;
        });
        
        // 정답을 순차적으로 저장할 큐 생성
        Queue<Integer> ans=new ArrayDeque<>();
        
        // 정렬된 장르의 인덱스에 존재하는 우선순위큐에서 값을 빼와서 저장한다
        for(genre g : list){
            ans.offer(pqs[g.loc].poll().index);
            if(!pqs[g.loc].isEmpty()){
                ans.offer(pqs[g.loc].poll().index);
            }
        }
        
        // 리턴할 배열 선언
        int[] answer = new int[ans.size()];
        
        // answer에 저장될 위치를 계산하기 위해 생성
        int location=0;
        
        // answer배열에 값 저장
        while(!ans.isEmpty()){
            answer[location]=ans.poll();
            location++;
        }
        
        return answer;
    }
    
    class genre{
        int loc;
        int play;
        
        public genre(int loc, int play){
            this.loc=loc;
            this.play=play;
        }
    }
    
    class Song{
        int play;
        int index;
        
        public Song(int play, int index){
            this.play=play;
            this.index=index;
        }
        
    }
    
}
