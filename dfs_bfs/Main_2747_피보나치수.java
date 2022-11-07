import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2747_피보나치수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        if (num <= 1) System.out.println(num);
        else System.out.println(dfs(0, 1, num));
    }

    private static int dfs(int prev1, int prev2, int loop) {
        if (loop > 1) {
            return dfs(prev2, (prev1 + prev2), loop - 1);
        }
        return prev2;
    }
}
