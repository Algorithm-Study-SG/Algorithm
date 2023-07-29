import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, s;
    static int[] seq;
    static int count;

    public static void DFS(int index, int sum) {
        if(index == n) {
            if(sum == s) {
                count++;
            }
            return;
        }

        DFS(index+1, sum + seq[index]);
        DFS(index+1, sum);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        s = Integer.parseInt(stk.nextToken());

        seq = new int[n];

        stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(stk.nextToken());
        }

        DFS(0, 0);

        if(s == 0) count--;

        System.out.println(count);
    }
}