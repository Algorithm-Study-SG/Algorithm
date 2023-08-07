package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15686_치킨배달 {

	static int m = 0; // 목표 점포 갯수
	static int ans = Integer.MAX_VALUE; // 최소 거리값

	static List<Integer[]> store_list = new ArrayList<Integer[]>(); // 점포의 위치 저장 리스트
	static List<Integer[]> home_list = new ArrayList<Integer[]>(); // 집의 위치 저장 리스트

	static boolean selected[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input_string = br.readLine();
		StringTokenizer stk = new StringTokenizer(input_string);

		int n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());

		for (int i = 0; i < n; i++) {
			input_string = br.readLine();
			stk = new StringTokenizer(input_string);
			for (int j = 0; j < n; j++) {
				int input = Integer.parseInt(stk.nextToken());
				if (input == 1) {
					home_list.add(new Integer[] { i + 1, j + 1 });
				} else if (input == 2) {
					store_list.add(new Integer[] { i + 1, j + 1 });
				}
			}
		}

		selected = new boolean[store_list.size()];

		search(0, 0);

		System.out.println(ans);
		br.close();
	}

	public static void search(int depth, int loc) {
		if (depth == m) {
			List<Integer[]> current = new ArrayList<>();
			for (int i = 0; i < store_list.size(); i++) {
				if (selected[i]) {
					current.add(store_list.get(i));
				}
			}
			int min_list[] = new int[home_list.size()];
			for (int i = 0; i < current.size(); i++) {

				int store_x = current.get(i)[0];
				int store_y = current.get(i)[1];

				for (int j = 0; j < home_list.size(); j++) {

					int home_x = home_list.get(j)[0];
					int home_y = home_list.get(j)[1];

					int cal_x = Math.abs(store_x - home_x);
					int cal_y = Math.abs(store_y - home_y);

					if (min_list[j] == 0) {
						min_list[j] = cal_x + cal_y;
					} else if (min_list[j] > cal_x + cal_y) {
						min_list[j] = cal_x + cal_y;
					}
				}
			}
			int sum = 0;
			for (int i : min_list) {
				sum += i;
			}
			if (sum < ans) {
				ans = sum;
			}
			return;
		}
		for (int i = loc; i < store_list.size(); i++) { // 매장 선택이 안끝났으면 다시 호출
			if (!selected[i]) {
				selected[i] = true;
				search(depth + 1, i);
				selected[i] = false;
			}
		}
	}
}