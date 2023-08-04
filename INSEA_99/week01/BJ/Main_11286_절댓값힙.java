package INSEA_99.week01.BJ;

import java.io.*;
import java.util.*;

public class Main_11286_절댓값힙{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 우선순위 적용한 큐
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				if(Math.abs(a) == Math.abs(b)) return a > b ? 1 : -1;
				return Math.abs(a) > Math.abs(b) ? 1 : -1;
			}
		});
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			int cmd = Integer.parseInt(br.readLine());
			if(cmd != 0) pq.add(cmd);
			else {
				if(pq.size() == 0) System.out.println(0);
				else System.out.println(pq.poll());
			}
		}
	}
}
