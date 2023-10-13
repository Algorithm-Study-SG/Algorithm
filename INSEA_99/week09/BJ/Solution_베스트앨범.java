package INSEA_99.week09.BJ;

import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
 * idea : class 만들어서 pq사용하기
 * solved!
 * 
 * 테스트 1 〉	통과 (3.28ms, 77.3MB)
 * 테스트 2 〉	통과 (4.35ms, 66MB)
 * 테스트 3 〉	통과 (4.12ms, 74.1MB)
 * 테스트 4 〉	통과 (3.54ms, 70.8MB)
 * 테스트 5 〉	통과 (3.76ms, 70.1MB)
 * 테스트 6 〉	통과 (3.62ms, 83.5MB)
 * 테스트 7 〉	통과 (4.48ms, 80.3MB)
 * 테스트 8 〉	통과 (4.82ms, 74.1MB)
 * 테스트 9 〉	통과 (3.20ms, 76.7MB)
 * 테스트 10 〉	통과 (5.44ms, 73MB)
 * 테스트 11 〉	통과 (3.34ms, 75.2MB)
 * 테스트 12 〉	통과 (4.86ms, 78.8MB)
 * 테스트 13 〉	통과 (4.95ms, 71MB)
 * 테스트 14 〉	통과 (6.08ms, 72.7MB)
 * 테스트 15 〉	통과 (3.40ms, 71.2MB)
 * 
 */

class Solution {
	public int[] solution(String[] genres, int[] plays) {
		List<Integer> tempAnswer = new ArrayList<>();

		PriorityQueue<Song> pq = new PriorityQueue<>();
		Map<String, Integer> answerGenresCnt = new HashMap<>();

		// 장르 기준 합 추가
		for (int i = 0; i < genres.length; ++i) {
			Song.updateGenresStreaming(genres[i], plays[i]);
		}

		// 노래별 재생 횟수 추가 및 pq 넣기
		for (int i = 0; i < genres.length; ++i) {
			pq.add(new Song(i, genres[i], plays[i]));
		}
		
		// pq 빼면서 장르 별 개수 세서 정답 넣기
		for (int i = 0; i < genres.length; ++i) {
			Song now = pq.poll();
			answerGenresCnt.merge(now.genre, 1, (v1, v2) -> v1 + v2);
			if (answerGenresCnt.get(now.genre) <= 2) {
				tempAnswer.add(now.id);
			}
		}
		int[] answer = tempAnswer.stream().mapToInt(x -> x).toArray();
		return answer;
	}

	static class Song implements Comparable<Song> {
		static Map<String, Integer> genresStreaming = new HashMap<>();
		int id;
		String genre;
		int streamingCnt;

		Song(int id, String genre, int streamingCnt) {
			this.id = id;
			this.genre = genre;
			this.streamingCnt = streamingCnt;
		}

		static void updateGenresStreaming(String genre, int num) {
			genresStreaming.merge(genre, num, (v1, v2) -> v1 + v2);
		}

		@Override
		public int compareTo(Song s) {
			if (genresStreaming.get(this.genre) == genresStreaming.get(s.genre)) {
				if (this.streamingCnt == s.streamingCnt) {
					return Integer.compare(this.id, s.id);
				}
				return Integer.compare(s.streamingCnt, this.streamingCnt);
			}
			return Integer.compare(genresStreaming.get(s.genre), genresStreaming.get(this.genre));
		}
	}
}