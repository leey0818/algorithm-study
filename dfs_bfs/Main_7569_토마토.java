import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7569_토마토 {
    private static final int[] dw = {0, 0, -1, 1, 0, 0};
    private static final int[] dh = {-1, 1, 0, 0, 0, 0};
    private static final int[] dz = {0, 0, 0, 0, -1, 1};  // 6방탐색 (상하좌우위아래)

    private static int N;
    private static int M;
    private static int H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        Queue<Position> queue = new LinkedList<>();
        int[][][] tomato = new int[H][N][M];
        int willRipeCount = 0;
        int t;

        for (int z = 0; z < H; z++) {
            for (int h = 0; h < N; h++) {
                st = new StringTokenizer(br.readLine());
                for (int w = 0; w < M; w++) {
                    t = Integer.parseInt(st.nextToken());
    
                    // 익힐 토마토 갯수 +1
                    if (t == 0) {
                        willRipeCount += 1;
                    } else if (t == 1) {
                        // 익은 토마토이면 queue에 저장
                        queue.offer(new Position(w, h, z));
                    }

                    tomato[z][h][w] = t;
                }
            }
        }

        System.out.println(bfs(queue, tomato, willRipeCount));
    }

    private static int bfs(Queue<Position> queue, int[][][] tomato, int willRipeCount) {
        int loopCount = queue.size();
        int dayCount = 0;
        int totalRipeCount = 0;

        while (!queue.isEmpty()) {
            int oneCycleCount = 0;
            for (int i = loopCount; i > 0; i--) {
                Position pos = queue.poll();

                while (pos.next()) {
                    int nx = pos.nx();
                    int ny = pos.ny();
                    int nz = pos.nz();

                    if (nx < 0 || ny < 0 || nz < 0) continue;
                    if (nx >= M || ny >= N || nz >= H) continue;

                    // 다음 위치가 익힐 토마토 위치가 아니면 무시
                    if (tomato[nz][ny][nx] != 0) continue;

                    // 다음날에 익힐 수 있도록 표시
                    tomato[nz][ny][nx] = 1;
                    oneCycleCount += 1;
                    queue.offer(new Position(nx, ny, nz));
                }
            }

            // 익히는중인 토마토가 없으면 끝남
            if (oneCycleCount == 0) break;

            loopCount = queue.size();
            totalRipeCount += oneCycleCount;

            // 익은 날짜 하루 추가
            dayCount += 1;
        }

        return totalRipeCount == willRipeCount ? dayCount : -1;
    }

    private static class Position {
        final int x;
        final int y;
        final int z;
        int d = -1;

        public Position(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public boolean next() {
            d += 1;
            return d < 6;
        }

        public int nx() { return this.x + dw[this.d]; }
        public int ny() { return this.y + dh[this.d]; }
        public int nz() { return this.z + dz[this.d]; }
    }
}