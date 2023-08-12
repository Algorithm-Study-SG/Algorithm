package Week2;

import java.util.*;
import java.io.*;

public class Main_1339_단어수학 {
	
	static int ans = 0;
	static List<Character> alpha = new ArrayList<>();	// 알파벳 저장
	static char [][] words;								// 단어 저장
	static Set<Character> checkAlpha = new HashSet<>();	// 알파벳 중복 체크
	static boolean [] isVisited = new boolean [10];    	// 숫자 순열을 위한 방문 체크
	static int [] numPermut;							// 숫자 순열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		words = new char[N][];
		
		for(int i = 0; i < N; i++) {	// 단어 저장
			String s = br.readLine();
			words[i] = s.toCharArray();
		}
		
		for(int i = 0; i < N; i++) {	// 알파벳 중복 체크 및 저장
			for(int j = 0; j < words[i].length; j++) {
				if(checkAlpha.contains(words[i][j])) {
					continue;
				} else {
					checkAlpha.add(words[i][j]);
					alpha.add(words[i][j]);
				}
			}
		}
		
		numPermut = new int[alpha.size()];		// 알파뱃 개수 길이의 순열 저장
		select(0);
		System.out.println(ans);
		
	}
	
	static void select(int cnt) {
		if(cnt == alpha.size()) {
			checkSum();							// 단어의 합 계산
			return;
		}
		
		for(int i = 0; i < isVisited.length; i++) {	// 0 ~ 9
			if(isVisited[i] == true) continue;
			
			isVisited[i] = true;
			numPermut[cnt] = i;
			select(cnt+1);
			isVisited[i] = false;
		}
	}
	
	static void checkSum() {	// 단어의 합 계산
		int num, sum = 0;
		
		for(int i = 0; i < words.length; i++) {	// 각 단어를 숫자로 변경
			num = 0;
			for(int j = 0; j < words[i].length; j++) {	// 단어 하나 변경
				num = num*10 + find(words[i][j]);
			}
			sum += num;
		}
		
		ans = sum > ans ? sum : ans;
	}
	
	static int find(char c) {	// 알파벳에 해당하는 숫자 리턴
		for(int i = 0; i < alpha.size(); i++) {
			if(alpha.get(i).equals(c)) {
				return numPermut[i];
			}
		}
		
		return 0;
	}

}