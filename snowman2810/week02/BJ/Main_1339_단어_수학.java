package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_1339_단어_수학 {

	static HashMap<Character, Integer> map = new HashMap<>(); // 해당 문자가 나타내는 숫자를 저장하기 위한 map 생성
	static List<charNum> list; // 문자의 값에 따라서 정렬하기 위한 list 생성

	public static void main(String[] args) throws IOException {
		// 기본 입력틀
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		String input[] = new String[n];
		for (int i = 0; i < n; i++) {
			input[i] = br.readLine();
		}

		sorting(input);
		mapInit();

		int ans = calNumber(input);

		bw.write(String.valueOf(ans));

		bw.close();
		br.close();

	}

	public static void mapInit() { // list의 문자를 처음부터 빼오고 숫자를 할당해준다
		int number = 9;

		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getIn(), number);
			number--;
		}

	}

	public static int calNumber(String input[]) { // map에 저장된 문자를 숫자로 적용한 후 총합을 리턴하는 함수
		StringBuilder sb = new StringBuilder();

		int result[] = new int[input.length];

		for (int i = 0; i < input.length; i++) {

			for (int j = 0; j < input[i].length(); j++) {
				sb.append(map.get(input[i].charAt(j))); // map에 저장된 문자의 값을 StringBuilder에 추가한다
			}
			result[i] = Integer.parseInt(sb.toString()); // StringBuilder에 추가된 문자열을 int값으로 바꾼 뒤 저장한다
			sb.setLength(0); // StringBuilder 초기화
		}

		int ans = 0;

		for (int i = 0; i < input.length; i++) { // int의 값들을 더해서 총합을 저장한 후 리턴한다
			ans += result[i];
		}

		return ans;
	}

	public static void sorting(String input[]) {

		HashMap<Character, Integer> value = new HashMap<>(); // 문자가 얼만큼의 가치를 지니는지 저장하기 위한 value

		for (int i = 0; i < input.length; i++) {
			int num = 9;
			for (int j = input[i].length() - 1; j >= 0; j--) {
				if (value.containsKey(input[i].charAt(j))) { // value에 해당 문자가 존재하면 원래 있던 값에 num을 추가해서 저장한다
					value.put(input[i].charAt(j), value.get(input[i].charAt(j)) + num);
				} else { // value에 해당 문자가 존재하지 않으면 새로 추가
					value.put(input[i].charAt(j), num);
				}
				num *= 10; // 자리수에 따라 다른 값을 주기위해 계산
			}
		}

		list = new ArrayList<>();

		charNum temp; // list에 저장하기 위해서 생성

		for (Map.Entry<Character, Integer> e : value.entrySet()) { // map에서 뽑아낸 key값과 value값을 list에 추가
			temp = new charNum();
			temp.setIn(e.getKey());
			temp.setNum(e.getValue());
			list.add(temp);
		}

		Collections.sort(list); // charNum에 선언된 compareTo에 따라서 정렬

	}

	static class charNum implements Comparable<charNum> { // 정렬을 사용하기 편하게끔 class를 선언 후 comparable을 받는다

		private char in;
		private int num;

		@Override
		public int compareTo(charNum o) {
			return o.num - this.num;
		}

		public char getIn() {
			return in;
		}

		public void setIn(char in) {
			this.in = in;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}
	}

}