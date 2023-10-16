package JisinKeo.week09.pro;

import java.util.*;

class Solution_42579_베스트앨범 {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> genreToSum = new HashMap<>();
        HashMap<String, PriorityQueue<Song>> genreToSongs = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            genreToSum.put(genres[i], genreToSum.getOrDefault(genres[i], 0) + plays[i]);

            if (!genreToSongs.containsKey(genres[i])) {
                genreToSongs.put(genres[i], new PriorityQueue<>());
            }
            genreToSongs.get(genres[i]).offer(new Song(i, plays[i]));
        }

        List<String> sortedGenres = new ArrayList<>(genreToSum.keySet());
        sortedGenres.sort((a, b) -> genreToSum.get(b) - genreToSum.get(a));

        List<Integer> result = new ArrayList<>();

        for (String genre : sortedGenres) {
            for (int i = 0; i < 2 && !genreToSongs.get(genre).isEmpty(); i++) {
                result.add(genreToSongs.get(genre).poll().id);
            }
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    static class Song implements Comparable<Song> {
        int id, play;

        Song(int id, int play) {
            this.id = id;
            this.play = play;
        }

        @Override
        public int compareTo(Song other) {
            if (this.play == other.play) {
                return this.id - other.id;
            } else {
                return other.play - this.play;
            }
        }
    }
}
