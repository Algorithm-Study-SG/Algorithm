package JisinKeo.week08.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 메모리 : 20700 KB
 * 시간 : 688 ms
 */
public class Main_2109_순회강연 {

    static int n;

    static class Lecture{
        int p;
        int d;

        Lecture(int p, int d) {
            this.p = p;
            this.d = d;
        }

        public int getD() {
            return this.d;
        }

        public int getP() {
            return this.p;
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[10001];

        List<Lecture> list = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int dInput = Integer.parseInt(st.nextToken());
            int pInput = Integer.parseInt(st.nextToken());

            list.add(new Lecture(dInput, pInput));
        }

        Collections.sort(list, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture o1, Lecture o2) {
                if(o1.p == o2.p){
                    return o2.d - o1.d;
                }
                return o1.p - o2.p;
            }
        });

        int result = 0;

        for(int i = n-1; i >= 0; i--){
            Lecture lecture = list.get(i);

            while(lecture.d >= 1) {
                if (!visited[lecture.d]) {
                    visited[lecture.d] = true;
                    result += lecture.p;
                    break;
                }
                lecture.d -= 1;

            }
        }
        System.out.println(result);

    }

}