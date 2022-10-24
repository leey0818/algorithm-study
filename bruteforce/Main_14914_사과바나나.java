import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14914_사과바나나 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int appleCount = Integer.parseInt(st.nextToken());
        int bananaCount = Integer.parseInt(st.nextToken());

        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= 1000; i++) {
            if (appleCount % i == 0 && bananaCount % i == 0) {
                if (sb.length() > 0) sb.append("\n");
                sb.append(i).append(" ")
                    .append(appleCount / i)
                    .append(" ")
                    .append(bananaCount / i);
            }
        }

        System.out.println(sb.toString());
    }
}
