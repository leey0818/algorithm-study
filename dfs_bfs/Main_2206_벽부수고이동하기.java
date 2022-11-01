import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2206_벽부수고이동하기 {
    private static final int[] dw = {0, 0, -1, 1};
    private static final int[] dh = {-1, 1, 0, 0}; // 4방탐색

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().trim().split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        int[][] map = new int[N][M];

        for (int h = 0; h < N; h++) {
            arr = br.readLine().trim().split("");
            for (int w = 0; w < M; w++) {
                map[h][w] = Integer.parseInt(arr[w]);
            }
        }

        int distance = bfs(map, N, M);
        System.out.println(distance);
    }

    private static int bfs(int[][] map, int N, int M) {
        int minCount = N * M;
        boolean isArrived = false;

        boolean[][] visit = new boolean[N][M];
        visit[0][0] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int c = cur[2];

            System.out.println(String.format("(%d,%d) %d %d", cur[0], cur[1], cur[2], cur[3]));

            if (x + 1 == M && y + 1 == N) {
                if (c < minCount) minCount = c;
                isArrived = true;
                continue;
            }

            // visit[y][x] = true;

            for (int d = 0; d < 4; d++) {
                int nx = x + dw[d];
                int ny = y + dh[d];
                boolean isBroken = cur[3] > 0;

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (visit[ny][nx]) continue;

                if (map[ny][nx] != 0) {
                    if (isBroken) continue;
                    isBroken = true;
                }

                visit[ny][nx] = true;

                queue.offer(new int[]{nx, ny, c + 1, isBroken ? 1 : 0});
            }
        }

        return isArrived ? minCount : -1;
    }
}
