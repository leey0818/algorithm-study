import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS(Breadth-First Search: 너비우선탐색) 알고리즘을 이용한 풀이
 */
public class Main_16173_점프왕쩰리_BFS {
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

        Queue<int[]> moveQueue = new LinkedList<>();
        moveQueue.offer(new int[] {0, 0, board[0][0]});

        boolean result = moveJelly(boardSize, board, moveQueue);
        System.out.print(result ? "HaruHaru" : "Hing");
    }

    private static boolean moveJelly(int boardSize, int[][] board, Queue<int[]> moveQueue) {
        boolean result = false;

        label: while (!moveQueue.isEmpty()) {
            int[] cur = moveQueue.poll();

            // 상하좌우 중 하단,우측 2방탐색 진행..
            for (int d = 0; d < 2; d++) {
                int nx = cur[0] + (dx[d] * cur[2]);
                int ny = cur[1] + (dy[d] * cur[2]);

                // 다음으로 이동할 좌표가 게임판 크기를 넘어버리면 안댐
                if (nx >= boardSize || ny >= boardSize) continue;

                // 다음으로 이동할 좌표가 현재 좌표랑 동일하면 더이상 진행 불가
                if (nx == cur[0] && ny == cur[1]) break label;

                // 다음으로 이동할 좌표가 끝지점이면 승리!
                if (board[nx][ny] == -1) {
                    result = true;
                    break label;
                }

                // 다음 좌표를 queue에 추가
                moveQueue.offer(new int[] {nx, ny, board[nx][ny]});
            }
        }

        return result;
    }
}
