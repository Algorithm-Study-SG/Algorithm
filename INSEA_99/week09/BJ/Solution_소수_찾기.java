package INSEA_99.week09.BJ;
import java.util.Set;
import java.util.HashSet;

/*
 * idea : 입력 값에서 만들 수 있는 숫자 조합을 구한 후 소수를 판별한다.
 * 
 * 테스트 1 〉	통과 (10.58ms, 75.1MB)
 * 테스트 2 〉	통과 (18.23ms, 78.5MB)
 * 테스트 3 〉	통과 (9.41ms, 76.3MB)
 * 테스트 4 〉	통과 (18.96ms, 81.3MB)
 * 테스트 5 〉	통과 (32.01ms, 79.5MB)
 * 테스트 6 〉	통과 (14.57ms, 82.2MB)
 * 테스트 7 〉	통과 (12.46ms, 67.9MB)
 * 테스트 8 〉	통과 (26.23ms, 88.2MB)
 * 테스트 9 〉	통과 (10.15ms, 79.1MB)
 * 테스트 10 〉	통과 (25.37ms, 83.8MB)
 * 테스트 11 〉	통과 (15.06ms, 94.2MB)
 * 테스트 12 〉	통과 (12.97ms, 73.5MB)
 * 
 */

class Solution_소수_찾기 {
	public int solution(String numbers) {
		int answer = run(numbers);
		return answer;
	}

	public int run(String numbers) {
		int primeNumcnt = 0; // 가능한 소수

		Set<Integer> candiNums = getNums(numbers); // 가능한 숫자 조합
		for (int num : candiNums) {
			if (isPrime(num)) { // 소수 판별
				primeNumcnt++;
			}
		}
		return primeNumcnt;
	}

	public Set<Integer> getNums(String numbers) {
		permutation(0, numbers, ""); // 순열로 가능한 숫자 조합 탐색
		return Candidate.nums;
	}

	public void permutation(int flag, String numbers, String temp) { // 순열
		for (int i = 0; i < numbers.length(); ++i) {
			if (((1 << i) & flag) == 0) {
				Candidate.nums.add(Integer.parseInt(temp + numbers.charAt(i)));
				permutation((1 << i) | flag, numbers, temp + numbers.charAt(i));
			}
		}
	}

	public boolean isPrime(int numbers) { // 소수 판별
		if (numbers == 0 || numbers == 1) {
			return false;
		}

		int to = (int) Math.pow(numbers, 0.5);
		for (int i = 2; i <= to; ++i) {
			if (numbers % i == 0) {
				return false;
			}
		}
		return true;
	}

	static class Candidate {
		static Set<Integer> nums = new HashSet<>(); // 가능한 숫자 조합 저장
	}
}