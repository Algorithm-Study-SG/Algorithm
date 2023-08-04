package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1759_암호만들기 {

	static int l = 0;
	static int c = 0;
	static String[] input;
	static List<String> check_list=new ArrayList<>();
	static StringBuilder sb=new StringBuilder();
	static boolean select[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		check_list.add("a");
		check_list.add("e");
		check_list.add("i");
		check_list.add("o");
		check_list.add("u");
		StringTokenizer stk=new StringTokenizer(br.readLine());
		l = Integer.parseInt(stk.nextToken());
		c = Integer.parseInt(stk.nextToken());
		select=new boolean[c];
		input = new String[c];
		stk=new StringTokenizer(br.readLine());
		for(int i=0;i<c;i++) {
			input[i]=stk.nextToken();
		}
		Arrays.sort(input);
		search(0,0);
		System.out.print(sb.toString());
		br.close();
	}

	public static void search(int depth,int loc) {
		if(depth==l) {
			int must=0;
			for(int i=0;i<c;i++) {
				if(select[i]) {
					if(check_list.contains(input[i])) {
						must++;
					}
				}
			}
			if(must>=1 && l-must>=2) {
				for(int i=0;i<c;i++) {
					if(select[i]) {
						sb.append(input[i]);
					}
				}
				sb.append("\n");
			}
			return;
		}
		for(int i=loc;i<c;i++) {
			if(!select[i]) {
				select[i]=true;
				search(depth+1,i);
				select[i]=false;				
			}
		}
	}
}