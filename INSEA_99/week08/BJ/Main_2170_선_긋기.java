package INSEA_99.week08.BJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 *	280656 KB 	1464ms
 *  스위핑 검색해서 풀었어요
 *  정렬 후 왼쪽 오른쪽 범위 기준 늘려주고, 범위 벗어나면 기존 범위 더한 수 범위 새로운 것으로 갱신
 * 
 */

public class Main_2170_선_긋기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int n, s, d, length;
	static List<Point> points;

	public static void main(String[] args) throws IOException {
		input(); // 입력
		run(); // 실행
		output(); // 출력
		return;
	}

	static class Point implements Comparable {
		int l;
		int r;

		Point() {
		}

		Point(int l, int r) {
			this.l = l;
			this.r = r;
		}

		@Override
		public int compareTo(Object o) {
			if (o == null || o.getClass() != this.getClass()) {
				return 0;
			}
			Point point = (Point) o;
			return Integer.compare(this.l, point.l);

		}
	}

	/* 입력 */
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		points = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			points.add(new Point(l, r));
		}
		s = Integer.MAX_VALUE;
		d = Integer.MIN_VALUE;
		length = -1;
	}

	/* 출력 */
	static void output() throws IOException {
		bw.write(length + "");
		bw.flush();
		bw.close();
		br.close();
	}

	/* 실행 */
	static void run() {
		Collections.sort(points);
		for (int i = 0; i < n; ++i) {		
			int l = points.get(i).l;	// 선 왼쪽 점
			int r = points.get(i).r;	// 선 오른쪽 점
			if (l >= d) {				// 범위 벗어났으면 기존 범위 더하고 범위 새롭게 갱신
				length += d - s;
				s = l;
				d = r;
			} else {					// 범위 겹치면 기존 기준으로 범위 갱신
				s = Math.min(s, l);
				d = Math.max(d, r);
			}
		}
		length += d - s;
	}
}