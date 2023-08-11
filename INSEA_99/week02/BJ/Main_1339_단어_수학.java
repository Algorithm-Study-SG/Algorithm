package INSEA_99.week02.BJ;

import java.io.*;
import java.util.*;

/***
 * 단어 수학 문제는 N개의 단어로 이루어져 있으며, 각 단어는 알파벳 대문자로만 이루어져 있다. 
 * 이때, 각 알파벳 대문자를 0부터 9까지의 숫자 중 하나로 바꿔서 N개의 수를 합하는 문제이다. 
 * 같은 알파벳은 같은 숫자로 바꿔야 하며, 두 개 이상의 알파벳이 같은 숫자로 바뀌어지면 안 된다.
 * 
 * N개의 단어가 주어졌을 때, 그 수의 합을 최대로 만드는 프로그램을 작성하시오.
 * @author 인바다
 *
 */

public class Main_1339_단어_수학{
	static private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static private BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(System.out));
	static private StringTokenizer st;
	
	static int N, maxN;	// 단어 수, 합 최대
	static String word;	// 단어
	static Integer prior[] = new Integer[27]; // 문자별 자리수에 따른 개수 
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		Arrays.fill(prior, 0);	
		
		for(int i = 0; i < N; ++i) {
			word = br.readLine();
			int idx = 0;
			for (int digit = word.length() - 1; digit >=0; --digit) 
				prior[word.charAt(idx++) - 'A'] += (int)Math.pow(10, digit);	// 문자별 자리수에 따른 개수
		}
		Arrays.sort(prior, Collections.reverseOrder());		// 우선순위 내림차순 정렬
		int weight = 9;
		for(int p : prior) maxN += p*weight--;		// 우선순위에 따른 가중치 합
		bw.write(maxN+"");
		bw.flush();
		bw.close();
	}
}
