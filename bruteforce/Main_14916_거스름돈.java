import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_14916_거스름돈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int money = Integer.parseInt(br.readLine());
        br.close();

        int maxCount5 = money / 5;
        int minCount = -1;

        for (int i = maxCount5; i >= 0; i--) {
            int amount = money - (i * 5); // 5원으로 바꾸고 나머지 금액

            // 2원으로 나누어지면 그게 갯수
            if (amount % 2 == 0) {
                minCount = i + (amount / 2);  // 5원개수 + 2원개수
                break;
            }
        }

        System.out.print(String.valueOf(minCount));
    }
}