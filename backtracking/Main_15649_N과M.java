import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_15649_N과M {
    private static int N;
    private static int M;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 4
        M = Integer.parseInt(st.nextToken());  // 2
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[] visit = new boolean[N];
        int[] answer = new int[M];

        dfs(answer, visit, 0);

        bw.flush();
        bw.close();
    }

    private static void dfs(int[] answer, boolean[] visit, int depth) throws IOException {
        // 깊이가 끝까지 갔으면 끝냄
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                if (i != 0) bw.write(" ");
                bw.write(answer[i] + 48);
            }
            bw.write(System.lineSeparator());
            return;
        }

        for (int n = 0; n < N; n++) {
            if (!visit[n]) {
                visit[n] = true;
                answer[depth] = n + 1;
                dfs(answer, visit, depth + 1);
                visit[n] = false;
            }
        }
    }
}
