package study.boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main_1874_스택수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		Stack<Integer> stack = new Stack<>();
		int n = Integer.parseInt(br.readLine());
		int input[] = new int[n];
		int count = 0;
		int number = 0;
		StringBuilder sb = new StringBuilder();
		boolean check[] = new boolean[n];
		boolean err = false;
		for (int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}

		while (count < n) {
			if (input[count] > number) {
				number++;
				if (!check[number - 1]) {
					stack.add(number);
					sb.append("+\n");
				}
				check[number - 1] = true;
			} else if (input[count] == number) {
				if (stack.size() == 0) {
					err = true;
					break;
				}
				stack.pop();
				sb.append("-\n");
				number--;
				count++;
			} else if (input[count] < number) {
				if (stack.size() == 0) {
					err = true;
					break;
				}
				if (stack.peek() == input[count]) {
					count++;
				}
				stack.pop();
				sb.append("-\n");
				number--;
			} else {
				err = true;
				break;
			}
		}
		if (err) {
			System.out.println("NO");
		} else {
			System.out.println(sb.toString());
		}

		bw.close();
		br.close();

	}

}