import java.util.*;

class Solution_43162_네트워크 {

    
    // 각 대표자를 지정할 배열
    int[] parents;
    
    public int solution(int n, int[][] computers) {
        // 공간 할당
        parents=new int[n+1];
        // 자기 자신을 대표하도록 초기화
        fill(n);
        
        // 들어오는 인접 행렬의 정보를 이용해서 유니온 파인드를 해준다
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(computers[i-1][j-1]==1){
                    union(i,j);
                }
            }
        }
        // 중복을 제외한 개수를 구하기 위해서 set생성
        Set<Integer> set=new HashSet<>();
        for(int i=1;i<parents.length;i++){
            // 대표자를 set에 입력
            set.add(find(parents[i]));
        }
        // 사이즈 리턴
        int answer = set.size();
        return answer;
    }
    
    // 대표자가 다르다면 같은 대표자를 가르키도록 합치기
    void union(int a,int b){
        if(find(a) != find(b)){
            parents[find(a)] = find(b);
        }
    }
    
    // 대표자를 찾는 과정
    int find(int a){
        if(parents[a] == a){
            return a;
        }
        return parents[a]=find(parents[a]);
    }
    
    // 초기값 세팅
    void fill(int n){
        for(int i=1;i<=n;i++){
            parents[i]=i;
        }
    }
    
}
