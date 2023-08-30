package INSEA_99.week05.BJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.math.BigInteger;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 메모리 : 11592 KB
 * 시간 : 76 ms
 * 
 * idea1 : 입력을 int로 받아서 배열 dp 점화식 활용
 * fail! -> int형은 40자리수를 저장할 수 없음
 * 
 * idea2 : 입력을 String으로 받아서 map dp 점화식 활용
 * solved! -> 백트래킹중에 조건식을 잘 못 써서 못풀다가 풀었어요
 * 
 * 
 * 농쳤던 점 : 입력이 40이하 암호문이기 때문에 0을 고려해야한다.
 * 
 */

public class Main_2591_숫자카드 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int key;
	static String number;
	static int n;
	static Map<String, Integer> dp = new HashMap<>();

	public static void main (String[] args) throws IOException {
		input(); // 입력
		run(); // 실행
		output(); // 출력			
		return;
	}

	/* 입력 */
	static void input () throws IOException {
		number = br.readLine();
		br.close();
	}

	/* 출력 */
	static void output () throws IOException {
		bw.write(dp.get(number) + "");
		bw.flush();
		bw.close();
	}

	/* 실행 */
	static void run () {
		init();
		getNumberCases(number);
	}

	static void init () {
		dp.put("0", 0);
		for (int i = 1; i <= 99; ++i) {
			if (i < 10 || i % 10 == 0 || i > 34) {
				dp.put(Integer.toString(i), 1);
			} else {
				dp.put(Integer.toString(i), 2);
			}
		}
	}
	
	static int getNumberCases (String number) {
		if(!dp.containsKey(number)) {
			int temp_1 = 0;
			int temp_2 = 0;
			int nLenght = number.length();
			if(number.charAt(nLenght - 2) != '0' && Integer.parseInt(number.substring(nLenght - 2, nLenght)) <= 34) {
				temp_1 = getNumberCases(number.substring(0, nLenght - 2));
			}
			if(number.charAt(nLenght - 1) != '0' ) {
				temp_2 = getNumberCases(number.substring(0, nLenght - 1));
			}
			dp.put(number, temp_1 + temp_2);
		}
		return dp.get(number);
	}
	
//	System.out.printf("dp[%s] = dp[%s] + dp[%s]%n", number, number.substring(0, nLenght - 2), number.substring(0, nLenght - 1));
//	System.out.printf("dp[%s] = %d + %d%n", number, temp_1, temp_2);
//	System.out.printf("%d. dp[%s] = %d%n", ++key, number, dp.get(number));
//	System.out.println("=================");
}
