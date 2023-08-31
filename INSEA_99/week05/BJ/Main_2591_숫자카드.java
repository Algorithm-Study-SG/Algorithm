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
<<<<<<< HEAD
	
	static String number;		// 입력 카드 값
	static Map<String, Integer> dp = new HashMap<>();	// 카드 값을 key값으로 갖는 dp
=======
	static int key;
	static String number;
	static int n;
	static Map<String, Integer> dp = new HashMap<>();
>>>>>>> eea4858fe7a75ab332f9d1bd6738251e2674044e

	public static void main (String[] args) throws IOException {
		input(); // 입력
		run(); // 실행
		output(); // 출력			
		return;
	}

	/* 입력 */
	static void input () throws IOException {
<<<<<<< HEAD
		number = br.readLine();		// 입력 카드 값
=======
		number = br.readLine();
>>>>>>> eea4858fe7a75ab332f9d1bd6738251e2674044e
		br.close();
	}

	/* 출력 */
	static void output () throws IOException {
<<<<<<< HEAD
		bw.write(dp.get(number) + "");		// 입력 카드 값 배열 수
=======
		bw.write(dp.get(number) + "");
>>>>>>> eea4858fe7a75ab332f9d1bd6738251e2674044e
		bw.flush();
		bw.close();
	}

	/* 실행 */
	static void run () {
<<<<<<< HEAD
		init();		// dp 초기화
		getNumberCases(number);		// 입력 카드 배열 배열 수 얻기
	}

	/***
	 * 0~99까지 가능한 배열 수 초기화해준다.
	 */
=======
		init();
		getNumberCases(number);
	}

>>>>>>> eea4858fe7a75ab332f9d1bd6738251e2674044e
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
	
<<<<<<< HEAD
	/***
	 * number의 배열 수를 모르면 (맨 뒷자리를 뺀 카드 값의 배열 수)(= a)와 (맨 뒷자리, 그 다음 뒷자리를 뺀 카드 값의 배열 수)(= b)를 합친 값을 구하여 저장한다..
	 * 단 맨 뒷자리가 0인경우 a를 더하지 않고, 맨 뒤에서 두번째 ~ 맨 뒷자리까지의 두자리 수가 34를 초과하면 b를 더하지 않는다.
	 * @param number 배열 수를 얻고자 하는 카드 값
	 * @return number의 가능한 배열 수
	 */
=======
>>>>>>> eea4858fe7a75ab332f9d1bd6738251e2674044e
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
<<<<<<< HEAD
}
=======
	
//	System.out.printf("dp[%s] = dp[%s] + dp[%s]%n", number, number.substring(0, nLenght - 2), number.substring(0, nLenght - 1));
//	System.out.printf("dp[%s] = %d + %d%n", number, temp_1, temp_2);
//	System.out.printf("%d. dp[%s] = %d%n", ++key, number, dp.get(number));
//	System.out.println("=================");
}
>>>>>>> eea4858fe7a75ab332f9d1bd6738251e2674044e
