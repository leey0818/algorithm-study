import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class Main_1303_전쟁전투 {
    private static final int[] dw = {0, 0, -1, 1};
    private static final int[] dh = {-1, 1, 0, 0};
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] board = new char[M][N];

        for (int h = 0; h < M; h++) {
            String line = br.readLine();
            for (int w = 0; w < N; w++) {
                board[h][w] = line.charAt(w);
            }
        }

        int myTeam = 0;
        int rivalTeam = 0;
        boolean[][] visit = new boolean[M][N];
        for (int h = 0; h < M; h++) {
            for (int w = 0; w < N; w++) {
                if (!visit[h][w]) {
                    AtomicInteger count = new AtomicInteger();
                    dfs(board, visit, w, h, board[h][w], count);

                    //DEBUG
                    System.out.println("============================ " + count.get());
                    for (int h2 = 0; h2 < M; h2++) {
                        for (int w2 = 0; w2 < N; w2++) {
                            System.out.print(visit[h2][w2] ? "1 " : "0 ");
                        }
                        System.out.println();
                    }
                    //DEBUG

                    if (board[h][w] == 'W') {
                        myTeam += Math.pow(count.get(), 2);
                    } else {
                        rivalTeam += Math.pow(count.get(), 2);
                    }
                }
            }
        }

        System.out.println(myTeam + " " + rivalTeam);
    }

    private static void dfs(char[][] board, boolean[][] visit, int x, int y, char knight, AtomicInteger count) {
        visit[y][x] = true;
        count.incrementAndGet();

        for (int d = 0; d < 4; d++) {
            int nx = x + dw[d];
            int ny = y + dh[d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visit[ny][nx]) continue;

            // 다른 팀이면 무시
            if (board[ny][nx] != knight) continue;

            dfs(board, visit, nx, ny, knight, count);
        }
    }
}
