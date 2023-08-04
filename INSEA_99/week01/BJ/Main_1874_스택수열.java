package INSEA_99.week01.BJ;

import java.io.*;
import java.util.*;

public class Main_1874_스택수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		List<Integer> list = new ArrayList<>();	// 만들어야 할 수열
		List<String> ans = new ArrayList<>(); // +- 결과 저장
		Deque<Integer> dq = new ArrayDeque<>();	// 큐
		
		int n = Integer.parseInt(br.readLine());
		for(int i = 1; i <= n; ++i) // 만들어야 할 수열 입력 받기
			list.add(Integer.parseInt(br.readLine()));
		
		int idx = 0;	// 현재 만들어야 할 수열 index
		int i = 0;		// stack에 추가할 수
		while(i < n) {
			while(++i <= list.get(idx)) {	// 만들어야 할 수열 숫자보다 stack에 추가할 수가 적은 경우 stack에 추가
				ans.add("+");
				dq.addLast(i);
			}
			i--;	
			while(!dq.isEmpty()) {	
				int x = dq.getLast();	// 스택에서 가장 위 수
				if(x > list.get(idx)) {		// 스택에서 얻을 수가 현재 만들어야 할 수열 수보다 크면 실패
					System.out.println("NO");
					return;
				}
				if(x < list.get(idx)) break;	// 스택에서 얻을 수가 현재 만들어야 할 수열 수보다 작으면 스택에 수 추가
				dq.removeLast();	// 스택에서 얻을 수가 현재 만들어야 할 수열 수와 같으면 얻기
				ans.add("-");
				idx++;	// 만들어야 할 수열 다음 수
			}
		}
		for(String x : ans) System.out.println(x);
	}
}
