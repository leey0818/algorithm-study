import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_숨바꼭질 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(calc(N, K));
    }

    public static int calc(int N, int K) {
        // 동생이랑 같은 위치이거나, 뒤에 있으면 -1 밖에 안되기 때문에 그냥 계산...
        if (N >= K) return N - K;

        boolean[] visitPos = new boolean[100001];
        visitPos[N] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{N, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int d = 0; d < 3; d++) {
                int nextPos = getNextPosition(d, cur[0]);

                if (nextPos < 0 || nextPos > 100000 || visitPos[nextPos]) continue;

                // 다음으로 이동할 위치가 동생위치이면 끝냄
                if (nextPos == K) {
                    return cur[1] + 1;
                }

                visitPos[nextPos] = true;
                queue.offer(new int[]{nextPos, cur[1] + 1});
            }
        }

        return -1;
    }

    public static int getNextPosition(int d, int pos) {
        if (d == 0) return pos - 1;
        if (d == 1) return pos + 1;
        if (d == 2) return pos * 2;
        return pos;
    }
}
