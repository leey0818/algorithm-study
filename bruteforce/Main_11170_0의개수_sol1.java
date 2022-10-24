import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11170_0의개수_sol1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int minNum = Integer.parseInt(st.nextToken());
            int maxNum = Integer.parseInt(st.nextToken());
            int zeroCount = 0;

            for (int s = minNum; s <= maxNum; s++) {
                int temp = s;
                if (temp == 0) {
                    zeroCount += 1;
                } else {
                    while ((temp / 10) > 0) {
                        if (temp % 10 == 0) zeroCount++;
                        temp = temp / 10;
                    }
                }
            }

            System.out.println(zeroCount);
        }
    }
}
