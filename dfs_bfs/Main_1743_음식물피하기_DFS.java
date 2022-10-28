import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1743_음식물피하기_DFS {
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0}; // 상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[][] trashmap = new boolean[N][M];
        boolean[][] visit = new boolean[N][M];

        int tx, ty;
        for (int t = 0; t < K; t++) {
            st = new StringTokenizer(br.readLine());
            ty = Integer.parseInt(st.nextToken()) - 1;
            tx = Integer.parseInt(st.nextToken()) - 1;

            trashmap[ty][tx] = true;
        }

        int largestTrash = 1;
        for (int h = 0; h < N; h++) {
            for (int w = 0; w < M; w++) {
                if (trashmap[h][w] && !visit[h][w]) {
                    int trashSize = findNearTrash(trashmap, visit, w, h, 1);
                    if (trashSize > largestTrash) {
                        largestTrash = trashSize;
                    }
                }
            }
        }

        System.out.print(largestTrash);
    }

    private static int findNearTrash(boolean[][] map, boolean[][] visit, int x, int y, int trashSize) {
        visit[y][x] = true;

        // 4방탐색
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 좌표 벗어나면 무시
            if (nx < 0 || ny < 0 || nx >= map[0].length || ny >= map.length) continue;

            // 이미 이동한 좌표면 무시
            if (visit[ny][nx]) continue;

            // 이동할 위치가 쓰레기가 아니면 무시
            if (!map[ny][nx]) continue;

            trashSize = findNearTrash(map, visit, nx, ny, trashSize + 1);
        }

        return trashSize;
    }
}
