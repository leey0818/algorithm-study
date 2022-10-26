import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_4963_섬의개수_BFS {
    private static final int[] dh = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dw = {0, 1, 1, 1, 0, -1, -1, -1};  // 상하좌우 (대각선까지)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer result = new StringBuffer();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int landWidth = Integer.parseInt(st.nextToken());
            int landHeight = Integer.parseInt(st.nextToken());

            // 종료 라인
            if (landWidth == 0 && landHeight == 0) break;

            boolean[][] map = new boolean[landHeight][landWidth];
            boolean[][] visit = new boolean[landHeight][landWidth];

            for (int h = 0; h < landHeight; h++) {
                st = new StringTokenizer(br.readLine());
                for (int w = 0; w < landWidth; w++) {
                    map[h][w] = "1".equals(st.nextToken());
                }
            }

            // 섬의 개수
            int islandCount = 0;

            for (int h = 0; h < landHeight; h++) {
                for (int w = 0; w < landWidth; w++) {
                    if (map[h][w] && !visit[h][w]) {
                        // 현재 위치 방문처리
                        visit[h][w] = true;

                        // 주변에 연결된 섬 있는지 확인
                        findNearIsland(map, visit, w, h);

                        // 섬 갯수 증가
                        islandCount += 1;
                    }
                }
            }

            result.append(islandCount).append("\n");
        }

        System.out.print(result.toString());
    }

    private static void findNearIsland(boolean[][] map, boolean[][] visit, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 주변 8방탐색
            for (int d = 0; d < 8; d++) {
                int nx = cur[0] + dw[d];
                int ny = cur[1] + dh[d];
    
                // 다음으로 이동할 좌표가 지도 밖이면 무시
                if (nx < 0 || ny < 0 || nx >= map[0].length || ny >= map.length) continue;
    
                // 이미 이동한 위치이면 무시
                if (visit[ny][nx]) continue;
    
                // 이동할 위치가 바다이면 무시
                if (!map[ny][nx]) continue;

                // 이동할 위치 방문처리
                visit[ny][nx] = true;
    
                // 다음 위치 Queue에 추가
                queue.offer(new int[]{nx, ny});
            }
        }
    }
}
