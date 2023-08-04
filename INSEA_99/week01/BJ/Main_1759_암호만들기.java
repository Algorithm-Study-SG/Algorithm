package INSEA_99.week01.BJ;

import java.io.*;
import java.util.*;

public class Main_1759_암호만들기 {
	static int L, C;	// 암호 길이, 알파벳 개수
	static List<String> PW = new ArrayList<>();	// 완성된 암호
	static char arr[];	// 알파벳 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; ++i) arr[i] = st.nextToken().charAt(0);
		Arrays.sort(arr); // 알파벳 오름차순 정렬
		
		// 순차적으로 완전 탐색
		making(0, 0, 0, 0, "");
		PW.sort(Comparator.naturalOrder());	// 암호 오름차순 정렬
		for(String pw: PW) System.out.println(pw);	// 암호 출력
	}
	
	/***
	 * 암호를 완전 탐색으로 만들어 감
	 * 
	 * @param l: 현재 암호 길이
	 * @param idx: 현재 알파벳 사용할 알파펫 index
	 * @param gatherN: 암호에 있는 모음 수
	 * @param consonantN: 암호에 있는 자음 수
	 * @param pw: 현재 만들어진 암호
	 */
	public static void making(int l, int idx, int gatherN, int consonantN, String pw) {
		if(l == L) {
			if(gatherN >= 1 && consonantN >= 2) PW.add(pw);
		}
		else if(idx == C) return;
		else {
			making(l, idx+1, gatherN, consonantN, pw);
			if(isGather(arr[idx])) gatherN++;
			else consonantN++;
			pw = pw.concat(Character.toString(arr[idx]));
			making(l+1, idx+1, gatherN, consonantN, pw);
		}
	}
	
	// 모음이면 true, 아니면 false return
	public static boolean isGather(char x) {
		if(x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') return true;
		return false;
	}
}
