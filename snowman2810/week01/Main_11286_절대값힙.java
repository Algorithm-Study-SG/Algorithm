package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_11286 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Queue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1)==Math.abs(o2)) {
					return o1-o2;
				}else {
					return Math.abs(o1) - Math.abs(o2);
				}
			}
		});
		
		int n = Integer.parseInt(br.readLine());
		int input[] = new int[n];

		for (int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < n; i++) {
			if (input[i] != 0) {
				queue.add(input[i]);
			} else if (input[i] == 0 && queue.size() == 0) {
				sb.append("0\n");
			} else {
				sb.append(queue.poll()).append("\n");
			}
		}

		System.out.println(sb.toString());

		br.close();

	}

}