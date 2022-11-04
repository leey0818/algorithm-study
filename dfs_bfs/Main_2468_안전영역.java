import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2468_안전영역 {
    private static final int[][] dp = {{0, 0, -1, 1}, {-1, 1, 0, 0}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        int minHeight = 100;
        int maxHeight = 0;
        for (int h = 0; h < N; h++) {
            st = new StringTokenizer(br.readLine());
            for (int w = 0; w < N; w++) {
                map[h][w] = Integer.parseInt(st.nextToken());
                if (map[h][w] < minHeight) minHeight = map[h][w];
                if (map[h][w] > maxHeight) maxHeight = map[h][w];
            }
        }

        int maxMapCount = 1;
        for (int n = minHeight; n < maxHeight; n++) {
            boolean[][] visit = new boolean[N][N];
            int mapCount = 0;

            for (int h = 0; h < N; h++) {
                for (int w = 0; w < N; w++) {
                    if (map[h][w] > n && !visit[h][w]) {
                        mapCount += 1;
                        dfs(map, visit, w, h, n + 1);
                    }
                }
            }

            //DEBUG
            System.out.println("=================== " + mapCount + " 높이:" + n);
            for (int h = 0; h < N; h++) {
                for (int w = 0; w < N; w++) {
                    System.out.print(visit[h][w] ? "1 " : "0 ");
                }
                System.out.println();
            }
            //DEBUG

            if (mapCount > maxMapCount) maxMapCount = mapCount;
        }

        System.out.println(maxMapCount);
    }

    private static void dfs(int[][] map, boolean[][] visit, int x, int y, int h) {
        visit[y][x] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dp[0][d];
            int ny = y + dp[1][d];

            if (nx < 0 || ny < 0 || nx >= map.length || ny >= map.length || visit[ny][nx]) continue;

            if (map[ny][nx] < h) continue;

            dfs(map, visit, nx, ny, h);
        }
    }
}
