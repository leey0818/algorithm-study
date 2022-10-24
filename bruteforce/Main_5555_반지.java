import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_5555_반지 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] findChar = br.readLine().toCharArray();
        int wordLength = findChar.length;
        int wordCount = Integer.parseInt(br.readLine());
        int matchCount = 0;

        for (int i = 0; i < wordCount; i++) {
            String word = br.readLine();

            for (int c = 0; c < 10; c++) {
                if (word.charAt(c) == findChar[0]) {
                    boolean isMatched = true;
                    for (int n = 1; n < wordLength; n++) {
                        int cursor = (n + c) % 10;

                        if (word.charAt(cursor) != findChar[n]) {
                            isMatched = false;
                            break;
                        }
                    }

                    if (isMatched) {
                        matchCount += 1;
                        break;
                    }
                }
            }
        }

        System.out.println(matchCount);
    }
}