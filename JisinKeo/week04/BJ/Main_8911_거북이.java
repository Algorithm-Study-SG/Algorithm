package JisinKeo.week04.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  메모리 : 27560 KB
 *  시간 : 572 ms
 */
public class Main_8911_거북이 {

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcases = Integer.parseInt(br.readLine());

        for (int test = 1; test <= testcases; test++) {
            String control = br.readLine();
            int area = computeTurtleMovementArea(control);
            System.out.println(area);
        }
    }


    private static int computeTurtleMovementArea(String control) {
        int x = 0;
        int y = 0;
        int direction = 0;

        int minX = 0, maxX = 0, minY = 0, maxY = 0;

        for (char command : control.toCharArray()) {
            switch (command) {
                case 'F':
                    x += dx[direction];
                    y += dy[direction];
                    break;
                case 'B':
                    x -= dx[direction];
                    y -= dy[direction];
                    break;
                case 'L':
                    direction = (direction == 0) ? 3 : direction - 1;
                    break;
                case 'R':
                    direction = (direction == 3) ? 0 : direction + 1;
                    break;
            }

            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
        }
        return (maxX - minX) * (maxY - minY);
    }
}