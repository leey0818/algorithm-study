import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main_2667_단지번호붙이기 {
    private static final int[] dw = {0, 0, -1, 1};
    private static final int[] dh = {-1, 1, 0, 0};
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];
        for (int n = 0; n < N; n++) {
            map[n] = br.readLine().toCharArray();
        }

        AtomicInteger potCount = new AtomicInteger();
        List<Integer> countPerPot = new ArrayList<>();
        boolean[][] visit = new boolean[N][N];
        for (int h = 0; h < N; h++) {
            for (int w = 0; w < N; w++) {
                if (map[h][w] == '1' && !visit[h][w]) {
                    potCount.incrementAndGet();
                    countPerPot.add(dfs(map, visit, w, h, 1));
                }
            }
        }

        // 오름차순 정렬
        Collections.sort(countPerPot);

        System.out.println(potCount.get());
        for (int i = 0; i < countPerPot.size(); i++) {
            System.out.println(countPerPot.get(i));
        }
    }

    private static int dfs(char[][] map, boolean[][] visit, int x, int y, int count) {
        visit[y][x] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dw[d];
            int ny = y + dh[d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || visit[ny][nx]) continue;

            if (map[ny][nx] != '1') continue;

            count = dfs(map, visit, nx, ny, count + 1);
        }

        return count;
    }
}
