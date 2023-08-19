package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//메모리 14256KB, 시간 140ms
public class Main_1039_교환{

	static int K, ans, start = 0;
	static int[] N; // 들어온 숫자를 담을 배열
	static boolean dupl; // 같은값을 가진 수가 2개가 되는지 확인

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		String tempN = stk.nextToken();
		K = Integer.parseInt(stk.nextToken());
		N = new int[tempN.length()];
		boolean zero = false; // 들어온 값중에 0 포함여부 확인
		int[] count = new int[10]; // 같은 값이 들어왔는지 확인하기 위한 배열
		for (int i = 0; i < tempN.length(); i++) {
			// char형의 문자를 string으로 바꾼 후 int형으로 바꿔서 배열에 저장
			N[i] = Integer.parseInt(Character.toString(tempN.charAt(i)));
			if (N[i] == 0) { // 0이 포함되었는지 여부 확인
				zero = true;
			}
			count[N[i]]++; // 해당 숫자의 값 증가
		}
		dupl = false; // 같은 값의 중복여부 확인
		for (int i = 0; i < 10; i++) {
			if (count[i] >= 2) { // 같은 숫자가 2번이상 나오면
				dupl = true; // 중복체크
				break;
			}
		}
		// 교환을 못하는 조건인 길이가 1일때와 길이가 2면서 0이 포함되있는지 확인
		if (N.length == 1 || (N.length == 2 && zero)) {
			System.out.println("-1");
			return;
		}
		search(0);

		System.out.println(ans);

		br.close();
	}

	public static void search(int count) {
		if (count == K) { // 바꿀 횟수가 충족되면
			// 배열에있는 숫자들을 하나의 값으로 바꾸기 위해 사용
			StringBuilder sb = new StringBuilder();
			for (int i : N) {
				sb.append(i);
			}
			int output = Integer.parseInt(sb.toString());
			// 해당 결과와 ans중 큰값 저장
			if (output > ans) {
				ans = output;
			}
			return;
		}
		boolean find = false;
		List<Integer> list = new ArrayList<>(); // 큰 값이 중복일 경우 리스트에 추가

		// i위치의 값이 해당 위치부터 끝까지 값들 중에서 가장 큰 값이면 i위치 증가
		for (int i = start; i < N.length; i++) {
			// 값들이 내림차순으로 정렬이 되어있는 상태여서 끝까지 도달하면
			if (i == N.length - 1) {
				if (dupl) { // 중복된 값이 있으면 리턴
					search(K);
				} else { // 중복된 값이 없으면
					swap(N.length - 2, N.length - 1); // 마지막과 마지막 전 값을 교환
					search(count + 1);
				}
			} else { // 내림차순으로 정렬이 안되어있으면
				int max = N[i]; // 큰값을 찾기 위해서 첫 시작점의 값을 입력
				for (int j = N.length - 1; j > i; j--) { // 바꿀 값의 위치를 찾는다
					if (N[j] > max) { // 뒤에서 부터 탐색한 값이 바꿀 값보다 크다면
						list = new ArrayList<>(); // 리스트를 초기화
						max = N[j]; // 큰값의 위치를 갱신
						list.add(j); // 위치를 리스트에 추가
						find = true; // 바꿀 값을 찾았다면 체크
					} else if (N[j] == max) { // 같은 값을 가지면 해당 위치를 리스트에 추가
						list.add(j);
					}
				}
				if (find) { // 바꿀 값이 존재한다면
					for (int in : list) { // 리스트에 있는 요소들 하나씩 꺼낸다
						swap(i, in); // 해당 요소의 위치와 변경
						start++; // 처음 검색을 시작할 위치 증가
						search(count + 1); // 다시 검색
						start--; // 처음 검색을 시작할 위치 감소
						swap(i, in); // 바꿔준 위치 원상복구
					}
					break;
				}
			}
		}
	}

	// 자리를 바꿔준다
	public static void swap(int i, int j) {
		int temp = N[i];
		N[i] = N[j];
		N[j] = temp;
	}

}

