import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1759_암호만들기 {
    private static BufferedWriter bw;
    private static int L, C, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = C - L;
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] alphabets = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alphabets[i] = st.nextToken().charAt(0);
        }

        // 오름차순 정렬
        Arrays.sort(alphabets);

        char[] answer = new char[L];
        boolean[] visit = new boolean[C];

        for (int i = 0; i <= M; i++) {
            visit[i] = true;
            answer[0] = alphabets[i];
            comb(alphabets, answer, visit, i + 1, 1, isVowelChar(alphabets[i]) ? 1 : 0);
            visit[i] = false;
        }

        bw.flush();
        bw.close();
    }

    private static void comb(char[] alphabets, char[] answer, boolean[] visit, int start, int depth, int vowelCount) throws IOException {
        if (depth == L) {
            // 모음이 1개 이상, 자음이 2개 이상인 조합만
            if (vowelCount >= 1 && (L - vowelCount) >= 2) {
                for (int n = 0; n < L; n++) {
                    bw.write(answer[n]);
                }
                bw.write(System.lineSeparator());
            }
            return;
        }

        for (int i = start; i < C; i++) {
            if (visit[i]) continue;

            visit[i] = true;
            answer[depth] = alphabets[i];
            comb(alphabets, answer, visit, i + 1, depth + 1, isVowelChar(alphabets[i]) ? vowelCount + 1 : vowelCount);
            visit[i] = false;
        }
    }

    private static boolean isVowelChar(char alphabet) {
        return alphabet == 'a' || alphabet == 'i' || alphabet == 'u' || alphabet == 'e' || alphabet == 'o';
    }
}
