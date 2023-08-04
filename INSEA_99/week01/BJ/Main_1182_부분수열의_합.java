package INSEA_99.week01.BJ;

import java.io.*;
import java.util.*;

public class Main_1182_부분수열의_합 {
	static int N, S, ans;
	static List<Integer> arr = new ArrayList<>();;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i)
			arr.add(Integer.parseInt(st.nextToken()));
			
		search(0, 0);
		System.out.println(ans);
	}
	public static void search(int s, int idx) {
		if(idx == arr.size()) return;
		if(s + arr.get(idx)== S) ans++;
		search(s, idx+1);
		search(s + arr.get(idx), idx+1);
	}
}