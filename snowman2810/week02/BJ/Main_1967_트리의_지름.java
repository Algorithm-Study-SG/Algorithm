package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1967_트리의_지름 {

	static int n;
	static int max = 0;
	static int maxLoc = 0; // 첫 탐색에서 가장 값이 큰 노드를 저장

	static boolean selected[]; // 방문여부 확인을 위한 변수
	static List<List<Node>> list = new ArrayList<>(); // 입력받은 값을 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		selected = new boolean[n + 1];
		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < n - 1; i++) {
			stk = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(stk.nextToken());
			int child = Integer.parseInt(stk.nextToken());
			int value = Integer.parseInt(stk.nextToken());
			list.get(parent).add(new Node(child, value));
			list.get(child).add(new Node(parent, value));
		}

		search(1, 0); // 루트 노드에서 탐색을 진행

		selected = new boolean[n + 1]; // 방문여부를 초기화

		search(maxLoc, 0); // 루트 노드에서 탐색을 했을 때 가장 큰 값을 지닌 노드에서 탐색

		System.out.println(max);

		br.close();
	}

	public static void search(int number, int sum) {
		if (max < sum) { // sum값이 max보다 크면 해당 값과 위치를 저장
			max = sum;
			maxLoc = number;
		}
		selected[number] = true; // 방문체크
		for (Node node : list.get(number)) { // 해당 노드에서 움직일 수 있으면 실행
			if (!selected[node.getNumber()]) { // 이미 방문했으면 넘김
				search(node.getNumber(), sum + node.getValue()); // 방문할 노드를 알려주고 sum을 넘겨준다
			}
		}
	}

	public static class Node {
		private int number;
		private int value;

		public Node(int number, int value) {
			this.number = number;
			this.value = value;
		}

		public int getNumber() {
			return number;
		}

		public int getValue() {
			return value;
		}

	}

}
