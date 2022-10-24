import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10419_지각 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxlen = Integer.parseInt(br.readLine());

        for (int i = 0; i < maxlen; i++) {
            int time = Integer.parseInt(br.readLine());

            for (int n = 1; n <= 100; n++) {
                if ((n + (n * n)) > time) {
                    System.out.println(n - 1);
                    break;
                }
            }
        }
    }
}
