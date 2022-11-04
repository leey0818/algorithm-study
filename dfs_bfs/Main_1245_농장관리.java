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

        int count = 0;
        boolean[][] visit = new boolean[N][M];
        for (int h = 0; h < N; h++) {
            for (int w = 0; w < M; w++) {
                if (!visit[h][w]) {
                    visit[h][w] = true;
                    if (!bfs(map, visit, N, M, w, h)) {
                        count += 1;
                        System.out.println("==============================");
                    } else {
                        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                    }

                    // VISIT DEBUG
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < M; j++) {
                            System.out.print(visit[i][j] ? "1 " : "0 ");
                        }
                        System.out.println();
                    }
                }
            }
        }

        System.out.println(count);
    }

    private static boolean bfs(int[][] map, boolean[][] visit, int N, int M, int x, int y) {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(map, x, y, false));

        boolean hasHigher = false;
        while (!queue.isEmpty()) {
            Position pos = queue.poll();

            while (pos.next()) {
                int nx = pos.nx();
                int ny = pos.ny();

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (visit[ny][nx]) continue;

                if (map[ny][nx] == pos.height()) {
                    visit[ny][nx] = true;
                    queue.offer(new Position(map, nx, ny, pos.down));
                } else if (map[ny][nx] < pos.height()) {
                    // pos.down = true;
                    visit[ny][nx] = true;
                    queue.offer(new Position(map, nx, ny, true));
                } else if (!pos.isDown()) {
                    hasHigher = true;
                }
            }
        }

        return hasHigher;
    }

    private static class Position {
        final int[][] map;
        int x;
        int y;
        int d = -1;
        boolean down = false;
        public Position(int[][] map, int x, int y, boolean down) {
            this.map = map;
            this.x = x;
            this.y = y;
            this.down = down;
        }
        public int height() {
            return this.map[this.y][this.x];
        }
        public boolean next() {
            d += 1;
            return d < 8;
        }
        public int nx() {
            return this.x + dw[this.d];
        }
        public int ny() {
            return this.y + dh[this.d];
        }
        public boolean isDown() {
            return this.down;
        }
    }
}