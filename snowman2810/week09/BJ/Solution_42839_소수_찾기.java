import java.util.*;

// 정확성  테스트
// 테스트 1 〉	통과 (321.33ms, 99.4MB)
// 테스트 2 〉	통과 (186.66ms, 104MB)
// 테스트 3 〉	통과 (325.05ms, 86.4MB)
// 테스트 4 〉	통과 (193.16ms, 85.7MB)
// 테스트 5 〉	통과 (186.89ms, 104MB)
// 테스트 6 〉	통과 (201.00ms, 83.9MB)
// 테스트 7 〉	통과 (205.90ms, 101MB)
// 테스트 8 〉	통과 (204.42ms, 108MB)
// 테스트 9 〉	통과 (198.96ms, 88.7MB)
// 테스트 10 〉	통과 (194.90ms, 86.4MB)
// 테스트 11 〉	통과 (219.56ms, 94.4MB)
// 테스트 12 〉	통과 (203.82ms, 86.2MB)

class Solution_42839_소수_찾기 {

    // 에라스토테네스의 체
    static boolean[] notPrime=new boolean[10000000];
    static boolean[] visited;
    
    // 구해진 숫자들의 조합을 저장
    static Set<Integer> set=new HashSet<>();
    static int length = 0;
    
    public int solution(String numbers) {
        length=numbers.length();
        visited=new boolean[length];
        
        fillPrime();
        
        // 들어오는 String형 데이터를 int배열로 전환
        int[] number=new int[length];
        char[] temp=numbers.toCharArray();
        for(int i=0;i<length;i++){
            number[i]=temp[i]-'0';
        }
        
        // 길이(i)에 해당하는 모든 순열을 구해준다
        for(int i=1; i<=length; i++){
            for(int j=0;j<length;j++){
                if(number[j]!=0){
                    visited[j]=true;
                    search(number,0,1,j,i);
                    visited[j]=false;
                }
            }
        }
        
        // 구해진 숫자들이 소수인지를 판별해서 정답을 저장
        int answer = 0;
        for(int i : set){
            if(!notPrime[i]){
                answer++;
            }
        }
        
        return answer;
    }
    
    // 에라스토테네스의 체 완성하기
    void fillPrime(){
        notPrime[0]=true;
        notPrime[1]=true;
        notPrime[4]=true;
        for(int i=2;i<=3162;i++){
            int count=2;
            while(i * count < 10000000){
                notPrime[i * count]=true;
                count++;
            }
        }
    }
    
    // 길이에 해당하는 수들의 조합을 구한다
    void search(int[] numbers, int sum, int count, int loc, int maxLength){
        sum += numbers[loc] * (int) Math.pow(10, count-1);
        
        // 최대 길이에 도달하면 set에 저장
        if(count == maxLength){
            set.add(sum);
            return;
        }
        
        // 최대 길이가 아니라면 방문하 적이 없는 수를 선택
        for(int i=0;i<length;i++){
            if(!visited[i]){
                visited[i]=true;
                search(numbers, sum, count+1, i, maxLength);
                visited[i]=false;
            }
        }
        
    }
    
    
}
