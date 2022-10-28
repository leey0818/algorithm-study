import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1743_음식물피하기_BFS {
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0}; // 상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[][] trashmap = new boolean[N][M];
        boolean[][] visit = new boolean[N][M];

        int tx, ty;
        for (int t = 0; t < K; t++) {
            st = new StringTokenizer(br.readLine());
            ty = Integer.parseInt(st.nextToken()) - 1;
            tx = Integer.parseInt(st.nextToken()) - 1;

            trashmap[ty][tx] = true;
        }

        int largestTrash = 1;
        for (int h = 0; h < N; h++) {
            for (int w = 0; w < M; w++) {
                if (trashmap[h][w] && !visit[h][w]) {
                    // 현재 위치 방문처리
                    visit[h][w] = true;

                    int trashSize = findNearTrash(trashmap, visit, w, h);
                    if (trashSize > largestTrash) {
                        largestTrash = trashSize;
                    }
                }
            }
        }

        System.out.print(largestTrash);
    }

    private static int findNearTrash(boolean[][] map, boolean[][] visit, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});

        int trashSize = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 큐에서 꺼낼때마다 음식물 크기 늘리기
            trashSize += 1;

            // 4방탐색
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
    
                // 좌표 벗어나면 무시
                if (nx < 0 || ny < 0 || nx >= map[0].length || ny >= map.length) continue;
    
                // 이미 이동한 좌표면 무시
                if (visit[ny][nx]) continue;
    
                // 이동할 위치가 쓰레기가 아니면 무시
                if (!map[ny][nx]) continue;
    
                // 이동할 위치 방문처리
                visit[ny][nx] = true;

                queue.offer(new int[]{nx, ny});
            }
        }

        return trashSize;
    }
}
