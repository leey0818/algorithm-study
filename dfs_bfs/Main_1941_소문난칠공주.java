import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

public class Main_1941_소문난칠공주 {
    private static final int[] dw = {0, 0, -1, 1};
    private static final int[] dh = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] map = new char[5][5];
        for (int n = 0; n < 5; n++) {
            map[n] = br.readLine().toCharArray();
        }

        boolean[][] visit = new boolean[5][5];
        AtomicInteger caseCount = new AtomicInteger();

        dfs(map, visit, 0, 0, 0, caseCount);

        System.out.println(caseCount.get());
    }

    private static void dfs(char[][] map, boolean[][] visit, int start, int depth, int limCount, AtomicInteger caseCount) {
        if (limCount > 3) return;
        if (depth >= 7) {
            if (limCount <= 3) {
                AtomicInteger linkCount = new AtomicInteger();
                label: for (int h = 0; h < 5; h++) {
                    for (int w = 0; w < 5; w++) {
                        if (visit[h][w]) {
                            boolean[][] visit2 = new boolean[5][5];
                            dfsLinkCount(visit, visit2, w, h, linkCount);
                            break label;
                        }
                    }
                }

                if (linkCount.get() == 7) caseCount.incrementAndGet();

                //DEBUG
                if (linkCount.get() == 7) {
                    System.out.println("=====================");
                    for (int h = 0; h < 5; h++) {
                        for (int w = 0; w < 5; w++) {
                            System.out.print(visit[h][w] ? "1 " : "0 ");
                        }
                        System.out.println();
                    }
                }
                //DEBUG
            }
            return;
        }

        for (int c = start; c < 25; c++) {
            int x = c % 5;
            int y = c / 5;

            if (visit[y][x]) continue;

            visit[y][x] = true;
            dfs(map, visit, c + 1, depth + 1, map[y][x] == 'Y' ? limCount + 1 : limCount, caseCount);
            visit[y][x] = false;
        }
    }

    private static void dfsLinkCount(boolean[][] map, boolean[][] visit, int x, int y, AtomicInteger loopCount) {
        visit[y][x] = true;
        loopCount.incrementAndGet();

        for (int d = 0; d < 4; d++) {
            int nx = x + dw[d];
            int ny = y + dh[d];

            if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || visit[ny][nx]) continue;

            if (map[ny][nx]) {
                dfsLinkCount(map, visit, nx, ny, loopCount);
            }
        }
    }
}
