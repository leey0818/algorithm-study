import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9663_NQueen {
    private static final int[] dw = {-1, -1, 0, 1, 1};
    private static final int[] dh = {0, 1, 1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int caseCount = 0;
        for (int c = 0; c < N; c++) {
            boolean[][] board = new boolean[N][N];
            boolean isFilled = true;

            for (int n = 0; n < N; n++) {
                // TODO
                int x = getNextX(board[n], N);

                if (x == -1) {
                    isFilled = false;
                    break;
                }

                for (int d = 0; d < 5; d++) {
                    dfs(board, N, x, n, d);
                }

                // DEBUG
                System.out.println("======================= " + n);
                for (int h = 0; h < N; h++) {
                    for (int w = 0; w < N; w++) {
                        System.out.print((board[h][w] ? "1 " : "0 "));
                    }
                    System.out.println();
                }
                // DEBUG
            }

            if (isFilled) caseCount += 1;
        }

        System.out.println(caseCount);
    }

    private static void dfs(boolean[][] board, int N, int x, int y, int d) {
        board[y][x] = true;

        int nx = x + dw[d];
        int ny = y + dh[d];

        if (nx < 0 || ny < 0 || nx >= N || ny >= N) return;

        dfs(board, N, nx, ny, d);
    }

    private static int getNextX(boolean[] line, int N) {
        for (int i = 0; i < N; i++) {
            if (!line[i]) return i;
        }
        return -1;
    }
}
