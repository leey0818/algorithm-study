import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_3040_백설공주 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] numArr = new int[9];
        int totalNum = 0;

        for (int i = 0; i < 9; i++) {
            numArr[i] = Integer.parseInt(br.readLine());
            totalNum += numArr[i];
        }

        int remain = totalNum - 100;  // 100을 초과한 수 구하기
        int target1 = -1;  // 범인1
        int target2 = -1;  // 범인2
        boolean isMatched = false;

        for (int i = 0; i < 9; i++) {
            for (int n = i + 1; n < 9; n++) {
                // 초과된 수와 일치하면 그 두명이 범인
                if (remain == numArr[i] + numArr[n]) {
                    isMatched = true;
                    target1 = i;
                    target2 = n;
                    break;
                }
            }

            if (isMatched) break;
        }

        for (int i = 0; i < 9; i++) {
            if (i == target1 || i == target2) continue;
            System.out.println(numArr[i]);
        }
    }
}
