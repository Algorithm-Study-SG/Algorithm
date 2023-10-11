package KIMSEI1124.week08;

import java.io.*;
import java.util.*;

/**
 * 풀이방법:
 * 각 좌표들에 대한 객체를 생성하여 리스트에 저장합니다.
 * 이후 `compareTo`로 재정의된 방식으로 정렬을 진행하고 반복할 때 마다 값(길이)을 더해줍니다.
 * 메모리: 281,488KB
 * 시간: 1,644ms
 */
public class Main_2170_선긋기 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int answer;

    private static int n;
    private static List<Line> lines;

    private static int start = Integer.MIN_VALUE;
    private static int end = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(answer);
    }

    private static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        lines = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lines.add(Line.from(new StringTokenizer(br.readLine())));
        }
    }

    private static void solve() {
        Collections.sort(lines);

        for (int i = 0; i < n; i++) {
            Line line = lines.get(i);
            if (line.getStart() > end) { // 선이 겹치지 않으므로 새로 시작
                start = line.getStart();
                end = line.getEnd();
                answer += end - start;
            } else if (line.getStart() <= end) { // 선이 겹치므로 끝나는 지점만 변경
                answer += Math.max(end, line.getEnd()) - end;
                end = Math.max(end, line.getEnd());
            }
        }
    }

    private static class Line implements Comparable<Line> {
        private int start;
        private int end;

        private Line(int s, int e) {
            start = s;
            end = e;
        }

        public static Line from(StringTokenizer st) {
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            return new Line(start, end);
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public int compareTo(Line other) {
            if (start < other.getStart()) {
                return -1;
            }
            if (start == other.getStart()) {
                if (end < other.getEnd()) {
                    return -1;
                } else if (end == other.getEnd()) {
                    return 0;
                } else {
                    return 1;
                }
            }
            return 1;
        }
    }

}