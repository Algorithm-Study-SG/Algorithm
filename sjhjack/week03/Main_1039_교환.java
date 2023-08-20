package Week03;

/**
 * 메모리 : 22616 KB
 * 시간 : 164 ms
 * 
 * 접근 1 : 그리디
 * 			가장 큰 숫자가 젤 앞에 와야 한다는 생각.
 * 			반례들을 찾아가며 해결해보려 했지만, 더 이상 반례를 찾지 못하고 실패
 * 
 * 접근 2 : BFS
 * 			처음에는 BFS를 어떻게 적용할지 아이디어가 떠오르지 않다가, 그림으로 경우의 수를 그려보며 깨달음
 * 			N을 1번 교환할 수 있는 경우의 수는 M.
 * 			M가지 경우의 수를 N의 자식 노드라고 생각하고 BFS 진행.
 * 			시간을 줄이기 위해 중복된 값은 탐색하지 않음.
 * 			교환이 K번 일어난 모든 경우가 최종적으로 queue에 담겨있다.(깊이 K)
 * 			queue의 값중 가장 큰 값이 답이 된다.
 * 
 *  ++ 해당 코드에서 cnt 변수를 추가하여 사용하면 1%에서 메모리 초과가 뜬다.
 *     cnt를 사용한 경우, 52, 57, 64, 66 line의 코드가 사용되고
 *                    64, 68 line은 사용되지 않는다.
 *                    
 *     시간 초과를 일으킬 만큼 큰 차이인가?
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1039_교환 {
	
	static int[] isVisited = new int [1000001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int len = 0, n = N;
		while(n>0) {			// 자리수 구하기
			n = n/10;
			len++;
		}
		
		if((len==1) || (len==2 && N%10==0)) {	// 한자리수, 두자리수 교환 불가능한 경우 제외
			System.out.print(-1);
			return;
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(N);
		
//		int cnt = 0;
		while(K>0 && !queue.isEmpty()) {
			int size = queue.size();			
			for(int s = 0; s < size; s++) {		// 현재 단계에서 얻을 수 있는 모든 숫자에 대해 탐색
				int cur = queue.poll();
//				cnt++;
				// 현재 숫자에서 교환했을때 얻을 수 있는 모든 경우의 수 탐색
				for(int i = 0; i < len-1; i++) {
					for(int j = i+1; j < len; j++) {	// 문제의 조건이 i<j 이므로 j=i+1 시작
						int next = swap(cur, i, j);
						// 맨 앞이 0, 현재 단계에서 중복인 경우 continue;
						if(next == -1 || isVisited[next] == K) continue;		
//						if(next == -1 || isVisited[next] == cnt) continue;	
						
//						isVisited[next] = cnt;
						isVisited[next] = K;
						queue.add(next);
					}
				}
			}
			K--;
		}
		
		if(queue.isEmpty()) System.out.print(-1);
		else {
			int max = 0;
			while(!queue.isEmpty()) {
				int x = queue.poll();
				max = x > max ? x : max;
			}
			
			System.out.print(max);
		}
		
	}
	
	static int swap(int next, int i, int j) {
		char[] cur = String.valueOf(next).toCharArray();
		char tmp = cur[i];
		cur[i] = cur[j];
		cur[j] = tmp;
		
		if(cur[0] == '0') return -1;
		
		int n = 0;
		for(int k = 0; k < cur.length; k++) {
			n = n*10  + (cur[k]-'0');
		}
		return n;
		
	}

}
