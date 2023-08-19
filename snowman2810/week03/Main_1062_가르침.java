package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//메모리 16476KB, 시간 344ms
public class Main_1062_가르침 {

	static String[] input;
	static boolean[] visited;
	static int K, max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		/*
		 * 남극의 모든 단어는 "anta"로 시작하고 "tica"로 끝나기 때문에
		 * 가르칠 단어의 수가 5개보다 적다면 바로 탈출한다
		 * 가르칠 단어의 수가 알파벳의 수와 같다면 바로 탈출한다
		 */
		if (K < 5) { 
			System.out.println("0");
			return;
		} else if (K == 26) {
			System.out.println(N);
			return;
		}
		input = new String[N];
		for (int i = 0; i < N; i++) {
			input[i] = br.readLine();
		}
		// 기본으로 배워야 하는 단어를 세팅
		visited = new boolean[26];
		visited['a' - 'a'] = true;
		visited['c' - 'a'] = true;
		visited['i' - 'a'] = true;
		visited['n' - 'a'] = true;
		visited['t' - 'a'] = true;
		K -= 5; // 찾아야하는 개수에서 5개를 빼준다
		search(0, 0);
		System.out.println(max);
		br.close();

	}

	public static void search(int start, int cnt) {
		if (cnt == K) { // K개의 선택이 끝나면
			int count = 0;
			for (int i = 0; i < input.length; i++) {
				boolean fail = false; // 가르친 단어의 조합으로 해당 단어가 완성이 안되는지 확인
				for (int j = 0; j < input[i].length(); j++) {
					// 단어의 j번째가 false로 방문하지 않았으면 fail을 true로 주고 탈출
					if (!visited[input[i].charAt(j) - 'a']) {
						fail = true;
						break;
					}
				}
				if (!fail) { // 완성이 되어야 count 증가
					count++;
				}
			}
			if (count > max) { // max값 확인
				max = count;
			}
			return;
		}
		for (int i = start; i < 26; i++) {
			if (!visited[i]) { // 중복체크
				visited[i] = true; //방문체크
				search(i, cnt + 1); 
				visited[i] = false;
			}
		}
	}
}