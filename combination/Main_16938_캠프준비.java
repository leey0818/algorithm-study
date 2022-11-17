import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class Main_16938_캠프준비 {
    private static int N, L, R, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        int[] level = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            level[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N];
        boolean[] visit = new boolean[N];
        AtomicInteger caseCount = new AtomicInteger();
        comb(level, answer, visit, 0, 0, 0, caseCount);

        System.out.println(caseCount.get());
    }

    private static void comb(int[] level, int[] answer, boolean[] visit, int start, int depth, int sum, AtomicInteger caseCount) {
        if (sum > R) return;
        if (depth > 1 && sum >= L && sum <= R) {
            int diff = getDiffNum(answer, depth - 1);
            if (diff >= X) {
                caseCount.incrementAndGet();
            }

            if (depth == N) return;
        }

        for (int i = start; i < N; i++) {
            if (visit[i]) continue;

            visit[i] = true;
            answer[depth] = level[i];
            comb(level, answer, visit, i + 1, depth + 1, sum + level[i], caseCount);
            visit[i] = false;
        }
    }

    private static int getDiffNum(int[] answer, int endIdx) {
        int minNum = Integer.MAX_VALUE;
        int maxNum = 1;
        for (int i = 0; i <= endIdx; i++) {
            if (answer[i] < minNum) minNum = answer[i];
            if (answer[i] > maxNum) maxNum = answer[i];
        }
        return maxNum - minNum;
    }
}
