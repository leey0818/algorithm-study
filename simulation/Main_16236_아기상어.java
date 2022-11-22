import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16236_아기상어 {
    private static final int[] dw = {0, 0, -1, 1};
    private static final int[] dh = {-1, 1, 0, 0};
    private static int[] fishArr = new int[7];
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];
        Position sharkPos = null;
        StringTokenizer st;

        for (int h = 0; h < N; h++) {
            st = new StringTokenizer(br.readLine());
            for (int w = 0; w < N; w++) {
                int fishSize = Integer.parseInt(st.nextToken());

                // 상어 위치 저장
                if (fishSize == 9) {
                    sharkPos = new Position(w, h, 0);
                    fishSize = 0;
                }
                // 물고기 갯수 저장
                else if (fishSize != 0) {
                    fishArr[fishSize] += 1;
                }

                board[h][w] = fishSize;
            }
        }

        Queue<Position> moveQueue = new LinkedList<>();
        moveQueue.offer(sharkPos);

        int sharkSize = 2;
        int eatCount = 0;
        int totalMoveCount = 0;
        while (!moveQueue.isEmpty()) {
            // 아기상어 크기보다 작은 물고기가 있는지 확인
            if (getLowerFishCount(sharkSize) == 0) break;

            // 다음으로 먹을 물고기 위치 가져오기
            Position nextPos = getNextFishPosition(moveQueue, board, sharkSize);

            // 다음으로 먹을 물고기가 없으면 끝냄
            if (nextPos == null) break;

            // 물고기 갯수 -1
            int fishSize = board[nextPos.y][nextPos.x];
            fishArr[fishSize] -= 1;

            // 해당 위치로 이동한 횟수를 더하고 빈공간으로 셋팅, 먹은 물고기 수 +1
            totalMoveCount += nextPos.move;
            board[nextPos.y][nextPos.x] = 0;
            eatCount += 1;

            // 아기상어 크기만큼 물고기를 먹었으면 상어크기 +1
            if (sharkSize == eatCount) {
                sharkSize += 1;
                eatCount = 0;
            }

            // 해당 좌표를 다시 큐에 담기
            moveQueue.offer(new Position(nextPos.x, nextPos.y, 0));
        }

        System.out.println(totalMoveCount);
    }

    private static Position getNextFishPosition(Queue<Position> moveQueue, int[][] board, int sharkSize) {
        boolean[][] visit = new boolean[N][N];
        Position fishPos = null;

        while (!moveQueue.isEmpty()) {
            Position pos = moveQueue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = pos.x + dw[d];
                int ny = pos.y + dh[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visit[ny][nx]) continue;

                // 아기상어보다 큰 물고기 위치는 지나갈 수 없다
                if (board[ny][nx] > sharkSize) continue;

                // 아기상어랑 크기가 동일한 물고기 위치는 지나만 갈 수 있음 (먹지못함)
                if (board[ny][nx] == sharkSize || board[ny][nx] == 0) {
                    visit[ny][nx] = true;
                    moveQueue.offer(new Position(nx, ny, pos.move + 1));
                } else {
                    // 아기상어보다 작은 물고기는 먹을 수 있음
                    Position nextPos = new Position(nx, ny, pos.move + 1);
                    if (fishPos == null || isLowerPosition(fishPos, nextPos)) {
                        fishPos = nextPos;
                    }
                }
            }
        }

        return fishPos;
    }

    private static boolean isLowerPosition(Position curPos, Position nextPos) {
        // 이전 위치보다 이동횟수가 작거나 같아야 하고, 이전 위치보다 상단,좌측에 위치해 있는지 확인
        return nextPos.move <= curPos.move && (curPos.y * N + curPos.x) > (nextPos.y * N + nextPos.x);
    }

    private static int getLowerFishCount(int sharkSize) {
        int fishCount = 0;
        for (int i = 1; i < fishArr.length; i++) {
            if (i >= sharkSize) break;
            fishCount += fishArr[i];
        }
        return fishCount;
    }

    static class Position {
        int x;
        int y;
        int move;
        Position(int x, int y, int moveCount) {
            this.x = x;
            this.y = y;
            this.move = moveCount;
        }
    }
}
