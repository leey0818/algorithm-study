import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DFS(Depth-First Search: 깊이우선탐색) 알고리즘을 이용한 풀이
 */
public class Main_16173_점프왕쩰리_DFS {
    private final static int[] dx = {1,0};  // X좌표 진행방향
    private final static int[] dy = {0,1};  // Y좌표 진행방향 (하단, 우측)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int boardSize = Integer.parseInt(br.readLine());
        int[][] board = new int[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int n = 0; n < boardSize; n++) {
                board[i][n] = Integer.parseInt(st.nextToken());
            }
        }

        boolean result = moveJelly(boardSize, board, board[0][0], 0, 0);
        System.out.print(result ? "HaruHaru" : "Hing");
    }

    private static boolean moveJelly(int boardSize, int[][] board, int moveCount, int x, int y) {
        // 상하좌우 중 하단,우측 2방탐색 진행..
        for (int d = 0; d < 2; d++) {
            int nx = x + (dx[d] * moveCount);
            int ny = y + (dy[d] * moveCount);

            // 다음으로 이동할 좌표가 게임판 크기를 넘어버리면 안댐
            if (nx >= boardSize || ny >= boardSize) continue;

            // 다음으로 이동할 좌표가 현재 좌표랑 동일하면 더이상 진행 불가
            if (nx == x && ny == y) return false;

            // 다음으로 이동할 좌표가 끝지점이면 승리!
            if (board[nx][ny] == -1) return true;

            // 다음 좌표로 이동!
            if (moveJelly(boardSize, board, board[nx][ny], nx, ny)) {
                return true;
            }
        }

        return false;
    }
}