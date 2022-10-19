import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_7568_덩치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxlen = Integer.parseInt(br.readLine());

        int[] weight = new int[maxlen];
        int[] height = new int[maxlen];

        String line;
        StringTokenizer st;
        for (int i = 0; i < maxlen; i++) {
            line = br.readLine();

            st = new StringTokenizer(line);
            weight[i] = Integer.parseInt(st.nextToken()); // 55
            height[i] = Integer.parseInt(st.nextToken()); // 185
        }

        for (int i = 0; i < maxlen; i++) {
            int lowerCount = 0;
            for (int j = 0; j < maxlen; j++) {
                if (i == j) continue; // 본인 데이터는 무시
                int temp = 0;

                // 몸무게 계산
                if (weight[i] > weight[j] && height[i] > height[j]) temp += 1;
                if (weight[i] < weight[j] && height[i] < height[j]) temp -= 1;

                // 나보다 덩치가 작거나 같은 사람?
                if (temp >= 0) lowerCount += 1;
            }

            if (i > 0) System.out.print(" ");
            System.out.print(maxlen - lowerCount);
        }
    }
}