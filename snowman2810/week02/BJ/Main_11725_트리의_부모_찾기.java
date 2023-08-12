package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_11725_트리의_부모_찾기 {

	static List<List<Integer>> list = new ArrayList<>(); // 입력값을 저장할 리스트 선언
	static boolean selected[]; // 방문 여부를 체크할 배열 선언
	static int ans[]; // 출력할 값들을 저장할 배열 선언

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		selected = new boolean[n + 1]; // n까지 들어오는 값을 편하게 저장하기 위해서 n+1만큼 선언
		ans = new int[n + 1]; // n까지 출력해야하는 값을 편하게 저장하기 위해서 n+1만큼 선언

		for (int i = 0; i < n + 1; i++) { // n까지 들어오는 값을 편하게 저장하기 위해서 n+1만큼 선언
			list.add(new ArrayList<>()); // 2중 리스트를 이용하기위해 초기화 진행
		}

		for (int i = 0; i < n - 1; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());

			int left = Integer.parseInt(stk.nextToken()); // 왼쪽 값
			int right = Integer.parseInt(stk.nextToken()); // 오른쪽 값

			/*
			 * 1 6	->	list의 1번 위치에 6을 더해준다, list의 6번 위치에 1을 더해준다
			 * 6 3	->	list의 6번 위치에 3을 더해준다, list의 3번 위치에 6을 더해준다
			 * 3 5	->	list의 3번 위치에 5을 더해준다, list의 5번 위치에 3을 더해준다
			 * 4 1	->	list의 4번 위치에 1을 더해준다, list의 1번 위치에 4을 더해준다
			 * 2 4	->	list의 2번 위치에 4을 더해준다, list의 4번 위치에 2을 더해준다
			 * 4 7	->	list의 4번 위치에 7을 더해준다, list의 7번 위치에 4을 더해준다
			 * 
			 * 결과는
			 * 
			 * list.get(1) -> [6, 4]
			 * list.get(2) -> [4]
			 * list.get(3) -> [6, 5]
			 * list.get(4) -> [1, 2, 7]
			 * list.get(5) -> [3]
			 * list.get(6) -> [1, 3]
			 * list.get(7) -> [4]
			 * 
			 */
			
			list.get(left).add(right);
			list.get(right).add(left);

		}

		search(1); // 루트 노드는 1이기 때문에 시작값을 1로 준다

		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < ans.length; i++) { // i가 2부터 시작하는 이유는 0은 안쓰는 값이고 1은 부모가 없는 루트이기 때문에
			sb.append(ans[i]).append("\n");
		}
		System.out.print(sb.toString());

		br.close();
	}

	public static void search(int number) {
		selected[number] = true; // 방문한 지점을 표시해준다

		for (int i : list.get(number)) { // forEach를 이용해서 list.get(number)의 값들을 꺼낸다
			if (!selected[i]) { // 방문여부체크
				selected[i] = true; // 방문체크
				search(i); // 부모부터 자식으로 쭉 탐색하기
				ans[i] = number; // 들어온 값이 부모가 되고 해당 값과 연결된 값들이 자식이 된다
			}
		}
	}
}
