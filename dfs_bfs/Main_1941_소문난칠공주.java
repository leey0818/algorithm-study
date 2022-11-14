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

        AtomicInteger caseCount = new AtomicInteger();
        boolean[][] visit = new boolean[5][5];
        for (int h = 0; h < 5; h++) {
            for (int w = 0; w < 5; w++) {
                if (!visit[h][w]) {
                    visit[h][w] = true;
                    dfs(map, visit, w, h, 1, map[h][w] == 'Y' ? 1 : 0, caseCount);
                    visit[h][w] = false;
                }
            }
        }

        System.out.println(caseCount.get());
    }

    private static void dfs(char[][] map, boolean[][] visit, int x, int y, int loopCount, int limCount, AtomicInteger caseCount) {
        if (limCount > 3) return;
        if (loopCount >= 7) {
            if (limCount <= 3) {
                //DEBUG
                System.out.println("===========");
                for (int h = 0; h < 5; h++) {
                    for (int w = 0; w < 5; w++) {
                        System.out.print(visit[h][w] ? "1 " : "0 ");
                    }
                    System.out.println();
                }
                //DEBUG

                caseCount.incrementAndGet();
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dw[d];
            int ny = y + dh[d];

            if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || visit[ny][nx]) continue;

            visit[ny][nx] = true;
            dfs(map, visit, nx, ny, loopCount + 1, map[ny][nx] == 'Y' ? limCount + 1 : limCount, caseCount);
            visit[ny][nx] = false;
        }
    }
}
