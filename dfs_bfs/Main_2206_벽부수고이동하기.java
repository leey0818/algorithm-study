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
        char[][] map = new char[N][M];

        for (int h = 0; h < N; h++) {
            map[h] = br.readLine().toCharArray();
        }

        int distance = bfs(map, N, M);
        System.out.println(distance);
    }

    private static int bfs(char[][] map, int N, int M) {
        boolean[][] visitForNormal = new boolean[N][M];
        boolean[][] visitForBroken = new boolean[N][M];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int c = cur[2];

            if (x + 1 == M && y + 1 == N) {
                return c;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dw[d];
                int ny = y + dh[d];
                boolean isBroken = cur[3] > 0;

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                // 다음으로 이동할 좌표가 벽이 아님
                if (map[ny][nx] == '0') {
                    if (isBroken && !visitForBroken[ny][nx]) {
                        visitForBroken[ny][nx] = true;
                        queue.offer(new int[]{nx, ny, c + 1, 1});
                    } else if (!isBroken && !visitForNormal[ny][nx]) {
                        visitForNormal[ny][nx] = true;
                        queue.offer(new int[]{nx, ny, c + 1, 0});
                    }
                } else if (!isBroken && !visitForBroken[ny][nx]) {
                    visitForBroken[ny][nx] = true;
                    queue.offer(new int[]{nx, ny, c + 1, 1});
                }
            }
        }

        return -1;
    }
}
