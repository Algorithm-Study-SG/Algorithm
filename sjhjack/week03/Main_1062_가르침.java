package Week03;

/**
 * 메모리 : 21508 KB
 * 시간 : 2212 ms
 * 
 * # 접근 1 : 가장 많은 단어에 포함된 알파뱃부터 배운다.
 * 				-> 각 알파뱃이 1개씩만 필요한 경우 반례가 생긴다.
 * 			<반례 예시>
 * 			3 7
 *			antawxtica
 * 			antaytica
 * 			antaztica
 * 			정답: 2
 * 			출력: 1
 * 
 * # 접근 2 : 입력받은 단어를 읽기 위해 필요한 알파뱃에서 acint를 제외하고 배울 알파뱃 조합 생성
 * 				-> 시간을 줄이기 위해 Next Permutation 활용
 * 				-> 재귀로 조합 생성했으면 시간 초과 예상됨
 * 
 * 비트마스킹이나 백트래킹을 사용하면 시간이 줄어들 것 같으나 아이디어가 떠오르지 않음
 */

import java.io.*;
import java.util.*;

public class Main_1062_가르침 {

	static int N, K;
	static Set<Character> antatica = new HashSet<>();	// anta tica 중복 체크 용도
	static int[][] alpha = new int [26][2];				// 각 알파뱃의 개수 체크
	static Set<Character>[] words;						// 단어 저장
	static Set<Character> totalSet = new HashSet<>();	// 모든 단어를 읽기 위해 배워야 하는 알파뱃 목록 저장
	static int ans;
	
	static void init() {
		antatica.add('a');
		antatica.add('c');
		antatica.add('i');
		antatica.add('n');
		antatica.add('t');
		
		for(int i = 0; i < 26; i++) {
			alpha[i][0] = i;	// [0] : 알파뱃 번호, [1] : 알파뱃 개수
		}
	}
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		words = new Set[N];

		for(int i = 0; i < N; i++) {
			words[i] = new HashSet<>();
			String s = br.readLine();
			for(int j = 4; j < s.length()-4; j++) {	// anta tica 를 제외한 나머지만 입력 받음
				char c = s.charAt(j);
				if(antatica.contains(c) || words[i].contains(c)) continue;	// anta tica 에 포함되거나 단어에서 중복되는 알파뱃 제외
				
				totalSet.add(c);					// 필요한 모든 알파뱃 저장
				words[i].add(c);					// 단어 저장
				alpha[c-'a'][1]++;					// 알파뱃 개수 카운트
			}
			if(words[i].size() == 0) ans++;			// 다른 알파뱃 안배워도 읽을 수 있는 경우 (ex) antatica
		}
	}
	
	static void sol() {
		
		Arrays.sort(alpha, (o1, o2) -> o2[1]-o1[1]);	// 포함된 단어가 많은 알파뱃 순서로 정렬
		
		int[] select = new int[totalSet.size()];	// 입력받은 모든 단어의 알파뱃 개수
		for(int i = 0; i < K-5; i++) {				// 알파뱃 a c i n t는 무조건 배워야 하므로 K-5개
			select[select.length-1-i] = 1;			// totalSet.size() 중에서 K-5개의 알파뱃을 뽑는 조합 필요
		}
		
		do {
			int totCnt = 0;	// 현재 선택된 알파뱃 조합으로 읽을 수 있는 단어 개수 카운트
			for(int i = 0; i < words.length; i++) {			// 각 단어가
				int cnt = 0;
				for(int j = 0; j < select.length; j++) {	// np로 선택된 알파뱃 조합으로 읽을 수 있는지 확인
					if(select[j] == 1 && words[i].contains((char)(alpha[j][0]+'a'))) {
						cnt++;
					}
					if(cnt == words[i].size()) {	// 현재 단어를 읽을 수 있는 경우
						totCnt++;
						break;
					}
				}
			}
			ans = totCnt > ans ? totCnt : ans;
		} while(np(select));	// 알파뱃 조합 탐색
	}
	
	static boolean np(int[] arr) {	// Next Permutation 조합으로 활용
		int N = arr.length;
		
		int i = N-1;
		while(i>0 && arr[i-1]>=arr[i]) --i;	// 뒤에서부터 꼭대기 찾기
		
		if(i == 0) return false;	// 모든 경우의 수 찾았음
		
		int j = N-1;
		while(arr[i-1] >= arr[j]) --j;
		
		swap(arr, i-1, j);
		
		int k = N-1;
		while(i<k) swap(arr, i++, k--);
		
		return true;
	}
	
	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		init();
		input();
		
		if(K < 5) {	// 단어를 아무것도 읽을 수 없는 경우
			System.out.print(0);
			return;
		}			// 모든 단어를 읽을 수 있는 경우
		if(totalSet.size() <= K-5){
			System.out.print(N);
			return;
		}
		
		sol();

		System.out.print(ans);
		
	}

}
