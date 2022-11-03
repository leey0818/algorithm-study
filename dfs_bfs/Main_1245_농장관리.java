import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1245_농장관리 {
    private static final int[] dw = {-1, 0, 1, 1, 1, 0, -1, -1};
    private static final int[] dh = {-1, -1, -1, 0, 1, 1, 1, 0};  // 8방탐색

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for (int h = 0; h < N; h++) {
            st = new StringTokenizer(br.readLine());
            for (int w = 0; w < M; w++) {
                map[h][w] = Integer.parseInt(st.nextToken());
            }
        }

        if (N == 1 && M == 1) {
            System.out.println("1");
        } else {
            int count = 0;
            boolean[][] visit = new boolean[N][M];
            for (int h = 0; h < N; h++) {
                for (int w = 0; w < M; w++) {
                    if (!visit[h][w]) {
                        bfs(map, visit, N, M, w, h);

                        // if (map[h][w] > lowest) {
                        //     System.out.println(String.format("%d,%d", w, h));
                        //     count += 1;
                        // }

                        // System.out.println("===============================");
                        // for (int i = 0; i < N; i++) {
                        //     for (int j = 0; j < M; j++) {
                        //         System.out.print((visit[i][j] ? "1 " : "0 "));
                        //     }
                        //     System.out.println();
                        // }
                    }
                }
            }

            System.out.println(count);
        }
    }

    private static void bfs(int[][] map, boolean[][] visit, int N, int M, int x, int y) {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(map, x, y));

        while (!queue.isEmpty()) {
            Position pos = queue.poll();

            for (int d = 0; d < 8; d++) {
                int nx = pos.x + dw[d];
                int ny = pos.y + dh[d];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (visit[ny][nx]) continue;

                // 이동할 좌표의 높이가 낮거나 동일할때
                if (map[ny][nx] <= pos.height()) {
                    visit[ny][nx] = true;
                } else {
                    // 이동할 좌표의 높이가 더 높을때
                }

                queue.offer(new Position(map, nx, ny));
            }
        }

        System.out.println("Done!");
    }

    private static class Position {
        final int[][] map;
        int x;
        int y;
        public Position(int[][] map, int x, int y) {
            this.map = map;
            this.x = x;
            this.y = y;
        }

        public int height() {
            return this.map[this.y][this.x];
        }
    }
}