package KIMSEI1124.week03.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Q1325_시간초과테스트 {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        File file = new File("./Q1325Test.txt");
        file.createNewFile();

        sb.append("10000 99900\n");
        for (int i = 1; i < 9991; i++) {
            for (int j = i + 1; j < i + 11; j++) {
                sb.append(i).append(" ").append(j).append("\n");
            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.append(sb);
        bw.close();
    }
}
