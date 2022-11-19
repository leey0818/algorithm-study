import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1010_다리놓기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuffer result = new StringBuffer();
        StringTokenizer st;
        int N, M;
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int[] memo = new int[N + 1];
            for (int m = 1; m <= M; m++) {
                // TODO
            }
        }

        System.out.print(result.toString());
    }

    private static void comb(int[] memo, int start) {
        // TODO
    }
}
