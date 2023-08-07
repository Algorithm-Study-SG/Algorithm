package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1182_부분수열의_합 {

	static int n = 0;
	static int goal = 0;
	static int[] input;
	static int ans = 0;
	static boolean select[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk=new StringTokenizer(br.readLine());

		n = Integer.parseInt(stk.nextToken());
		goal = Integer.parseInt(stk.nextToken());
		input = new int[n];
		select=new boolean[n];
		stk=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			input[i]=Integer.parseInt(stk.nextToken());
		}
		
		search(0, 0);

		System.out.println(ans);

		br.close();
	}

	public static void search(int depth, int loc) {
		for (int i = loc; i < n; i++) {
			if(!select[i]) {
				select[i]=true;
				search(depth+1, i);
				select[i]=false;				
			}
		}
		int sum=0;
		boolean notZero=true;
		for(int i=0;i<n;i++) {
			if(select[i]) {
				sum+=input[i];
				notZero=false;
			}
		}
		if (!notZero && sum == goal) {
			ans++;
			return;
		}
		if(depth==n) {
			return;
		}
	}
}