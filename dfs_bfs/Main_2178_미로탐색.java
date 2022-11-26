import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2178_미로탐색 {
    private static final int[] dw = {0, 0, -1, 1};
    private static final int[] dh = {-1, 1, 0, 0};
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        for (int n = 0; n < N; n++) {
            map[n] = br.readLine().toCharArray();
        }

        boolean[][] visit = new boolean[N][M];
        visit[0][0] = true;

        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(0, 0, 1));

        while (!queue.isEmpty()) {
            Position pos = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = pos.x + dw[d];
                int ny = pos.y + dh[d];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N || visit[ny][nx]) continue;
                if (map[ny][nx] != '1') continue;

                // 마지막 지점이면 그냥 끝냄
                if (nx == (M - 1) && ny == (N - 1)) {
                    System.out.println(pos.move + 1);
                    queue.clear();
                    break;
                }

                visit[ny][nx] = true;
                queue.offer(new Position(nx, ny, pos.move + 1));
            }
        }
    }

    static class Position {
        final int x;
        final int y;
        final int move;
        Position(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
}
