import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main_1010_다리놓기 {
    private static final BigInteger[] fibo = new BigInteger[30];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // fibonacci 계산
        fibo[1] = new BigInteger("1");
        fibo[2] = new BigInteger("2");
        for (int n = 3; n < 30; n++) {
            fibo[n] = new BigInteger(String.valueOf(n)).multiply(fibo[n - 1]);
        }

        StringBuffer result = new StringBuffer();
        StringTokenizer st;
        int N, M;
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == M) {
                result.append("1");
            } else {
                result.append(comb(M, N));
            }
            result.append(System.lineSeparator());
        }

        System.out.print(result.toString());
    }

    private static BigInteger comb(int M, int N) {
        return fibo[M].divide(fibo[M - N].multiply(fibo[N]));
    }
}
